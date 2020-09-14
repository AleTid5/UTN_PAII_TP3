package src.Models;

import src.Exceptions.CarRegistrationException;
import src.Exceptions.TimeException;
import src.Protocols.CarRegistrationProtocol;

public class Parking {
    private String carRegistration;
    private Integer time; // In minutes

    public Parking(String carRegistration, Integer time) throws TimeException, CarRegistrationException {
        this.setCarRegistration(carRegistration);
        this.setTime(time);
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) throws CarRegistrationException {
        carRegistration = carRegistration.toUpperCase();
        CarRegistrationProtocol.validateCarRegistration(carRegistration);

        this.carRegistration = carRegistration;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) throws TimeException {
        if (time <= 0) {
            throw new TimeException("Solo podemos guardar valores positivos 😔");
        }

        this.time = time;
    }
}
