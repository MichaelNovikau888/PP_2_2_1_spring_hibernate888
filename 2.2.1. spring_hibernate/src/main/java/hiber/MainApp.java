package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);



      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car();
      car1.setModel("Toyota");
      car1.setSeries(2020);

      User user1 = new User("Liza", "Novikava", "LizaN@mail.ru", car1);

      Car car2 = new Car();
      car2.setModel("Honda");
      car2.setSeries(2019);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car Model = " + user.getCar().getModel());
         System.out.println("Car Series = " + user.getCar().getSeries());
         System.out.println();
      }

      User user = userService.getUserByCarModelAndSeries("Toyota", 2020);
      System.out.println("User with Toyota 2020: " + user.getFirstName());

      context.close();
   }
}
