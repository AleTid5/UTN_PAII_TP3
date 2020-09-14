package src.Activities.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp3_pa_grupo_3.R;

import java.util.List;

import src.Exceptions.TimeException;
import src.Models.Parking;
import src.Protocols.TimeProtocol;

public class ParkingAdapter extends BaseAdapter {
    private List<Parking> elements;
    private Context context;

    public ParkingAdapter(Context context, List<Parking> elements) {
        this.context = context;
        this.elements = elements;
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
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View newView = view;

        if (view == null){
            newView = inflater.inflate(R.layout.home_grid_layout,null);
        }

        TextView carRegistration = newView.findViewById(R.id.grid_car_registration);
        TextView parkingTime = newView.findViewById(R.id.grid_parking_time);

        carRegistration.setText(getItem(i).getCarRegistration());

        try {
            parkingTime.setText(TimeProtocol.getParsedTime(getItem(i).getTime()));
        } catch (TimeException e) {
            parkingTime.setText(R.string.unavailable_time);
        }

        return newView;
    }
}
