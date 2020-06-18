package ua.shuba.stocks.mapper;

import org.springframework.stereotype.Component;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoQuote;

@Component
public class MapperQuote implements Mapper<DtoQuote, Quote> {

    public DtoQuote toDto(Quote quote) {
        if (quote == null) {
            return null;
        }
        DtoQuote dtoQuote = new DtoQuote();
        dtoQuote.setSymbol(quote.getSymbol());
        dtoQuote.setCompanyName(quote.getCompanyName());
        dtoQuote.setCalculationPrice(quote.getCalculationPrice());
        dtoQuote.setOpen(quote.getOpen());
        dtoQuote.setOpenTime(quote.getOpenTime());
        dtoQuote.setClose(quote.getClose());
        dtoQuote.setCloseTime(quote.getCloseTime());
        dtoQuote.setHigh(quote.getHigh());
        dtoQuote.setLow(quote.getLow());
        dtoQuote.setLatestPrice(quote.getLatestPrice());
        dtoQuote.setLatestSource(quote.getLatestSource());
        dtoQuote.setLatestTime(quote.getLatestTime());
        dtoQuote.setLatestUpdate(quote.getLatestUpdate());
        dtoQuote.setLatestVolume(quote.getLatestVolume());
        dtoQuote.setVolume(quote.getVolume());
        dtoQuote.setIexRealtimePrice(quote.getIexRealtimePrice());
        dtoQuote.setIexRealtimeSize(quote.getIexRealtimeSize());
        dtoQuote.setIexLastUpdated(quote.getIexLastUpdated());
        dtoQuote.setDelayedPrice(quote.getDelayedPrice());
        dtoQuote.setDelayedPriceTime(quote.getDelayedPriceTime());
        dtoQuote.setOddLotDelayedPrice(quote.getOddLotDelayedPrice());
        dtoQuote.setOddLotDelayedPriceTime(quote.getOddLotDelayedPriceTime());
        dtoQuote.setExtendedPrice(quote.getExtendedPrice());
        dtoQuote.setExtendedChange(quote.getExtendedChange());
        dtoQuote.setExtendedChangePercent(quote.getExtendedChangePercent());
        dtoQuote.setExtendedPriceTime(quote.getExtendedPriceTime());
        dtoQuote.setPreviousClose(quote.getPreviousClose());
        dtoQuote.setPreviousVolume(quote.getPreviousVolume());
        dtoQuote.setChange(quote.getChange());
        dtoQuote.setChangePercent(quote.getChangePercent());
        dtoQuote.setIexMarketPercent(quote.getIexMarketPercent());
        dtoQuote.setIexVolume(quote.getIexVolume());
        dtoQuote.setAvgTotalVolume(quote.getAvgTotalVolume());
        dtoQuote.setIexBidPrice(quote.getIexBidPrice());
        dtoQuote.setIexBidSize(quote.getIexBidSize());
        dtoQuote.setIexAskPrice(quote.getIexAskPrice());
        dtoQuote.setIexAskSize(quote.getIexAskSize());
        dtoQuote.setMarketCap(quote.getMarketCap());
        dtoQuote.setWeek52High(quote.getWeek52High());
        dtoQuote.setWeek52Low(quote.getWeek52Low());
        dtoQuote.setYtdChange(quote.getYtdChange());
        dtoQuote.setPeRatio(quote.getPeRatio());
        dtoQuote.setLastTradeTime(quote.getLastTradeTime());
        dtoQuote.setIsUSMarketOpen(quote.getIsUSMarketOpen());
        return dtoQuote;
    }


    public Quote toEntity(DtoQuote dtoQuote) {
        if (dtoQuote == null) {
            return null;
        }
        Quote quote = new Quote();
        quote.setSymbol(dtoQuote.getSymbol());
        quote.setCompanyName(dtoQuote.getCompanyName());
        quote.setCalculationPrice(dtoQuote.getCalculationPrice());
        quote.setOpen(dtoQuote.getOpen());
        quote.setOpenTime(dtoQuote.getOpenTime());
        quote.setClose(dtoQuote.getClose());
        quote.setCloseTime(dtoQuote.getCloseTime());
        quote.setHigh(dtoQuote.getHigh());
        quote.setLow(dtoQuote.getLow());
        quote.setLatestPrice(dtoQuote.getLatestPrice());
        quote.setLatestSource(dtoQuote.getLatestSource());
        quote.setLatestTime(dtoQuote.getLatestTime());
        quote.setLatestUpdate(dtoQuote.getLatestUpdate());
        quote.setLatestVolume(dtoQuote.getLatestVolume());
        quote.setVolume(dtoQuote.getVolume());
        quote.setIexRealtimePrice(dtoQuote.getIexRealtimePrice());
        quote.setIexRealtimeSize(dtoQuote.getIexRealtimeSize());
        quote.setIexLastUpdated(dtoQuote.getIexLastUpdated());
        quote.setDelayedPrice(dtoQuote.getDelayedPrice());
        quote.setDelayedPriceTime(dtoQuote.getDelayedPriceTime());
        quote.setOddLotDelayedPrice(dtoQuote.getOddLotDelayedPrice());
        quote.setOddLotDelayedPriceTime(dtoQuote.getOddLotDelayedPriceTime());
        quote.setExtendedPrice(dtoQuote.getExtendedPrice());
        quote.setExtendedChange(dtoQuote.getExtendedChange());
        quote.setExtendedChangePercent(dtoQuote.getExtendedChangePercent());
        quote.setExtendedPriceTime(dtoQuote.getExtendedPriceTime());
        quote.setPreviousClose(dtoQuote.getPreviousClose());
        quote.setPreviousVolume(dtoQuote.getPreviousVolume());
        quote.setChange(dtoQuote.getChange());
        quote.setChangePercent(dtoQuote.getChangePercent());
        quote.setIexMarketPercent(dtoQuote.getIexMarketPercent());
        quote.setIexVolume(dtoQuote.getIexVolume());
        quote.setAvgTotalVolume(dtoQuote.getAvgTotalVolume());
        quote.setIexBidPrice(dtoQuote.getIexBidPrice());
        quote.setIexBidSize(dtoQuote.getIexBidSize());
        quote.setIexAskPrice(dtoQuote.getIexAskPrice());
        quote.setIexAskSize(dtoQuote.getIexAskSize());
        quote.setMarketCap(dtoQuote.getMarketCap());
        quote.setWeek52High(dtoQuote.getWeek52High());
        quote.setWeek52Low(dtoQuote.getWeek52Low());
        quote.setYtdChange(dtoQuote.getYtdChange());
        quote.setPeRatio(dtoQuote.getPeRatio());
        quote.setLastTradeTime(dtoQuote.getLastTradeTime());
        quote.setIsUSMarketOpen(dtoQuote.getIsUSMarketOpen());
        return quote;
    }
}
