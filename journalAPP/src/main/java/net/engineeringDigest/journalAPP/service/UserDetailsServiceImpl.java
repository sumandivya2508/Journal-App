package net.engineeringDigest.journalAPP.service;

import net.engineeringDigest.journalAPP.Repository.UserEntryRepo;
import net.engineeringDigest.journalAPP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Array;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserEntryRepo userrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userrepository.findByUserName(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRole().toArray(new String[0]))
                    .build();
        }
           throw  new UsernameNotFoundException("user not found with username"+username);

    }
}

