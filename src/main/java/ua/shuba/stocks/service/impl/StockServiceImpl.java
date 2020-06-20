package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.client.CompanyHttpClient;
import ua.shuba.stocks.dto.DtoCompany;
import ua.shuba.stocks.service.StockService;
import ua.shuba.stocks.service.Task;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final CompanyHttpClient companyHttpClient;
    private final ObjectFactory<Task> taskFactory;
    private final Executor stockExecutorService;
    private final Lock lock;

    public void downloadCompanies() {
        try {
            lock.lock();
            List<DtoCompany> companies = processCompanies();
            CountDownLatch countDownLatch = new CountDownLatch(companies.size());

            for (DtoCompany company : companies) {
                Task task = taskFactory.getObject();
                task.setStockCode(company.getSymbol());
                task.setLatch(countDownLatch);
                stockExecutorService.execute(task);
            }
            countDownLatch.await();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private List<DtoCompany> processCompanies() {
        List<DtoCompany> companies = companyHttpClient.getAllCompaniesList();
        return findEnabledCompanies(companies);
    }

    private List<DtoCompany> findEnabledCompanies(List<DtoCompany> companies) {
        return companies.stream()
                .filter(company -> company.getIsEnabled())
                .collect(Collectors.toList());
    }
}
