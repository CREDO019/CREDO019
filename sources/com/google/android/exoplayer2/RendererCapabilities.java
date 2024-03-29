package com.google.android.exoplayer2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
public interface RendererCapabilities {
    public static final int ADAPTIVE_NOT_SEAMLESS = 8;
    public static final int ADAPTIVE_NOT_SUPPORTED = 0;
    public static final int ADAPTIVE_SEAMLESS = 16;
    public static final int ADAPTIVE_SUPPORT_MASK = 24;
    public static final int DECODER_SUPPORT_FALLBACK = 0;
    public static final int DECODER_SUPPORT_PRIMARY = 128;
    @Deprecated
    public static final int FORMAT_EXCEEDS_CAPABILITIES = 3;
    @Deprecated
    public static final int FORMAT_HANDLED = 4;
    public static final int FORMAT_SUPPORT_MASK = 7;
    @Deprecated
    public static final int FORMAT_UNSUPPORTED_DRM = 2;
    @Deprecated
    public static final int FORMAT_UNSUPPORTED_SUBTYPE = 1;
    @Deprecated
    public static final int FORMAT_UNSUPPORTED_TYPE = 0;
    public static final int HARDWARE_ACCELERATION_NOT_SUPPORTED = 0;
    public static final int HARDWARE_ACCELERATION_SUPPORTED = 64;
    public static final int HARDWARE_ACCELERATION_SUPPORT_MASK = 64;
    public static final int MODE_SUPPORT_MASK = 128;
    public static final int TUNNELING_NOT_SUPPORTED = 0;
    public static final int TUNNELING_SUPPORTED = 32;
    public static final int TUNNELING_SUPPORT_MASK = 32;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AdaptiveSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Capabilities {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DecoderSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Deprecated
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface FormatSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface HardwareAccelerationSupport {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TunnelingSupport {
    }

    String getName();

    int getTrackType();

    int supportsFormat(Format format) throws ExoPlaybackException;

    int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException;

    /* renamed from: com.google.android.exoplayer2.RendererCapabilities$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static int create(int r0, int r1, int r2, int r3, int r4) {
            return r0 | r1 | r2 | r3 | r4;
        }

        public static int getAdaptiveSupport(int r0) {
            return r0 & 24;
        }

        public static int getDecoderSupport(int r0) {
            return r0 & 128;
        }

        public static int getFormatSupport(int r0) {
            return r0 & 7;
        }

        public static int getHardwareAccelerationSupport(int r0) {
            return r0 & 64;
        }

        public static int getTunnelingSupport(int r0) {
            return r0 & 32;
        }

        public static int create(int r1) {
            return create(r1, 0, 0);
        }

        public static int create(int r2, int r3, int r4) {
            return create(r2, r3, r4, 0, 128);
        }
    }
}
