package pl.swsdn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
class SecurityProvider {

    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    boolean isSecured(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authenticationProvider.authenticate(authorization)) {
            return true;
        }
        return false;
    }
}
