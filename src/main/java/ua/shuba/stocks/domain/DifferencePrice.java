package ua.shuba.stocks.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "difference_price",
        indexes = {@Index(columnList = "symbol", name = "difference_price_symbol_index"),
                @Index(columnList = "difference_price", name = "difference_price_difference_price_index")},
        uniqueConstraints = {@UniqueConstraint(name = "difference_price_symbol_constrain", columnNames = "symbol")})
@Data
@NoArgsConstructor
public class DifferencePrice {
    @Id
    private String symbol;
    private String companyName;
    @Column(name = "difference_price")
    private BigDecimal differencePrice;
    private Calendar settlementDate;
}
