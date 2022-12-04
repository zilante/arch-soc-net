package archsocnet.controller;

import archsocnet.payload.request.LoginRequest;
import archsocnet.payload.request.SignupRequest;
import archsocnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller, which handles queries:
 * 1) login the user
 * 2) signup the user
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    private final static String LOGIN_SUCCESS = "Successful login";
    private final static String SIGNUP_SUCCESS = "Successful signup";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request
    ) {
        userService.login(request);
        return new ResponseEntity<>(LOGIN_SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @Valid @RequestBody SignupRequest request
    ) {
        userService.signup(request);
        return new ResponseEntity<>(SIGNUP_SUCCESS, HttpStatus.OK);
    }
}
