package com.aj.ayush.gibbr_fill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    TextView storyTextView;
    GibbrUtil gUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Intent intent = getIntent();
        ArrayList<String> words = intent.getStringArrayListExtra(TextFillActivity.WORDS_KEY);

        gUtil = new GibbrUtil();
        storyTextView = (TextView) findViewById(R.id.text_view_story);
        storyTextView.setText(gUtil.replaceWords(GibbrUtil.GIBBR_FILL_FILE, words, StoryActivity.this));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, TextFillActivity.class));
    }

}
