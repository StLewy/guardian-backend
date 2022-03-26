package com.sl.guardianbackend.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sl.guardianbackend.security.config.JwtConfig;
import com.sl.guardianbackend.security.model.AppUser;
import com.sl.guardianbackend.security.model.Role;
import com.sl.guardianbackend.security.repository.AppUserRepository;
import com.sl.guardianbackend.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@Service
@RequiredArgsConstructor
@Transactional
public class AppUsersServiceImpl implements AppUsersService, UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findAppUserByUsername(username);
        if (user == null) {

            throw new UsernameNotFoundException("User not found in the db");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public void deleteAppUser(String username) {
        appUserRepository.deleteAppUserByUsername(username);
    }

    @Override
    public String[] getRoleFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String[] roles = new String[0];
        if (authorizationHeader != null && authorizationHeader.startsWith(JwtConfig.tokenPrefix)) {
            String token = authorizationHeader.substring(JwtConfig.tokenPrefix.length());
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.secretKey.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            roles = decodedJWT.getClaim("roles").asArray(String.class);
        }
        return roles;
    }

    @Override
    public String getUsernameFromRequest(HttpServletRequest request) {
        String username = "";
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(JwtConfig.tokenPrefix)) {
            String token = authorizationHeader.substring(JwtConfig.tokenPrefix.length());
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.secretKey.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            username = decodedJWT.getSubject();
        }
        return username;
    }
}
