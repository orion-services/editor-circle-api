package circle.controller;

import circle.model.User;
import circle.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> userToCreate(@RequestParam @Valid String name, @RequestParam @Valid String hashUser){
        return new ResponseEntity<>(this.userService.userToCreate(name, hashUser), HttpStatus.CREATED);
    }

    @GetMapping("/hash")
    public ResponseEntity<User> userToFindByHash(@RequestParam @Valid String hashUser){
        return new ResponseEntity<>(this.userService.userToFindByHash(hashUser), HttpStatus.CREATED);
    }

}
