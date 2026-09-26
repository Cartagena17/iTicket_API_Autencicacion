package iTicket.Autenticacion.auth.controller;

import iTicket.Autenticacion.auth.dto.LoginRequestDTO;
import iTicket.Autenticacion.auth.dto.LoginResponseDTO;
import iTicket.Autenticacion.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        LoginResponseDTO respuesta = authService.login(dto);
        return ResponseEntity.ok(respuesta);
    }
}
