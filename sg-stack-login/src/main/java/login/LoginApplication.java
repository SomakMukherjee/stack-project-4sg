package login;

import login.model.User;
import login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoginApplication{// implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args){
		SpringApplication.run(LoginApplication.class, args);
	}

	/*
	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("A", "XXX", false, "x@y.com", "999"));
		userRepository.save(new User("B", "XyX", false, "yx@y.com", "9099"));
		userRepository.save(new User("A", "XXX", false, "x@y.com", "999"));
		userRepository.save(new User("Ad", "XXXd", true, "x@ey.com", "99912"));
		userRepository.save(new User("Ad", "XXXd", true, "x@ey.com", "99912"));

		System.out.println("Currently Users");
		for (User user : userRepository.findByUserType(false)) {
			System.out.println(user);
		}

		System.out.println("Currently Inside");
		for (User user : userRepository.findAll()) {
			System.out.println(user);
		}

		System.out.println("Currently Names");
		System.out.println(userRepository.findByName("Mike"));
		System.out.println(userRepository.findByName("Kike"));
		//System.out.println(userRepository.findAllByName("A"));

		System.out.println("ALL DONE_____________________________________________");
	}*/
}
