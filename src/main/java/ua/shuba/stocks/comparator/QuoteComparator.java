package ua.shuba.stocks.comparator;

import ua.shuba.stocks.domain.Quote;

import java.util.Comparator;

public class QuoteComparator implements Comparator<Quote> {
    @Override
    public int compare(Quote o1, Quote o2) {
        return o1.getCompanyName().compareTo(o2.getCompanyName());
    }
}
