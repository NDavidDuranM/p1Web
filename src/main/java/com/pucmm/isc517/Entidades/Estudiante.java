package com.pucmm.isc517.Entidades;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Estudiante {

    private int matricula;
    private String nombre;
    private String apellido;
    private String telefono;

}
