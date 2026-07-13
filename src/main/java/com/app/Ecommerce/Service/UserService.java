package com.app.Ecommerce.Service;
import com.app.Ecommerce.Model.User;
import com.app.Ecommerce.Repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final List<User> userList =new ArrayList<>();
    private final userRepository userRepository;

    public List<User> fetchUsers()
    {
        return userRepository.findAll();
    }
    public void addUsers(User users)
    {
        userRepository.save(users);
    }

    public User fetchUser(Long id) {
        return userRepository.findById(id).orElse(null);

    }

    public boolean updateUser(Long id,User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser->{
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
