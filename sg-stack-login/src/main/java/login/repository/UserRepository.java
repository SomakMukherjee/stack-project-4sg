package login.repository;

import login.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends MongoRepository<User,String> {

    public User findByName(String name);
    public List<User> findByNameAndPassword(String name, String password);
    public List<User> findByNameOrPassword(String name, String password);
    public List<User> findByEmail(String email);
    public List<User> findAllByName(String name);
    public List<User> findByUserType(boolean userType);

}
