package com.vedruna.watchlist.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DNIValidator implements ConstraintValidator<DNI, String> {

	private static final String NIE_REGEX = "^\\d{8}[A-Z]$";
    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public void initialize(DNI constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Compilamos la expresión regular en un patrón
        Pattern pattern = Pattern.compile(NIE_REGEX);

        // Verificamos si la cadena coincide con el patrón
        boolean matches = pattern.matcher(value).matches();

        if (matches) {
            try {
                int number = Integer.parseInt(value.substring(0, 8));
                char character = value.charAt(8);
    
                int rm = number % 23;
                char dniChar = LETRAS.charAt(rm);
    
                return character == dniChar;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}