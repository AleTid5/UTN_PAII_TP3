package src.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tp3_pa_grupo_3.R;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegister(View view) {

    }

    public void onCancel(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}