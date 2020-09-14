package src.Protocols;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import src.Exceptions.CarRegistrationException;

public abstract class CarRegistrationProtocol {
    private static final List<Pattern> validators = Arrays.asList(
            Pattern.compile("[a-zA-Z]{3}[\\d]{3}"), // ABC123 - Autos viejos
            Pattern.compile("[a-zA-Z]{2}[\\d]{3}[a-zA-Z]{2}"), // AB123CD - Autos nuevo
            Pattern.compile("[\\d]{3}[a-zA-Z]{3}"), // A123BCD - Motos viejas
            Pattern.compile("[a-zA-Z][\\d]{3}[a-zA-Z]{3}") // A123BCD - - Motos nuevas
    );

    public static void validateCarRegistration(final String carRegistration) throws CarRegistrationException {
        Long checks = validators.stream().filter(validator -> validator.matcher(carRegistration).find()).count();

        if (checks.equals(0L)) {
            throw new CarRegistrationException(String.format("La patente '%s' es inválida", carRegistration));
        }

    }
}
