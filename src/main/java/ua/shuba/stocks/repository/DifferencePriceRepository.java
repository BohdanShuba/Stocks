package ua.shuba.stocks.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.shuba.stocks.domain.DifferencePrice;
import ua.shuba.stocks.domain.Quote;
import ua.shuba.stocks.dto.DtoDifferencePrice;

import java.util.List;

@Repository
public interface DifferencePriceRepository extends JpaRepository<DifferencePrice, String> {

//    List<DifferencePrice> findTop5ByOrderByDifferencePriceDesc();
//
    @Query("SELECT NEW ua.shuba.stocks.dto.DtoDifferencePrice(dp.symbol, dp.companyName, dp.differencePrice, dp.settlementDate)" +
            " FROM DifferencePrice dp ORDER BY ABS(dp.differencePrice) DESC")
    List<DtoDifferencePrice> findTopAbsDifferencePrice(Pageable p);

//    @Query(value = "SELECT * FROM stocks_db.difference_price order by abs(difference_price) desc LIMIT 5", nativeQuery = true)
//    List<DifferencePrice> findTop5AbsDifferencePrice();



}
