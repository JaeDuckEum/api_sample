package com.softsquared.template.src.app.domain.user;

import com.softsquared.template.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "")
    public BaseResponse<UserDTO.createRes> createUser(@RequestBody UserDTO.createReq request){

        return new BaseResponse<>(userService.createUser(request));
    }

    @PostMapping(value = "/login")
    public BaseResponse<UserDTO.loginRes> loginUser(@RequestBody UserDTO.loginReq request){

        return new BaseResponse<>(userService.loginUser(request));
    }

    @GetMapping(value="/info")
    public BaseResponse<UserDTO.getUserInfoRes> getUserInfo(){
        return new BaseResponse<>(userService.getUserInfo());
    }
}
