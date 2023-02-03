package in.co.fininfocomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import in.co.fininfocomapp.utils.SharedPreferenceUtil;

public class LoginActivity extends AppCompatActivity {


    EditText et_username, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(view -> validation());

    }

    private void validation() {
        String email = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (Objects.equals(email, "")) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "please enter valid email address", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
        } else if (password.length() != 7) {
            Toast.makeText(getApplicationContext(), "Please enter seven characters password", Toast.LENGTH_SHORT).show();
        } else {
            loginUser(email, password);
        }
    }

    private void loginUser(String email, String password) {
        if (email.equals("Fininfocom@gmail.com") && password.equals("Fin@123")) {
            storeLoginInfoInSPF();
            redirectToDashboard();
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(LoginActivity.this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void storeLoginInfoInSPF() {
        SharedPreferenceUtil.setUserLoggedInStatus(LoginActivity.this, true);
    }

    private void redirectToDashboard() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }

    private void initViews() {
        et_username = findViewById(R.id.user_name);
        et_password = findViewById(R.id.password);
        btn_login =  findViewById(R.id.btn_login);
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}