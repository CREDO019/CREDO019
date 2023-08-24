package expo.modules.interfaces.barcodescanner;

import android.graphics.Bitmap;
import java.util.List;

/* loaded from: classes4.dex */
public interface BarCodeScannerInterface {
    BarCodeScannerResult scan(byte[] bArr, int r2, int r3, int r4);

    List<BarCodeScannerResult> scanMultiple(Bitmap bitmap);

    void setSettings(BarCodeScannerSettings barCodeScannerSettings);
}
