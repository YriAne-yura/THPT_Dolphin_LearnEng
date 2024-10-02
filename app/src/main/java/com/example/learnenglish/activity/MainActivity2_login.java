package com.example.learnenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2_login extends AppCompatActivity {

    EditText liemail, lipassword;
    Button loginbtn;
    TextView signupRedirectText;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_login);

        liemail = findViewById(R.id.liemail);
        lipassword = findViewById(R.id.lipassword);
        loginbtn = findViewById(R.id.loginbtn);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        mAuth = FirebaseAuth.getInstance();




        loginbtn.setOnClickListener(view -> {
            String email = liemail.getText().toString().trim();
            String password = lipassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity2_login.this, "Hãy điền thông tin vào tất cả các ô", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Skip login
                                Intent intent = new Intent(MainActivity2_login.this, MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(MainActivity2_login.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(MainActivity2_login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

        signupRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity2_login.this, SignupActivity2.class);
            startActivity(intent);
        });
    }
}
