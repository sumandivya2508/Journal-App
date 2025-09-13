package net.engineeringDigest.journalAPP.service;

import net.engineeringDigest.journalAPP.Repository.JournalEntryRepo;
import net.engineeringDigest.journalAPP.entity.JournalEntry;
import net.engineeringDigest.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo  journalEntryRepo;

    @Autowired
     private UserEntryService userEntryService;
@Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
    try {
        User user= userEntryService.findByUserName(userName);
        JournalEntry saved= journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);

        userEntryService.saveEntry(user);
    }catch(Exception e){
        System.out.println(e);
        throw new RuntimeException("An error occured while saving the entry"+e);
    }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry>getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
          return journalEntryRepo.findById(id);
    }
    public void deleteById(ObjectId id,String username){
        User user= userEntryService.findByUserName(username);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));
        userEntryService.saveEntry(user);
        journalEntryRepo.deleteById(id);
    }
}



// controller--->service---->repository