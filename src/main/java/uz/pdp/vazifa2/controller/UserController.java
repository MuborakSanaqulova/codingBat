package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.common.ApiResponse;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.payload.UserDto;
import uz.pdp.vazifa2.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getLanguages() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");

        return ResponseEntity.status(200).body(user);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);

        if (apiResponse.getSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editUser(@Valid @RequestBody UserDto userDto, @PathVariable String id) {
        ApiResponse apiResponse = userService.editUser(userDto, id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
        ApiResponse apiResponse = userService.deleteUser(id);
        if (apiResponse.getSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

}
