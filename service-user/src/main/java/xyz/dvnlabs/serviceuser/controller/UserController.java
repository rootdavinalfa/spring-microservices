package xyz.dvnlabs.serviceuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dvnlabs.serviceuser.entity.User;
import xyz.dvnlabs.serviceuser.service.UserService;

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
    ){
        return userService.findById(username);
    }
}
