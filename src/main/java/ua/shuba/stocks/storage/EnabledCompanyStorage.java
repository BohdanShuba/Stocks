package ua.shuba.stocks.storage;

import ua.shuba.stocks.dto.DtoCompany;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EnabledCompanyStorage {
    private final List<DtoCompany> enabledCompanyList;

    public EnabledCompanyStorage(List<DtoCompany> companyList) {
        this.enabledCompanyList = companyList.stream().filter(i -> i.getIsEnabled()).collect(Collectors.toList());
    }

    public List<DtoCompany> getBikeList() {
        return Collections.unmodifiableList(enabledCompanyList);
    }

}
