package com.jorge_alan.spring_git_mvc.apis.Controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiGitSpring")
public class LoginApiController {

    @PostMapping("/login")
    @Async("executorAsync")
    public CompletableFuture<ResponseEntity<String>> Login() {
        return CompletableFuture.completedFuture(ResponseEntity.ok("hola"));
    }

}
