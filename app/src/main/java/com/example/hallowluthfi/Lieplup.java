package com.example.hallowluthfi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hallowluthfi.Models.UserDetails;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.FirebaseDatabase;


public class Lieplup extends AppCompatActivity {

    Button signUpBtn;
    TextInputEditText usernameSignUp, nimPengguna, passwordSignUp, emailPengguna;
    FirebaseAuth mAuth;
    private static final String TAG = "Lieplup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lieplup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signUpBtn = findViewById(R.id.btTombolSignUp);
        usernameSignUp = findViewById(R.id.etEnterUsername);
        emailPengguna = findViewById(R.id.etEnterEmail);
        nimPengguna = findViewById(R.id.etEnterNIM);
        passwordSignUp = findViewById(R.id.etEnterPassword);

        signUpBtn.setOnClickListener(view -> {
            String username, email, password, NIM;

            username = String.valueOf(usernameSignUp.getText());
            email = String.valueOf(emailPengguna.getText());
            NIM = String.valueOf(nimPengguna.getText());
            password = String.valueOf(passwordSignUp.getText());


            if (TextUtils.isEmpty(username)){
                Toast.makeText(Lieplup.this, "Enter Username", Toast.LENGTH_LONG).show();
                usernameSignUp.requestFocus();
            } else if (TextUtils.isEmpty(email)) {
                Toast.makeText(Lieplup.this, "Enter Your E-Mail", Toast.LENGTH_LONG).show();
                nimPengguna.requestFocus();
            } else if (TextUtils.isEmpty(NIM)) {
                Toast.makeText(Lieplup.this, "Enter Your NIM", Toast.LENGTH_LONG).show();
                nimPengguna.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(Lieplup.this, "Enter Password", Toast.LENGTH_LONG).show();
                passwordSignUp.requestFocus();
            } else {
                //method public void
                registerUser(username, email, NIM, password);
            }
        });
    }

    private void  registerUser(String username, String email, String NIM, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Lieplup.this,task -> {
           if (task.isSuccessful())  {
               FirebaseUser fUser = auth.getCurrentUser();
               String uid = fUser.getUid();

               UserDetails userDetails = new UserDetails(uid, username, email, NIM, password);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(fUser.getUid()).setValue(userDetails).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        fUser.sendEmailVerification();
                        Toast.makeText(Lieplup.this, "Account created", Toast.LENGTH_LONG).show();

                        //Pindah Page
                        Intent intent = new Intent(Lieplup.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Lieplup.this, "Account Register Failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Register: Error");
                    }
               });

           }
        });
    }
}