package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.comparator.QuoteComparator;
import ua.shuba.stocks.domain.Company;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.dto.DtoQuote;
import ua.shuba.stocks.mapper.Mapper;
import ua.shuba.stocks.mapper.MapperCompany;
import ua.shuba.stocks.mapper.MapperQuote;
import ua.shuba.stocks.repository.CompanyRepository;
import ua.shuba.stocks.repository.QuoteRepository;
import ua.shuba.stocks.service.CompanyService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final QuoteRepository quoteRepository;

    public void printStats() {
        System.out.println("The top 5 highest value stocks:");
        List<Quote> topQuote = quoteRepository.findTop5ByOrderByPreviousVolumeDesc();
        for (Quote quote : topQuote) {
            System.out.println("Company name: " + quote.getCompanyName() + " - previous volume: " + quote.getPreviousVolume());
        }
        System.out.println("The top 5 highest value stocks (order by company name):");
        QuoteComparator quoteComparator = new QuoteComparator();
        topQuote.sort(quoteComparator);
        for (Quote quote : topQuote) {
            System.out.println("Company name: " + quote.getCompanyName() + " - previous volume: " + quote.getPreviousVolume());
        }
    }

    public void saveAllCompanies(List<DtoCompany> dtoCompanyList) {
        List<Company> companyList = mappedDtoCompanyListToCompanyList(dtoCompanyList);
        companyRepository.saveAll(companyList);
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
