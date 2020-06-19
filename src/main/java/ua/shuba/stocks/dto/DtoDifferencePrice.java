package ua.shuba.stocks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
@AllArgsConstructor
public class DtoDifferencePrice {
    private String symbol;
    private String companyName;
    private BigDecimal differencePrice;
    private Calendar settlementDate;

    @Override
    public String toString() {
        return "Company name: " + companyName + " - difference price: " + differencePrice + "%, date of change: " + settlementDate.getTime();
    }
}
