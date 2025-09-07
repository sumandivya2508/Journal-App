package net.engineeringDigest.journalAPP.service;

import net.engineeringDigest.journalAPP.Repository.JournalEntryRepo;
import net.engineeringDigest.journalAPP.Repository.UserEntryRepo;
import net.engineeringDigest.journalAPP.entity.JournalEntry;
import net.engineeringDigest.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    public void saveEntry(User user){
        userEntryRepo.save(user);
    }
    public List<User>getAll(){
        return userEntryRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
          return userEntryRepo.findById(id);
    }
    public void deleteById(ObjectId id){
        userEntryRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return userEntryRepo.findByUserName(userName);
    }
}



// controller--->service---->repository