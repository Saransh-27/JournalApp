package com.company.journalApp.controller;

import com.company.journalApp.dto.JournalEntryDTO;
import com.company.journalApp.entity.JournalEntry;
import com.company.journalApp.entity.User;
import com.company.journalApp.mapper.JournalEntryMapper;
import com.company.journalApp.service.JournalEntryService;
import com.company.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal APIs", description = "Create, update, delete, get Journal Entry")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all journal entries of a user")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Create Journal entry for a user")
    public ResponseEntity<JournalEntryDTO> create(@RequestBody JournalEntryDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        JournalEntry entry = JournalEntryMapper.toEntity(dto);
        journalEntryService.saveEntry(entry, username);

        return new ResponseEntity<>(
                JournalEntryMapper.toDTO(entry),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/id/{myId}")
    @Operation(summary = "Get journal entry by their ID")
        public ResponseEntity<?> getJournalEntryById(@PathVariable("myId") String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    @Operation(summary = "Delete journal entries of a user by ID")
    public ResponseEntity<?> deleteJournalEntryById(
            @PathVariable("myId") String myId
    ) {
        ObjectId objectId = new ObjectId(myId);

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean removed =
                journalEntryService.deleteById(objectId, username);

        if (removed) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/id/{myId}")
    @Operation(summary = "Update journal entries of a user by ID")
    public ResponseEntity<?> updateJournalById(
            @PathVariable("myId") String myId,
            @RequestBody JournalEntry newEntry
    ) {
        ObjectId objectId = new ObjectId(myId);

        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        List<JournalEntry> collect = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(objectId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry =
                    journalEntryService.findById(objectId);

            if (journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();

                old.setTitle(
                        newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                                ? newEntry.getTitle()
                                : old.getTitle()
                );

                old.setContent(
                        newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                                ? newEntry.getContent()
                                : old.getContent()
                );

                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}