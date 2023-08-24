package com.onesignal;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes3.dex */
public class TLS12SocketFactory extends SSLSocketFactory {
    SSLSocketFactory sslSocketFactory;

    public TLS12SocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.sslSocketFactory = sSLSocketFactory;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.sslSocketFactory.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.sslSocketFactory.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return enableTLS(this.sslSocketFactory.createSocket());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int r4, boolean z) throws IOException {
        return enableTLS(this.sslSocketFactory.createSocket(socket, str, r4, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int r3) throws IOException {
        return enableTLS(this.sslSocketFactory.createSocket(str, r3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int r3, InetAddress inetAddress, int r5) throws IOException {
        return enableTLS(this.sslSocketFactory.createSocket(str, r3, inetAddress, r5));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int r3) throws IOException {
        return enableTLS(this.sslSocketFactory.createSocket(inetAddress, r3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int r3, InetAddress inetAddress2, int r5) throws IOException {
        return enableTLS(this.sslSocketFactory.createSocket(inetAddress, r3, inetAddress2, r5));
    }

    private Socket enableTLS(Socket socket) {
        if (socket instanceof SSLSocket) {
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1.2"});
        }
        return socket;
    }
}
