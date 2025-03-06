package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userRepository.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // возвращаем страницу users.html
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        // Создаем пустой объект пользователя для привязки данных формы
        model.addAttribute("user", new User());
        return "addUser"; // имя шаблона (addUser.html)
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        // Сохранение пользователя в базу данных или другое действие
        userRepository.addUser(user);
        return "redirect:/user/list"; // Перенаправление на страницу с пользователями после добавления
    }

    // Отображение формы редактирования
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";  // Это форма редактирования
    }

    // Обработка формы редактирования
    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userRepository.updateUser(user);  // Используем updateUser для изменения пользователя
        return "redirect:/user/list";  // Перенаправление после обновления
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userRepository.deleteUser(id);
        return "redirect:/user/list"; // после удаления редиректим на список пользователей
    }
}
