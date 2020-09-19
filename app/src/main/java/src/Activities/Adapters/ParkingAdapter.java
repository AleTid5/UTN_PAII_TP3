package src.Activities.Adapters;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp3_pa_grupo_3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import src.Activities.ui.parking.ParkingViewModel;
import src.Exceptions.TimeException;
import src.Models.Parking;
import src.Protocols.TimeProtocol;

public class ParkingAdapter extends BaseAdapter {
    ParkingViewModel parkingViewModel;
    private List<Parking> elements;

    public ParkingAdapter(ParkingViewModel parkingViewModel, List<Parking> elements) {
        this.elements = elements;
        this.parkingViewModel = parkingViewModel;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Parking getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View newView = view;

        if (view == null){
            newView = inflater.inflate(R.layout.home_grid_layout,null);
        }

        TextView carRegistration = newView.findViewById(R.id.grid_car_registration);
        TextView parkingTime = newView.findViewById(R.id.grid_parking_time);
        FloatingActionButton fab_remove_registration = newView.findViewById(R.id.btn_remove_registration);

        fab_remove_registration.setOnClickListener(v -> {
            String strCarRegistration = getItem(i).getCarRegistration();
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(String.format("¿Quitar el parqueo de %s?", strCarRegistration));
            builder.setMessage(String.format("Realmente desea quitar el parqueo de %s", strCarRegistration));
            builder.setPositiveButton(R.string.accept, (dialog, which) -> parkingViewModel.removeParking(getItem(i).getCarRegistration()));
            builder.setNegativeButton(R.string.cancel, null);

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        carRegistration.setText(getItem(i).getCarRegistration());

        try {
            parkingTime.setText(TimeProtocol.getParsedTime(getItem(i).getTime()));
        } catch (TimeException e) {
            parkingTime.setText(R.string.unavailable_time);
        }

        return newView;
    }
}
