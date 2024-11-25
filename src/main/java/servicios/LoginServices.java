package servicios;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginServices {
    Optional<String>getUsername(HttpServletRequest request);

}
