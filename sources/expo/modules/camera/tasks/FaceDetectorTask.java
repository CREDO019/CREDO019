package expo.modules.camera.tasks;

import expo.modules.interfaces.facedetector.FaceDetectionError;
import expo.modules.interfaces.facedetector.FaceDetectionSkipped;
import expo.modules.interfaces.facedetector.FaceDetectorInterface;
import expo.modules.interfaces.facedetector.FacesDetectionCompleted;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceDetectorTask.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/camera/tasks/FaceDetectorTask;", "", "mDelegate", "Lexpo/modules/camera/tasks/FaceDetectorAsyncTaskDelegate;", "mFaceDetector", "Lexpo/modules/interfaces/facedetector/FaceDetectorInterface;", "mImageData", "", "mWidth", "", "mHeight", "mRotation", "mMirrored", "", "mScaleX", "", "mScaleY", "(Lexpo/modules/camera/tasks/FaceDetectorAsyncTaskDelegate;Lexpo/modules/interfaces/facedetector/FaceDetectorInterface;[BIIIZDD)V", "execute", "", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FaceDetectorTask {
    private final FaceDetectorAsyncTaskDelegate mDelegate;
    private final FaceDetectorInterface mFaceDetector;
    private final int mHeight;
    private final byte[] mImageData;
    private final boolean mMirrored;
    private final int mRotation;
    private final double mScaleX;
    private final double mScaleY;
    private final int mWidth;

    public FaceDetectorTask(FaceDetectorAsyncTaskDelegate mDelegate, FaceDetectorInterface mFaceDetector, byte[] mImageData, int r5, int r6, int r7, boolean z, double d, double d2) {
        Intrinsics.checkNotNullParameter(mDelegate, "mDelegate");
        Intrinsics.checkNotNullParameter(mFaceDetector, "mFaceDetector");
        Intrinsics.checkNotNullParameter(mImageData, "mImageData");
        this.mDelegate = mDelegate;
        this.mFaceDetector = mFaceDetector;
        this.mImageData = mImageData;
        this.mWidth = r5;
        this.mHeight = r6;
        this.mRotation = r7;
        this.mMirrored = z;
        this.mScaleX = d;
        this.mScaleY = d2;
    }

    public final void execute() {
        this.mFaceDetector.detectFaces(this.mImageData, this.mWidth, this.mHeight, this.mRotation, this.mMirrored, this.mScaleX, this.mScaleY, new FacesDetectionCompleted() { // from class: expo.modules.camera.tasks.FaceDetectorTask$$ExternalSyntheticLambda2
            @Override // expo.modules.interfaces.facedetector.FacesDetectionCompleted
            public final void detectionCompleted(ArrayList arrayList) {
                FaceDetectorTask.m1627execute$lambda2(FaceDetectorTask.this, arrayList);
            }
        }, new FaceDetectionError() { // from class: expo.modules.camera.tasks.FaceDetectorTask$$ExternalSyntheticLambda0
            @Override // expo.modules.interfaces.facedetector.FaceDetectionError
            public final void onError(Throwable th) {
                FaceDetectorTask.m1628execute$lambda3(FaceDetectorTask.this, th);
            }
        }, new FaceDetectionSkipped() { // from class: expo.modules.camera.tasks.FaceDetectorTask$$ExternalSyntheticLambda1
            @Override // expo.modules.interfaces.facedetector.FaceDetectionSkipped
            public final void onSkipped(String str) {
                FaceDetectorTask.m1629execute$lambda4(FaceDetectorTask.this, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execute$lambda-2  reason: not valid java name */
    public static final void m1627execute$lambda2(FaceDetectorTask this$0, ArrayList result) {
        Unit unit;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (result == null) {
            unit = null;
        } else {
            FaceDetectorAsyncTaskDelegate faceDetectorAsyncTaskDelegate = this$0.mDelegate;
            Intrinsics.checkNotNullExpressionValue(result, "result");
            faceDetectorAsyncTaskDelegate.onFacesDetected(result);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            this$0.mDelegate.onFaceDetectionError(this$0.mFaceDetector);
        }
        this$0.mDelegate.onFaceDetectingTaskCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execute$lambda-3  reason: not valid java name */
    public static final void m1628execute$lambda3(FaceDetectorTask this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mDelegate.onFaceDetectionError(this$0.mFaceDetector);
        this$0.mDelegate.onFaceDetectingTaskCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: execute$lambda-4  reason: not valid java name */
    public static final void m1629execute$lambda4(FaceDetectorTask this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mDelegate.onFaceDetectingTaskCompleted();
    }
}
