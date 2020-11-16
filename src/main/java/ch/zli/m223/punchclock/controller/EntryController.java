package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * EntryController.java
 *
 * @author jlam
 * @version 16.11.2020
 *
 * EntryController allows the user to gain full CRUD functionality for
 * creating, reading, updating and deleting Entries.
 *
 * Entries basically define every single saved time that the user has saved.
 * Each user has their own entries.
 * Entries also include the category, room and applicationuser.
 * */
@RestController
@RequestMapping("/entries")
public class EntryController {
    private final EntryService entryService;
    private final UserService userService;

    public EntryController(EntryService entryService, UserService userService) {
        this.entryService = entryService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Entry> getEntry(@PathVariable long id) {
        return entryService.findEntry(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry, Principal user) {
        if (entry.getCheckOut().isBefore(entry.getCheckIn()))
            throw new BadRequestException();
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        entry.setApplicationUser(applicationUser);
        return entryService.createEntry(entry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable long id) {
        try {
            entryService.deleteEntry(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@PathVariable long id, @Valid @RequestBody Entry entry) {
        try {
            entry.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return entryService.updateEntry(entry);
    }
}
