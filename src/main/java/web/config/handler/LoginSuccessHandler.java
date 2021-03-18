package web.config.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.List;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles =  AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/");
        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user/" +
                    ((User) userService.loadUserByUsername(authentication.getName())).getId());
        }
    }

}
//        if(authentication.getAuthorities().contains(new Role("ADMIN"))){
//            httpServletResponse.sendRedirect("/users");
//        } else if(authentication.getAuthorities().contains(new Role("USER"))){
//            httpServletResponse.sendRedirect("/profile/" +
//                    ((User) userService.loadUserByUsername(authentication.getName())).getId());
//        }
//        httpServletResponse.sendRedirect("/users");

    /*@Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        Long id = userService
                .findUserByName(((UserDetails) authentication.getPrincipal()).getUsername()).get().getId();

        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user/" + id);
        }
    }*/
