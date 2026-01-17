package com.company.journalApp.repositry;

import com.company.journalApp.entity.ConfigJournalAppEntity;
import com.company.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}

