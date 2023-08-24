package expo.modules.barcodescanner.scanners;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import expo.modules.barcodescanner.utils.Frame;
import expo.modules.barcodescanner.utils.FrameFactory;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GMVBarCodeScanner.kt */
@Metadata(m184d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J*\u0010\u000b\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/barcodescanner/scanners/GMVBarCodeScanner;", "Lexpo/modules/barcodescanner/scanners/ExpoBarCodeScanner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "barcodeDetector", "Lcom/google/android/gms/vision/barcode/BarcodeDetector;", "kotlin.jvm.PlatformType", "isAvailable", "", "()Z", "scan", "", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "frame", "Lexpo/modules/barcodescanner/utils/Frame;", "data", "", "width", "", "height", ViewProps.ROTATION, "scanMultiple", "bitmap", "Landroid/graphics/Bitmap;", "setSettings", "", "settings", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerSettings;", "Companion", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class GMVBarCodeScanner extends ExpoBarCodeScanner {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "GMVBarCodeScanner";
    private BarcodeDetector barcodeDetector;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GMVBarCodeScanner(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.barcodeDetector = new BarcodeDetector.Builder(getMContext()).setBarcodeFormats(0).build();
    }

    @Override // expo.modules.barcodescanner.scanners.ExpoBarCodeScanner
    public boolean isAvailable() {
        return this.barcodeDetector.isOperational();
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerInterface
    public BarCodeScannerResult scan(byte[] data, int r4, int r5, int r6) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            List<BarCodeScannerResult> scan = scan(FrameFactory.INSTANCE.buildFrame(data, r4, r5, r6));
            if (!scan.isEmpty()) {
                return scan.get(0);
            }
            return null;
        } catch (Exception e) {
            String str = TAG;
            String message = e.getMessage();
            Log.e(str, "Failed to detect barcode: " + message);
            return null;
        }
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerInterface
    public List<BarCodeScannerResult> scanMultiple(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return scan(FrameFactory.INSTANCE.buildFrame(bitmap));
    }

    private final List<BarCodeScannerResult> scan(Frame frame) {
        try {
            SparseArray<Barcode> detect = this.barcodeDetector.detect(frame.getFrame());
            ArrayList arrayList = new ArrayList();
            int width = frame.getDimensions().getWidth();
            int height = frame.getDimensions().getHeight();
            int size = detect.size();
            int r3 = 0;
            while (r3 < size) {
                int r11 = r3 + 1;
                Barcode barcode = detect.get(detect.keyAt(r3));
                ArrayList arrayList2 = new ArrayList();
                Point[] pointArr = barcode.cornerPoints;
                Intrinsics.checkNotNullExpressionValue(pointArr, "barcode.cornerPoints");
                int length = pointArr.length;
                int r7 = 0;
                while (r7 < length) {
                    Point point = pointArr[r7];
                    r7++;
                    arrayList2.addAll(CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(point.x), Integer.valueOf(point.y)}));
                }
                arrayList.add(new BarCodeScannerResult(barcode.format, barcode.rawValue, arrayList2, height, width));
                r3 = r11;
            }
            return arrayList;
        } catch (Exception e) {
            String str = TAG;
            String message = e.getMessage();
            Log.e(str, "Failed to detect barcode: " + message);
            return CollectionsKt.emptyList();
        }
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerInterface
    public void setSettings(BarCodeScannerSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        List<Integer> parseBarCodeTypesFromSettings = parseBarCodeTypesFromSettings(settings);
        if (areNewAndOldBarCodeTypesEqual(parseBarCodeTypesFromSettings)) {
            return;
        }
        int r0 = 0;
        if (parseBarCodeTypesFromSettings != null) {
            Iterator<T> it = parseBarCodeTypesFromSettings.iterator();
            if (!it.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object next = it.next();
            while (it.hasNext()) {
                next = Integer.valueOf(((Number) next).intValue() | ((Number) it.next()).intValue());
            }
            Integer num = (Integer) next;
            if (num != null) {
                r0 = num.intValue();
            }
        }
        setBarCodeTypes(parseBarCodeTypesFromSettings);
        this.barcodeDetector.release();
        this.barcodeDetector = new BarcodeDetector.Builder(getMContext()).setBarcodeFormats(r0).build();
    }

    /* compiled from: GMVBarCodeScanner.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/barcodescanner/scanners/GMVBarCodeScanner$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
