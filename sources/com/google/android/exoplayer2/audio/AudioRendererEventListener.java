package com.google.android.exoplayer2.audio;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* loaded from: classes2.dex */
public interface AudioRendererEventListener {

    /* renamed from: com.google.android.exoplayer2.audio.AudioRendererEventListener$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static void $default$onAudioCodecError(AudioRendererEventListener _this, Exception exc) {
        }

        public static void $default$onAudioDecoderInitialized(AudioRendererEventListener _this, String str, long j, long j2) {
        }

        public static void $default$onAudioDecoderReleased(AudioRendererEventListener _this, String str) {
        }

        public static void $default$onAudioDisabled(AudioRendererEventListener _this, DecoderCounters decoderCounters) {
        }

        public static void $default$onAudioEnabled(AudioRendererEventListener _this, DecoderCounters decoderCounters) {
        }

        @Deprecated
        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener _this, Format format) {
        }

        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener _this, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        }

        public static void $default$onAudioPositionAdvancing(AudioRendererEventListener _this, long j) {
        }

        public static void $default$onAudioSinkError(AudioRendererEventListener _this, Exception exc) {
        }

        public static void $default$onAudioUnderrun(AudioRendererEventListener _this, int r1, long j, long j2) {
        }

        public static void $default$onSkipSilenceEnabledChanged(AudioRendererEventListener _this, boolean z) {
        }
    }

    void onAudioCodecError(Exception exc);

    void onAudioDecoderInitialized(String str, long j, long j2);

    void onAudioDecoderReleased(String str);

    void onAudioDisabled(DecoderCounters decoderCounters);

    void onAudioEnabled(DecoderCounters decoderCounters);

    @Deprecated
    void onAudioInputFormatChanged(Format format);

    void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation);

    void onAudioPositionAdvancing(long j);

    void onAudioSinkError(Exception exc);

    void onAudioUnderrun(int r1, long j, long j2);

    void onSkipSilenceEnabledChanged(boolean z);

    /* loaded from: classes2.dex */
    public static final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public EventDispatcher(Handler handler, AudioRendererEventListener audioRendererEventListener) {
            this.handler = audioRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler) : null;
            this.listener = audioRendererEventListener;
        }

        public void enabled(final DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1199x5024e2cf(decoderCounters);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$enabled$0$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1199x5024e2cf(DecoderCounters decoderCounters) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioEnabled(decoderCounters);
        }

        public void decoderInitialized(final String str, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1202x34ee4b45(str, j, j2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$decoderInitialized$1$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1202x34ee4b45(String str, long j, long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(final Format format, final DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1198xd066461(format, decoderReuseEvaluation);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$inputFormatChanged$2$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1198xd066461(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format);
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void positionAdvancing(final long j) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1197x4b664277(j);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$positionAdvancing$3$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1197x4b664277(long j) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioPositionAdvancing(j);
        }

        public void underrun(final int r10, final long j, final long j2) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1195x69131a3f(r10, j, j2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$underrun$4$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1195x69131a3f(int r8, long j, long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioUnderrun(r8, j, j2);
        }

        public void decoderReleased(final String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1201x97e584ca(str);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$decoderReleased$5$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1201x97e584ca(String str) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderReleased(str);
        }

        public void disabled(final DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1200xa9a48754(decoderCounters);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$disabled$6$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1200xa9a48754(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDisabled(decoderCounters);
        }

        public void skipSilenceEnabledChanged(final boolean z) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1196x9104d974(z);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$skipSilenceEnabledChanged$7$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1196x9104d974(boolean z) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onSkipSilenceEnabledChanged(z);
        }

        public void audioSinkError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1203xcf2a89af(exc);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$audioSinkError$8$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1203xcf2a89af(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioSinkError(exc);
        }

        public void audioCodecError(final Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.google.android.exoplayer2.audio.AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudioRendererEventListener.EventDispatcher.this.m1204x305f60bf(exc);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$audioCodecError$9$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher */
        public /* synthetic */ void m1204x305f60bf(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioCodecError(exc);
        }
    }
}
