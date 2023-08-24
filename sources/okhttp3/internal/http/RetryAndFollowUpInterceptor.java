package okhttp3.internal.http;

import androidx.browser.trusted.sharing.ShareTarget;
import androidx.core.app.NotificationCompat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.C5015Response;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;

/* compiled from: RetryAndFollowUpInterceptor.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J(\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0012H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m183d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "exchange", "Lokhttp3/internal/connection/Exchange;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", NotificationCompat.CATEGORY_CALL, "Lokhttp3/internal/connection/RealCall;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", "okhttp"}, m182k = 1, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    public RetryAndFollowUpInterceptor(OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
    }

    @Override // okhttp3.Interceptor
    public C5015Response intercept(Interceptor.Chain chain) throws IOException {
        Exchange interceptorScopedExchange$okhttp;
        Request followUpRequest;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request request$okhttp = realInterceptorChain.getRequest$okhttp();
        RealCall call$okhttp = realInterceptorChain.getCall$okhttp();
        List emptyList = CollectionsKt.emptyList();
        C5015Response c5015Response = null;
        boolean z = true;
        int r8 = 0;
        while (true) {
            call$okhttp.enterNetworkInterceptorExchange(request$okhttp, z);
            try {
                if (call$okhttp.isCanceled()) {
                    throw new IOException("Canceled");
                }
                try {
                    try {
                        C5015Response proceed = realInterceptorChain.proceed(request$okhttp);
                        if (c5015Response != null) {
                            proceed = proceed.newBuilder().priorResponse(c5015Response.newBuilder().body(null).build()).build();
                        }
                        c5015Response = proceed;
                        interceptorScopedExchange$okhttp = call$okhttp.getInterceptorScopedExchange$okhttp();
                        followUpRequest = followUpRequest(c5015Response, interceptorScopedExchange$okhttp);
                    } catch (IOException e) {
                        if (!recover(e, call$okhttp, request$okhttp, !(e instanceof ConnectionShutdownException))) {
                            throw Util.withSuppressed(e, emptyList);
                        }
                        emptyList = CollectionsKt.plus((Collection<? extends IOException>) emptyList, e);
                        call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                        z = false;
                    }
                } catch (RouteException e2) {
                    if (!recover(e2.getLastConnectException(), call$okhttp, request$okhttp, false)) {
                        throw Util.withSuppressed(e2.getFirstConnectException(), emptyList);
                    }
                    emptyList = CollectionsKt.plus((Collection<? extends IOException>) emptyList, e2.getFirstConnectException());
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                    z = false;
                }
                if (followUpRequest == null) {
                    if (interceptorScopedExchange$okhttp != null && interceptorScopedExchange$okhttp.isDuplex$okhttp()) {
                        call$okhttp.timeoutEarlyExit();
                    }
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(false);
                    return c5015Response;
                }
                RequestBody body = followUpRequest.body();
                if (body != null && body.isOneShot()) {
                    call$okhttp.exitNetworkInterceptorExchange$okhttp(false);
                    return c5015Response;
                }
                ResponseBody body2 = c5015Response.body();
                if (body2 != null) {
                    Util.closeQuietly(body2);
                }
                r8++;
                if (r8 > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + r8);
                }
                call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                request$okhttp = followUpRequest;
                z = true;
            } catch (Throwable th) {
                call$okhttp.exitNetworkInterceptorExchange$okhttp(true);
                throw th;
            }
        }
    }

    private final boolean recover(IOException iOException, RealCall realCall, Request request, boolean z) {
        if (this.client.retryOnConnectionFailure()) {
            return !(z && requestIsOneShot(iOException, request)) && isRecoverable(iOException, z) && realCall.retryAfterFailure();
        }
        return false;
    }

    private final boolean requestIsOneShot(IOException iOException, Request request) {
        RequestBody body = request.body();
        return (body != null && body.isOneShot()) || (iOException instanceof FileNotFoundException);
    }

    private final boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private final Request followUpRequest(C5015Response c5015Response, Exchange exchange) throws IOException {
        RealConnection connection$okhttp;
        Route route = (exchange == null || (connection$okhttp = exchange.getConnection$okhttp()) == null) ? null : connection$okhttp.route();
        int code = c5015Response.code();
        String method = c5015Response.request().method();
        if (code != 307 && code != 308) {
            if (code != 401) {
                if (code == 421) {
                    RequestBody body = c5015Response.request().body();
                    if ((body == null || !body.isOneShot()) && exchange != null && exchange.isCoalescedConnection$okhttp()) {
                        exchange.getConnection$okhttp().noCoalescedConnections$okhttp();
                        return c5015Response.request();
                    }
                    return null;
                } else if (code == 503) {
                    C5015Response priorResponse = c5015Response.priorResponse();
                    if ((priorResponse == null || priorResponse.code() != 503) && retryAfter(c5015Response, Integer.MAX_VALUE) == 0) {
                        return c5015Response.request();
                    }
                    return null;
                } else if (code == 407) {
                    Intrinsics.checkNotNull(route);
                    if (route.proxy().type() != Proxy.Type.HTTP) {
                        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                    }
                    return this.client.proxyAuthenticator().authenticate(route, c5015Response);
                } else if (code == 408) {
                    if (this.client.retryOnConnectionFailure()) {
                        RequestBody body2 = c5015Response.request().body();
                        if (body2 == null || !body2.isOneShot()) {
                            C5015Response priorResponse2 = c5015Response.priorResponse();
                            if ((priorResponse2 == null || priorResponse2.code() != 408) && retryAfter(c5015Response, 0) <= 0) {
                                return c5015Response.request();
                            }
                            return null;
                        }
                        return null;
                    }
                    return null;
                } else {
                    switch (code) {
                        case 300:
                        case 301:
                        case 302:
                        case 303:
                            break;
                        default:
                            return null;
                    }
                }
            } else {
                return this.client.authenticator().authenticate(route, c5015Response);
            }
        }
        return buildRedirectRequest(c5015Response, method);
    }

    private final Request buildRedirectRequest(C5015Response c5015Response, String str) {
        String header$default;
        HttpUrl resolve;
        if (!this.client.followRedirects() || (header$default = C5015Response.header$default(c5015Response, "Location", null, 2, null)) == null || (resolve = c5015Response.request().url().resolve(header$default)) == null) {
            return null;
        }
        if (Intrinsics.areEqual(resolve.scheme(), c5015Response.request().url().scheme()) || this.client.followSslRedirects()) {
            Request.Builder newBuilder = c5015Response.request().newBuilder();
            if (HttpMethod.permitsRequestBody(str)) {
                int code = c5015Response.code();
                boolean z = HttpMethod.INSTANCE.redirectsWithBody(str) || code == 308 || code == 307;
                if (HttpMethod.INSTANCE.redirectsToGet(str) && code != 308 && code != 307) {
                    newBuilder.method(ShareTarget.METHOD_GET, null);
                } else {
                    newBuilder.method(str, z ? c5015Response.request().body() : null);
                }
                if (!z) {
                    newBuilder.removeHeader(com.google.common.net.HttpHeaders.TRANSFER_ENCODING);
                    newBuilder.removeHeader("Content-Length");
                    newBuilder.removeHeader("Content-Type");
                }
            }
            if (!Util.canReuseConnectionFor(c5015Response.request().url(), resolve)) {
                newBuilder.removeHeader("Authorization");
            }
            return newBuilder.url(resolve).build();
        }
        return null;
    }

    private final int retryAfter(C5015Response c5015Response, int r5) {
        String header$default = C5015Response.header$default(c5015Response, com.google.common.net.HttpHeaders.RETRY_AFTER, null, 2, null);
        if (header$default != null) {
            if (new Regex("\\d+").matches(header$default)) {
                Integer valueOf = Integer.valueOf(header$default);
                Intrinsics.checkNotNullExpressionValue(valueOf, "Integer.valueOf(header)");
                return valueOf.intValue();
            }
            return Integer.MAX_VALUE;
        }
        return r5;
    }

    /* compiled from: RetryAndFollowUpInterceptor.kt */
    @Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", "", "()V", "MAX_FOLLOW_UPS", "", "okhttp"}, m182k = 1, m181mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
