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
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    private final DifferencePriceRepository differencePriceRepository;
    private final QuoteRepository quoteRepository;

    public void updateDifferencePrice(DtoQuote currentQuote) {
        DifferencePrice differencePrice = new DifferencePrice();
        Optional<Quote> previousQuote = quoteRepository.findById(currentQuote.getSymbol());
        if (!previousQuote.isPresent()) {
            differencePrice.setDifferencePrice(BigDecimal.ZERO);
        } else if (previousQuote.get().getLatestPrice().compareTo(BigDecimal.ZERO) == 0) {
            differencePrice.setDifferencePrice(ONE_HUNDRED);
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
        return differencePriceRepository.findTopAbsDifferencePrice(PageRequest.of(0, NUMBER_OF_RESULTS));
    }

    private BigDecimal calculatePercentageDifference(BigDecimal first, BigDecimal second) {
        return second.subtract(first).multiply(ONE_HUNDRED).divide(first, 4, RoundingMode.HALF_UP);
    }
}
