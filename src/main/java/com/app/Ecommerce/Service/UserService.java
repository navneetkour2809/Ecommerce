package com.app.Ecommerce.Service;
import com.app.Ecommerce.Model.Address;
import com.app.Ecommerce.Model.User;
import com.app.Ecommerce.Repository.userRepository;
import com.app.Ecommerce.userDTO.AddressDTO;
import com.app.Ecommerce.userDTO.UserRequest;
import com.app.Ecommerce.userDTO.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final List<User> userList =new ArrayList<>();
    private final userRepository userRepository;

    public List<UserResponse> fetchUsers()
    {

        return userRepository.findAll().stream()
                .map(this::maptoUserResponse)
                .collect(Collectors.toList());
    }
    public void addUsers(UserRequest userRequest)
    {
        User user = new User();
        UpdateUserFromRequest(user,userRequest);
        userRepository.save(user);

    }

    private void UpdateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddress()!=null)
        {
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            address.setZip(userRequest.getAddress().getZip());
            user.setAddress(address);

        }

    }

    public Optional<UserResponse> fetchUser(Long id) {
        return userRepository.findById(id)
                .map(this::maptoUserResponse);
    }

    public boolean updateUser(Long id,UserRequest updatedUserRequest) {
        return userRepository.findById(id)
                .map(existingUser->{
                    UpdateUserFromRequest(existingUser,updatedUserRequest);
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
    private UserResponse maptoUserResponse(User user) {
        UserResponse Response = new UserResponse();
        Response.setId(Long.valueOf(String.valueOf(user.getId())));
        Response.setFirstName(user.getFirstName());
        Response.setLastName(user.getLastName());
        Response.setEmail(user.getEmail());
        Response.setPhone(user.getPhone());
        Response.setRole(user.getRole());

        if(user.getAddress()!=null){
            AddressDTO AddressDTO = new AddressDTO();
            AddressDTO.setStreet(user.getAddress().getStreet());
            AddressDTO.setCity(user.getAddress().getCity());
            AddressDTO.setState(user.getAddress().getState());
            AddressDTO.setCountry(user.getAddress().getCountry());
            AddressDTO.setZip(user.getAddress().getZip());
            Response.setAddress(AddressDTO);
        }

return Response;
    }
}
