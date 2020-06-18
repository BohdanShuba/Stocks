package ua.shuba.stocks.client;

import ua.shuba.stocks.dto.DtoCompany;

import java.util.List;

public interface CompanyHttpClient {
    List<DtoCompany> getAllCompaniesList();
}
