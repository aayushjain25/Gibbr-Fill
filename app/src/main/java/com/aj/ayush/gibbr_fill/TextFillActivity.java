package com.aj.ayush.gibbr_fill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TextFillActivity extends AppCompatActivity {

    public static final String WORDS_KEY = "words";
    public static ArrayList<String> words = new ArrayList<>();
    ArrayList<String> placeholderList = new ArrayList<>();
    Button button;
    EditText editText;
    TextInputLayout textInputLayout;
    TextView wordsTextView;
    GibbrUtil gUtil;
    int count, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_fill);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gUtil = new GibbrUtil();
        editText = (EditText) findViewById(R.id.editText1);
        button = (Button) findViewById(R.id.button1);
        wordsTextView = (TextView)findViewById(R.id.text_view_fill_words);
        textInputLayout = (TextInputLayout) findViewById(R.id.input1);

        String text = gUtil.readFile(GibbrUtil.GIBBR_FILL_FILE, this);
        count = 0;
        total = 0;

        words.clear();
        placeholderList.clear();

        for(int i=0; i<text.length(); i++) {
            if(text.charAt(i) == '<') {
                count++;
                int j = text.indexOf(">", i);
                placeholderList.add(text.substring(i+1, j));
            }
        }
        total = count;

        textInputLayout.setHint("Enter a/an " + placeholderList.get(0));
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(count + " word(s) remaining");

        addButtonListener();

    }

    public void addButtonListener() {
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().length() == 0)
                    Snackbar.make(view, R.string.empty_field, Snackbar.LENGTH_LONG).show();
                else if (count == 1) {
                    count--;
                    words.add(editText.getText().toString());
                    Intent intent = new Intent(TextFillActivity.this, StoryActivity.class);
                    intent.putExtra(WORDS_KEY, words);
                    TextFillActivity.this.startActivity(intent);
                }
                else {
                    count--;
                    textInputLayout.setHint("Enter a/an " + placeholderList.get(total-count));
                    textInputLayout.setError(count + " word(s) remaining");
                    words.add(editText.getText().toString());
                    editText.getText().clear();
                    if(count==1)
                        button.setText(R.string.show_story);
                }
            }
        });
    }

}
