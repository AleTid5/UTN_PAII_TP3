package src.Models;

import android.content.ContentValues;

import src.Database.Tables.ParkingTable;
import src.Exceptions.CarRegistrationException;
import src.Exceptions.TimeException;
import src.Interfaces.Entity;
import src.Protocols.CarRegistrationProtocol;
import src.Services.SessionService;

public class Parking implements Entity {
    private String carRegistration;
    private Integer time; // In minutes

    public Parking() {}

    public Parking(String carRegistration, Integer time) throws TimeException, CarRegistrationException {
        this.setCarRegistration(carRegistration);
        this.setTime(time);
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public Parking setCarRegistration(String carRegistration) throws CarRegistrationException {
        carRegistration = carRegistration.toUpperCase();
        CarRegistrationProtocol.validateCarRegistration(carRegistration);

        this.carRegistration = carRegistration;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public Parking setTime(Integer time) throws TimeException {
        if (time <= 0) {
            throw new TimeException("Solo podemos guardar valores positivos 😔");
        }

        this.time = time;
        return this;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(ParkingTable.Entry.USER_ID, SessionService.getUserId());
        values.put(ParkingTable.Entry.CAR_REGISTER, this.carRegistration);
        values.put(ParkingTable.Entry.TIME, this.time);

        return values;
    }
}
