package soft.ubi.hu.daveapp.controllers.network.dto.registration;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("roles")
    private String[] roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
