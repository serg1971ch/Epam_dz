package by.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//@Service
public class SecurityServiceImpl {}
//        implements SecurityService {
//
//    private AuthenticationManager authenticationManager;
//
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    public void autoLogin(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//    }
//}
