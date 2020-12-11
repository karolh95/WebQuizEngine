package engine.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers(HttpMethod.GET, "/api/quizzes").permitAll()
                .antMatchers("/api/quizzes").authenticated()
                .antMatchers("/api/quizzes/{id:[0-9]+}/solve").authenticated()
                .antMatchers(HttpMethod.POST, "/actuator/shutdown").permitAll()
                .anyRequest().authenticated()
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
