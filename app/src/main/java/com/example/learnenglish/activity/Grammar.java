package com.example.learnenglish.activity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.R;

public class Grammar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar); // Đảm bảo bạn có layout cho Activity này

        Button btnBasicEnglish = findViewById(R.id.btnBasicEnglish);
        btnBasicEnglish.setOnClickListener(v -> {
            // Navigate to LessonActivity (replace LessonFragment with LessonActivity)
            Intent intent = new Intent(Grammar.this, LessonActivity.class);
            startActivity(intent);
        });
    }
}
