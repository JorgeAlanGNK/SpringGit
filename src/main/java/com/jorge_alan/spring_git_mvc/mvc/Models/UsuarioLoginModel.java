package com.jorge_alan.spring_git_mvc.mvc.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLoginModel {
    
    private String Username;
    private String Password;
}
