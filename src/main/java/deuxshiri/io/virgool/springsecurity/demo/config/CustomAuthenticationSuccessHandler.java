package deuxshiri.io.virgool.springsecurity.demo.config;

import deuxshiri.io.virgool.springsecurity.demo.entity.User;
import deuxshiri.io.virgool.springsecurity.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  @Autowired
  private UserService service;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                      Authentication authentication) throws IOException, ServletException {
    System.out.println("\n\n customAuthenticationSuccessHandler\n\n");

    String username = authentication.getName();
    System.out.println("username: " + username);

    User user = service.findByUsername(username);

    // place in the session
    HttpSession session = httpServletRequest.getSession();
    session.setAttribute("user", user);

    // forward to home page
    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
  }
}
