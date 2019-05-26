package com.example.carpooly;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditTextExtractor extends AppCompatActivity implements ElementExtractor {
    private EditText element;

    @Override
    public String extractElement(View view) {
        return ((EditText) view).getText().toString();
    }
}
