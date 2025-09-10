package br.com.santospage.taxassistant.domain.repositories;

import br.com.santospage.taxassistant.domain.models.User;
import com.mongodb.connection.ProxySettings;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(ProxySettings user);

    Optional<User> findByUsername(String username);

    List<User> findAll();
}
