/*
package login.controller;

import login.dto.UserRequest;
import login.dto.UserResponse;
import login.repository.UserRepository;
import login.model.User;
import login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static java.lang.Boolean.parseBoolean;

@CrossOrigin(origins = {"http://localhost:8088","http://localhost:4200"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("test")
    public  ResponseEntity<String> getit(){
        return ResponseEntity.ok().body("Test");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String id){

        UserResponse result = userService.deleteUser(id);
        if(result==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    //Add New User
    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody User newUser){
        String result = userService.addUser(newUser);
        if(result=="1"){
            return ResponseEntity.badRequest().body(new UserResponse(null,"User with same Name already exists",false,null,null));
        }
        if(result=="2"){
            return ResponseEntity.badRequest().body(new UserResponse(null,"User with same email-Id already exists",false,null,null));
        }
        return ResponseEntity.ok().body(new UserResponse(result,newUser.getName(),newUser.isUserType(),newUser.getEmail(),newUser.getMobileNo()));
    }

    //Update Existing User
    @PostMapping("/update")
    public ResponseEntity<UserResponse> userUpdate(@RequestBody User user){
        UserResponse result = userService.update(user);
        if(result.getUserId()==null){return ResponseEntity.badRequest().body(result);}
        return ResponseEntity.ok().body(result);
    }

    //Login
    @GetMapping("/login")
    public ResponseEntity<UserResponse> Login(@RequestBody UserRequest userRequest){
        String result = userService.login(userRequest);
        if(result=="1"){
            return ResponseEntity.notFound().build();
        }
        if(result=="2"){

            return ResponseEntity.badRequest().body(new UserResponse(null,"Admin Privileges absent",false,null,null));
        }
        User newlyAddedUser = userService.findById(result).get();
        return ResponseEntity.ok().body(new UserResponse(result,newlyAddedUser.getName(),newlyAddedUser.isUserType(),newlyAddedUser.getEmail(),newlyAddedUser.getMobileNo()));
    }
}
*/