package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.ApplicationUser;
import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final UserService userService;
    private final CategoryService categoryService;

    public CategoryController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Category> getCategory(@PathVariable long id) {
        return categoryService.findCategory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@Valid @RequestBody Category category, Principal user) {
        try {
            ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
            if (!applicationUser.getRole().equals("ADMIN")) {
                throw new BadRequestException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable long id, Principal user) {
        try {
            ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
            if (!applicationUser.getRole().equals("ADMIN")) {
                throw new BadRequestException();
            }
            categoryService.deleteCategory(id);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@PathVariable long id, @Valid @RequestBody Category category, Principal user) {
        try {
            ApplicationUser applicationUser = userService.retrieveUserByUsername(user.getName());
            if (!applicationUser.getRole().equals("ADMIN")) {
                throw new BadRequestException();
            }
            category.setId(id);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return categoryService.updateCategory(category);
    }
}
