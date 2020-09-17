package src.Protocols;

import java.util.regex.Pattern;

import src.Exceptions.EmailException;

public abstract class EmailProtocol {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validate(String email) throws EmailException {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
            throw new EmailException("Debe ingresar un E-Mail válido");
        }
    }
}
