package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import com.google.common.base.Predicate;
import com.onesignal.OSInAppMessageContentKt;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface HttpDataSource extends DataSource {
    public static final Predicate<String> REJECT_PAYWALL_TYPES = new Predicate() { // from class: com.google.android.exoplayer2.upstream.HttpDataSource$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Predicate
        public final boolean apply(Object obj) {
            return HttpDataSource.CC.lambda$static$0((String) obj);
        }
    };

    void clearAllRequestProperties();

    void clearRequestProperty(String str);

    @Override // 
    void close() throws HttpDataSourceException;

    int getResponseCode();

    @Override // 
    Map<String, List<String>> getResponseHeaders();

    @Override // 
    long open(DataSpec dataSpec) throws HttpDataSourceException;

    @Override // 
    int read(byte[] bArr, int r2, int r3) throws HttpDataSourceException;

    void setRequestProperty(String str, String str2);

    /* loaded from: classes2.dex */
    public interface Factory extends DataSource.Factory {
        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        HttpDataSource createDataSource();

        Factory setDefaultRequestProperties(Map<String, String> map);

        /* renamed from: com.google.android.exoplayer2.upstream.HttpDataSource$Factory$-CC  reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
        }
    }

    /* loaded from: classes2.dex */
    public static final class RequestProperties {
        private final Map<String, String> requestProperties = new HashMap();
        private Map<String, String> requestPropertiesSnapshot;

        public synchronized void set(String str, String str2) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.put(str, str2);
        }

        public synchronized void set(Map<String, String> map) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.putAll(map);
        }

        public synchronized void clearAndSet(Map<String, String> map) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.clear();
            this.requestProperties.putAll(map);
        }

        public synchronized void remove(String str) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.remove(str);
        }

        public synchronized void clear() {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.clear();
        }

        public synchronized Map<String, String> getSnapshot() {
            if (this.requestPropertiesSnapshot == null) {
                this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));
            }
            return this.requestPropertiesSnapshot;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class BaseFactory implements Factory {
        private final RequestProperties defaultRequestProperties = new RequestProperties();

        protected abstract HttpDataSource createDataSourceInternal(RequestProperties requestProperties);

        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        public final HttpDataSource createDataSource() {
            return createDataSourceInternal(this.defaultRequestProperties);
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }
    }

    /* renamed from: com.google.android.exoplayer2.upstream.HttpDataSource$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        static {
            Predicate<String> predicate = HttpDataSource.REJECT_PAYWALL_TYPES;
        }

        public static /* synthetic */ boolean lambda$static$0(String str) {
            if (str == null) {
                return false;
            }
            String lowerCase = Ascii.toLowerCase(str);
            if (TextUtils.isEmpty(lowerCase)) {
                return false;
            }
            return ((lowerCase.contains("text") && !lowerCase.contains(MimeTypes.TEXT_VTT)) || lowerCase.contains(OSInAppMessageContentKt.HTML) || lowerCase.contains("xml")) ? false : true;
        }
    }

    /* loaded from: classes2.dex */
    public static class HttpDataSourceException extends DataSourceException {
        public static final int TYPE_CLOSE = 3;
        public static final int TYPE_OPEN = 1;
        public static final int TYPE_READ = 2;
        public final DataSpec dataSpec;
        public final int type;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Type {
        }

        private static int assignErrorCode(int r1, int r2) {
            return (r1 == 2000 && r2 == 1) ? PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED : r1;
        }

        public static HttpDataSourceException createForIOException(IOException iOException, DataSpec dataSpec, int r5) {
            int r0;
            String message = iOException.getMessage();
            if (iOException instanceof SocketTimeoutException) {
                r0 = PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT;
            } else if (iOException instanceof InterruptedIOException) {
                r0 = 1004;
            } else {
                r0 = (message == null || !Ascii.toLowerCase(message).matches("cleartext.*not permitted.*")) ? PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED : PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED;
            }
            if (r0 == 2007) {
                return new CleartextNotPermittedException(iOException, dataSpec);
            }
            return new HttpDataSourceException(iOException, dataSpec, r0, r5);
        }

        @Deprecated
        public HttpDataSourceException(DataSpec dataSpec, int r3) {
            this(dataSpec, 2000, r3);
        }

        public HttpDataSourceException(DataSpec dataSpec, int r2, int r3) {
            super(assignErrorCode(r2, r3));
            this.dataSpec = dataSpec;
            this.type = r3;
        }

        @Deprecated
        public HttpDataSourceException(String str, DataSpec dataSpec, int r4) {
            this(str, dataSpec, 2000, r4);
        }

        public HttpDataSourceException(String str, DataSpec dataSpec, int r3, int r4) {
            super(str, assignErrorCode(r3, r4));
            this.dataSpec = dataSpec;
            this.type = r4;
        }

        @Deprecated
        public HttpDataSourceException(IOException iOException, DataSpec dataSpec, int r4) {
            this(iOException, dataSpec, 2000, r4);
        }

        public HttpDataSourceException(IOException iOException, DataSpec dataSpec, int r3, int r4) {
            super(iOException, assignErrorCode(r3, r4));
            this.dataSpec = dataSpec;
            this.type = r4;
        }

        @Deprecated
        public HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec, int r10) {
            this(str, iOException, dataSpec, 2000, r10);
        }

        public HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec, int r4, int r5) {
            super(str, iOException, assignErrorCode(r4, r5));
            this.dataSpec = dataSpec;
            this.type = r5;
        }
    }

    /* loaded from: classes2.dex */
    public static final class CleartextNotPermittedException extends HttpDataSourceException {
        public CleartextNotPermittedException(IOException iOException, DataSpec dataSpec) {
            super("Cleartext HTTP traffic not permitted. See https://exoplayer.dev/issues/cleartext-not-permitted", iOException, dataSpec, PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED, 1);
        }
    }

    /* loaded from: classes2.dex */
    public static final class InvalidContentTypeException extends HttpDataSourceException {
        public final String contentType;

        public InvalidContentTypeException(String str, DataSpec dataSpec) {
            super("Invalid content type: " + str, dataSpec, (int) PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE, 1);
            this.contentType = str;
        }
    }

    /* loaded from: classes2.dex */
    public static final class InvalidResponseCodeException extends HttpDataSourceException {
        public final Map<String, List<String>> headerFields;
        public final byte[] responseBody;
        public final int responseCode;
        public final String responseMessage;

        @Deprecated
        public InvalidResponseCodeException(int r8, Map<String, List<String>> map, DataSpec dataSpec) {
            this(r8, null, null, map, dataSpec, Util.EMPTY_BYTE_ARRAY);
        }

        @Deprecated
        public InvalidResponseCodeException(int r8, String str, Map<String, List<String>> map, DataSpec dataSpec) {
            this(r8, str, null, map, dataSpec, Util.EMPTY_BYTE_ARRAY);
        }

        public InvalidResponseCodeException(int r9, String str, IOException iOException, Map<String, List<String>> map, DataSpec dataSpec, byte[] bArr) {
            super("Response code: " + r9, iOException, dataSpec, PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS, 1);
            this.responseCode = r9;
            this.responseMessage = str;
            this.headerFields = map;
            this.responseBody = bArr;
        }
    }
}
