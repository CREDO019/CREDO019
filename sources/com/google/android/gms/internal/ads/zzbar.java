package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbar extends HandlerThread implements SurfaceTexture.OnFrameAvailableListener, Handler.Callback {
    private final int[] zza;
    private Handler zzb;
    private SurfaceTexture zzc;
    private Error zzd;
    private RuntimeException zze;
    private zzbat zzf;

    public zzbar() {
        super("dummySurface");
        this.zza = new int[1];
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int r2 = message.what;
        try {
            if (r2 != 1) {
                if (r2 == 2) {
                    this.zzc.updateTexImage();
                    return true;
                } else if (r2 != 3) {
                    return true;
                } else {
                    try {
                        this.zzc.release();
                        this.zzf = null;
                        this.zzc = null;
                        GLES20.glDeleteTextures(1, this.zza, 0);
                    } finally {
                        try {
                            return true;
                        } finally {
                        }
                    }
                    return true;
                }
            }
            try {
                boolean z = message.arg1 != 0;
                EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
                zzazy.zzf(eglGetDisplay != null, "eglGetDisplay failed");
                int[] r8 = new int[2];
                zzazy.zzf(EGL14.eglInitialize(eglGetDisplay, r8, 0, r8, 1), "eglInitialize failed");
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                int[] r12 = new int[1];
                zzazy.zzf(EGL14.eglChooseConfig(eglGetDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, r12, 0) && r12[0] > 0 && eGLConfigArr[0] != null, "eglChooseConfig failed");
                EGLConfig eGLConfig = eGLConfigArr[0];
                EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, z ? new int[]{12440, 2, 12992, 1, 12344} : new int[]{12440, 2, 12344}, 0);
                zzazy.zzf(eglCreateContext != null, "eglCreateContext failed");
                EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, z ? new int[]{12375, 1, 12374, 1, 12992, 1, 12344} : new int[]{12375, 1, 12374, 1, 12344}, 0);
                zzazy.zzf(eglCreatePbufferSurface != null, "eglCreatePbufferSurface failed");
                zzazy.zzf(EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext), "eglMakeCurrent failed");
                GLES20.glGenTextures(1, this.zza, 0);
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.zza[0]);
                this.zzc = surfaceTexture;
                surfaceTexture.setOnFrameAvailableListener(this);
                this.zzf = new zzbat(this, this.zzc, z, null);
                synchronized (this) {
                    notify();
                }
            } catch (Error e) {
                Log.e("DummySurface", "Failed to initialize dummy surface", e);
                this.zzd = e;
                synchronized (this) {
                    notify();
                }
            } catch (RuntimeException e2) {
                Log.e("DummySurface", "Failed to initialize dummy surface", e2);
                this.zze = e2;
                synchronized (this) {
                    notify();
                }
            }
            return true;
        } catch (Throwable th) {
            synchronized (this) {
                notify();
                throw th;
            }
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzb.sendEmptyMessage(2);
    }

    public final zzbat zza(boolean z) {
        boolean z2;
        start();
        this.zzb = new Handler(getLooper(), this);
        synchronized (this) {
            z2 = false;
            this.zzb.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
            while (this.zzf == null && this.zze == null && this.zzd == null) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    z2 = true;
                }
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        RuntimeException runtimeException = this.zze;
        if (runtimeException == null) {
            Error error = this.zzd;
            if (error == null) {
                return this.zzf;
            }
            throw error;
        }
        throw runtimeException;
    }

    public final void zzb() {
        this.zzb.sendEmptyMessage(3);
    }
}
