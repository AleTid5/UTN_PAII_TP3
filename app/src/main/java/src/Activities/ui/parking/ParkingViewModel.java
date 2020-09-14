package src.Activities.ui.parking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import src.Exceptions.CarRegistrationException;
import src.Exceptions.TimeException;
import src.Models.Parking;
import src.Services.ParkingService;

public class ParkingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ParkingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    protected void saveParking(String carRegistration, Integer time) throws TimeException, CarRegistrationException {
        ParkingService.saveParking(new Parking(carRegistration, time));
    }
}