package net.engineeringDigest.journalAPP.controller;

import net.engineeringDigest.journalAPP.entity.User;
import net.engineeringDigest.journalAPP.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/health-check")
    public String HealthCheck(){
        return "ok";
    }
   @Autowired
    private UserEntryService userEntryService;

    @PostMapping("/create_user")
    public void createUser(@RequestBody User user){
        userEntryService.saveEntry(user);
    }

}

