package ua.shuba.stocks.service;

import ua.shuba.stocks.dto.DtoDifferencePrice;
import ua.shuba.stocks.dto.DtoQuote;

import java.util.List;

public interface DifferencePriceService {
    void updateDifferencePrice(DtoQuote dtoQuote);

    List<DtoDifferencePrice> getTopDifferencePrice();
}
