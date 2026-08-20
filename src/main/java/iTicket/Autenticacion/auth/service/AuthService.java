package iTicket.Autenticacion.auth.service;

import iTicket.Autenticacion.auth.dto.LoginRequestDTO;
import iTicket.Autenticacion.auth.dto.LoginResponseDTO;
import iTicket.Autenticacion.auth.entity.UsuariosAuthEntity;
import iTicket.Autenticacion.auth.repository.UsuariosAuthRepository;
import iTicket.Autenticacion.utils.PasswordUtil;
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
            log.warn("Intento de login con correo no registrado: " + dto.getCorreo());
            return null;
        }

        UsuariosAuthEntity usuario = usuarioOpcional.get();

        if (!"T".equalsIgnoreCase(usuario.getEstado())) {
            log.warn("Intento de login de usuario inactivo: " + dto.getCorreo());
            return null;
        }

        if (!passwordUtil.coincidence(dto.getClave(), usuario.getClave())) {
            log.warn("Contraseña incorrecta para: " + dto.getCorreo());
            return null;
        }

        log.info("Login exitoso: " + dto.getCorreo());
        return new LoginResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getCorreo(),
                usuario.getIdRol()
        );}
}
