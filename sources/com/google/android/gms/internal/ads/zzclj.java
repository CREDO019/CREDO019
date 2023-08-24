package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzclj extends SSLSocketFactory {
    final SSLSocketFactory zza = (SSLSocketFactory) SSLSocketFactory.getDefault();
    final /* synthetic */ zzclk zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzclj(zzclk zzclkVar) {
        this.zzb = zzclkVar;
    }

    private final Socket zza(Socket socket) throws SocketException {
        int r1;
        int r0;
        zzclk zzclkVar = this.zzb;
        r1 = zzclkVar.zzr;
        if (r1 > 0) {
            r0 = zzclkVar.zzr;
            socket.setReceiveBufferSize(r0);
        }
        this.zzb.zzs.add(socket);
        return socket;
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int r3) throws IOException {
        Socket createSocket = this.zza.createSocket(str, r3);
        zza(createSocket);
        return createSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        return this.zza.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        return this.zza.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int r3, InetAddress inetAddress, int r5) throws IOException {
        Socket createSocket = this.zza.createSocket(str, r3, inetAddress, r5);
        zza(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int r3) throws IOException {
        Socket createSocket = this.zza.createSocket(inetAddress, r3);
        zza(createSocket);
        return createSocket;
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int r3, InetAddress inetAddress2, int r5) throws IOException {
        Socket createSocket = this.zza.createSocket(inetAddress, r3, inetAddress2, r5);
        zza(createSocket);
        return createSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int r4, boolean z) throws IOException {
        Socket createSocket = this.zza.createSocket(socket, str, r4, z);
        zza(createSocket);
        return createSocket;
    }
}
