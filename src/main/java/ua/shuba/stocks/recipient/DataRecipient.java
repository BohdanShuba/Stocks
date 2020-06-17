package ua.shuba.stocks.recipient;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.exception.StockException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DataRecipient {
    public List<DtoCompany> sendRequest() {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet("https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571");
            CloseableHttpResponse responseGet = client.execute(request);
            String jsonResponseGet = getJsonResponse(responseGet);
            List<DtoCompany> dtoCompanies = jsonToDtoProfile(jsonResponseGet);
            return dtoCompanies;
        } catch (IOException e) {
            throw new StockException("Bad request");
        }
    }

    private List<DtoCompany> jsonToDtoProfile(String dtoCompanyLine) {
        List<DtoCompany> dtoCompanies = Arrays.asList(new Gson().fromJson(dtoCompanyLine, DtoCompany[].class));
        return dtoCompanies;
    }

    private String getJsonResponse(HttpResponse response) {
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            String line = "";
            while ((line = rd.readLine()) != null) {
                return line;
            }

        } catch (IOException e) {
            throw new StockException("Bad request");
        }
        return null;
    }

}
