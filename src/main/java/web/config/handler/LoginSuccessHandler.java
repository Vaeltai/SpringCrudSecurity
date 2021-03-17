package web.config.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

//    @Autowired
//    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
//        if(authentication.getAuthorities().contains(new Role(1L, "ADMIN"))){
//            httpServletResponse.sendRedirect("/profile/1");
//        } else {
//            httpServletResponse.sendRedirect("/profile/" +
//                    ((User) userService.loadUserByUsername(authentication.getName())).getId());
//        }
        httpServletResponse.sendRedirect("/users");
    }
}