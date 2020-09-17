package src.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp3_pa_grupo_3.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import src.Models.User;
import src.Services.UserService;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegister(View view) {
        try {
            String password = ((TextView) findViewById(R.id.input_login_password)).getText().toString();
            String password_repeat = ((TextView) findViewById(R.id.input_password_repeat)).getText().toString();

            if (!password.equals(password_repeat)) {
                throw new Exception("Las contraseñas no coinciden");
            }

            User user = new User(
                    ((TextView) findViewById(R.id.input_name)).getText().toString(),
                    ((TextView) findViewById(R.id.input_email)).getText().toString(),
                    password
            );

            UserService.saveUser(user);
            finish();
        } catch (Exception e) {
            Snackbar.make(view, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
        }
    }

    public void onCancel(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}