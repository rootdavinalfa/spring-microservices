package xyz.dvnlabs.serviceuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.dvnlabs.corelibrary.exception.ResourceExistException;
import xyz.dvnlabs.serviceuser.entity.User;
import xyz.dvnlabs.serviceuser.service.UserService;

import java.util.List;

@RequestMapping("/user")
@RestController
@Api(tags = "User Controller")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @ApiOperation("Find by Username / ID")
    User findById(
            @PathVariable String username
    ) {
        return userService.findById(username);
    }

    @GetMapping("/list")
    @ApiOperation("Find list user")
    List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @ApiOperation("Insert User")
    User insert(
            @RequestBody User user
    ) throws ResourceExistException {
        return userService.create(user);
    }

}
