package tsf.reservation.api;

import org.springframework.web.bind.annotation.*;
import tsf.reservation.controller.UserController;
import tsf.reservation.database.models.User;

@CrossOrigin
@RestController
public class UserApiController {
    private UserController userController = UserController.getInstance();

    @PostMapping("/reservation/user")
    public String loginUser(@RequestBody User user) {
       return userController.loginUser(user);
    }

    /*@GetMapping("/http-servlet-response")
    public String usingHttpServletResponse(HttpServletResponse response) {
        response.addHeader("Baeldung-Example-Header", "Value-HttpServletResponse");
        return "Response with header using HttpServletResponse";
    }*/

    @GetMapping("/reservation/user/{email}")
    public User getUser(@PathVariable String email) {
        return userController.getUser(email);
    }

    @PutMapping("/reservation/user/{token}")
    public String logoutUser(@RequestBody User user, @PathVariable String token) {
        return userController.logoutUser(user, token);
    }
}
