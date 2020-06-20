package ua.shuba.stocks.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "quote",
        indexes = {@Index(columnList = "symbol", name = "quote_symbol_index"),
                @Index(columnList = "volume", name = "quote_volume_index")},
        uniqueConstraints = {@UniqueConstraint(name = "quote_symbol_constrain", columnNames = "symbol")})
@Data
@NoArgsConstructor
public class Quote {
    @Id
    private String symbol;
    private String companyName;
    private String calculationPrice;
    private BigDecimal open;
    private Long openTime;
    private BigDecimal close;
    private Long closeTime;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal latestPrice;
    private String latestSource;
    private String latestTime;
    private Long latestUpdate;
    private Long latestVolume;
    private Long volume;
    private BigDecimal iexRealtimePrice;
    private Integer iexRealtimeSize;
    private Long iexLastUpdated;
    private BigDecimal delayedPrice;
    private Long delayedPriceTime;
    private BigDecimal oddLotDelayedPrice;
    private Long oddLotDelayedPriceTime;
    private BigDecimal extendedPrice;
    private BigDecimal extendedChange;
    private BigDecimal extendedChangePercent;
    private Long extendedPriceTime;
    private BigDecimal previousClose;
    private Long previousVolume;

    @Column(name = "change_in_price")
    private BigDecimal change;
    private BigDecimal changePercent;
    private BigDecimal iexMarketPercent;
    private Integer iexVolume;
    private Long avgTotalVolume;
    private BigDecimal iexBidPrice;
    private Integer iexBidSize;
    private BigDecimal iexAskPrice;
    private Integer iexAskSize;
    private Long marketCap;
    private BigDecimal week52High;
    private BigDecimal week52Low;
    private BigDecimal ytdChange;
    private BigDecimal peRatio;
    private Long lastTradeTime;
    private Boolean isUSMarketOpen;
}
