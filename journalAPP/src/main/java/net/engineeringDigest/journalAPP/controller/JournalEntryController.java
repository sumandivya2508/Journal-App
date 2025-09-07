package net.engineeringDigest.journalAPP.controller;

import net.engineeringDigest.journalAPP.entity.JournalEntry;
import net.engineeringDigest.journalAPP.entity.User;
import net.engineeringDigest.journalAPP.service.JournalEntryService;
import net.engineeringDigest.journalAPP.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService JournalEntryService;

    @Autowired
    private UserEntryService userEntryService;


  @GetMapping("/{userName}")
  public ResponseEntity<?>getAllJournalEntryOfUser(@PathVariable String userName){
      User user= userEntryService.findByUserName(userName);
      List<JournalEntry>all=user.getJournalEntries();
      if(all!=null && !all.isEmpty()){
          return new ResponseEntity<>(all, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
      @PostMapping("/{userName}")
      public  ResponseEntity<JournalEntry>  createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
      try {
          User user= userEntryService.findByUserName(userName);
          JournalEntryService.saveEntry(myEntry,userName);
          return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
      }
      }

  @GetMapping("id/{myid}")
     public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid){
      Optional<JournalEntry> journalEntry = JournalEntryService.findById(myid);
      if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
@PutMapping("id/{userName}/{myId}")
    public ResponseEntity updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry,
                                                 @PathVariable String userName){
      JournalEntry old=JournalEntryService.findById(myId).orElse(null);
      if(old!=null){
         old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals(" ")? newEntry.getTitle(): old.getTitle());
         old.setContent(newEntry.getContent()!= null && !newEntry.equals("")? newEntry.getContent():old.getContent());
          JournalEntryService.saveEntry(old);
          return new ResponseEntity<>(old,HttpStatus.OK);
      }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
  @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?>  DeleteJournalEntryById(@PathVariable ObjectId myid,@PathVariable String userName){
         JournalEntryService.deleteById(myid,userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }







}
