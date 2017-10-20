package com.mobdev.leninortiz.sentencesinverter;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;

/**
 * Created by leninortiz on 19/10/17.
 */

public class Inverter extends IntentService {
    private static final String ACTION_INVERT = "com.mobdev.leninortiz.sentencesinverter.action.invert";

    private static final String EXTRA_PARAM_SENTENCE = "com.mobdev.leninortiz.sentencesinverter.extra.sentence";
    private static final String EXTRA_PARAM_RECEIVER = "com.mobdev.leninortiz.sentencesinverter.extra.receiver";

    public Inverter() {
        super("Inverter");
    }

    public static void startActionInvert(Context context, String sentence, Receiver receiver) {
        Intent intent = new Intent(context, Inverter.class);
        intent.setAction(ACTION_INVERT);
        intent.putExtra(EXTRA_PARAM_SENTENCE, sentence);
        intent.putExtra(EXTRA_PARAM_RECEIVER, receiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INVERT.equals(action)) {
                final String sentence = intent.getStringExtra(EXTRA_PARAM_SENTENCE);
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_PARAM_RECEIVER);
                final String result = handleActionInvert(sentence);
                Bundle data = new Bundle();
                data.putString("Result", result);
                receiver.send(0, data);
            }
        }
    }

    private String handleActionInvert(String sentences) {
        String result = "";
        String[] sentencesArray = sentences.split(" ");
        int length = sentencesArray.length;
        for (int i = length - 1; i >= 0; i--) {
            if (sentencesArray[i].isEmpty()) continue;
            result = result + " " + sentencesArray[i];
        }
        return result;
    }
}
