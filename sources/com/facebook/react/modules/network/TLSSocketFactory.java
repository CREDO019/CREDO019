package com.facebook.react.modules.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class TLSSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory delegate;

    public TLSSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, null, null);
        this.delegate = sSLContext.getSocketFactory();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int r4, boolean z) throws IOException {
        return enableTLSOnSocket(this.delegate.createSocket(socket, str, r4, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int r3) throws IOException, UnknownHostException {
        return enableTLSOnSocket(this.delegate.createSocket(str, r3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int r3, InetAddress inetAddress, int r5) throws IOException, UnknownHostException {
        return enableTLSOnSocket(this.delegate.createSocket(str, r3, inetAddress, r5));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int r3) throws IOException {
        return enableTLSOnSocket(this.delegate.createSocket(inetAddress, r3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int r3, InetAddress inetAddress2, int r5) throws IOException {
        return enableTLSOnSocket(this.delegate.createSocket(inetAddress, r3, inetAddress2, r5));
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if (socket != null && (socket instanceof SSLSocket)) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        }
        return socket;
    }
}
