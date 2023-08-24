package com.rnfs;

import com.facebook.react.bridge.ReadableMap;
import java.io.File;
import java.net.URL;
import java.util.Map;

/* loaded from: classes3.dex */
public class DownloadParams {
    public int connectionTimeout;
    public File dest;
    public ReadableMap headers;
    public OnDownloadBegin onDownloadBegin;
    public OnDownloadProgress onDownloadProgress;
    public OnTaskCompleted onTaskCompleted;
    public float progressDivider;
    public int progressInterval;
    public int readTimeout;
    public URL src;

    /* loaded from: classes3.dex */
    public interface OnDownloadBegin {
        void onDownloadBegin(int r1, long j, Map<String, String> map);
    }

    /* loaded from: classes3.dex */
    public interface OnDownloadProgress {
        void onDownloadProgress(long j, long j2);
    }

    /* loaded from: classes3.dex */
    public interface OnTaskCompleted {
        void onTaskCompleted(DownloadResult downloadResult);
    }
}
