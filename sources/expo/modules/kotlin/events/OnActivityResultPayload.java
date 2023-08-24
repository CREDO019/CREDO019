package expo.modules.kotlin.events;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnActivityResultPayload.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/kotlin/events/OnActivityResultPayload;", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "(IILandroid/content/Intent;)V", "getData", "()Landroid/content/Intent;", "getRequestCode", "()I", "getResultCode", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class OnActivityResultPayload {
    private final Intent data;
    private final int requestCode;
    private final int resultCode;

    public static /* synthetic */ OnActivityResultPayload copy$default(OnActivityResultPayload onActivityResultPayload, int r1, int r2, Intent intent, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            r1 = onActivityResultPayload.requestCode;
        }
        if ((r4 & 2) != 0) {
            r2 = onActivityResultPayload.resultCode;
        }
        if ((r4 & 4) != 0) {
            intent = onActivityResultPayload.data;
        }
        return onActivityResultPayload.copy(r1, r2, intent);
    }

    public final int component1() {
        return this.requestCode;
    }

    public final int component2() {
        return this.resultCode;
    }

    public final Intent component3() {
        return this.data;
    }

    public final OnActivityResultPayload copy(int r2, int r3, Intent intent) {
        return new OnActivityResultPayload(r2, r3, intent);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnActivityResultPayload) {
            OnActivityResultPayload onActivityResultPayload = (OnActivityResultPayload) obj;
            return this.requestCode == onActivityResultPayload.requestCode && this.resultCode == onActivityResultPayload.resultCode && Intrinsics.areEqual(this.data, onActivityResultPayload.data);
        }
        return false;
    }

    public int hashCode() {
        int r0 = ((this.requestCode * 31) + this.resultCode) * 31;
        Intent intent = this.data;
        return r0 + (intent == null ? 0 : intent.hashCode());
    }

    public String toString() {
        int r0 = this.requestCode;
        int r1 = this.resultCode;
        Intent intent = this.data;
        return "OnActivityResultPayload(requestCode=" + r0 + ", resultCode=" + r1 + ", data=" + intent + ")";
    }

    public OnActivityResultPayload(int r1, int r2, Intent intent) {
        this.requestCode = r1;
        this.resultCode = r2;
        this.data = intent;
    }

    public final Intent getData() {
        return this.data;
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    public final int getResultCode() {
        return this.resultCode;
    }
}
