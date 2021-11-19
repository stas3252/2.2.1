package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car carA = new Car("model1", 1);
      carService.add(carA);
      Car carB = new Car("model2", 2);
      carService.add(carB);
      Car carC = new Car("model3", 3);
      carService.add(carC);
      Car carD = new Car("model4", 4);
      carService.add(carD);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", carA));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", carB));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", carC));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", carD));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car: " + user.getCar());
         System.out.println();
      }
      System.out.println("________________________________");
      System.out.println(userService.getUser(carA));
      context.close();
   }
}
