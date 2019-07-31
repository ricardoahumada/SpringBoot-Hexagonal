package es.bit.tareasproyectoshex.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "usuario")

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;


    @Column(name = "nombre")
    private String name;

    private String email;

    @Column(name = "pass")
    private String password;
}
