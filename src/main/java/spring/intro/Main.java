package spring.intro;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    public static void main(String[] args) {
        User bob = new User();
        bob.setName("Bob");
        bob.setEmail("bob@example.com");
        bob.setPassword("1234");

        User alice = new User();
        alice.setName("Alice");
        alice.setEmail("alice@example.com");
        alice.setPassword("1234");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(bob);
        userService.add(alice);

        userService.getAllUsers().forEach(System.out::println);
    }
}
