package es.bit.TareasProyectos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long uid;

    @NonNull
    private String name;

    private String email;
    
    private String password;
}
