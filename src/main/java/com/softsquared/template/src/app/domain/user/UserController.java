package com.softsquared.template.src.app.domain.user;

import com.softsquared.template.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/user")
    public BaseResponse<UserDTO.createRes> createUser(@RequestBody UserDTO.createReq request){

        return new BaseResponse<>(userService.createUser(request));
    }
}
