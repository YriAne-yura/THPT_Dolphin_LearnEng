package com.example.learnenglish.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnenglish.R;
import com.example.learnenglish.adapter.VocabularyItemAdapter;
import com.example.learnenglish.database.VocabularyDatabase;
import com.example.learnenglish.database.VocabularyItemDatabase;
import com.example.learnenglish.listener.OnQuestionCallbackVocabularyItem;
import com.example.learnenglish.model.Vocabulary;
import com.example.learnenglish.model.VocabularyItem;

import java.util.List;

public class VocabularyItemActivity extends AppCompatActivity implements OnQuestionCallbackVocabularyItem {

    private Vocabulary vocabulary;
    private int level;
    private ListView lv_item;
    private VocabularyItemAdapter adapterItem;
    private Button btnNext;
    private VocabularyItemDatabase vocabularyItemDatabase;
    private VocabularyDatabase vocabularyDatabase;
    private List<VocabularyItem> itemArrayList;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocabulary_item);

        getIncomingData();

        // Đặt tên cho thanh Action Bar theo từ vựng
        getSupportActionBar().setTitle(vocabulary.getNameVocabulary());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Bật nút back

        lv_item = findViewById(R.id.listview_item);
        btnNext = findViewById(R.id.btNext);

        // Thiết lập text cho nút next dựa trên level
        setNextButtonText();

        vocabularyItemDatabase = new VocabularyItemDatabase(this);
        vocabularyDatabase = new VocabularyDatabase(this);
        itemArrayList = vocabularyItemDatabase.getListLevelVocabularyItem(vocabulary.getIdVocabulary(), level);

        adapterItem = new VocabularyItemAdapter(this, R.layout.stream_item_vocabulary, itemArrayList);
        adapterItem.setListener(this);
        lv_item.setAdapter(adapterItem);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNextButtonClick();
            }
        });
    }

    private void setNextButtonText() {
        switch (level) {
            case 0:
            case 1:
                btnNext.setText("More");
                break;
            case 2:
                btnNext.setText("Next topic");
                break;
            default:
                btnNext.setText("Chuyển level tiếp theo");
        }
    }

    private void handleNextButtonClick() {
        if (level == 0 || level == 1) {
            level++;
            startNewVocabularyItemActivity();
        } else if (level == 2) {
            Vocabulary nextVocabulary = vocabularyDatabase.getListVocabularyFromId(vocabulary.getIdVocabulary() + 1);
            Intent intent = new Intent(VocabularyItemActivity.this, VocabularyItemActivity.class);
            intent.putExtra("position", nextVocabulary);
            intent.putExtra("level", 0);
            startActivity(intent);
        }
    }

    private void startNewVocabularyItemActivity() {
        Intent intent = new Intent(VocabularyItemActivity.this, VocabularyItemActivity.class);
        intent.putExtra("position", vocabulary);
        intent.putExtra("level", level);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();  // Quay lại màn hình trước
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getIncomingData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("position")) {
                vocabulary = (Vocabulary) intent.getSerializableExtra("position");
            }
            if (intent.hasExtra("level")) {
                level = intent.getIntExtra("level", 0); // Sử dụng getIntExtra
            }
        }
    }

    @Override
    public void onClickQuestion(VocabularyItem item) {
        int soundResId = getResourcesIdFromName(item.getSoundItem());
        if (soundResId != 0) { // Kiểm tra xem âm thanh có tồn tại không
            mediaPlayer = MediaPlayer.create(this, soundResId);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Âm thanh không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @RawRes
    private int getResourcesIdFromName(String name) {
        return getResources().getIdentifier(name, "raw", getPackageName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Giải phóng MediaPlayer
            mediaPlayer = null;
        }
    }
}
