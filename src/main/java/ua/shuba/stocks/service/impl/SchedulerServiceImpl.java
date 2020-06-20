package ua.shuba.stocks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.service.PrintService;
import ua.shuba.stocks.service.SchedulerService;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final PrintService printService;

    @Scheduled(fixedRate = 5000)
    @Override
    public void printStatistics() {
        printService.printStats();
    }
}
