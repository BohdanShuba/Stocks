package ua.shuba.stocks.service;

import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoQuote;

import java.util.List;

public interface CompanyService {
    void saveQuote(DtoQuote dtoQuote);

    List<Quote> getTopValueStocks();
}
