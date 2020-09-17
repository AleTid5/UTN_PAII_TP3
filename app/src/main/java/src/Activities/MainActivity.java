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
import src.Services.ContextManagerService;
import src.Services.UserService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextManagerService.setContext(this.getBaseContext());
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        try {
            String email = ((TextView) findViewById(R.id.input_login_email)).getText().toString();
            String password = ((TextView) findViewById(R.id.input_login_password)).getText().toString();

            if (email.length() == 0) {
                throw new Exception("Debe ingresar un email");
            }

            if (password.length() == 0) {
                throw new Exception("Debe ingresar una contraseña");
            }

            UserService.authenticate(new User(email, password));
            startActivity(new Intent(this, System.class));
        } catch (Exception e) {
            Snackbar.make(view, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
        }
    }

    public void onRegister(View view) {
        startActivity(new Intent(this, Register.class));
    }
}