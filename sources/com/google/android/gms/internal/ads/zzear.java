package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzear implements SensorEventListener {
    private final Context zza;
    @Nullable
    private SensorManager zzb;
    private Sensor zzc;
    private long zzd;
    private int zze;
    private zzeaq zzf;
    private boolean zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzear(Context context) {
        this.zza = context;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int r2) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhD)).booleanValue()) {
            float f = sensorEvent.values[0] / 9.80665f;
            float f2 = sensorEvent.values[1] / 9.80665f;
            float f3 = sensorEvent.values[2] / 9.80665f;
            if (((float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3))) < ((Float) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhE)).floatValue()) {
                return;
            }
            long currentTimeMillis = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
            if (this.zzd + ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhF)).intValue() > currentTimeMillis) {
                return;
            }
            if (this.zzd + ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhG)).intValue() < currentTimeMillis) {
                this.zze = 0;
            }
            com.google.android.gms.ads.internal.util.zze.zza("Shake detected.");
            this.zzd = currentTimeMillis;
            int r10 = this.zze + 1;
            this.zze = r10;
            zzeaq zzeaqVar = this.zzf;
            if (zzeaqVar != null) {
                if (r10 == ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhH)).intValue()) {
                    zzeai zzeaiVar = (zzeai) zzeaqVar;
                    zzeaiVar.zzg(new zzeaf(zzeaiVar), zzeah.GESTURE);
                }
            }
        }
    }

    public final void zza() {
        synchronized (this) {
            if (this.zzg) {
                SensorManager sensorManager = this.zzb;
                if (sensorManager != null) {
                    sensorManager.unregisterListener(this, this.zzc);
                    com.google.android.gms.ads.internal.util.zze.zza("Stopped listening for shake gestures.");
                }
                this.zzg = false;
            }
        }
    }

    public final void zzb() {
        SensorManager sensorManager;
        Sensor sensor;
        synchronized (this) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhD)).booleanValue()) {
                if (this.zzb == null) {
                    SensorManager sensorManager2 = (SensorManager) this.zza.getSystemService("sensor");
                    this.zzb = sensorManager2;
                    if (sensorManager2 != null) {
                        this.zzc = sensorManager2.getDefaultSensor(1);
                    } else {
                        com.google.android.gms.ads.internal.util.zze.zzj("Shake detection failed to initialize. Failed to obtain accelerometer.");
                        return;
                    }
                }
                if (!this.zzg && (sensorManager = this.zzb) != null && (sensor = this.zzc) != null) {
                    sensorManager.registerListener(this, sensor, 2);
                    this.zzd = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() - ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhF)).intValue();
                    this.zzg = true;
                    com.google.android.gms.ads.internal.util.zze.zza("Listening for shake gestures.");
                }
            }
        }
    }

    public final void zzc(zzeaq zzeaqVar) {
        this.zzf = zzeaqVar;
    }
}
