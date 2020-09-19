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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3_pa_grupo_3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import src.Activities.Adapters.ParkingAdapter;

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
                PropertyValuesHolder.ofFloat("scaleX", 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f));

        scaleDown.setDuration(310);
        scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.start();

        parkingViewModel.getParkingList().observe(getViewLifecycleOwner(), parkingList -> {
            ParkingAdapter adapter = new ParkingAdapter(parkingViewModel, parkingList);

            GridView gridView = requireView().findViewById(R.id.grid_parking_view);
            gridView.setAdapter(adapter);

            requireView().findViewById(R.id.not_found_parkings).setVisibility(parkingList.size() > 0 ? View.INVISIBLE : View.VISIBLE);
        });

        return root;
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
                    .show();
        } catch (Exception e) {
            Snackbar.make(dialogView, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG)
                    .show();
        }
    }
}