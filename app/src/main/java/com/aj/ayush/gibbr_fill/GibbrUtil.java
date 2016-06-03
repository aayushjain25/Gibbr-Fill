package com.aj.ayush.gibbr_fill;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by aayush.ja on 6/3/2016.
 */

public class GibbrUtil {

    public static final String GIBBR_FILL_FILE = "gibbr_fill.txt";
    public static final String GIBBR_FILL_INTRO_FILE = "gibbr_fill_intro.txt";

    public String readFile(String fileName, Context context) {
        StringBuilder text = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            InputStreamReader inputReader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(inputReader);
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            text.append("Cannot read file : ").append(fileName);
            e.printStackTrace();
        }
        return text.toString();
    }

    public SpannableStringBuilder replaceWords(String fileName, ArrayList<String> filledWords, Context context) {
        StringBuilder text = new StringBuilder();
        int counter = 0;
        try {
            InputStream is = context.getAssets().open(fileName);
            InputStreamReader inputReader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(inputReader);
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            text.append("Cannot read file : ").append(fileName);
            e.printStackTrace();
        }

        final SpannableStringBuilder str = new SpannableStringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '<') {
                int j = text.indexOf(">", i);
                text.replace(i, j+1, filledWords.get(counter));
                str.replace(i, j + 1, filledWords.get(counter));
                str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), i, i + filledWords.get(counter).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                counter++;
            }
        }
        return str;
    }


}
