package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User[] addedUsers = {
              new User(
                      "User1",
                      "Lastname1",
                      "user1@mail.ru",
                      new Car("BMW", 3L)
              ),
              new User(
                      "User2",
                      "Lastname2",
                      "user2@mail.ru",
                      new Car("Mercedes", 606L)
              ),
              new User(
                      "User3",
                      "Lastname3",
                      "user3@mail.ru",
                      new Car("Audi", 8L)
              ),
              new User(
                      "User4",
                      "Lastname4",
                      "user4@mail.ru",
                      new Car("Volvo", 122L)
              )
      };

      for (User user : addedUsers) {
         userService.add(user);
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println();
         System.out.println(user);
      }

      System.out.println();
      Long seriesCriteria = 3L;
      String modelCriteria = "BMW";
      User foundUser = userService.findUserByCar(seriesCriteria, modelCriteria);
      if (foundUser == null) {
         System.out.println("User with Car (Model: " + modelCriteria + "; Series: " + seriesCriteria + ") not found");
      } else {
         System.out.println("User with Car (Model: " + modelCriteria + "; Series: " + seriesCriteria + ") founded:");
         System.out.println(foundUser);
      }

      context.close();
   }
}
