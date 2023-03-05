package sccul.com.sccul.security;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.utils.CustomResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<String>> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return new ResponseEntity<>(new CustomResponse<>(jwtService.generateToken(authRequest.getUsername()), false, 200, "Usuario autenticado"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CustomResponse<>("", true, 401, "Usuario no autenticado"), HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return new ResponseEntity<>(new CustomResponse<>("", true, 401, "Nombre de usuario o contraseña incorrectos"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>("", true, 500, "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
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
