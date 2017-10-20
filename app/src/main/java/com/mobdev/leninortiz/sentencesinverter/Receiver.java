package com.mobdev.leninortiz.sentencesinverter;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by leninortiz on 19/10/17.
 */

public class Receiver extends ResultReceiver {
    public interface ReceiverListener {
        void onReceive(int resultCode, Bundle resultData);
    }
    private ReceiverListener listener;

    public Receiver(Handler handler) {
        super(handler);
    }

    public void setListener(ReceiverListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        listener.onReceive(resultCode, resultData);
    }
}
