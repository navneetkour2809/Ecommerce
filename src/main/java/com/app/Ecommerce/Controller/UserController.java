package com.app.Ecommerce.Controller;
import com.app.Ecommerce.Service.UserService;
import com.app.Ecommerce.userDTO.UserResponse;
import com.app.Ecommerce.userDTO.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
  private UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }

  //@GetMapping("/api/users")
    @RequestMapping(value="/api/users", method = RequestMethod.GET)
  public ResponseEntity<List<UserResponse>> getUsers() {
    return ResponseEntity.ok(userService.fetchUsers());
  }

   @RequestMapping(value="/api/users", method = RequestMethod.POST)
  public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
   userService.addUsers(userRequest);
   return ResponseEntity.ok("User has been created");

  }
  @RequestMapping(value="/api/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<UserResponse>> getUser(@PathVariable Long id) {
Optional<UserResponse> user=userService.fetchUser(id);
if(user==null) {
  return ResponseEntity.notFound().build();
}
    return ResponseEntity.ok(user);

}
    @PutMapping ("/api/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody UserRequest updateUserRequest, @PathVariable Long id) {
        boolean updated=userService.updateUser(id,updateUserRequest);
        if(updated) {
            return ResponseEntity.ok("User has been updated");

        }
        return ResponseEntity.notFound().build();


    }

}