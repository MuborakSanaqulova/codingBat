package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.payload.UserDto;
import uz.pdp.vazifa2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);

    }

    public ApiResponse addUser(UserDto userDto) {

        User user = new User();
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return new ApiResponse("SAVED", true);
    }

    public ApiResponse editUser(UserDto userDto, String id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);

        User user = new User();
        user.setEmail(id);
        user.setPassword(userDto.getPassword());
        userRepository.save(user);

        return new ApiResponse("user edited", true);

    }

    public ApiResponse deleteUser(String id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);

        userRepository.deleteById(id);
        return new ApiResponse("user deleted", true);

    }

}
