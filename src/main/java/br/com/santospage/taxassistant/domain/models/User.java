package br.com.santospage.taxassistant.domain.models;

import br.com.santospage.taxassistant.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String id;
    private String username;
    private String password;
    private Role role;
}
