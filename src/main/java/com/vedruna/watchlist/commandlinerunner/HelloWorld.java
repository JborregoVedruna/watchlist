package com.vedruna.watchlist.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vedruna.watchlist.service.cron.CronService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HelloWorld implements CommandLineRunner {

    @Autowired
    CronService cronService;
    
    @Override
    public void run(String... args) throws Exception {
        log.trace("----------------------------------- LOADING DATA FROM API -----------------------------------");
        cronService.cronJob();
        log.trace("----------------------------------- APPLICATION STARTED -----------------------------------");
    
    }
    
}
