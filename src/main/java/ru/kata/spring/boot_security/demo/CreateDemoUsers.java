package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreateDemoUsers {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public CreateDemoUsers(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void createDemoUsers() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");

        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        roleAdmin.add(adminRole);
        roleUser.add(userRole);
        User admin = new User("admin", "adm Sec-name", (byte) 20,
                "$2a$12$boS3Oud9fYxdXMSA4SJcPu9nJUf0JRwP032PxmlR85bFrvM845rr2",
                "admin@mail.ru");
        User user = new User("user", "user Sec-name", (byte) 20,
                "$2a$12$boS3Oud9fYxdXMSA4SJcPu9nJUf0JRwP032PxmlR85bFrvM845rr2",
                "user@mail.ru");

//        roleAdmin.add(userRole);
        admin.setRoles(roleAdmin);
        user.setRoles(roleUser);

        userRepository.save(user);
        userRepository.save(admin);
    }
}