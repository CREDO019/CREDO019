package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public abstract class ReviewInfo implements Parcelable {
    /* renamed from: a */
    public static ReviewInfo m624a(PendingIntent pendingIntent) {
        return new C2584a(pendingIntent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract PendingIntent mo623a();

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int r3) {
        parcel.writeParcelable(mo623a(), 0);
    }
}
