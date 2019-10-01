package es.bit.tareasproyectoshex.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long uid;
    private List<Task> tareas;

    @NonNull
    private String name;

    private String email;
    
    private String password;

    public void addTarea(Task tarea){
        if(tareas==null){
            tareas= new ArrayList<Task>();
        }

        tareas.add(tarea);
    }
}
