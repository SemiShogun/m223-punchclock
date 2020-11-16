package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserController.java
 *
 * @author jlam
 * @version 16.11.2020
 *
 * UserController allows the user to gain full CRUD functionality for
 * creating, reading, updating and deleting users.
 *
 * An example for a user would be: username: Max Muster, password: 1234
 *
 * I've added users so that everyone can save their own entries, making it a multi-user
 * application with authentication/authorization functionalities.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private EntityManagerFactory emfactory;
    private EntityManager em = emfactory.createEntityManager();

    public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getUsername().toLowerCase().equals("admin")) {
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }
        applicationUserRepository.save(user);
    }

    @GetMapping("/currentUser")
    public ApplicationUser getCurrentUser(Principal user) {
        return userService.retrieveUserByUsername(user.getName());
    }

    @GetMapping("/role")
    public String role(Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        return applicationUser.getRole();
    }

    @GetMapping("/allUsers")
    public List<String> retrieveUsers(Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
//        List<String> values = (List<String>) em.createQuery("SELECT c FROM APPLICATION_USER c");
        if (!applicationUser.getRole().equals("ADMIN")) {
            throw new BadRequestException();
        }
        return applicationUserRepository.findAll().stream().map(ApplicationUser::getUsername).collect(Collectors.toList());
//        return values;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createUser(@RequestBody ApplicationUser applicationUser, Principal user) {
        ApplicationUser _applicationUser = userService.retrieveUserByUsername(user.getName());
        if (!_applicationUser.getRole().equals("ADMIN")) {
            throw new BadRequestException();
        }
        return applicationUserRepository.save(applicationUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id, Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        if (!applicationUser.getRole().equals("ADMIN")) {
            throw new BadRequestException();
        }
        if (applicationUserRepository.findById(id) == null) {
            throw new BadRequestException();
        }
        applicationUserRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationUser updateUser(@PathVariable long id, @Valid @RequestBody ApplicationUser updatedUser, Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        if (!applicationUser.getRole().equals("ADMIN")) {
            throw new BadRequestException();
        }
        if (applicationUserRepository.findById(id) == null) {
            throw new BadRequestException();
        }
        Optional<ApplicationUser> _updatedUser = applicationUserRepository.findById(id);
        _updatedUser.get().setUsername(updatedUser.getUsername());
        _updatedUser.get().setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        _updatedUser.get().setRole(updatedUser.getRole());
        return applicationUserRepository.save(_updatedUser.get());
    }

}
