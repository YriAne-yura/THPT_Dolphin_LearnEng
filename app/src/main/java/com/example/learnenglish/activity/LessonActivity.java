package com.example.learnenglish.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.R;

public class LessonActivity extends AppCompatActivity {

    private String[] lessons = {"Thì hiện tại đơn", "Thì thứ 2...", "Th thứ 3..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        ListView lessonListView = findViewById(R.id.lessonListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons);
        lessonListView.setAdapter(adapter);

        lessonListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedLesson = lessons[position];
            Toast.makeText(LessonActivity.this, "Selected: " + selectedLesson, Toast.LENGTH_SHORT).show();

            // Start LessonDetailActivity and pass the selected lesson
            Intent intent = new Intent(LessonActivity.this, LessonDetailActivity.class);
            intent.putExtra("selectedLesson", selectedLesson);
            startActivity(intent);
        });
    }
}
