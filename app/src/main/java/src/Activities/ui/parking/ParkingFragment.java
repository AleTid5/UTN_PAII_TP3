package src.Activities.ui.parking;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3_pa_grupo_3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import src.Activities.Adapters.ParkingAdapter;
import src.Models.Parking;

public class ParkingFragment extends Fragment {

    private ParkingViewModel parkingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parkingViewModel = new ViewModelProvider(this).get(ParkingViewModel.class);

        View root = inflater.inflate(R.layout.fragment_parking, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            Dialog dialog = new Dialog(root.getContext());
            dialog.setTitle("Registrar parqueo");
            dialog.setContentView(R.layout.dialog_parking);
            dialog.setCancelable(false);
            dialog.findViewById(R.id.dialog_cancel).setOnClickListener(v -> dialog.dismiss());
            dialog.findViewById(R.id.dialog_ok).setOnClickListener(v -> this.onDialogAccept(dialog, v, view));

            dialog.show();
        });

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                fab,
                PropertyValuesHolder.ofFloat("scaleX", 1.05f),
                PropertyValuesHolder.ofFloat("scaleY", 1.05f));

        scaleDown.setDuration(310);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();

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

    private void onDialogAccept(Dialog dialog, View dialogView, View parentView) {
        try {
            String carRegistration = ((TextView) dialog.findViewById(R.id.text_registration_car)).getText().toString();
            String time = ((TextView) dialog.findViewById(R.id.text_time)).getText().toString();

            if (carRegistration.length() == 0) throw new Exception(this.requireContext().getString(R.string.missing_car_registration));
            if (time.length() == 0) throw new Exception(this.requireContext().getString(R.string.missing_time));

            parkingViewModel.saveParking(
                    carRegistration,
                    Integer.parseInt(time)
            );

            dialog.dismiss();

            Snackbar.make(parentView, this.requireContext().getString(R.string.added_parking), Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();
        } catch (Exception e) {
            Snackbar.make(dialogView, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show();
        }
    }
}