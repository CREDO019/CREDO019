package expo.modules.interfaces.facedetector;

import android.net.Uri;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes4.dex */
public interface FaceDetectorInterface {
    void detectFaces(Uri uri, FacesDetectionCompleted facesDetectionCompleted, FaceDetectionError faceDetectionError) throws IOException;

    void detectFaces(byte[] bArr, int r2, int r3, int r4, boolean z, double d, double d2, FacesDetectionCompleted facesDetectionCompleted, FaceDetectionError faceDetectionError, FaceDetectionSkipped faceDetectionSkipped);

    void release();

    void setSettings(Map<String, Object> map);
}