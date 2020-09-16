package src.Activities.ui.parking;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import src.Exceptions.CarRegistrationException;
import src.Exceptions.TimeException;
import src.Models.Parking;
import src.Services.ParkingService;

public class ParkingViewModel extends ViewModel {

    private MutableLiveData<List<Parking>> liveParkingList = new MutableLiveData<>();

    public ParkingViewModel() {
        liveParkingList.setValue(ParkingService.getParkingList());
    }

    public LiveData<List<Parking>> getParkingList() {
        return liveParkingList;
    }

    public void saveParking(String carRegistration, Integer time) throws TimeException, CarRegistrationException {
        Parking parking = new Parking(carRegistration, time);
        ParkingService.saveParking(parking);
        Objects.requireNonNull(liveParkingList.getValue()).add(parking);
        liveParkingList.postValue(liveParkingList.getValue());
    }

    public void removeParking(String carRegistration) {
        try {
            Parking parking = new Parking().setCarRegistration(carRegistration);
            ParkingService.removeParking(parking);
            List<Parking> parkingList = Objects.requireNonNull(liveParkingList.getValue())
                    .stream()
                    .filter(p -> !p.getCarRegistration().equals(parking.getCarRegistration()))
                    .collect(Collectors.toList());
            liveParkingList.postValue(parkingList);
        } catch (Exception ignored) {}
    }
}