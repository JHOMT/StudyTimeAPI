package jhomt.com.studytimeapi.Infra.Errors;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> treatError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> treatError404(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity<?> errorHandlerValidationsIntegrity(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> errorHandlerBusinessValidations(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointerExeption(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    private record DataErrorValidation(String campo, String error){
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
