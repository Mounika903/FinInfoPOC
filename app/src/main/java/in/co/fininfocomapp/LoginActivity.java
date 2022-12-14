package in.co.fininfocomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import in.co.fininfocomapp.utils.SharedPreferenceUtil;

public class LoginActivity extends AppCompatActivity {


    EditText et_username, et_password;
    Button btn_login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

    }

    private void validation() {
        String email = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (email.equals("") || email.equals(null)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "please enter valid email address", Toast.LENGTH_SHORT).show();
        } else if (password.equals("") || password.equals(null)) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
        } else if (password.length() > 7 || password.length() < 7) {
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
        et_username = (EditText) findViewById(R.id.user_name);
        et_password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}