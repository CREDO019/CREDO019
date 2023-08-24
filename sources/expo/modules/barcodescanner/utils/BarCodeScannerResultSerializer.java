package expo.modules.barcodescanner.utils;

import android.os.Bundle;
import android.util.Pair;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.internal.progressionUtil;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarCodeScannerResultSerializer.kt */
@Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JB\u0010\u0003\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007\u0012\u0004\u0012\u00020\u00060\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/barcodescanner/utils/BarCodeScannerResultSerializer;", "", "()V", "getCornerPointsAndBoundingBox", "Landroid/util/Pair;", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "cornerPoints", "", "", "boundingBox", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult$BoundingBox;", "density", "", "getPoint", "x", "y", "getSize", "width", "height", "toBundle", "result", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerResultSerializer {
    public static final BarCodeScannerResultSerializer INSTANCE = new BarCodeScannerResultSerializer();

    private BarCodeScannerResultSerializer() {
    }

    public final Bundle toBundle(BarCodeScannerResult result, float f) {
        Intrinsics.checkNotNullParameter(result, "result");
        Bundle bundle = new Bundle();
        bundle.putString("data", result.getValue());
        bundle.putInt(SessionDescription.ATTR_TYPE, result.getType());
        BarCodeScannerResultSerializer barCodeScannerResultSerializer = INSTANCE;
        List<Integer> cornerPoints = result.getCornerPoints();
        Intrinsics.checkNotNullExpressionValue(cornerPoints, "result.cornerPoints");
        BarCodeScannerResult.BoundingBox boundingBox = result.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue(boundingBox, "result.boundingBox");
        Pair<ArrayList<Bundle>, Bundle> cornerPointsAndBoundingBox = barCodeScannerResultSerializer.getCornerPointsAndBoundingBox(cornerPoints, boundingBox, f);
        bundle.putParcelableArrayList("cornerPoints", (ArrayList) cornerPointsAndBoundingBox.first);
        bundle.putBundle("bounds", (Bundle) cornerPointsAndBoundingBox.second);
        return bundle;
    }

    private final Pair<ArrayList<Bundle>, Bundle> getCornerPointsAndBoundingBox(List<Integer> list, BarCodeScannerResult.BoundingBox boundingBox, float f) {
        ArrayList arrayList = new ArrayList();
        int r2 = 0;
        int progressionLastElement = progressionUtil.getProgressionLastElement(0, list.size() - 1, 2);
        if (progressionLastElement >= 0) {
            while (true) {
                int r3 = r2 + 2;
                arrayList.add(getPoint(list.get(r2).intValue() / f, list.get(r2 + 1).intValue() / f));
                if (r2 == progressionLastElement) {
                    break;
                }
                r2 = r3;
            }
        }
        Bundle bundle = new Bundle();
        BarCodeScannerResultSerializer barCodeScannerResultSerializer = INSTANCE;
        bundle.putParcelable("origin", barCodeScannerResultSerializer.getPoint(boundingBox.getX() / f, boundingBox.getY() / f));
        bundle.putParcelable("size", barCodeScannerResultSerializer.getSize(boundingBox.getWidth() / f, boundingBox.getHeight() / f));
        return new Pair<>(arrayList, bundle);
    }

    private final Bundle getSize(float f, float f2) {
        Bundle bundle = new Bundle();
        bundle.putFloat("width", f);
        bundle.putFloat("height", f2);
        return bundle;
    }

    private final Bundle getPoint(float f, float f2) {
        Bundle bundle = new Bundle();
        bundle.putFloat("x", f);
        bundle.putFloat("y", f2);
        return bundle;
    }
}
