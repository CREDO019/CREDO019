package expo.modules.barcodescanner.scanners;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.react.uimanager.ViewProps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import expo.modules.interfaces.barcodescanner.BarCodeScannerSettings;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ZxingBarCodeScanner.kt */
@Metadata(m184d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J*\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m183d2 = {"Lexpo/modules/barcodescanner/scanners/ZxingBarCodeScanner;", "Lexpo/modules/barcodescanner/scanners/ExpoBarCodeScanner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isAvailable", "", "()Z", "mMultiFormatReader", "Lcom/google/zxing/MultiFormatReader;", "generateSourceFromImageData", "Lcom/google/zxing/LuminanceSource;", "imageData", "", "width", "", "height", "scan", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "source", "data", ViewProps.ROTATION, "scanMultiple", "", "bitmap", "Landroid/graphics/Bitmap;", "setSettings", "", "settings", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerSettings;", "Companion", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ZxingBarCodeScanner extends ExpoBarCodeScanner {
    private final boolean isAvailable;
    private final MultiFormatReader mMultiFormatReader;
    public static final Companion Companion = new Companion(null);
    private static final Map<Integer, String> VALID_BARCODE_TYPES = MapsKt.mapOf(TuplesKt.m176to(4096, BarcodeFormat.AZTEC.toString()), TuplesKt.m176to(32, BarcodeFormat.EAN_13.toString()), TuplesKt.m176to(64, BarcodeFormat.EAN_8.toString()), TuplesKt.m176to(256, BarcodeFormat.QR_CODE.toString()), TuplesKt.m176to(2048, BarcodeFormat.PDF_417.toString()), TuplesKt.m176to(1024, BarcodeFormat.UPC_E.toString()), TuplesKt.m176to(16, BarcodeFormat.DATA_MATRIX.toString()), TuplesKt.m176to(2, BarcodeFormat.CODE_39.toString()), TuplesKt.m176to(4, BarcodeFormat.CODE_93.toString()), TuplesKt.m176to(128, BarcodeFormat.ITF.toString()), TuplesKt.m176to(8, BarcodeFormat.CODABAR.toString()), TuplesKt.m176to(1, BarcodeFormat.CODE_128.toString()), TuplesKt.m176to(512, BarcodeFormat.UPC_A.toString()));
    private static final Map<BarcodeFormat, Integer> GMV_FROM_ZXING = MapsKt.mapOf(TuplesKt.m176to(BarcodeFormat.AZTEC, 4096), TuplesKt.m176to(BarcodeFormat.EAN_13, 32), TuplesKt.m176to(BarcodeFormat.EAN_8, 64), TuplesKt.m176to(BarcodeFormat.QR_CODE, 256), TuplesKt.m176to(BarcodeFormat.PDF_417, 2048), TuplesKt.m176to(BarcodeFormat.UPC_E, 1024), TuplesKt.m176to(BarcodeFormat.DATA_MATRIX, 16), TuplesKt.m176to(BarcodeFormat.CODE_39, 2), TuplesKt.m176to(BarcodeFormat.CODE_93, 4), TuplesKt.m176to(BarcodeFormat.ITF, 128), TuplesKt.m176to(BarcodeFormat.CODABAR, 8), TuplesKt.m176to(BarcodeFormat.CODE_128, 1), TuplesKt.m176to(BarcodeFormat.UPC_A, 512));

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZxingBarCodeScanner(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mMultiFormatReader = new MultiFormatReader();
        this.isAvailable = true;
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerInterface
    public List<BarCodeScannerResult> scanMultiple(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int[] r0 = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(r0, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        BarCodeScannerResult scan = scan(new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), r0));
        List<BarCodeScannerResult> listOf = scan == null ? null : CollectionsKt.listOf(scan);
        return listOf == null ? CollectionsKt.emptyList() : listOf;
    }

    @Override // expo.modules.barcodescanner.scanners.ExpoBarCodeScanner
    public boolean isAvailable() {
        return this.isAvailable;
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerInterface
    public BarCodeScannerResult scan(byte[] data, int r9, int r10, int r11) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (r11 == 0) {
            byte[] bArr = new byte[data.length];
            int r1 = 0;
            while (r1 < r10) {
                int r2 = r1 + 1;
                int r3 = 0;
                while (r3 < r9) {
                    int r4 = r3 + 1;
                    int r5 = (r1 * r9) + r3;
                    int r32 = (((r3 * r10) + r10) - r1) - 1;
                    if (r5 >= 0 && r5 < data.length && r32 >= 0 && r32 < data.length) {
                        bArr[r32] = data[r5];
                    }
                    r3 = r4;
                }
                r1 = r2;
            }
            int r92 = r9 + r10;
            r10 = r92 - r10;
            r9 = r92 - r10;
            data = bArr;
        }
        return scan(generateSourceFromImageData(data, r9, r10));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final expo.modules.interfaces.barcodescanner.BarCodeScannerResult scan(com.google.zxing.LuminanceSource r11) {
        /*
            r10 = this;
            r0 = 0
            com.google.zxing.BinaryBitmap r1 = new com.google.zxing.BinaryBitmap     // Catch: java.lang.Throwable -> L16 com.google.zxing.NotFoundException -> L1c
            com.google.zxing.common.HybridBinarizer r2 = new com.google.zxing.common.HybridBinarizer     // Catch: java.lang.Throwable -> L16 com.google.zxing.NotFoundException -> L1c
            r2.<init>(r11)     // Catch: java.lang.Throwable -> L16 com.google.zxing.NotFoundException -> L1c
            com.google.zxing.Binarizer r2 = (com.google.zxing.Binarizer) r2     // Catch: java.lang.Throwable -> L16 com.google.zxing.NotFoundException -> L1c
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L16 com.google.zxing.NotFoundException -> L1c
            com.google.zxing.MultiFormatReader r11 = r10.mMultiFormatReader     // Catch: java.lang.Throwable -> L14 com.google.zxing.NotFoundException -> L1d
            com.google.zxing.Result r11 = r11.decodeWithState(r1)     // Catch: java.lang.Throwable -> L14 com.google.zxing.NotFoundException -> L1d
            goto L1e
        L14:
            r11 = move-exception
            goto L18
        L16:
            r11 = move-exception
            r1 = r0
        L18:
            r11.printStackTrace()
            goto L1d
        L1c:
            r1 = r0
        L1d:
            r11 = r0
        L1e:
            if (r1 == 0) goto L50
            if (r11 != 0) goto L23
            goto L50
        L23:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Map<com.google.zxing.BarcodeFormat, java.lang.Integer> r3 = expo.modules.barcodescanner.scanners.ZxingBarCodeScanner.GMV_FROM_ZXING
            com.google.zxing.BarcodeFormat r4 = r11.getBarcodeFormat()
            java.lang.Object r3 = r3.get(r4)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 != 0) goto L37
            return r0
        L37:
            int r5 = r3.intValue()
            expo.modules.interfaces.barcodescanner.BarCodeScannerResult r0 = new expo.modules.interfaces.barcodescanner.BarCodeScannerResult
            java.lang.String r6 = r11.getText()
            r7 = r2
            java.util.List r7 = (java.util.List) r7
            int r8 = r1.getHeight()
            int r9 = r1.getWidth()
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9)
        L50:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.barcodescanner.scanners.ZxingBarCodeScanner.scan(com.google.zxing.LuminanceSource):expo.modules.interfaces.barcodescanner.BarCodeScannerResult");
    }

    @Override // expo.modules.interfaces.barcodescanner.BarCodeScannerInterface
    public void setSettings(BarCodeScannerSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (areNewAndOldBarCodeTypesEqual(parseBarCodeTypesFromSettings(settings))) {
            return;
        }
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet noneOf = EnumSet.noneOf(BarcodeFormat.class);
        List<Integer> barCodeTypes = getBarCodeTypes();
        if (barCodeTypes != null) {
            for (Number number : barCodeTypes) {
                String str = VALID_BARCODE_TYPES.get(Integer.valueOf(number.intValue()));
                if (str != null) {
                    noneOf.add(BarcodeFormat.valueOf(str));
                }
            }
        }
        EnumMap enumMap2 = enumMap;
        enumMap2.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) noneOf);
        this.mMultiFormatReader.setHints(enumMap2);
    }

    private final LuminanceSource generateSourceFromImageData(byte[] bArr, int r12, int r13) {
        return new PlanarYUVLuminanceSource(bArr, r12, r13, 0, 0, r12, r13, false);
    }

    /* compiled from: ZxingBarCodeScanner.kt */
    @Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m183d2 = {"Lexpo/modules/barcodescanner/scanners/ZxingBarCodeScanner$Companion;", "", "()V", "GMV_FROM_ZXING", "", "Lcom/google/zxing/BarcodeFormat;", "", "VALID_BARCODE_TYPES", "", "expo-barcode-scanner_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
