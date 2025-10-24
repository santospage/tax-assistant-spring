package br.com.santospage.taxassistant.domain.repositories.mongo;

import br.com.santospage.taxassistant.domain.models.mongo.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {
    boolean existsByUserName(String userName);

    Optional<UserModel> findByUserName(String userName);
}
