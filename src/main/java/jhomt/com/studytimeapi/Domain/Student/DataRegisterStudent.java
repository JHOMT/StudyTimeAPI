package jhomt.com.studytimeapi.Domain.Student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterStudent(
        @NotNull(message = "El nombre no puede ser nulo")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String name,

        @NotNull(message = "El correo electrónico no puede ser nulo")
        @Email(message = "El correo electrónico debe tener un formato válido")
        @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres")
        String email,

        @NotNull(message = "La contraseña no puede ser nula")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password
) {
}
