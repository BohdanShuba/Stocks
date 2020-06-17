package ua.shuba.stocks.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.shuba.stocks.service.SchedulerService;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    @Scheduled(fixedRate = 5000)
    public void sendRequest() {
        //todo start method
    }
}
