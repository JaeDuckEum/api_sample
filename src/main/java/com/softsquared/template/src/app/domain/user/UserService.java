package com.softsquared.template.src.app.domain.user;

import com.softsquared.template.config.BaseException;
import com.softsquared.template.config.BaseResponseStatus;
import com.softsquared.template.utils.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDTO.createRes createUser(UserDTO.createReq request){
        System.out.println("password: "+request.getPassword());
        User user = userRepository.save(request.buildUser(passwordEncoder));
        return new UserDTO.createRes(user.getId(),user.getEmail());
    }

    public UserDTO.loginRes loginUser(UserDTO.loginReq request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REQUEST_ERROR));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN);
        }
        return new UserDTO.loginRes(user.getId(), user.getNickname(),JwtManager.createJwt(String.valueOf(user.getId())));
    }

    public UserDTO.getUserInfoRes getUserInfo(){
        User user = userRepository.findById(Long.valueOf(JwtManager.getUserId()))
                .orElseThrow(() -> new BaseException(BaseResponseStatus.REQUEST_ERROR));
        return new UserDTO.getUserInfoRes(user.getId(), user.getNickname());
    }

}
