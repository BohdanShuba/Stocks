package ua.shuba.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shuba.stocks.domain.DifferencePrice;
import ua.shuba.stocks.domain.Quote;

import java.util.List;

@Repository
public interface DifferencePriceRepository extends JpaRepository<DifferencePrice, String> {
    List<DifferencePrice> findTop5ByOrderByDifferencePriceDesc();

}
