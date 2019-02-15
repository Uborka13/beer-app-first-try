package soft.ubi.hu.daveapp.controllers.network.dto.user;

import java.util.List;

public class UserListResponse {

    private List<UserResponse> users;

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }
}
