package com.amanuel.RESTProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;


    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Resource<User> getUser(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);

        if (!user.isPresent())
            throw new UserNotFoundException("Can't find user with id: " + id);

        Resource<User> userResource = new Resource<>(user.get());
        // add link to retrieve all users.

        ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());

        userResource.add(link.withRel("Link to all Users"));

        return userResource;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User savedUser = UserDAO.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        if (!UserDAO.delete(id))
            throw new UserNotFoundException("Can't find user with this id: " + id);
    }

    @GetMapping("/greeting")
    public String greeting(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
