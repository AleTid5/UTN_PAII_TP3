package src.Protocols;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import src.Exceptions.CarRegistrationException;

public abstract class CarRegistrationProtocol {
    private static class Validator {
        public final Integer length;
        public final Pattern pattern;

        public Validator(Integer length, Pattern pattern) {
            this.length = length;
            this.pattern = pattern;
        }
    }

    private static final List<Validator> validators = Arrays.asList(
            new Validator(6, Pattern.compile("[a-zA-Z]{3}[\\d]{3}")),            // ABC123  - Autos viejos
            new Validator(7, Pattern.compile("[a-zA-Z]{2}[\\d]{3}[a-zA-Z]{2}")), // AB123CD - Autos nuevos
            new Validator(6, Pattern.compile("[\\d]{3}[a-zA-Z]{3}")),            // 123BCD  - Motos viejas
            new Validator(7, Pattern.compile("[a-zA-Z][\\d]{3}[a-zA-Z]{3}"))     // A123BCD - Motos nuevas
    );

    public static void validateCarRegistration(final String carRegistration) throws CarRegistrationException {
        Long checks = validators.stream()
                .filter(validator -> validator.length.equals(carRegistration.length())) // Filtra los validadores que no sean necesarios
                .filter(validator -> validator.pattern.matcher(carRegistration.substring(0, validator.length)).find()).count(); // Cuenta cuantas validaciones superó

        if (checks.equals(0L)) {
            throw new CarRegistrationException(String.format("La patente '%s' es inválida 😅", carRegistration));
        }
    }
}
