package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class E {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;
}
