package src.Activities.ui.parking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3_pa_grupo_3.R;

import java.util.Arrays;
import java.util.List;

import src.Activities.Adapters.ParkingAdapter;
import src.Models.Parking;

public class ParkingFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        new ViewModelProvider(this).get(ParkingViewModel.class);

        View root = inflater.inflate(R.layout.fragment_parking, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            List<Parking> some = Arrays.asList(
                    new Parking("AB123CD", 35),
                    new Parking("B123ACD", 123),
                    new Parking("856FRE", 67),
                    new Parking("KDE843", 60));

            ParkingAdapter adapter = new ParkingAdapter(this.getContext(), some);

            GridView gridView = requireView().findViewById(R.id.grid_parking_view);
            gridView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}