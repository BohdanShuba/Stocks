package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.comparator.QuoteComparator;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.service.CompanyService;
import ua.shuba.stocks.service.PrintService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {
    private final CompanyService companyService;

    public void printStats() {
        System.out.println("The top 5 highest value stocks:");
        List<Quote> topQuote = companyService.getTopValueStocks();
        printTopCompany(topQuote);
        System.out.println("The top 5 highest value stocks (order by company name):");
        QuoteComparator quoteComparator = new QuoteComparator();
        topQuote.sort(quoteComparator);
        printTopCompany(topQuote);
    }

    private void printTopCompany(List<Quote> quoteList) {
        for (Quote quote : quoteList) {
            System.out.println(String.format("Company name: %s - previous volume: %s", quote.getCompanyName(), quote.getPreviousVolume()));
        }
    }
}
