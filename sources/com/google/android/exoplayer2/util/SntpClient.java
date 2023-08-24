package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

/* loaded from: classes2.dex */
public final class SntpClient {
    public static final String DEFAULT_NTP_HOST = "time.android.com";
    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 2208988800L;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int TIMEOUT_MS = 10000;
    private static final int TRANSMIT_TIME_OFFSET = 40;
    private static long elapsedRealtimeOffsetMs = 0;
    private static boolean isInitialized = false;
    private static String ntpHost = "time.android.com";
    private static final Object loaderLock = new Object();
    private static final Object valueLock = new Object();

    /* loaded from: classes2.dex */
    public interface InitializationCallback {
        void onInitializationFailed(IOException iOException);

        void onInitialized();
    }

    static /* synthetic */ long access$400() throws IOException {
        return loadNtpTimeOffsetMs();
    }

    private SntpClient() {
    }

    public static String getNtpHost() {
        String str;
        synchronized (valueLock) {
            str = ntpHost;
        }
        return str;
    }

    public static void setNtpHost(String str) {
        synchronized (valueLock) {
            if (!ntpHost.equals(str)) {
                ntpHost = str;
                isInitialized = false;
            }
        }
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (valueLock) {
            z = isInitialized;
        }
        return z;
    }

    public static long getElapsedRealtimeOffsetMs() {
        long j;
        synchronized (valueLock) {
            j = isInitialized ? elapsedRealtimeOffsetMs : C1856C.TIME_UNSET;
        }
        return j;
    }

    public static void initialize(Loader loader, InitializationCallback initializationCallback) {
        if (isInitialized()) {
            if (initializationCallback != null) {
                initializationCallback.onInitialized();
                return;
            }
            return;
        }
        if (loader == null) {
            loader = new Loader("SntpClient");
        }
        loader.startLoading(new NtpTimeLoadable(), new NtpTimeCallback(initializationCallback), 1);
    }

    private static long loadNtpTimeOffsetMs() throws IOException {
        InetAddress byName = InetAddress.getByName(getNtpHost());
        DatagramSocket datagramSocket = new DatagramSocket();
        try {
            datagramSocket.setSoTimeout(10000);
            byte[] bArr = new byte[48];
            DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, byName, 123);
            bArr[0] = Ascii.ESC;
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
            writeTimestamp(bArr, 40, currentTimeMillis);
            datagramSocket.send(datagramPacket);
            datagramSocket.receive(new DatagramPacket(bArr, 48));
            long elapsedRealtime2 = android.os.SystemClock.elapsedRealtime();
            long j = currentTimeMillis + (elapsedRealtime2 - elapsedRealtime);
            long readTimestamp = readTimestamp(bArr, 24);
            long readTimestamp2 = readTimestamp(bArr, 32);
            long readTimestamp3 = readTimestamp(bArr, 40);
            checkValidServerReply((byte) ((bArr[0] >> 6) & 3), (byte) (bArr[0] & 7), bArr[1] & 255, readTimestamp3);
            long j2 = (j + (((readTimestamp2 - readTimestamp) + (readTimestamp3 - j)) / 2)) - elapsedRealtime2;
            datagramSocket.close();
            return j2;
        } catch (Throwable th) {
            try {
                datagramSocket.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static long readTimestamp(byte[] bArr, int r6) {
        long read32 = read32(bArr, r6);
        long read322 = read32(bArr, r6 + 4);
        if (read32 == 0 && read322 == 0) {
            return 0L;
        }
        return ((read32 - OFFSET_1900_TO_1970) * 1000) + ((read322 * 1000) / 4294967296L);
    }

    private static void writeTimestamp(byte[] bArr, int r12, long j) {
        if (j == 0) {
            Arrays.fill(bArr, r12, r12 + 8, (byte) 0);
            return;
        }
        long j2 = j / 1000;
        long j3 = j - (j2 * 1000);
        long j4 = j2 + OFFSET_1900_TO_1970;
        int r5 = r12 + 1;
        bArr[r12] = (byte) (j4 >> 24);
        int r122 = r5 + 1;
        bArr[r5] = (byte) (j4 >> 16);
        int r52 = r122 + 1;
        bArr[r122] = (byte) (j4 >> 8);
        int r123 = r52 + 1;
        bArr[r52] = (byte) (j4 >> 0);
        long j5 = (j3 * 4294967296L) / 1000;
        int r0 = r123 + 1;
        bArr[r123] = (byte) (j5 >> 24);
        int r124 = r0 + 1;
        bArr[r0] = (byte) (j5 >> 16);
        bArr[r124] = (byte) (j5 >> 8);
        bArr[r124 + 1] = (byte) (Math.random() * 255.0d);
    }

    private static long read32(byte[] bArr, int r6) {
        int r0 = bArr[r6];
        int r1 = bArr[r6 + 1];
        int r2 = bArr[r6 + 2];
        int r5 = bArr[r6 + 3];
        if ((r0 & 128) == 128) {
            r0 = (r0 & 127) + 128;
        }
        if ((r1 & 128) == 128) {
            r1 = (r1 & 127) + 128;
        }
        if ((r2 & 128) == 128) {
            r2 = (r2 & 127) + 128;
        }
        if ((r5 & 128) == 128) {
            r5 = (r5 & 127) + 128;
        }
        return (r0 << 24) + (r1 << 16) + (r2 << 8) + r5;
    }

    private static void checkValidServerReply(byte b, byte b2, int r3, long j) throws IOException {
        if (b == 3) {
            throw new IOException("SNTP: Unsynchronized server");
        }
        if (b2 != 4 && b2 != 5) {
            throw new IOException("SNTP: Untrusted mode: " + ((int) b2));
        } else if (r3 != 0 && r3 <= 15) {
            if (j == 0) {
                throw new IOException("SNTP: Zero transmitTime");
            }
        } else {
            throw new IOException("SNTP: Untrusted stratum: " + r3);
        }
    }

    /* loaded from: classes2.dex */
    private static final class NtpTimeLoadable implements Loader.Loadable {
        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void cancelLoad() {
        }

        private NtpTimeLoadable() {
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void load() throws IOException {
            synchronized (SntpClient.loaderLock) {
                synchronized (SntpClient.valueLock) {
                    if (SntpClient.isInitialized) {
                        return;
                    }
                    long access$400 = SntpClient.access$400();
                    synchronized (SntpClient.valueLock) {
                        long unused = SntpClient.elapsedRealtimeOffsetMs = access$400;
                        boolean unused2 = SntpClient.isInitialized = true;
                    }
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private static final class NtpTimeCallback implements Loader.Callback<Loader.Loadable> {
        private final InitializationCallback callback;

        @Override // com.google.android.exoplayer2.upstream.Loader.Callback
        public void onLoadCanceled(Loader.Loadable loadable, long j, long j2, boolean z) {
        }

        public NtpTimeCallback(InitializationCallback initializationCallback) {
            this.callback = initializationCallback;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Callback
        public void onLoadCompleted(Loader.Loadable loadable, long j, long j2) {
            if (this.callback != null) {
                if (!SntpClient.isInitialized()) {
                    this.callback.onInitializationFailed(new IOException(new ConcurrentModificationException()));
                } else {
                    this.callback.onInitialized();
                }
            }
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Callback
        public Loader.LoadErrorAction onLoadError(Loader.Loadable loadable, long j, long j2, IOException iOException, int r7) {
            InitializationCallback initializationCallback = this.callback;
            if (initializationCallback != null) {
                initializationCallback.onInitializationFailed(iOException);
            }
            return Loader.DONT_RETRY;
        }
    }
}
