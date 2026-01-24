package com.company.journalApp.Schedulers;

import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.entity.User;
import com.company.journalApp.repositry.UserRepo;
import com.company.journalApp.repositry.UserRepoimpl;
import com.company.journalApp.service.EmailService;
import com.company.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchedulers {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoimpl userRepoimpl;

    @Scheduled(cron = "0 * * * * *")
    public void fetchUsersAndsendMails() {
        List<User> users = userRepoimpl.getUserforSA();
        for (User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = SentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days ", sentiment);
        }
    }
}
