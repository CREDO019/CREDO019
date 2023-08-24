package expo.modules.camera.tasks;

import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import kotlin.Metadata;

/* compiled from: BarCodeScannerAsyncTaskDelegate.kt */
@Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/camera/tasks/BarCodeScannerAsyncTaskDelegate;", "", "onBarCodeScanned", "", "barCode", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "onBarCodeScanningTaskCompleted", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public interface BarCodeScannerAsyncTaskDelegate {
    void onBarCodeScanned(BarCodeScannerResult barCodeScannerResult);

    void onBarCodeScanningTaskCompleted();
}
