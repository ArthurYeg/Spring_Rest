package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateDemoUsers {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CreateDemoUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Transactional
    public void createDemoUsers() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleService.add(adminRole);
        roleService.add(userRole);

        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        adminRoles.add(adminRole);
        userRoles.add(userRole);

        User admin = new User("admin", "adm Sec-name", (byte) 20,
                "123",
                "admin@mail.ru");
        User user = new User("user", "user Sec-name", (byte) 20,
                "123",
                "user@yandex.ru");


        admin.setRoles(adminRoles);
        user.setRoles(userRoles);

        userService.createUser(admin);
        userService.createUser(user);
    }
}