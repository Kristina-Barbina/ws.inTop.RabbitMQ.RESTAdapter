package ws.inTop.RabbitMQ.RESTAdapter.controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConfigurationProperties(prefix = "adapter-credentials")
@Data
public class BasicAuthWebSecurityConfiguration
{

    @Setter(AccessLevel.PACKAGE)
    private String UserName;

    @Setter(AccessLevel.PACKAGE)
    private String UserPassword;

/*
    @Autowired
    private PasswordEncoder passwordEncoder;
*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().sessionManagement().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User
                .withUsername(UserName)
                .password(new BCryptPasswordEncoder().encode(UserPassword))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}