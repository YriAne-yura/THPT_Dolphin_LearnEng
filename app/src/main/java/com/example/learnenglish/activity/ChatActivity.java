package com.example.learnenglish.activity;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.learnenglish.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private EditText messageInput;
    private Button sendButton;
    private LinearLayout chatContainer;
    private ScrollView scrollView;

    private Map<String, DictionaryEntry> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        chatContainer = findViewById(R.id.chatContainer);
        scrollView = findViewById(R.id.scrollView);

        // Initialize the dictionary with hard-coded values
        dictionary = new HashMap<>();
        dictionary.put("desert", new DictionaryEntry("(n):", "sa mạc", "  \nThe desert was hot and dry."));

        addMessageToChat("Hãy gửi từ bạn cần giải nghĩa, Dolphin sẽ giúp bạn", false);
        sendButton.setOnClickListener(v -> handleSendButtonClick());
    }

    private void handleSendButtonClick() {
        String inputText = messageInput.getText().toString().trim();

        if (!inputText.isEmpty()) {
            DictionaryEntry entry = dictionary.get(inputText);
            String response = (entry != null) ? entry.toString() : "Mình chưa nhận diện được từ này. Bạn hãy kiểm tra lại hoặc thử từ khác.";

            addMessageToChat("Bạn: " + inputText, true);
            addMessageToChat("Dolphin: " + response, false);

            messageInput.setText("");
        }
    }

    private void addMessageToChat(String message, boolean isUser) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setPadding(10, 10, 10, 10);


        int backgroundColor = isUser ? getResources().getColor(R.color.user_message_color) : getResources().getColor(R.color.bot_message_color);
        textView.setBackgroundColor(backgroundColor);

        chatContainer.addView(textView);

        // Scroll to the bottom of the ScrollView
        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
    }
}
