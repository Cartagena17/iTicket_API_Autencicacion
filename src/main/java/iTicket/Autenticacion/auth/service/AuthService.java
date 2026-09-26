package iTicket.Autenticacion.auth.service;

import iTicket.Autenticacion.auth.dto.LoginRequestDTO;
import iTicket.Autenticacion.auth.dto.LoginResponseDTO;
import iTicket.Autenticacion.auth.entity.UsuariosAuthEntity;
import iTicket.Autenticacion.auth.repository.UsuariosAuthRepository;
import iTicket.Autenticacion.utils.PasswordUtil;
import iTicket.Autenticacion.exception.OperacionInvalidaException;
import iTicket.Autenticacion.exception.RecursoNoEncontradoException;
import iTicket.Autenticacion.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuariosAuthRepository repo;
    private final PasswordUtil passwordUtil;

    public LoginResponseDTO login(LoginRequestDTO dto) {
        Optional<UsuariosAuthEntity> usuarioOpcional = repo.findByCorreo(dto.getCorreo());

        if (usuarioOpcional.isEmpty()) {
            throw new RecursoNoEncontradoException(ErrorCode.WAUT003, "Correo no registrado: " + dto.getCorreo());
            
        }

        UsuariosAuthEntity usuario = usuarioOpcional.get();

        if (!"T".equalsIgnoreCase(usuario.getEstado())) {
            throw new OperacionInvalidaException(ErrorCode.WAUT002, "Usuario inactivo: " + dto.getCorreo());
            
        }

        if (!passwordUtil.coincidence(dto.getClave(), usuario.getClave())) {
            throw new OperacionInvalidaException(ErrorCode.WAUT001, "Contraseña incorrecta");
            
        }

        log.info("Login exitoso: " + dto.getCorreo());
        return new LoginResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getCorreo(),
                usuario.getIdRol()
        );}
}
