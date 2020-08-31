package login.controller;

import login.model.User;
import login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Boolean.parseBoolean;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserControllerOld {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        return "Added User with name "+user.getName();
    }

    @GetMapping("/getAll")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/get/{name}")
    public User getUserByName(@PathVariable String name){
        return userRepository.findByName(name);
    }

    @DeleteMapping("/kill/{id}")
    public String kill(@PathVariable String id){
        userRepository.deleteById(id);
        return "Payment received";
    }

    //Add New User
    @PostMapping("/add")
    public String addUser(@RequestBody String details){

        String[] detailList=details.split("\\+",5);
        List<User> x = userRepository.findAllByName(detailList[0]);
        if(x.size()!=0){
            return "[1]";
        }
        x = userRepository.findByEmail(detailList[3]);
        if(x.size()!=0){
            return "[2]";
        }
        User newUser = new User(detailList[0],detailList[1],parseBoolean(detailList[2]),detailList[3],detailList[4]);
        userRepository.save(newUser);
        return "[0]";

    }

    //Update Existing User

    //Login
    @GetMapping("/login/{details}")
    public String Login(@PathVariable String details){
        String[] detailList=details.split("\\+",3);
        List<User> x = userRepository.findByNameAndPassword(detailList[0],detailList[1]);
        if(x.size()==0){
            return "[1]";
        }
        if(x.get(0).isUserType()==false && parseBoolean(detailList[2])==true){

            return "[2]";
        }
        return "[0]";
    }
}

