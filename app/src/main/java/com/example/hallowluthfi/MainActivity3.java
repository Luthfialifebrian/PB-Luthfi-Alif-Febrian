package com.example.hallowluthfi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {
    TextInputEditText emailUser, passwordUser;
    CheckBox checkBoxes;
    Button btLogin;
    TextView forgotPass, signUp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailUser = findViewById(R.id.inputUsername);
        passwordUser = findViewById(R.id.inputSandi);
        checkBoxes = findViewById(R.id.cbingatSya);
        btLogin = findViewById(R.id.bttombolLogin);
        forgotPass = findViewById(R.id.tvlupaSandi);
        signUp = findViewById(R.id.signUp);

        btLogin.setOnClickListener(view -> {
            String email, password;
            email = String.valueOf(emailUser.getText());
            password = String.valueOf(passwordUser.getText());

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity3.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}