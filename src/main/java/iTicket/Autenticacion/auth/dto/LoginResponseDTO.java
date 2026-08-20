package iTicket.Autenticacion.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String correo;
    private Long idRol;
}
