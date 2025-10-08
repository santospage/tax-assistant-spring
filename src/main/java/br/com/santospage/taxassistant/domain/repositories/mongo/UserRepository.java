package br.com.santospage.taxassistant.domain.repositories.mongo;

import br.com.santospage.taxassistant.domain.models.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);
}
