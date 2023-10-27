package com.banana.internal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public User(String nombre, String email, String password){
        this.nombre=nombre;
        this.email=email;
        this.password=password;
    }

}
