package st.protok.crud.b_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import st.protok.crud.c_model.User;
import st.protok.crud.e_service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "all-users";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @RequestMapping("/updateInfo/{id}")
    public String updateInfo(@ModelAttribute("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "user-info";
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@ModelAttribute("id") int id) {
        userService.removeUser(id);

        return "redirect:/";
    }

    @RequestMapping("/generateUsers")
    public String generateUsers() {
        userService.addUser(new User("Simba", "Pecking", "QWE", 1100));
        userService.addUser(new User("Ратмир", "Усманов", "ПАТ", 1200));
        userService.addUser(new User("Кира", "Ван Вогт", "QAZ", 1300));
        userService.addUser(new User("Александр", "Севостьянов", "Kata1", 1400));
        userService.addUser(new User("Джонсон", "Трал", "МЕХ", 1502));
        userService.addUser(new User("Германикус", "Из Стратона", "TP2", 1600));

        return "redirect:/";
    }

}
