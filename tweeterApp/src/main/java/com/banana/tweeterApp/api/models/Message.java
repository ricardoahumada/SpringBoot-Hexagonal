package com.banana.tweeterApp.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String mensaje;
}
