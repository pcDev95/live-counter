package com.livecounter.lc.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.livecounter.lc.repository.ProjectRepository;
import com.livecounter.lc.service.CounterService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@AllArgsConstructor
@Log4j2
public class CountScheduler {
    private final CounterService counterService;
    private final ProjectRepository projectRepository;

    // 5 seconds
    @Scheduled(fixedRate = 5000)
    public void pushUpdatedCount() {
        Long currentCount = projectRepository.count();
        log.info("Count is: {}", currentCount);
        counterService.broadcast(currentCount.intValue());
    }

}
