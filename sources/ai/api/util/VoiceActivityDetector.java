package ai.api.util;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* loaded from: classes.dex */
public class VoiceActivityDetector {
    private static final double ENERGY_FACTOR = 3.1d;
    public static final int FRAME_SIZE_IN_BYTES = 320;
    private static final int MAX_CZ = 15;
    private static final long MAX_SILENCE_MILLIS = 3500;
    private static final int MIN_CZ = 5;
    private static final long MIN_SILENCE_MILLIS = 800;
    private static final int MIN_SPEECH_SEQUENCE_COUNT = 3;
    public static final int NOISE_BYTES = 4800;
    private static final int NOISE_FRAMES = 15;
    private static final int SEQUENCE_LENGTH_MILLIS = 30;
    private static final long SILENCE_DIFF_MILLIS = 2700;
    public static final String TAG = "ai.api.util.VoiceActivityDetector";
    private SpeechEventsListener eventsListener;
    private int frameNumber;
    private final int sampleRate;
    private double noiseEnergy = 0.0d;
    private long lastActiveTime = -1;
    private long lastSequenceTime = 0;
    private int sequenceCounter = 0;
    private long time = 0;
    private long silenceMillis = MAX_SILENCE_MILLIS;
    private boolean speechActive = false;
    private boolean enabled = true;
    private boolean process = true;
    private double sum = 0.0d;
    private int size = 0;

    /* loaded from: classes.dex */
    public interface SpeechEventsListener {
        void onSpeechBegin();

        void onSpeechCancel();

        void onSpeechEnd();
    }

    public VoiceActivityDetector(int r6) {
        this.sampleRate = r6;
    }

    public void processBuffer(byte[] bArr, int r8) {
        if (this.process) {
            boolean isFrameActive = isFrameActive(ByteBuffer.wrap(bArr, 0, r8).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer());
            long j = ((this.frameNumber * (r8 / 2)) * 1000) / this.sampleRate;
            this.time = j;
            if (isFrameActive) {
                long j2 = this.lastActiveTime;
                if (j2 >= 0 && j - j2 < 30) {
                    int r7 = this.sequenceCounter + 1;
                    this.sequenceCounter = r7;
                    if (r7 >= 3) {
                        if (!this.speechActive) {
                            onSpeechBegin();
                        }
                        this.lastSequenceTime = this.time;
                        this.silenceMillis = Math.max((long) MIN_SILENCE_MILLIS, this.silenceMillis - 675);
                    }
                } else {
                    this.sequenceCounter = 1;
                }
                this.lastActiveTime = this.time;
            } else if (j - this.lastSequenceTime > this.silenceMillis) {
                if (this.speechActive) {
                    onSpeechEnd();
                } else {
                    onSpeechCancel();
                }
            }
        }
    }

    private boolean isFrameActive(ShortBuffer shortBuffer) {
        short s;
        int limit = shortBuffer.limit();
        this.size += limit;
        double d = 0.0d;
        int r4 = 0;
        int r5 = 0;
        char c = 0;
        while (true) {
            if (r4 >= limit) {
                break;
            }
            float f = (float) (shortBuffer.get(r4) / 32767.0d);
            d += (f * f) / limit;
            this.sum += s * s;
            char c2 = f <= 0.0f ? (char) 65535 : (char) 1;
            if (c != 0 && c2 != c) {
                r5++;
            }
            r4++;
            c = c2;
        }
        int r15 = this.frameNumber + 1;
        this.frameNumber = r15;
        if (r15 >= 15) {
            return r5 >= 5 && r5 <= 15 && d > this.noiseEnergy * ENERGY_FACTOR;
        }
        this.noiseEnergy += d / 15.0d;
        return false;
    }

    public double calculateRms() {
        double sqrt = Math.sqrt(this.sum / this.size) / 100.0d;
        this.sum = 0.0d;
        this.size = 0;
        return sqrt;
    }

    public void reset() {
        this.time = 0L;
        this.frameNumber = 0;
        this.noiseEnergy = 0.0d;
        this.lastActiveTime = -1L;
        this.lastSequenceTime = 0L;
        this.sequenceCounter = 0;
        this.silenceMillis = MAX_SILENCE_MILLIS;
        this.speechActive = false;
        this.process = true;
    }

    public void setSpeechListener(SpeechEventsListener speechEventsListener) {
        this.eventsListener = speechEventsListener;
    }

    private void onSpeechEnd() {
        SpeechEventsListener speechEventsListener;
        Log.v(TAG, "onSpeechEnd");
        this.speechActive = false;
        this.process = false;
        if (!this.enabled || (speechEventsListener = this.eventsListener) == null) {
            return;
        }
        speechEventsListener.onSpeechEnd();
    }

    private void onSpeechCancel() {
        Log.v(TAG, "onSpeechCancel");
        this.speechActive = false;
        this.process = false;
        SpeechEventsListener speechEventsListener = this.eventsListener;
        if (speechEventsListener != null) {
            speechEventsListener.onSpeechCancel();
        }
    }

    private void onSpeechBegin() {
        Log.v(TAG, "onSpeechBegin");
        this.speechActive = true;
        SpeechEventsListener speechEventsListener = this.eventsListener;
        if (speechEventsListener != null) {
            speechEventsListener.onSpeechBegin();
        }
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }
}
