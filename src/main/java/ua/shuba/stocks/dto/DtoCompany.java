package ua.shuba.stocks.dto;

import lombok.Data;

@Data
public class DtoCompany {
    private String symbol;
    private String exchange;
    private String name;
    private String date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private Boolean isEnabled;
    private String figi;
    private String cik;
}
