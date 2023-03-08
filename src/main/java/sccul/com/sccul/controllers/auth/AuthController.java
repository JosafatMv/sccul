package sccul.com.sccul.controllers.auth;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.auth.dtos.AuthRegisterDto;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.security.AuthRequest;
import sccul.com.sccul.security.JwtService;
import sccul.com.sccul.services.auth.AuthService;
import sccul.com.sccul.utils.CustomResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/web/login")
    public ResponseEntity<CustomResponse<String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String role = authentication.getAuthorities().toArray()[0].toString();

                if (role.equals("admin")) {
                    return new ResponseEntity<>(new CustomResponse<>(jwtService.generateToken(authRequest.getUsername()), false, 200, "Usuario autenticado"), HttpStatus.OK);
                }

                return new ResponseEntity<>(new CustomResponse<>(null, true, 401, "Solo administradores pueden iniciar sesión"), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(new CustomResponse<>("", true, 401, "Usuario no autenticado"), HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return new ResponseEntity<>(new CustomResponse<>("", true, 401, "Nombre de usuario o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>("", true, 500, "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/mobile/login")
    public ResponseEntity<CustomResponse<String>> authenticateAndGetTokenMobile(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                String role = authentication.getAuthorities().toArray()[0].toString();

                if (role.equals("user")) {
                    return new ResponseEntity<>(new CustomResponse<>(jwtService.generateToken(authRequest.getUsername()), false, 200, "Usuario autenticado"), HttpStatus.OK);
                }

                return new ResponseEntity<>(new CustomResponse<>(null, true, 401, "Solo usuarios pueden iniciar sesión"), HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(new CustomResponse<>("", true, 401, "Usuario no autenticado"), HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return new ResponseEntity<>(new CustomResponse<>("", true, 401, "Nombre de usuario o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>("", true, 500, "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<User>> register(@Valid @RequestBody AuthRegisterDto userRegister) {
        try {
            return new ResponseEntity<>(this.authService.register(userRegister.castToUser()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>(null, true, 500, "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/renew")
    public ResponseEntity<CustomResponse<String>> renewToken(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(this.jwtService.renewToken(token), HttpStatus.OK);
        } catch (JwtException e) {
            return new ResponseEntity<>(new CustomResponse<>(null, true, 401, "El token ha expirado"), HttpStatus.UNAUTHORIZED);
        }
    }

}
