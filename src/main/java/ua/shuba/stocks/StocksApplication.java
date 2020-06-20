package ua.shuba.stocks;

import com.google.gson.Gson;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import ua.shuba.stocks.service.StockService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@EnableScheduling
@SpringBootApplication
public class StocksApplication {

    private static StockService stockService;

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
        while (true) {
            stockService.downloadCompanies();
        }
    }

    @Autowired
    public void setStockService(StockService stockService) {
        StocksApplication.stockService = stockService;
    }

    @Bean
    public Executor stockExecutorService() {
        return Executors.newFixedThreadPool(4);
    }

    @Bean
    public Lock lock() {
        return new ReentrantLock();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
    }

}
