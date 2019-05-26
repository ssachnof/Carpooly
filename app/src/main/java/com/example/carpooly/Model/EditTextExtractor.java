package com.example.carpooly.Model;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.carpooly.ElementExtractor;

public class EditTextExtractor extends AppCompatActivity implements ElementExtractor {
    private EditText element;

    @Override
    public String extractElement(View view) {
        return ((EditText) view).getText().toString();
    }
}
