package br.com.santospage.taxassistant.interfaces.dtos;

import br.com.santospage.taxassistant.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String username;
    private Role role;

    public UserResponse(Object id, Object username, Object role) {
    }
}
