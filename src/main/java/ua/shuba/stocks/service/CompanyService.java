package ua.shuba.stocks.service;

import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.dto.DtoQuote;

import java.util.List;

public interface CompanyService {
    void printStats();
    void saveAllCompanies(List<DtoCompany> dtoCompanyList);
    void saveQuote(DtoQuote dtoQuote);
}
