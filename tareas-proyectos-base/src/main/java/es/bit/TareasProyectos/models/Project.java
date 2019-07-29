package es.bit.TareasProyectos.models;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Project {
	private long pid;

    @NonNull
    private String title;

    private Date initDate;
    
    private Date endDate;
    
    private User responsible;
        
}
