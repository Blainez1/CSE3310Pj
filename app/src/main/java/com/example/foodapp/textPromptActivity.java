package com.example.foodapp;

import android.os.Bundle;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class textPromptActivity extends Activity {
    private EditText ingredientPrompt;
    private Button submitButton;
    private Button exitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //START UP DEFAULT -> DO NOT DELETE
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_promp);

        submitButton = (Button) findViewById(R.id.ingredientPromptSubmitButton);
        exitButton = (Button) findViewById(R.id.ingredientPromptBoxExitButton);
        ingredientPrompt = (EditText) findViewById(R.id.ingredientPromptTextBox);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = submitButton.getText().toString();

                System.out.println(inputText);
            }
        });
    }
}