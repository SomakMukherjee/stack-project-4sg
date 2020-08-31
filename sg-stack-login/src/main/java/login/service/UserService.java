package login.service;

import login.dto.UserRequest;
import login.dto.UserResponse;
import login.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public String addUser(User user);
    public UserResponse deleteUser(String id);
    public User getUserByName(String userName);
    public String login(UserRequest userRequest);
    public UserResponse update(User user);
    public List<User> findAll();
    public Optional<User> findById(String id);
}
