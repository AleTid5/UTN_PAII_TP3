package src.Protocols;

import src.Exceptions.TimeException;

public abstract class TimeProtocol {
    public static String getParsedTime(Integer minutes) throws TimeException {
        if (minutes == 0) {
            throw new TimeException(null);
        }

        int hours = minutes / 60;
        minutes %= 60;
        String minuteStr = minutes == 1 ? "minuto" : "minutos";

        if (hours > 0) {
            String hourStr = hours == 1 ? "hora" : "horas";

            return minutes > 0 ? String.format("%s %s y %s %s", hours, hourStr, minutes, minuteStr) : String.format("%s %s", hours, hourStr);
        }

        return String.format("%s %s", minutes, minuteStr);
    }
}
