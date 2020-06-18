package ua.shuba.stocks.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Integer open;
    private Long openTime;
    private Double close;
    private Long closeTime;
    private Double high;
    private Double low;
    private Double latestPrice;
    private String latestSource;
    private String latestTime;
    private Long latestUpdate;
    private Long latestVolume;
    private Long volume;
    private Double iexRealtimePrice;
    private Integer iexRealtimeSize;
    private Long iexLastUpdated;
    private Double delayedPrice;
    private Long delayedPriceTime;
    private Double oddLotDelayedPrice;
    private Long oddLotDelayedPriceTime;
    private Double extendedPrice;
    private Double extendedChange;
    private Double extendedChangePercent;
    private Long extendedPriceTime;
    private Double previousClose;
    private Long previousVolume;

    @Column(name = "change_in_price")
    private Double change;
    private Double changePercent;
    private Double iexMarketPercent;
    private Integer iexVolume;
    private Long avgTotalVolume;
    private Double iexBidPrice;
    private Integer iexBidSize;
    private Double iexAskPrice;
    private Integer iexAskSize;
    private Long marketCap;
    private Double week52High;
    private Double week52Low;
    private Double ytdChange;
    private Double peRatio;
    private Long lastTradeTime;
    private Boolean isUSMarketOpen;
}
