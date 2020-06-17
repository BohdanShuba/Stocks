package ua.shuba.stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.shuba.stocks.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
}
