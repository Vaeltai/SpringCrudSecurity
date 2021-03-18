package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;
import web.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(new LoginSuccessHandler())//указываем логику обработки при логине
                .loginProcessingUrl("/login")// указываем action с формы логина
                .usernameParameter("j_username")// Указываем параметры логина и пароля с формы логина
                .passwordParameter("j_password")
                .permitAll();// даем доступ к форме логина всем

        http.logout()
                .permitAll() // разрешаем делать логаут всем
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // указываем URL логаута
                .logoutSuccessUrl("/") // указываем URL при удачном логауте
                .and().csrf().disable(); //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)

        http
                .authorizeRequests() // делаем страницу регистрации недоступной для авторизированных пользователей
                .antMatchers("/login").anonymous()  //страницы аутентификаци доступна всем
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin").access("hasAnyRole('ADMIN')").anyRequest().authenticated();// защищенные URL
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}