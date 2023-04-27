package ma.emsi.patientmvc.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder.encode("123")).roles("USERS").build(),
                User.withUsername("moamed").password(passwordEncoder.encode("123")).roles("USERS").build(),
                User.withUsername("saas").password(passwordEncoder.encode("123")).roles("USERS").build()
        );
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.formLogin();
    httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
    httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
    httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
    httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
    httpSecurity.formLogin().loginPage("/login").permitAll();
    httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
        return httpSecurity.build();
    }
}
