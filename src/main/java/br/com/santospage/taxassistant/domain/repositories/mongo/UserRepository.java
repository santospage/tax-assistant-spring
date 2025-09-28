package br.com.santospage.taxassistant.domain.repositories.mongo;

import br.com.santospage.taxassistant.domain.models.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
