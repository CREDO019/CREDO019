package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzaxj implements zzaxc {
    public final String zze;

    public zzaxj(String str) {
        Objects.requireNonNull(str);
        this.zze = str;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }
}
