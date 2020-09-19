package src.Activities.ui.account;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Parking;
import src.Protocols.TimeProtocol;
import src.Services.ParkingService;
import src.Services.SessionService;

public class AccountViewModel extends ViewModel {

    public void fillUserInfo(TextView name, TextView email, TextView totalParkings, TextView totalTime) {
        List<Parking> parkingList = ParkingService.getParkingList();
        name.setText(SessionService.getUser().getName());
        email.setText(SessionService.getUser().getEmail());
        totalParkings.setText(Integer.toString(parkingList.size()));

        try {
            totalTime.setText(
                    TimeProtocol.getParsedTime(parkingList.stream().map(parking -> parking.getTime()).reduce(0, Integer::sum))
            );
        } catch (Exception ignored) {}
    }
}