package br.com.santospage.taxassistant.interfaces.dtos;

import br.com.santospage.taxassistant.domain.enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private Role role;
}
