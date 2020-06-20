package ua.shuba.stocks.client.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.shuba.stocks.client.CompanyHttpClient;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.exception.StockException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class CompanyHttpClientImpl implements CompanyHttpClient {
    @Value("${company.url}")
    private String companyUrl;
    @Value("${quote.url}")
    private String quoteUrl;
    private final Gson gson;
    private final CloseableHttpClient client;

    public List<DtoCompany> getAllCompaniesList() {
        String jsonResponseGet = sendRequest(companyUrl);
        return convertJsonToDtoCompanyList(jsonResponseGet);
    }

    public DtoQuote getCompanyQuote(String stockCode) {
        String url = String.format(quoteUrl,stockCode);
        String jsonResponseGet = sendRequest(url);
        return convertJsonToDtoQuote(jsonResponseGet);
    }

    private String getJsonWithResponse(HttpResponse response) {
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new StockException("No data", e);
        }
    }

    private String sendRequest(String url) {
        CloseableHttpResponse response = null;
        try {
            HttpGet request = new HttpGet(url);
            response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new StockException("Cannot process request. " + url + " " + getJsonWithResponse(response));
            }
            return getJsonWithResponse(response);
        } catch (IOException e) {
            throw new StockException("Bad request", e);
        } finally {
            closeResponse(response);
        }
    }

    private void closeResponse(CloseableHttpResponse response) {
        if (nonNull(response)) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private DtoQuote convertJsonToDtoQuote(String dtoCompanyLine) {
        return gson.fromJson(dtoCompanyLine, DtoQuote.class);
    }

    private List<DtoCompany> convertJsonToDtoCompanyList(String dtoCompanyLine) {
        return Arrays.asList(gson.fromJson(dtoCompanyLine, DtoCompany[].class));
    }

}
