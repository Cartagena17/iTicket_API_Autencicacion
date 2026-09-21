package iTicket.Autenticacion.utils;

public enum ErrorCode {
    // Errores Globales
    WGLB001, // Peticion malformada / Datos invalidos (@Valid)
    WGLB002, // Error de Integridad en Base de Datos
    WGLB404, // Recurso no encontrado generico (Rutas inexistentes)
    WGLB409, // Conflicto / Recurso duplicado generico
    WGLB500, // Error interno del servidor

    // Errores de Autenticacion
    WAUT001, // Credenciales invalidas (contrasena incorrecta)
    WAUT002, // Usuario inactivo
    WAUT003  // Usuario no encontrado / Correo no registrado
}