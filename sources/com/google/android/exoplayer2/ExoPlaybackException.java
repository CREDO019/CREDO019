package com.google.android.exoplayer2;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.source.MediaPeriodId;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
public final class ExoPlaybackException extends PlaybackException {
    public static final Bundleable.Creator<ExoPlaybackException> CREATOR = new Bundleable.Creator() { // from class: com.google.android.exoplayer2.ExoPlaybackException$$ExternalSyntheticLambda0
        @Override // com.google.android.exoplayer2.Bundleable.Creator
        public final Bundleable fromBundle(Bundle bundle) {
            return ExoPlaybackException.$r8$lambda$mXbXdGG_PHMarv0ObcHmIhB4uIw(bundle);
        }
    };
    private static final int FIELD_IS_RECOVERABLE = 1006;
    private static final int FIELD_RENDERER_FORMAT = 1004;
    private static final int FIELD_RENDERER_FORMAT_SUPPORT = 1005;
    private static final int FIELD_RENDERER_INDEX = 1003;
    private static final int FIELD_RENDERER_NAME = 1002;
    private static final int FIELD_TYPE = 1001;
    public static final int TYPE_REMOTE = 3;
    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    final boolean isRecoverable;
    public final MediaPeriodId mediaPeriodId;
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    public final String rendererName;
    public final int type;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Type {
    }

    public static /* synthetic */ ExoPlaybackException $r8$lambda$mXbXdGG_PHMarv0ObcHmIhB4uIw(Bundle bundle) {
        return new ExoPlaybackException(bundle);
    }

    public static ExoPlaybackException createForSource(IOException iOException, int r3) {
        return new ExoPlaybackException(0, iOException, r3);
    }

    public static ExoPlaybackException createForRenderer(Throwable th, String str, int r13, Format format, int r15, boolean z, int r17) {
        return new ExoPlaybackException(1, th, null, r17, str, r13, format, format == null ? 4 : r15, z);
    }

    @Deprecated
    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return createForUnexpected(runtimeException, 1000);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException, int r3) {
        return new ExoPlaybackException(2, runtimeException, r3);
    }

    public static ExoPlaybackException createForRemote(String str) {
        return new ExoPlaybackException(3, null, str, 1001, null, -1, null, 4, false);
    }

    private ExoPlaybackException(int r11, Throwable th, int r13) {
        this(r11, th, null, r13, null, -1, null, 4, false);
    }

    private ExoPlaybackException(int r15, Throwable th, String str, int r18, String str2, int r20, Format format, int r22, boolean z) {
        this(deriveMessage(r15, str, str2, r20, format, r22), th, r18, r15, str2, r20, format, r22, null, SystemClock.elapsedRealtime(), z);
    }

    private ExoPlaybackException(Bundle bundle) {
        super(bundle);
        this.type = bundle.getInt(keyForField(1001), 2);
        this.rendererName = bundle.getString(keyForField(1002));
        this.rendererIndex = bundle.getInt(keyForField(1003), -1);
        Bundle bundle2 = bundle.getBundle(keyForField(1004));
        this.rendererFormat = bundle2 == null ? null : Format.CREATOR.fromBundle(bundle2);
        this.rendererFormatSupport = bundle.getInt(keyForField(1005), 4);
        this.isRecoverable = bundle.getBoolean(keyForField(1006), false);
        this.mediaPeriodId = null;
    }

    private ExoPlaybackException(String str, Throwable th, int r12, int r13, String str2, int r15, Format format, int r17, MediaPeriodId mediaPeriodId, long j, boolean z) {
        super(str, th, r12, j);
        boolean z2 = false;
        Assertions.checkArgument(!z || r13 == 1);
        Assertions.checkArgument((th != null || r13 == 3) ? true : true);
        this.type = r13;
        this.rendererName = str2;
        this.rendererIndex = r15;
        this.rendererFormat = format;
        this.rendererFormatSupport = r17;
        this.mediaPeriodId = mediaPeriodId;
        this.isRecoverable = z;
    }

    public IOException getSourceException() {
        Assertions.checkState(this.type == 0);
        return (IOException) Assertions.checkNotNull(getCause());
    }

    public Exception getRendererException() {
        Assertions.checkState(this.type == 1);
        return (Exception) Assertions.checkNotNull(getCause());
    }

    public RuntimeException getUnexpectedException() {
        Assertions.checkState(this.type == 2);
        return (RuntimeException) Assertions.checkNotNull(getCause());
    }

    @Override // com.google.android.exoplayer2.PlaybackException
    public boolean errorInfoEquals(PlaybackException playbackException) {
        if (super.errorInfoEquals(playbackException)) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) Util.castNonNull(playbackException);
            return this.type == exoPlaybackException.type && Util.areEqual(this.rendererName, exoPlaybackException.rendererName) && this.rendererIndex == exoPlaybackException.rendererIndex && Util.areEqual(this.rendererFormat, exoPlaybackException.rendererFormat) && this.rendererFormatSupport == exoPlaybackException.rendererFormatSupport && Util.areEqual(this.mediaPeriodId, exoPlaybackException.mediaPeriodId) && this.isRecoverable == exoPlaybackException.isRecoverable;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExoPlaybackException copyWithMediaPeriodId(MediaPeriodId mediaPeriodId) {
        return new ExoPlaybackException((String) Util.castNonNull(getMessage()), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, mediaPeriodId, this.timestampMs, this.isRecoverable);
    }

    private static String deriveMessage(int r1, String str, String str2, int r4, Format format, int r6) {
        String str3;
        if (r1 == 0) {
            str3 = "Source error";
        } else if (r1 != 1) {
            str3 = r1 != 3 ? "Unexpected runtime error" : "Remote error";
        } else {
            str3 = str2 + " error, index=" + r4 + ", format=" + format + ", format_supported=" + Util.getFormatSupportString(r6);
        }
        if (TextUtils.isEmpty(str)) {
            return str3;
        }
        return str3 + ": " + str;
    }

    @Override // com.google.android.exoplayer2.PlaybackException, com.google.android.exoplayer2.Bundleable
    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putInt(keyForField(1001), this.type);
        bundle.putString(keyForField(1002), this.rendererName);
        bundle.putInt(keyForField(1003), this.rendererIndex);
        if (this.rendererFormat != null) {
            bundle.putBundle(keyForField(1004), this.rendererFormat.toBundle());
        }
        bundle.putInt(keyForField(1005), this.rendererFormatSupport);
        bundle.putBoolean(keyForField(1006), this.isRecoverable);
        return bundle;
    }
}
