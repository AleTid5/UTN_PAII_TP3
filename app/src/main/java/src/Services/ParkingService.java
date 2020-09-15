package src.Services;

import java.util.ArrayList;
import java.util.List;

import src.Exceptions.CarRegistrationException;
import src.Exceptions.TimeException;
import src.Models.Parking;

public abstract class ParkingService {
    public static List<Parking> getParkingList() throws TimeException, CarRegistrationException {
        return new ArrayList<Parking>() {{
            add(new Parking("AB123CD", 35));
            add(new Parking("B123ACD", 123));
            add(new Parking("856FRE", 67));
            add(new Parking("KDE843", 60));
        }};
    }

    public static void saveParking(Parking parking) {

    }
}
