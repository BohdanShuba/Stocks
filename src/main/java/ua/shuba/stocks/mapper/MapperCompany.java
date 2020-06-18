package ua.shuba.stocks.mapper;

import org.springframework.stereotype.Component;
import ua.shuba.stocks.domain.Company;
import ua.shuba.stocks.dto.DtoCompany;

@Component
public class MapperCompany implements Mapper<DtoCompany, Company> {

    public DtoCompany toDto(Company company) {
        if (company == null) {
            return null;
        }
        DtoCompany dtoCompany = new DtoCompany();
        dtoCompany.setSymbol(company.getSymbol());
        dtoCompany.setExchange(company.getExchange());
        dtoCompany.setName(company.getName());
        dtoCompany.setDate(company.getDate());
        dtoCompany.setType(company.getType());
        dtoCompany.setIexId(company.getIexId());
        dtoCompany.setRegion(company.getRegion());
        dtoCompany.setCurrency(company.getCurrency());
        dtoCompany.setIsEnabled(company.getIsEnabled());
        dtoCompany.setFigi(company.getFigi());
        dtoCompany.setCik(company.getCik());
        return dtoCompany;
    }


    public Company toEntity(DtoCompany dtoCompany) {
        if (dtoCompany == null) {
            return null;
        }
        Company company = new Company();
        company.setSymbol(dtoCompany.getSymbol());
        company.setExchange(dtoCompany.getExchange());
        company.setName(dtoCompany.getName());
        company.setDate(dtoCompany.getDate());
        company.setType(dtoCompany.getType());
        company.setIexId(dtoCompany.getIexId());
        company.setRegion(dtoCompany.getRegion());
        company.setCurrency(dtoCompany.getCurrency());
        company.setIsEnabled(dtoCompany.getIsEnabled());
        company.setFigi(dtoCompany.getFigi());
        company.setCik(dtoCompany.getCik());
        return company;
    }
}
