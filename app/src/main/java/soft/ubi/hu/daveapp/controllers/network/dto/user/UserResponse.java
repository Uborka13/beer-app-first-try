package soft.ubi.hu.daveapp.controllers.network.dto.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("personalLists")
    private List<Object> personalLists;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private int userId;

    @SerializedName("email")
    private String email;

    public List<Object> getPersonalLists() {
        return personalLists;
    }

    public void setPersonalLists(List<Object> personalLists) {
        this.personalLists = personalLists;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}