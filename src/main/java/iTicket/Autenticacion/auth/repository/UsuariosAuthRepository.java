package iTicket.Autenticacion.auth.repository;

import iTicket.Autenticacion.auth.entity.UsuariosAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosAuthRepository extends JpaRepository <UsuariosAuthEntity, Long>{

    Optional<UsuariosAuthEntity> findByCorreo(String correo);

}
