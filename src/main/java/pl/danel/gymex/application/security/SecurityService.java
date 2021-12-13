package pl.danel.gymex.application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.security.command.SignInCommand;
import pl.danel.gymex.config.security.JwtTokenUtil;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public String authenticate(SignInCommand command) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(command.getUsername());
        return tokenUtil.generateToken(userDetails);
    }

}
