package expo.modules.barcodescanner.scanners;

import android.content.Context;
import expo.modules.interfaces.barcodescanner.BarCodeScannerInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExpoBarCodeScanner.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\r2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u0016R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0004¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/barcodescanner/scanners/ExpoBarCodeScanner;", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerInterface;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "barCodeTypes", "", "", "getBarCodeTypes", "()Ljava/util/List;", "setBarCodeTypes", "(Ljava/util/List;)V", "isAvailable", "", "()Z", "getMContext", "()Landroid/content/Context;", "setMContext", "areNewAndOldBarCodeTypesEqual", "newBarCodeTypes", "parseBarCodeTypesFromSettings", "settings", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerSettings;", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class ExpoBarCodeScanner implements BarCodeScannerInterface {
    private List<Integer> barCodeTypes;
    private Context mContext;

    public abstract boolean isAvailable();

    public ExpoBarCodeScanner(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getMContext() {
        return this.mContext;
    }

    protected final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<Integer> getBarCodeTypes() {
        return this.barCodeTypes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setBarCodeTypes(List<Integer> list) {
        this.barCodeTypes = list;
    }

    public final boolean areNewAndOldBarCodeTypesEqual(List<Integer> list) {
        List<Integer> list2 = this.barCodeTypes;
        if (list2 == null) {
            return false;
        }
        HashSet hashSet = CollectionsKt.toHashSet(list2);
        HashSet hashSet2 = list == null ? null : CollectionsKt.toHashSet(list);
        if (hashSet2 == null || hashSet.size() != hashSet2.size()) {
            return false;
        }
        hashSet.removeAll(hashSet2);
        return hashSet.isEmpty();
    }

    public final List<Integer> parseBarCodeTypesFromSettings(BarCodeScannerSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Object types = settings.getTypes();
        if (types == null || !(types instanceof List)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : (Iterable) types) {
            if (obj instanceof Number) {
                arrayList.add(obj);
            }
        }
        ArrayList<Number> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (Number number : arrayList2) {
            arrayList3.add(Integer.valueOf(number.intValue()));
        }
        return arrayList3;
    }
}
