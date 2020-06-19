package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.domain.DifferencePrice;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoDifferencePrice;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.repository.DifferencePriceRepository;
import ua.shuba.stocks.repository.QuoteRepository;
import ua.shuba.stocks.service.DifferencePriceService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DifferencePriceServiceImpl implements DifferencePriceService {
    private static final int NUMBER_OF_RESULTS = 5;

    private final DifferencePriceRepository differencePriceRepository;
    private final QuoteRepository quoteRepository;

    public void updateDifferencePrice(DtoQuote currentQuote) {
        DifferencePrice differencePrice = new DifferencePrice();
        Optional<Quote> previousQuote = quoteRepository.findById(currentQuote.getSymbol());
        if (!previousQuote.isPresent()) {
            differencePrice.setDifferencePrice(BigDecimal.valueOf(0.0000));
        } else if (previousQuote.get().getLatestPrice() == 0.0) {
            differencePrice.setDifferencePrice(BigDecimal.valueOf(100.00));
        } else {
            BigDecimal percentageDifference = calculatePercentageDifference(previousQuote.get().getLatestPrice(), currentQuote.getLatestPrice());
            differencePrice.setDifferencePrice(percentageDifference);
        }
        differencePrice.setSymbol(currentQuote.getSymbol());
        differencePrice.setCompanyName(currentQuote.getCompanyName());
        differencePrice.setSettlementDate(Calendar.getInstance());
        differencePriceRepository.save(differencePrice);
    }

    public List<DtoDifferencePrice> getTopDifferencePrice() {
        List<DtoDifferencePrice> topAbsDifferencePrice = differencePriceRepository.findTopAbsDifferencePrice(PageRequest.of(0, NUMBER_OF_RESULTS));
        return topAbsDifferencePrice;
    }

    private BigDecimal calculatePercentageDifference(Double first, Double second) {
        second *= 100;
        first *= 100;
        double difference = (second - first) / first * 100;
        return new BigDecimal(difference).setScale(4, RoundingMode.HALF_UP);
    }
}
