package com.canhub.cropper.common;

import android.os.Build;
import kotlin.Metadata;

/* compiled from: CommonVersionCheck.kt */
@Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\b"}, m183d2 = {"Lcom/canhub/cropper/common/CommonVersionCheck;", "", "()V", "isAtLeastJ18", "", "isAtLeastM23", "isAtLeastO26", "isAtLeastQ29", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CommonVersionCheck {
    public static final CommonVersionCheck INSTANCE = new CommonVersionCheck();

    private CommonVersionCheck() {
    }

    public final boolean isAtLeastJ18() {
        return Build.VERSION.SDK_INT >= 18;
    }

    public final boolean isAtLeastM23() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public final boolean isAtLeastO26() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public final boolean isAtLeastQ29() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
