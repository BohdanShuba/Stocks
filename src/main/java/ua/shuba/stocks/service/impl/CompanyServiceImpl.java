package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.domain.Company;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.mapper.Mapper;
import ua.shuba.stocks.repository.QuoteRepository;
import ua.shuba.stocks.service.CompanyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final QuoteRepository quoteRepository;
    private final Mapper<DtoQuote, Quote> quoteMapper;
    private final Mapper<DtoCompany, Company> companyMapper;

    public List<Quote> getTopValueStocks() {
        return quoteRepository.findTop5ByOrderByPreviousVolumeDesc();
    }

    public void saveQuote(DtoQuote dtoQuote) {
        Quote quote = quoteMapper.toEntity(dtoQuote);
        quoteRepository.save(quote);
    }

    private List<Company> mappedDtoCompanyListToCompanyList(List<DtoCompany> dtoCompanyList) {
        if (dtoCompanyList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Company> companyList = new ArrayList<>(dtoCompanyList.size());
        for (DtoCompany dtoCompany : dtoCompanyList) {
            companyList.add(companyMapper.toEntity(dtoCompany));
        }
        return companyList;
    }
}
