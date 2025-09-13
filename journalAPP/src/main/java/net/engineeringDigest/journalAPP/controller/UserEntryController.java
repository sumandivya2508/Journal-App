package net.engineeringDigest.journalAPP.controller;

import net.engineeringDigest.journalAPP.Repository.UserEntryRepo;
import net.engineeringDigest.journalAPP.entity.JournalEntry;
import net.engineeringDigest.journalAPP.entity.User;
import net.engineeringDigest.journalAPP.service.JournalEntryService;
import net.engineeringDigest.journalAPP.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextChangedEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserEntryController {


@Autowired
private UserEntryService userEntryService;

    @Autowired
    private UserEntryRepo userEntryRepo;

@GetMapping
public List<User>getAllUser(){
     return userEntryService.getAll();
}


@PutMapping
public ResponseEntity<?> updateUse(@RequestBody User user,@PathVariable String userName){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    String username=authentication.getName();
    User userInDb= userEntryService.findByUserName(userName);

    if(userInDb!=null){
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
       userEntryService.saveEntry(userInDb);
   }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@DeleteMapping
   public ResponseEntity<?> DeleteUserById(){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    userEntryRepo.deleteByUserName(authentication.getName());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


}
