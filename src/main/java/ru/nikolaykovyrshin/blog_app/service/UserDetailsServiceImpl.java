package ru.nikolaykovyrshin.blog_app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikolaykovyrshin.blog_app.data.MyUserDetails;
import ru.nikolaykovyrshin.blog_app.entity.sec.User;
import ru.nikolaykovyrshin.blog_app.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        logger.info("Get user: " + user.getUsername() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getRoles());


        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }

}
