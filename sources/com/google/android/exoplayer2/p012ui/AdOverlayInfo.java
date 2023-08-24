package com.google.android.exoplayer2.p012ui;

import android.view.View;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* renamed from: com.google.android.exoplayer2.ui.AdOverlayInfo */
/* loaded from: classes2.dex */
public final class AdOverlayInfo {
    public static final int PURPOSE_CLOSE_AD = 2;
    public static final int PURPOSE_CONTROLS = 1;
    public static final int PURPOSE_NOT_VISIBLE = 4;
    public static final int PURPOSE_OTHER = 3;
    public final int purpose;
    public final String reasonDetail;
    public final View view;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.google.android.exoplayer2.ui.AdOverlayInfo$Purpose */
    /* loaded from: classes2.dex */
    public @interface Purpose {
    }

    /* renamed from: com.google.android.exoplayer2.ui.AdOverlayInfo$Builder */
    /* loaded from: classes2.dex */
    public static final class Builder {
        private String detailedReason;
        private final int purpose;
        private final View view;

        public Builder(View view, int r2) {
            this.view = view;
            this.purpose = r2;
        }

        public Builder setDetailedReason(String str) {
            this.detailedReason = str;
            return this;
        }

        public AdOverlayInfo build() {
            return new AdOverlayInfo(this.view, this.purpose, this.detailedReason);
        }
    }

    @Deprecated
    public AdOverlayInfo(View view, int r3) {
        this(view, r3, null);
    }

    @Deprecated
    public AdOverlayInfo(View view, int r2, String str) {
        this.view = view;
        this.purpose = r2;
        this.reasonDetail = str;
    }
}
