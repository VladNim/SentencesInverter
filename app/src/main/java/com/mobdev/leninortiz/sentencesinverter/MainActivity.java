package com.mobdev.leninortiz.sentencesinverter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by leninortiz on 19/10/17.
 */

public class MainActivity extends AppCompatActivity implements TextWatcher, Receiver.ReceiverListener {
    private EditText txtSentence;
    private Button btnInvert;
    private TextView txvResult;

    private Receiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSentence = (EditText) findViewById(R.id.txtSentence);
        btnInvert = (Button) findViewById(R.id.btnInvert);
        txvResult = (TextView) findViewById(R.id.txvResult);

        txtSentence.addTextChangedListener(this);
        receiver = new Receiver(new Handler());
        receiver.setListener(this);
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
        Inverter.startActionInvert(this, txtSentence.getText().toString(), receiver);
    }

    @Override
    public void onReceive(int resultCode, Bundle resultData) {
        if (resultCode == 0)
            txvResult.setText(resultData.getString("Result"));
        else
            txvResult.setText("");
    }
}
