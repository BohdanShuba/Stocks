package ua.shuba.stocks.client;

import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.dto.DtoQuote;

import java.util.List;

public interface CompanyHttpClient {
    List<DtoCompany> getAllCompaniesList();
    DtoQuote getCompanyQuote(String stockCode);
}
