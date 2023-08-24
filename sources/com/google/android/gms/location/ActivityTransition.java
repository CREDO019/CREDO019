package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes3.dex */
public class ActivityTransition extends AbstractSafeParcelable {
    public static final int ACTIVITY_TRANSITION_ENTER = 0;
    public static final int ACTIVITY_TRANSITION_EXIT = 1;
    public static final Parcelable.Creator<ActivityTransition> CREATOR = new zzo();
    private final int zza;
    private final int zzb;

    /* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
    /* loaded from: classes3.dex */
    public static class Builder {
        private int zza = -1;
        private int zzb = -1;

        public ActivityTransition build() {
            Preconditions.checkState(this.zza != -1, "Activity type not set.");
            Preconditions.checkState(this.zzb != -1, "Activity transition type not set.");
            return new ActivityTransition(this.zza, this.zzb);
        }

        public Builder setActivityTransition(int r1) {
            ActivityTransition.zza(r1);
            this.zzb = r1;
            return this;
        }

        public Builder setActivityType(int r1) {
            this.zza = r1;
            return this;
        }
    }

    /* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes3.dex */
    public @interface SupportedActivityTransition {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActivityTransition(int r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public static void zza(int r3) {
        boolean z = true;
        z = (r3 < 0 || r3 > 1) ? false : false;
        StringBuilder sb = new StringBuilder(41);
        sb.append("Transition type ");
        sb.append(r3);
        sb.append(" is not valid.");
        Preconditions.checkArgument(z, sb.toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityTransition) {
            ActivityTransition activityTransition = (ActivityTransition) obj;
            return this.zza == activityTransition.zza && this.zzb == activityTransition.zzb;
        }
        return false;
    }

    public int getActivityType() {
        return this.zza;
    }

    public int getTransitionType() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Integer.valueOf(this.zzb));
    }

    public String toString() {
        int r0 = this.zza;
        int r1 = this.zzb;
        StringBuilder sb = new StringBuilder(75);
        sb.append("ActivityTransition [mActivityType=");
        sb.append(r0);
        sb.append(", mTransitionType=");
        sb.append(r1);
        sb.append(']');
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r4) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, getTransitionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
