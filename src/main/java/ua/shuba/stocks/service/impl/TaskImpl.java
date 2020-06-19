package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.shuba.stocks.client.impl.CompanyHttpClientImpl;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.service.CompanyService;
import ua.shuba.stocks.service.DifferencePriceService;
import ua.shuba.stocks.service.Task;

@Component
@RequiredArgsConstructor
public class TaskImpl implements Task {
    private final CompanyHttpClientImpl companyHttpClient;
    private final CompanyService companyService;
    private final DifferencePriceService differencePriceService;
    private String stockCode;

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Override
    public void run() {
        DtoQuote dtoQuote = companyHttpClient.getCompanyQuote(stockCode);
        differencePriceService.updateDifferencePrice(dtoQuote);
        companyService.saveQuote(dtoQuote);
    }
}
