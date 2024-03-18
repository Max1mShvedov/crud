package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;
import web.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/add")
    public String addUserSubmit(@ModelAttribute(name = "name") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/{id}/edit")
    public String editUserSubmit(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
