package com.example.learnenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity2 extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText usernameEditText;
    private Button signupButton;
    private TextView loginRedirectButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        emailEditText = findViewById(R.id.enteremail);
        passwordEditText = findViewById(R.id.enterpw);
        confirmPasswordEditText = findViewById(R.id.repassword);
        usernameEditText = findViewById(R.id.entername);
        signupButton = findViewById(R.id.signupbtn);
        loginRedirectButton = findViewById(R.id.loginRedirectText);

        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || username.isEmpty()) {
                    Toast.makeText(SignupActivity2.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity2.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupActivity2.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignupActivity2.this, MainActivity2_login.class));
                                    finish();
                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(SignupActivity2.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignupActivity2.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        loginRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
