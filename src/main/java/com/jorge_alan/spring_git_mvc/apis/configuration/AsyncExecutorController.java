package com.jorge_alan.spring_git_mvc.apis.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ssl.pem.PemContent;

@EnableAsync
@Configuration
public class AsyncExecutorController {

    @Bean("executorAsync")
    public Executor asyncExecutorController() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setQueueCapacity(20);
        executor.setMaxPoolSize(5);
        executor.setThreadNamePrefix("async-executor-controller");
        executor.initialize();
        return executor;
    }

}

@Configuration
@RequiredArgsConstructor
class InnerAsyncExecutorController {

    @Value("${spring.ssl.bundle.pem.server.keystore.private-key}")
    private String keyPath;

    @Value("{spring.ssl.bundle.pem.server.keystore.password}")
    private String passPath;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> 
                auth.requestMatchers("/apiGitSpring/login")
                .permitAll()
                .anyRequest()
                .authenticated())
            .csrf(csrf -> 
                csrf.disable());
        return http.build();
    }
    
    @Bean
    public PrivateKey ContenidoKey() throws IOException {
        File keyFile = new File(keyPath);
        PrivateKey privateKey = PemContent.load(keyFile.toPath()).getPrivateKey(passPath);
        return privateKey;
    }
}


@Component
class FiltroPeticion extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        filterChain.doFilter(request, response);
    }

}
