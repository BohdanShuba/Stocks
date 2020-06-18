package ua.shuba.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shuba.stocks.domain.Quote;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {

    List<Quote> findTop5ByOrderByPreviousVolumeDesc();
}
