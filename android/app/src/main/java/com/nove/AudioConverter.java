package com.nove;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AudioConverter {

    private final AudioTrack audioTrack;
    public AudioConverter(Integer rate) {
        int bufferSize = AudioTrack.getMinBufferSize(
                rate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
        );
        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, rate, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize, AudioTrack.MODE_STREAM);
        audioTrack.play();
    }

    public void writeArrayToPlayer(byte[] bytes) {
        try {
            short[] shorts = new short[bytes.length];
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
            byteBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            int counter = 0;
            while (byteBuffer.hasRemaining()) {
                shorts[counter] = byteBuffer.getShort();
                counter++;
            }
            audioTrack.write(shorts, 0, shorts.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
