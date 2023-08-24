package expo.modules.kotlin.activityresult;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataPersistor.kt */
@Metadata(m184d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0005H\u0002\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m183d2 = {"EXPIRATION_TIME", "", "EXPIRE_KEY", "", "toBase64", "Landroid/os/Bundle;", "toBundle", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DataPersistorKt {
    public static final long EXPIRATION_TIME = 300000;
    public static final String EXPIRE_KEY = "expire";

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bundle toBundle(String str) {
        byte[] decode = Base64.decode(str, 0);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(decode, 0, decode.length);
        obtain.setDataPosition(0);
        Bundle readBundle = obtain.readBundle(null);
        obtain.recycle();
        return readBundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toBase64(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        String encodeToString = Base64.encodeToString(marshall, 0);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(bytes, 0)");
        return encodeToString;
    }
}
