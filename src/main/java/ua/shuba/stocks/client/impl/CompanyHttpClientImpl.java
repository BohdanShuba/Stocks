package ua.shuba.stocks.client.impl;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import ua.shuba.stocks.client.CompanyHttpClient;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.exception.StockException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Component
public class CompanyHttpClientImpl implements CompanyHttpClient {
    public List<DtoCompany> getAllCompaniesList() {
        String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        CloseableHttpResponse response = sendRequest(url);
        String jsonResponseGet = getJsonWithResponse(response);
        List<DtoCompany> dtoCompanies = convertJsonToDtoCompanyList(jsonResponseGet);
        return dtoCompanies;
    }

    private String getJsonWithResponse(HttpResponse response) {
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            String line = "";
            while ((line = rd.readLine()) != null) {
                return line;
            }
        } catch (IOException e) {
            throw new StockException("No data");
        }

        return null;
    }

    private CloseableHttpResponse sendRequest(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            return client.execute(request);
        } catch (IOException e) {
            throw new StockException("Bad request");
        }
    }

    private List<DtoCompany> convertJsonToDtoCompanyList(String dtoCompanyLine) {
        List<DtoCompany> dtoCompanies = Arrays.asList(new Gson().fromJson(dtoCompanyLine, DtoCompany[].class));
        return dtoCompanies;
    }

}
