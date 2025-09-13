package net.engineeringDigest.journalAPP.Repository;

import net.engineeringDigest.journalAPP.entity.JournalEntry;
import net.engineeringDigest.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<User, ObjectId> {
   User findByUserName(String userName);

   void deleteByUserName(String userName);
}
