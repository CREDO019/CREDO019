package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
    /* loaded from: classes3.dex */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, null, null);
        }
        return initialize;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:13|14|15|16|(10:18|(1:(1:21))|22|23|(1:25)|26|27|(1:29)|30|31)|35|22|23|(0)|26|27|(0)|30|31) */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0066, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0067, code lost:
        android.util.Log.e(com.google.android.gms.maps.MapsInitializer.zza, "Failed to retrieve renderer type or log initialization.", r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[Catch: RemoteException -> 0x0066, all -> 0x009f, TryCatch #2 {RemoteException -> 0x0066, blocks: (B:21:0x0054, B:23:0x005a, B:24:0x005e), top: B:47:0x0054, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008c A[Catch: all -> 0x009f, TRY_LEAVE, TryCatch #3 {, blocks: (B:4:0x0003, B:7:0x0029, B:10:0x0030, B:11:0x0034, B:13:0x0043, B:15:0x0048, B:21:0x0054, B:23:0x005a, B:24:0x005e, B:28:0x006e, B:30:0x008c, B:27:0x0067, B:34:0x0094, B:35:0x0099, B:37:0x009b), top: B:49:0x0003, inners: #0, #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L9f
            java.lang.String r2 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L9f
            r3.length()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r3 = "preferredRenderer: "
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r2 = r3.concat(r2)     // Catch: java.lang.Throwable -> L9f
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L9f
            boolean r1 = com.google.android.gms.maps.MapsInitializer.zzb     // Catch: java.lang.Throwable -> L9f
            r2 = 0
            if (r1 == 0) goto L30
            if (r7 == 0) goto L2e
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L9f
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L9f
        L2e:
            monitor-exit(r0)
            return r2
        L30:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzca.zza(r5, r6)     // Catch: com.google.android.gms.common.GooglePlayServicesNotAvailableException -> L9a java.lang.Throwable -> L9f
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch: android.os.RemoteException -> L93 java.lang.Throwable -> L9f
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch: android.os.RemoteException -> L93 java.lang.Throwable -> L9f
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch: android.os.RemoteException -> L93 java.lang.Throwable -> L9f
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch: android.os.RemoteException -> L93 java.lang.Throwable -> L9f
            r3 = 1
            com.google.android.gms.maps.MapsInitializer.zzb = r3     // Catch: java.lang.Throwable -> L9f
            r4 = 2
            if (r6 == 0) goto L53
            int r6 = r6.ordinal()     // Catch: java.lang.Throwable -> L9f
            if (r6 == 0) goto L54
            if (r6 == r3) goto L51
            goto L53
        L51:
            r3 = 2
            goto L54
        L53:
            r3 = 0
        L54:
            int r6 = r1.zzd()     // Catch: android.os.RemoteException -> L66 java.lang.Throwable -> L9f
            if (r6 != r4) goto L5e
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch: android.os.RemoteException -> L66 java.lang.Throwable -> L9f
            com.google.android.gms.maps.MapsInitializer.zzc = r6     // Catch: android.os.RemoteException -> L66 java.lang.Throwable -> L9f
        L5e:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch: android.os.RemoteException -> L66 java.lang.Throwable -> L9f
            r1.zzl(r5, r3)     // Catch: android.os.RemoteException -> L66 java.lang.Throwable -> L9f
            goto L6e
        L66:
            r5 = move-exception
            java.lang.String r6 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch: java.lang.Throwable -> L9f
        L6e:
            java.lang.String r5 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L9f
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L9f
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L9f
            r1.length()     // Catch: java.lang.Throwable -> L9f
            java.lang.String r1 = "loadedRenderer: "
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r6 = r1.concat(r6)     // Catch: java.lang.Throwable -> L9f
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L9f
            if (r7 == 0) goto L91
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L9f
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L9f
        L91:
            monitor-exit(r0)
            return r2
        L93:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch: java.lang.Throwable -> L9f
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L9f
            throw r6     // Catch: java.lang.Throwable -> L9f
        L9a:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r0)
            return r5
        L9f:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
