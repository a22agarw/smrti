package org.smrti.service.log.repository;

import org.smrti.service.log.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LogRepository extends MongoRepository<Log, String> {
    Optional<Log> findById(String id);
}
