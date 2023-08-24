package com.rnfs;

import com.facebook.react.bridge.ReadableMap;
import java.net.URL;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class UploadParams {
    public boolean binaryStreamOnly;
    public ReadableMap fields;
    public ArrayList<ReadableMap> files;
    public ReadableMap headers;
    public String method;
    public String name;
    public onUploadBegin onUploadBegin;
    public onUploadComplete onUploadComplete;
    public onUploadProgress onUploadProgress;
    public URL src;

    /* loaded from: classes3.dex */
    public interface onUploadBegin {
        void onUploadBegin();
    }

    /* loaded from: classes3.dex */
    public interface onUploadComplete {
        void onUploadComplete(UploadResult uploadResult);
    }

    /* loaded from: classes3.dex */
    public interface onUploadProgress {
        void onUploadProgress(int r1, int r2);
    }
}
