package pl.swsdn.security;

public interface AuthenticationProvider {

    boolean authenticate(String authentication);

}
