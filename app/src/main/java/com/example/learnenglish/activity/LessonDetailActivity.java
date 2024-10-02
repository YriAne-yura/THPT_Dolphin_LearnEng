package com.example.learnenglish.activity;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.R;

public class LessonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        ImageView lessonImageView = findViewById(R.id.lessonImageView);

        // Get the lesson name passed from the LessonActivity
        String selectedLesson = getIntent().getStringExtra("selectedLesson");

        // Based on the selected lesson, display the appropriate image
        if (selectedLesson != null) {
            switch (selectedLesson) {
                case "Thì hiện tại đơn":
                    lessonImageView.setImageResource(R.drawable.pic1);
                    break;
                case "Thì thứ 2...":
                   // lessonImageView.setImageResource(R.drawable.image_second_lesson);
                    break;
                case "Th thứ 3...":
                   // lessonImageView.setImageResource(R.drawable.image_third_lesson);
                    break;
                default:
                    lessonImageView.setImageResource(R.drawable.default_image);
                    break;
            }
        }
    }
}
