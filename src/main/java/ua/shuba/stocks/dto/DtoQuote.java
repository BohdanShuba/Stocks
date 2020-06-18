package ua.shuba.stocks.dto;

import lombok.Data;

@Data
public class DtoQuote {
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
