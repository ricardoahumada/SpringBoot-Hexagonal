package es.bit.tweeterApp.api.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String mensaje;
}
