package login.service;

import login.dto.UserRequest;
import login.dto.UserResponse;
import login.model.User;
import login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.parseBoolean;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(User user) {

        List<User> x = userRepository.findAllByName(user.getName());
        if(x.size()!=0){
            return "1";
        }
        x = userRepository.findByEmail(user.getEmail());
        if(x.size()!=0){
            return "2";
        }
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public UserResponse deleteUser(String id) {

        boolean exists = userRepository.existsById(id);
        if(!exists){return null;}
        Optional<User> user = userRepository.findById(id);
        UserResponse userResponse = new UserResponse(user.get().getId(),user.get().getName(),user.get().isUserType(),user.get().getEmail(),user.get().getMobileNo());
        userRepository.deleteById(id);
        return userResponse;
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public String login(UserRequest userRequest) {
        List<User> x = userRepository.findByNameAndPassword(userRequest.getName(),userRequest.getPassword());
        if(x.size()==0){
            return "1";
        }
        if(x.get(0).isUserType()==false && userRequest.isUserType()==true){

            return "2";
        }
        return x.get(0).getId();
    }

    @Override
    public UserResponse update(User user) {
        Optional<User> oldUser= userRepository.findById(user.getId());
        List<User> x = userRepository.findAllByName(user.getName());

        if(!user.getName().equals(oldUser.get().getName())) {
            if (x.size() != 0 && x.get(0).getId() != user.getId()) {
                return new UserResponse(null, "User with same Name already exists", false, null, null);
            }
        }

        if(!user.getEmail().equals(oldUser.get().getEmail())){
            x = userRepository.findByEmail(user.getEmail());
            if (x.size() != 0 && x.get(0).getId() != user.getId()) {
                return new UserResponse(null, "User with same Email already exists", false, null, null);
            }
        }

        userRepository.save(user);
        return new UserResponse(user.getId(),user.getName(),user.isUserType(),user.getEmail(),user.getMobileNo());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
