package com.app.Ecommerce.Controller;

import com.app.Ecommerce.Model.User;
import com.app.Ecommerce.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
  private UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }

  //@GetMapping("/api/users")
    @RequestMapping(value="/api/users", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getUsers() {

    return ResponseEntity.ok(userService.fetchUsers());
  }

   @RequestMapping(value="/api/users", method = RequestMethod.POST)
  public ResponseEntity<String> createUser(@RequestBody User user) {
   userService.addUsers(user);
   return ResponseEntity.ok("User has been created");

  }
  @RequestMapping(value="/api/users/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> getUser(@PathVariable Long id) {
User user=userService.fetchUser(id);
if(user==null) {
  return ResponseEntity.notFound().build();
}
    return ResponseEntity.ok(user);

}
    @PutMapping ("/api/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User updateUser, @PathVariable Long id) {
        boolean updated=userService.updateUser(id,updateUser);
        if(updated) {
            return ResponseEntity.ok("User has been updated");

        }
        return ResponseEntity.notFound().build();


    }

}