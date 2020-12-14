package com.example.dashboardspring.service;

import com.example.dashboardspring.db.UserRepository;
import com.example.dashboardspring.entity.User;
import com.example.dashboardspring.objects.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean userExists(String email) {
        return findUserByEmail(email).isPresent();
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public User register(UserDTO userDTO) {
        //password encryption
        String password = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(password);

        User user = new User();
        //enable the new user
        user.setEnabled(true);
        //map userDTO to user
        modelMapper.map(userDTO, user);
        return save(user);
    }

}
