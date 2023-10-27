package com.banana.tweeterApp.internal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {
    private Long id;
    @NotNull
    @Size(min=1, max=140)
    private String texto;
    private Date fecha;

    @NotNull
    private User autor;

    public Tweet(String texto, User autor){
        this.texto=texto;
        this.autor=autor;
        this.fecha=new Date();
    }


}
