package br.com.santospage.taxassistant.interfaces.dto.mongo;

import br.com.santospage.taxassistant.domain.enums.UserRole;
import br.com.santospage.taxassistant.domain.models.mongo.UserModel;

public class UserDTO {
    private String userId;
    private String userName;
    private String userFullName;
    private String userEmail;
    private UserRole userRole;
    private String userPassword;

    public UserDTO() {
    }

    public UserDTO(UserModel user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userFullName = user.getUserFullName();
        this.userEmail = user.getUserEmail();
        this.userRole = user.getUserRole();
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

