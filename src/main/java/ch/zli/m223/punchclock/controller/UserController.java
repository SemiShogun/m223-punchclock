package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.repository.ApplicationUserRepository;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        applicationUserRepository.save(user);
    }

    @GetMapping("/role")
    public String role(Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        return applicationUser.getRole();
    }

    @GetMapping("/username")
    public String getCurrentUser(Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        return applicationUser.getUsername();
    }

    @GetMapping("/allUsers")
    public List<String> retrieveUsers(Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        if (applicationUser.getRole() != "ADMIN") {
            throw new BadRequestException();
        }
        return applicationUserRepository.findAll().stream().map(ApplicationUser::getUsername).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createUser(@RequestBody ApplicationUser applicationUser, Principal user) {
        ApplicationUser _applicationUser = userService.retrieveUserByUsername(user.getName());
        if (_applicationUser.getRole() != "ADMIN") {
            throw new BadRequestException();
        }
        return applicationUserRepository.save(applicationUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id, Principal user) {
        ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
        if (applicationUser.getRole() != "ADMIN") {
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
        if (applicationUser.getRole() != "ADMIN") {
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
