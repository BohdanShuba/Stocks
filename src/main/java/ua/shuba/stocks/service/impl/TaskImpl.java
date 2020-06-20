package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.shuba.stocks.client.impl.CompanyHttpClientImpl;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.service.CompanyService;
import ua.shuba.stocks.service.DifferencePriceService;
import ua.shuba.stocks.service.Task;

import java.util.concurrent.CountDownLatch;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class TaskImpl implements Task {
    private final CompanyHttpClientImpl companyHttpClient;
    private final CompanyService companyService;
    private final DifferencePriceService differencePriceService;
    private String stockCode;
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
        try {
            System.out.println("Processing " + stockCode);
            DtoQuote dtoQuote = companyHttpClient.getCompanyQuote(stockCode);
            differencePriceService.updateDifferencePrice(dtoQuote);
            companyService.saveQuote(dtoQuote);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Override
    public void setLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
