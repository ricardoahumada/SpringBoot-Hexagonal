package es.bit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetDto {
    private Long id;
    private String texto;
    private Date fecha;
    private Long autor;
}
