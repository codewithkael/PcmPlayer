package com.nove;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class NoveModel extends ReactContextBaseJavaModule {
    Context context;
    AudioConverter adapter;

    public NoveModel(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @ReactMethod
    public void playAudio(Integer rate, byte[] input) {
        if (adapter == null) {
            adapter = new AudioConverter(rate);
        }
        adapter.writeArrayToPlayer(input);
    }

    @NonNull
    @Override
    public String getName() {
        return "Nove";
    }
}
