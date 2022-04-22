package com.softsquared.template.src.app.domain.user;

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
}
