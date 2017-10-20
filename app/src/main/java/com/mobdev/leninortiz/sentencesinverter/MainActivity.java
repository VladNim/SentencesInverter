package com.mobdev.leninortiz.sentencesinverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private EditText txtSentence;
    private Button btnInvert;
    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSentence = (EditText) findViewById(R.id.txtSentence);
        btnInvert = (Button) findViewById(R.id.btnInvert);
        txvResult = (TextView) findViewById(R.id.txvResult);

        txtSentence.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        int sentencesCounter = 0;
        for (String sentence : s.toString().split(" ")) {
            if (!sentence.isEmpty()) sentencesCounter++;
        }
        btnInvert.setEnabled(sentencesCounter > 1);
    }

    public void toInvert (View view) {
        Log.d("SentencesInverter", "Invert method");
    }
}
