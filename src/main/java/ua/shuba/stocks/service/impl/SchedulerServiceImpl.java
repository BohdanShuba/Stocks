package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.service.CompanyService;
import ua.shuba.stocks.service.SchedulerService;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final CompanyService companyService;

    @Scheduled(fixedRate = 5000)
    public void printStatistics() {
        companyService.printStats();
    }
}
