package com.sunny.blog.service;

import com.sunny.blog.domain.User;
import com.sunny.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        this.userRepository.save(user);
        return user;
    }

    @Transactional
    public void encryptPassword(String password){
        User user = new User();
        String enPw = passwordEncoder.encode(password);
        user.setPassword(enPw);
        userRepository.save(user);
    }
}
