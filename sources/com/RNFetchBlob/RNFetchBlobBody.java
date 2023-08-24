package com.RNFetchBlob;

import android.net.Uri;
import android.util.Base64;
import androidx.webkit.internal.AssetHelper;
import com.RNFetchBlob.RNFetchBlobReq;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.apache.commons.p028io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class RNFetchBlobBody extends RequestBody {
    private File bodyCache;
    private ReadableArray form;
    private String mTaskId;
    private MediaType mime;
    private String rawBody;
    private InputStream requestStream;
    private RNFetchBlobReq.RequestType requestType;
    private long contentLength = 0;
    int reported = 0;
    private Boolean chunkedEncoding = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobBody(String str) {
        this.mTaskId = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobBody chunkedEncoding(boolean z) {
        this.chunkedEncoding = Boolean.valueOf(z);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobBody setMIME(MediaType mediaType) {
        this.mime = mediaType;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobBody setRequestType(RNFetchBlobReq.RequestType requestType) {
        this.requestType = requestType;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobBody setBody(String str) {
        this.rawBody = str;
        if (str == null) {
            this.rawBody = "";
            this.requestType = RNFetchBlobReq.RequestType.AsIs;
        }
        try {
            int r3 = C09211.$SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType[this.requestType.ordinal()];
            if (r3 == 1) {
                InputStream requestStream = getRequestStream();
                this.requestStream = requestStream;
                this.contentLength = requestStream.available();
            } else if (r3 == 2) {
                this.contentLength = this.rawBody.getBytes().length;
                this.requestStream = new ByteArrayInputStream(this.rawBody.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
            RNFetchBlobUtils.emitWarningEvent("RNFetchBlob failed to create single content request body :" + e.getLocalizedMessage() + IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.RNFetchBlob.RNFetchBlobBody$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C09211 {
        static final /* synthetic */ int[] $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType;

        static {
            int[] r0 = new int[RNFetchBlobReq.RequestType.values().length];
            $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType = r0;
            try {
                r0[RNFetchBlobReq.RequestType.SingleFile.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType[RNFetchBlobReq.RequestType.AsIs.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$RNFetchBlob$RNFetchBlobReq$RequestType[RNFetchBlobReq.RequestType.Others.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RNFetchBlobBody setBody(ReadableArray readableArray) {
        this.form = readableArray;
        try {
            this.bodyCache = createMultipartBodyCache();
            this.requestStream = new FileInputStream(this.bodyCache);
            this.contentLength = this.bodyCache.length();
        } catch (Exception e) {
            e.printStackTrace();
            RNFetchBlobUtils.emitWarningEvent("RNFetchBlob failed to create request multipart body :" + e.getLocalizedMessage());
        }
        return this;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        if (this.chunkedEncoding.booleanValue()) {
            return -1L;
        }
        return this.contentLength;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.mime;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) {
        try {
            pipeStreamToSink(this.requestStream, bufferedSink);
        } catch (Exception e) {
            RNFetchBlobUtils.emitWarningEvent(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean clearRequestBody() {
        try {
            File file = this.bodyCache;
            if (file == null || !file.exists()) {
                return true;
            }
            this.bodyCache.delete();
            return true;
        } catch (Exception e) {
            RNFetchBlobUtils.emitWarningEvent(e.getLocalizedMessage());
            return false;
        }
    }

    private InputStream getRequestStream() throws Exception {
        if (this.rawBody.startsWith(RNFetchBlobConst.FILE_PREFIX)) {
            String normalizePath = RNFetchBlobFS.normalizePath(this.rawBody.substring(19));
            if (RNFetchBlobFS.isAsset(normalizePath)) {
                try {
                    return C0908RNFetchBlob.RCTContext.getAssets().open(normalizePath.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, ""));
                } catch (Exception e) {
                    throw new Exception("error when getting request stream from asset : " + e.getLocalizedMessage());
                }
            }
            File file = new File(RNFetchBlobFS.normalizePath(normalizePath));
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                return new FileInputStream(file);
            } catch (Exception e2) {
                throw new Exception("error when getting request stream: " + e2.getLocalizedMessage());
            }
        } else if (this.rawBody.startsWith(RNFetchBlobConst.CONTENT_PREFIX)) {
            String substring = this.rawBody.substring(22);
            try {
                return C0908RNFetchBlob.RCTContext.getContentResolver().openInputStream(Uri.parse(substring));
            } catch (Exception e3) {
                throw new Exception("error when getting request stream for content URI: " + substring, e3);
            }
        } else {
            try {
                return new ByteArrayInputStream(Base64.decode(this.rawBody, 0));
            } catch (Exception e4) {
                throw new Exception("error when getting request stream: " + e4.getLocalizedMessage());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x016f, code lost:
        if (r10 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.io.File createMultipartBodyCache() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 490
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.RNFetchBlobBody.createMultipartBodyCache():java.io.File");
    }

    private void pipeStreamToSink(InputStream inputStream, BufferedSink bufferedSink) throws IOException {
        byte[] bArr = new byte[10240];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, 10240);
            if (read > 0) {
                bufferedSink.write(bArr, 0, read);
                j += read;
                emitUploadProgress(j);
            } else {
                inputStream.close();
                return;
            }
        }
    }

    private void pipeStreamToFileStream(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[10240];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return;
            }
        }
    }

    private ArrayList<FormField> countFormDataLength() throws IOException {
        int length;
        long j;
        ArrayList<FormField> arrayList = new ArrayList<>();
        ReactApplicationContext reactApplicationContext = C0908RNFetchBlob.RCTContext;
        long j2 = 0;
        for (int r5 = 0; r5 < this.form.size(); r5++) {
            FormField formField = new FormField(this.form.getMap(r5));
            arrayList.add(formField);
            if (formField.data == null) {
                RNFetchBlobUtils.emitWarningEvent("RNFetchBlob multipart request builder has found a field without `data` property, the field `" + formField.name + "` will be removed implicitly.");
            } else {
                if (formField.filename != null) {
                    String str = formField.data;
                    if (str.startsWith(RNFetchBlobConst.FILE_PREFIX)) {
                        String normalizePath = RNFetchBlobFS.normalizePath(str.substring(19));
                        if (RNFetchBlobFS.isAsset(normalizePath)) {
                            try {
                                length = reactApplicationContext.getAssets().open(normalizePath.replace(RNFetchBlobConst.FILE_PREFIX_BUNDLE_ASSET, "")).available();
                            } catch (IOException e) {
                                RNFetchBlobUtils.emitWarningEvent(e.getLocalizedMessage());
                            }
                        } else {
                            j = new File(RNFetchBlobFS.normalizePath(normalizePath)).length();
                            j2 += j;
                        }
                    } else if (str.startsWith(RNFetchBlobConst.CONTENT_PREFIX)) {
                        String substring = str.substring(22);
                        InputStream inputStream = null;
                        try {
                            try {
                                inputStream = reactApplicationContext.getContentResolver().openInputStream(Uri.parse(substring));
                                j2 += inputStream.available();
                                if (inputStream == null) {
                                }
                            } catch (Exception e2) {
                                RNFetchBlobUtils.emitWarningEvent("Failed to estimate form data length from content URI:" + substring + ", " + e2.getLocalizedMessage());
                                if (inputStream == null) {
                                }
                            }
                            inputStream.close();
                        } catch (Throwable th) {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    } else {
                        length = Base64.decode(str, 0).length;
                    }
                } else {
                    length = formField.data.getBytes().length;
                }
                j = length;
                j2 += j;
            }
        }
        this.contentLength = j2;
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FormField {
        public String data;
        String filename;
        String mime;
        public String name;

        FormField(ReadableMap readableMap) {
            if (readableMap.hasKey("name")) {
                this.name = readableMap.getString("name");
            }
            if (readableMap.hasKey("filename")) {
                this.filename = readableMap.getString("filename");
            }
            if (readableMap.hasKey(SessionDescription.ATTR_TYPE)) {
                this.mime = readableMap.getString(SessionDescription.ATTR_TYPE);
            } else {
                this.mime = this.filename == null ? AssetHelper.DEFAULT_MIME_TYPE : "application/octet-stream";
            }
            if (readableMap.hasKey("data")) {
                this.data = readableMap.getString("data");
            }
        }
    }

    private void emitUploadProgress(long j) {
        RNFetchBlobProgressConfig reportUploadProgress = RNFetchBlobReq.getReportUploadProgress(this.mTaskId);
        if (reportUploadProgress != null) {
            long j2 = this.contentLength;
            if (j2 == 0 || !reportUploadProgress.shouldReport(((float) j) / ((float) j2))) {
                return;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putString("taskId", this.mTaskId);
            createMap.putString("written", String.valueOf(j));
            createMap.putString("total", String.valueOf(this.contentLength));
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) C0908RNFetchBlob.RCTContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNFetchBlobConst.EVENT_UPLOAD_PROGRESS, createMap);
        }
    }
}
