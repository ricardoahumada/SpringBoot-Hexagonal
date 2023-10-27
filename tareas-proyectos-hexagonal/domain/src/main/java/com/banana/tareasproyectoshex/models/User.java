package com.banana.tareasproyectoshex.models;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long uid;

    private String name;

    private String email;

    private String password;
}
