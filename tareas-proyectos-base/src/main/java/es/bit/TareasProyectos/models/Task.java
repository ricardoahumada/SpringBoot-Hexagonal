package es.bit.TareasProyectos.models;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Task {
	private long tid;

    @NonNull
    private String description;

    private Date initDate;
    
    private int duration;
    
    private User responsible;
    
    private int state;
    
    private Project project;

	
}
