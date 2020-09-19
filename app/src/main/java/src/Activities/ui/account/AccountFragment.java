package src.Activities.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3_pa_grupo_3.R;

public class AccountFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        accountViewModel.fillUserInfo(
                root.findViewById(R.id.input_name),
                root.findViewById(R.id.input_email),
                root.findViewById(R.id.input_total_parkings),
                root.findViewById(R.id.input_total_time)
        );

        return root;
    }
}