package jhomt.com.studytimeapi.Infra.Errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s) {
        super(s);
    }
}
