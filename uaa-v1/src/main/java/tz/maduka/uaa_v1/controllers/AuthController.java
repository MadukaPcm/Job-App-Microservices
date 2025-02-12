package tz.maduka.uaa_v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.maduka.uaa_v1.config.SecurityOAuth.TokenGenerator;
import tz.maduka.uaa_v1.dtos.payload.LoginDto;
import tz.maduka.uaa_v1.dtos.payload.RegisterDto;
import tz.maduka.uaa_v1.models.Token;
import tz.maduka.uaa_v1.models.User;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    UserDetailsManager userDetailsManager;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    @Qualifier("jwtRefreshTokenAuthProvider")
    JwtAuthenticationProvider refreshTokenAuthProvider;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        System.out.println("USERNAME -> "+registerDto.getUsername());
        if (userDetailsManager.userExists(registerDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User user = new User(registerDto.getUsername(), registerDto.getPassword());
        userDetailsManager.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginDto loginDTO) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("token")
    public ResponseEntity token(@RequestBody Token tokenDTO) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
        Jwt jwt = (Jwt) authentication.getCredentials();
        // check if present in db and not revoked, etc

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }
}