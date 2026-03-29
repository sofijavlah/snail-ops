package com.bepos.schedulers;

import com.bepos.service.BountyService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BountyScheduler {

    @Inject
    private BountyService bountyService;

    @Scheduled(every="10s")
    public void updateBounties() {
        bountyService.updateHighThreatBounties();
    }
}
