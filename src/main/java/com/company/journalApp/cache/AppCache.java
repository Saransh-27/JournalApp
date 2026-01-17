package com.company.journalApp.cache;

import com.company.journalApp.entity.ConfigJournalAppEntity;
import com.company.journalApp.repositry.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();

        for (ConfigJournalAppEntity config : all) {
            APP_CACHE.put(config.getKey(), config.getValue());
        }
    }
}
