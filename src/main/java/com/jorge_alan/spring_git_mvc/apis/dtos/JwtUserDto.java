package com.jorge_alan.spring_git_mvc.apis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JwtUserDto {
    
    private String jwt;
}
