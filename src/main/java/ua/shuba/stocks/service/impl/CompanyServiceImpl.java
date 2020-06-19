package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.domain.Company;
import ua.shuba.stocks.domain.DifferencePrice;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.mapper.Mapper;
import ua.shuba.stocks.mapper.MapperCompany;
import ua.shuba.stocks.mapper.MapperQuote;
import ua.shuba.stocks.repository.CompanyRepository;
import ua.shuba.stocks.repository.DifferencePriceRepository;
import ua.shuba.stocks.repository.QuoteRepository;
import ua.shuba.stocks.service.CompanyService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final QuoteRepository quoteRepository;
    private final DifferencePriceRepository differencePriceRepository;

    public List<Quote> getTopValueStocks() {
        return quoteRepository.findTop5ByOrderByPreviousVolumeDesc();
    }

    public void saveAllCompanies(List<DtoCompany> dtoCompanyList) {
        List<Company> companyList = mappedDtoCompanyListToCompanyList(dtoCompanyList);
        companyRepository.saveAll(companyList);
    }

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

    private BigDecimal calculatePercentageDifference(Double first, Double second) {
        second *= 100;
        first *= 100;
        double difference = (second - first) / first * 100;
        return new BigDecimal(difference).setScale(4, RoundingMode.HALF_UP);
    }

    public void saveQuote(DtoQuote dtoQuote) {
        Mapper<DtoQuote, Quote> mapper = new MapperQuote();
        Quote quote = mapper.toEntity(dtoQuote);
        quoteRepository.save(quote);
    }

    private List<Company> mappedDtoCompanyListToCompanyList(List<DtoCompany> dtoCompanyList) {
        if (dtoCompanyList.isEmpty()) {
            return null;
        }
        List<Company> companyList = new ArrayList<>(dtoCompanyList.size());
        Mapper<DtoCompany, Company> mapper = new MapperCompany();
        for (DtoCompany dtoCompany : dtoCompanyList) {
            companyList.add(mapper.toEntity(dtoCompany));
        }
        return companyList;
    }
}
