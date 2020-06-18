package ua.shuba.stocks.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "company",
        indexes = {@Index(columnList = "symbol", name = "company_symbol_index")},
        uniqueConstraints = {@UniqueConstraint(name = "company_symbol_constrain", columnNames = "symbol")})
@Data
@NoArgsConstructor

public class Company {
    @Id
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
