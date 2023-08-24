package expo.modules.camera.tasks;

import android.os.AsyncTask;
import com.facebook.react.uimanager.ViewProps;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import expo.modules.interfaces.barcodescanner.BarCodeScannerInterface;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarCodeScannerAsyncTask.kt */
@Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b¢\u0006\u0002\u0010\u000eJ'\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0016\u0010\u0010\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0011\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/camera/tasks/BarCodeScannerAsyncTask;", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "delegate", "Lexpo/modules/camera/tasks/BarCodeScannerAsyncTaskDelegate;", "barCodeScanner", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerInterface;", "imageData", "", "width", "", "height", ViewProps.ROTATION, "(Lexpo/modules/camera/tasks/BarCodeScannerAsyncTaskDelegate;Lexpo/modules/interfaces/barcodescanner/BarCodeScannerInterface;[BIII)V", "doInBackground", OutcomeEventsTable.COLUMN_NAME_PARAMS, "", "([Ljava/lang/Void;)Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "onPostExecute", "", "result", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BarCodeScannerAsyncTask extends AsyncTask<Void, Void, BarCodeScannerResult> {
    private final BarCodeScannerInterface barCodeScanner;
    private final BarCodeScannerAsyncTaskDelegate delegate;
    private final int height;
    private final byte[] imageData;
    private final int rotation;
    private final int width;

    public BarCodeScannerAsyncTask(BarCodeScannerAsyncTaskDelegate delegate, BarCodeScannerInterface barCodeScanner, byte[] imageData, int r5, int r6, int r7) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(barCodeScanner, "barCodeScanner");
        Intrinsics.checkNotNullParameter(imageData, "imageData");
        this.delegate = delegate;
        this.barCodeScanner = barCodeScanner;
        this.imageData = imageData;
        this.width = r5;
        this.height = r6;
        this.rotation = r7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public BarCodeScannerResult doInBackground(Void... params) {
        Intrinsics.checkNotNullParameter(params, "params");
        if (isCancelled()) {
            return null;
        }
        return this.barCodeScanner.scan(this.imageData, this.width, this.height, this.rotation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(BarCodeScannerResult barCodeScannerResult) {
        super.onPostExecute((BarCodeScannerAsyncTask) barCodeScannerResult);
        if (barCodeScannerResult != null) {
            this.delegate.onBarCodeScanned(barCodeScannerResult);
        }
        this.delegate.onBarCodeScanningTaskCompleted();
    }
}
