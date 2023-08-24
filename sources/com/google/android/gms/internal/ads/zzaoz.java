package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzaoz extends zzaoy {
    protected static final Object zzr = new Object();
    static boolean zzs = false;
    private static final String zzw = "zzaoz";
    private static long zzx;
    private static zzapf zzy;
    private static zzaqk zzz;
    private final Map zzA;
    protected boolean zzt;
    protected final String zzu;
    zzaqi zzv;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzaoz(Context context, String str, boolean z) {
        super(context);
        this.zzt = false;
        this.zzA = new HashMap();
        this.zzu = str;
        this.zzt = z;
    }

    protected static zzaqb zzj(Context context, boolean z) {
        if (zza == null) {
            synchronized (zzr) {
                if (zza == null) {
                    zzaqb zzg = zzaqb.zzg(context, "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", z);
                    if (zzg.zzr()) {
                        try {
                            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcs)).booleanValue()) {
                                zzg.zzt("O/RWom2ttXKsxcIeQt9LD0kGEjz6kElPF5Gfn2k8dvND1rDfknl9Z31RPOB5g0G+", "GPnS+VgCUEsE+Z+TtHFvZYB0s31DHdUZ2eNJil33wtg=", new Class[0]);
                            }
                        } catch (IllegalStateException unused) {
                        }
                        zzg.zzt("f39Hbu/3ZHOuknzzffAN3L/wmMd3z47Qz3PAKZYAx1YBPOpdL/44XYH2Sf+BtSh+", "LWwi57CIM0frlO/aZZoO3fCsCmO9IloxmiaKJl7K70k=", Context.class);
                        zzg.zzt("F65Z/VGvH3HDSqbisIa05H0FMwZRHR3bv6841vtkw2aGLMzg81raAO8ZiFdKkdju", "44x6GMcLLfXIM1YaNbXK6PVfUUKLz6aX2MRhfiwtXAQ=", Context.class);
                        zzg.zzt("aOe/514coVpPRQegN4yl3ZJgMMZH4bY8vGVrQ08DnDuyKsRCp48F+Zjpb0HjBNAa", "MvgiGujNJnCbH7w8ay+vn+9KOY0pB5PpnwUR2iVU8Do=", Context.class);
                        zzg.zzt("6HNxGbhhczkgMwux7c1JnxuvU0wUTaRoejYXAQva8ckSWURuEsC3usSfONdhI0SW", "REWdnkKpTlNFb2GN/u4uBWWkAz1/VOT6KFxfIGHPouc=", Context.class);
                        zzg.zzt("755UReg2OebscDfMisTRcyHgU7deIoPFggCY/cxxLVcYt5+XFw3QcrbbBvUI25X2", "CL02Ci8InlBbRRgjjRnOk2pWpMOMnwG/UnnB/awQaM4=", Context.class);
                        zzg.zzt("dqHO3Wlmb7oighB5DEjImrvo4du4e9JtW0sm8RACV5Fa9z8v+l2/I/8OmoY2y3HK", "t8m4GxfDNdbzfvzmJ7ERdURqh0DgV49Pg4cGI5qmXbo=", Context.class, Boolean.TYPE);
                        zzg.zzt("/a8iouG/Y9ItWmHyK+NdI47yR9GXTao7DXYLnnbCwVxe2vjd2Eu6bHNA7uqUktHm", "2ehMwY4NjAVCxKNUUqdAHAq3dStVMVXOFsI0kFp0ZW0=", Context.class);
                        zzg.zzt("kypbHNkssvP0JsUhutxcI0i2X1rFBFEIMdtXVFZfH9yzsagSauN+Hc+hylkiTIg3", "RsQqTTopQSSPcEVMkYGLNYEBC1gHD99rtwIfq5A0FgY=", Context.class);
                        zzg.zzt("5hv7b5/1HNa7LxPWb3XOqhFW6rv55HngUHcIDPvFfbzKwKd7l2NkEC0tpFg/m2U1", "TWYb54U7OgagGMZWjzU5K3jAzzOExDb/MqAF9pfakrM=", MotionEvent.class, DisplayMetrics.class);
                        zzg.zzt("Iq9SbqSJ8yU7RzOiISCe3nCpBjH6mJ670ZCopYguBesKZiSJJGgYhB6NljSGo9ir", "o47UOqvH2XIjJOSYtJICa4qB9IxNnqh6Nq71y7pdPyU=", MotionEvent.class, DisplayMetrics.class);
                        zzg.zzt("xrMIVw+CZrXn1IekizTiUScI6wNAILuxj1b/rhxDE2pwil0Ht31LBwVl9aHUCekb", "3Va0stlf242U1Ue7o9TlJnm/36RIQrt1QqEOlEimTMw=", new Class[0]);
                        zzg.zzt("YKKxGLeU2zJMORJ3CXggTVER1rcusEu7lPKzdwQPcOhmeA2WpnQ11LfNiSgl6FV6", "MxHq/OY7GLM9L/Opnjlc3PuDp6q7R1CL8i73K0Ziu0Y=", new Class[0]);
                        zzg.zzt("DPPujNohd+oH/T6ZUMbZzyhfnSIvMDgRP7rWCRWsFdFXxrCKVlDjhvyLuEdzf2o9", "/wkWLWdulpNS6Pk1iA7T64KnscjeHNN2RH4sHzd4WPM=", new Class[0]);
                        zzg.zzt("tUt0vz6lOby0Kor5zovCxCU8AEhO9JFrPQ+FoHildCv7G6/grOfOnSHI07/MbEco", "fLYJ/dhEHiKfuxbMUjXGagNO9QZ/DvGDpPbugCyxqbI=", new Class[0]);
                        zzg.zzt("vwafhzajmko+XbfuSejKABEanPVybMYr0Xs0X58XDfD46RrNvhHs28KurUcFj7Vb", "F5L8q737MMzYnugLKIPW4LyN3bjV9SheOjBtsYe5lqc=", new Class[0]);
                        zzg.zzt("G1zSQHxSHVPUwoFnXHh/RUDU4HrWkkXkBz0C0L8MT/vMOwG2Z70Zze/sd76ItTPz", "Q7CPoNnCWDIIOccltii1S+O+2a/ZVRW78C0n4S9Y84k=", new Class[0]);
                        zzg.zzt("CL5CLQrzdJf7Vwsm6HGAxqUk+UMWsbN6k++UR113AdLOMpqSaLq8guKKFWwu33sx", "AU2/Ti/cc5wfSsdvyvo7rvRQAPyBVB0PKDRfRe8Q8Qo=", Context.class, Boolean.TYPE, String.class);
                        zzg.zzt("mKZoVC4c2F/JqX9WIT6IUJ7O1rqDja2RmF+/au0SoJW0hEJbfdMFQna3+PNp9GA3", "5YZBRBaKVoc53PJNiLp/sxiQ4sgkDdqNOlYuo9Kj11A=", StackTraceElement[].class);
                        zzg.zzt("I7Z8iinoDf65D6f8x6SJHqGD1Z2cIloE56napHJ3hKPe1zHuuQTwZLhUlKl9SuDr", "CMP58KUFBRi55MrO79QJf+iIcc+kMldspC1nSaWllCQ=", View.class, DisplayMetrics.class, Boolean.TYPE, Boolean.TYPE);
                        zzg.zzt("+MexfEnBZA7q7iZMuUPE2bpWWq7dZXL2urW+z97dpchqWh4hWOgUnbCk4z+Hbza8", "LyfKIPN68aZRbmvxI0qKlkb7QqDxcBxbJuROIg+7WFc=", Context.class, Boolean.TYPE);
                        zzg.zzt("SCpciX5wB77gNVd3QxtagyOGzU5F2jwXT8/fqGicur4e//OvUztB6/kDTY8ZtC7H", "1Cs3HyJ4gdHs1TY1t1mRXKXwLGulk9WztHFAT7PZCzg=", View.class, Activity.class, Boolean.TYPE);
                        zzg.zzt("U3RL9mJLLQMse/MZqcg0oM/eA70gzw1xT+w0micdYsejvCU5HnK+5K0knH1wagPk", "3AJSAiPIa+BfzMb1UB6dOa0g/yKIYU+RvBwyuDyaJrc=", Long.TYPE);
                        try {
                            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcx)).booleanValue()) {
                                zzg.zzt("Vzvp2EkF2pEbKPIdCn3PFXroHmxxEvOZYSjqA8kd9hBeO5S3wxnRIFg589Z0eHEB", "WecnIv7/+ap7EIcAgJwinTe6aq/WkhqHwfnV+h6mDgk=", Context.class);
                            }
                        } catch (IllegalStateException unused2) {
                        }
                        zzg.zzt("NEFdqqAAKqlTQEHTmI6xaqDgpQZyg1pRnKumbN+eqhQCHZmsI8VG5KhrQKR43pSu", "89jQARCIO27CLJ3vyAIPxf7eWUDAKRbGk8uiXj6moxg=", Context.class);
                        try {
                            if (Build.VERSION.SDK_INT >= 26) {
                                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcz)).booleanValue()) {
                                    zzg.zzt("fJzM8V+ATbwMqso1FfvPLoBhYN4ojhn6bStsV+iGZGXUcAQ5UdVWi/JBwFYSjW+Z", "EqhZfeJdagDkdmNpQFhVQ7G0RKq67vK3QlT8DoNCRs8=", NetworkCapabilities.class, Long.TYPE, Long.TYPE);
                                }
                            }
                        } catch (IllegalStateException unused3) {
                        }
                        try {
                            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcg)).booleanValue()) {
                                zzg.zzt("nPgtvuonjnkWdghv0SijHg6/i9Y34D7EpYJU2u9rcQe20YVAO3kNDyZXdnFoabyE", "8k8YzM5wR7o+JxVSif6W3gUExnUXRa+1SaNfeXoc/KI=", long[].class, Context.class, View.class);
                            }
                        } catch (IllegalStateException unused4) {
                        }
                    }
                    zza = zzg;
                }
            }
        }
        return zza;
    }

    static zzaqd zzm(zzaqb zzaqbVar, MotionEvent motionEvent, DisplayMetrics displayMetrics) throws zzaps {
        Method zzj = zzaqbVar.zzj("5hv7b5/1HNa7LxPWb3XOqhFW6rv55HngUHcIDPvFfbzKwKd7l2NkEC0tpFg/m2U1", "TWYb54U7OgagGMZWjzU5K3jAzzOExDb/MqAF9pfakrM=");
        if (zzj == null || motionEvent == null) {
            throw new zzaps();
        }
        try {
            return new zzaqd((String) zzj.invoke(null, motionEvent, displayMetrics));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzaps(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static synchronized void zzq(Context context, boolean z) {
        synchronized (zzaoz.class) {
            if (zzs) {
                return;
            }
            zzx = System.currentTimeMillis() / 1000;
            zza = zzj(context, z);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcz)).booleanValue()) {
                zzy = zzapf.zzc(context);
            }
            ExecutorService zzk = zza.zzk();
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcA)).booleanValue() && zzk != null) {
                zzz = zzaqk.zzd(context, zzk);
            }
            zzs = true;
        }
    }

    protected static final void zzr(List list) {
        ExecutorService zzk;
        if (zza == null || (zzk = zza.zzk()) == null || list.isEmpty()) {
            return;
        }
        try {
            zzk.invokeAll(list, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcc)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.d(zzw, String.format("class methods got exception: %s", zzaqe.zze(e)));
        }
    }

    private final synchronized void zzs(zzaqb zzaqbVar, zzamh zzamhVar) {
        MotionEvent motionEvent;
        try {
            zzaqd zzm = zzm(zzaqbVar, this.zzb, this.zzq);
            Long l = zzm.zza;
            if (l != null) {
                zzamhVar.zzM(l.longValue());
            }
            Long l2 = zzm.zzb;
            if (l2 != null) {
                zzamhVar.zzN(l2.longValue());
            }
            Long l3 = zzm.zzc;
            if (l3 != null) {
                zzamhVar.zzK(l3.longValue());
            }
            if (this.zzp) {
                Long l4 = zzm.zzd;
                if (l4 != null) {
                    zzamhVar.zzJ(l4.longValue());
                }
                Long l5 = zzm.zze;
                if (l5 != null) {
                    zzamhVar.zzG(l5.longValue());
                }
            }
        } catch (zzaps unused) {
        }
        zzamt zza = zzamu.zza();
        if (this.zzd > 0 && zzaqe.zzh(this.zzq)) {
            zza.zzd(zzaqe.zza(this.zzk, 1, this.zzq));
            zza.zzq(zzaqe.zza(this.zzn - this.zzl, 1, this.zzq));
            zza.zzr(zzaqe.zza(this.zzo - this.zzm, 1, this.zzq));
            zza.zzj(zzaqe.zza(this.zzl, 1, this.zzq));
            zza.zzl(zzaqe.zza(this.zzm, 1, this.zzq));
            if (this.zzp && (motionEvent = this.zzb) != null) {
                long zza2 = zzaqe.zza(((this.zzl - this.zzn) + motionEvent.getRawX()) - this.zzb.getX(), 1, this.zzq);
                if (zza2 != 0) {
                    zza.zzo(zza2);
                }
                long zza3 = zzaqe.zza(((this.zzm - this.zzo) + this.zzb.getRawY()) - this.zzb.getY(), 1, this.zzq);
                if (zza3 != 0) {
                    zza.zzp(zza3);
                }
            }
        }
        try {
            zzaqd zzi = zzi(this.zzb);
            Long l6 = zzi.zza;
            if (l6 != null) {
                zza.zzk(l6.longValue());
            }
            Long l7 = zzi.zzb;
            if (l7 != null) {
                zza.zzm(l7.longValue());
            }
            zza.zzi(zzi.zzc.longValue());
            if (this.zzp) {
                Long l8 = zzi.zze;
                if (l8 != null) {
                    zza.zzg(l8.longValue());
                }
                Long l9 = zzi.zzd;
                if (l9 != null) {
                    zza.zzh(l9.longValue());
                }
                Long l10 = zzi.zzf;
                if (l10 != null) {
                    zza.zzt(l10.longValue() != 0 ? 2 : 1);
                }
                if (this.zze > 0) {
                    Long valueOf = zzaqe.zzh(this.zzq) ? Long.valueOf(Math.round(this.zzj / this.zze)) : null;
                    if (valueOf != null) {
                        zza.zzb(valueOf.longValue());
                    } else {
                        zza.zza();
                    }
                    zza.zzc(Math.round(this.zzi / this.zze));
                }
                Long l11 = zzi.zzi;
                if (l11 != null) {
                    zza.zze(l11.longValue());
                }
                Long l12 = zzi.zzj;
                if (l12 != null) {
                    zza.zzn(l12.longValue());
                }
                Long l13 = zzi.zzk;
                if (l13 != null) {
                    zza.zzs(l13.longValue() != 0 ? 2 : 1);
                }
            }
        } catch (zzaps unused2) {
        }
        long j = this.zzh;
        if (j > 0) {
            zza.zzf(j);
        }
        zzamhVar.zzQ((zzamu) zza.zzal());
        long j2 = this.zzd;
        if (j2 > 0) {
            zzamhVar.zzH(j2);
        }
        long j3 = this.zze;
        if (j3 > 0) {
            zzamhVar.zzI(j3);
        }
        long j4 = this.zzf;
        if (j4 > 0) {
            zzamhVar.zzL(j4);
        }
        long j5 = this.zzg;
        if (j5 > 0) {
            zzamhVar.zzF(j5);
        }
        try {
            int size = this.zzc.size() - 1;
            if (size > 0) {
                zzamhVar.zzb();
                for (int r0 = 0; r0 < size; r0++) {
                    zzaqd zzm2 = zzm(zza, (MotionEvent) this.zzc.get(r0), this.zzq);
                    zzamt zza4 = zzamu.zza();
                    zza4.zzk(zzm2.zza.longValue());
                    zza4.zzm(zzm2.zzb.longValue());
                    zzamhVar.zza((zzamu) zza4.zzal());
                }
            }
        } catch (zzaps unused3) {
            zzamhVar.zzb();
        }
    }

    private static final void zzt() {
        zzaqk zzaqkVar = zzz;
        if (zzaqkVar != null) {
            zzaqkVar.zzh();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaoy
    protected final long zza(StackTraceElement[] stackTraceElementArr) throws zzaps {
        Method zzj = zza.zzj("mKZoVC4c2F/JqX9WIT6IUJ7O1rqDja2RmF+/au0SoJW0hEJbfdMFQna3+PNp9GA3", "5YZBRBaKVoc53PJNiLp/sxiQ4sgkDdqNOlYuo9Kj11A=");
        if (zzj == null || stackTraceElementArr == null) {
            throw new zzaps();
        }
        try {
            return new zzapt((String) zzj.invoke(null, stackTraceElementArr)).zza.longValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzaps(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaoy
    protected final zzamh zzb(Context context, View view, Activity activity) {
        zzt();
        zzamh zza = zzamx.zza();
        if (!TextUtils.isEmpty(this.zzu)) {
            zza.zzg(this.zzu);
        }
        zzp(zzj(context, this.zzt), zza, view, activity, true);
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzaoy
    protected final zzamh zzc(Context context, zzama zzamaVar) {
        zzt();
        zzamh zza = zzamx.zza();
        if (!TextUtils.isEmpty(this.zzu)) {
            zza.zzg(this.zzu);
        }
        zzaqb zzj = zzj(context, this.zzt);
        if (zzj.zzk() != null) {
            zzr(zzo(zzj, context, zza, null));
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzaoy
    protected final zzamh zzd(Context context, View view, Activity activity) {
        zzt();
        zzamh zza = zzamx.zza();
        zza.zzg(this.zzu);
        zzp(zzj(context, this.zzt), zza, view, activity, false);
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzaoy
    protected final zzaqd zzi(MotionEvent motionEvent) throws zzaps {
        Method zzj = zza.zzj("Iq9SbqSJ8yU7RzOiISCe3nCpBjH6mJ670ZCopYguBesKZiSJJGgYhB6NljSGo9ir", "o47UOqvH2XIjJOSYtJICa4qB9IxNnqh6Nq71y7pdPyU=");
        if (zzj == null || motionEvent == null) {
            throw new zzaps();
        }
        try {
            return new zzaqd((String) zzj.invoke(null, motionEvent, this.zzq));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new zzaps(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaoy, com.google.android.gms.internal.ads.zzaox
    public final void zzn(View view) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcf)).booleanValue()) {
            if (this.zzv == null) {
                zzaqb zzaqbVar = zza;
                this.zzv = new zzaqi(zzaqbVar.zza, zzaqbVar.zzf());
            }
            this.zzv.zzd(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List zzo(zzaqb zzaqbVar, Context context, zzamh zzamhVar, zzama zzamaVar) {
        long j;
        long j2;
        int zza = zzaqbVar.zza();
        ArrayList arrayList = new ArrayList();
        if (!zzaqbVar.zzr()) {
            zzamhVar.zzC(16384L);
            return arrayList;
        }
        arrayList.add(new zzaqo(zzaqbVar, "CL5CLQrzdJf7Vwsm6HGAxqUk+UMWsbN6k++UR113AdLOMpqSaLq8guKKFWwu33sx", "AU2/Ti/cc5wfSsdvyvo7rvRQAPyBVB0PKDRfRe8Q8Qo=", zzamhVar, zza, 27, context, null));
        arrayList.add(new zzaqr(zzaqbVar, "xrMIVw+CZrXn1IekizTiUScI6wNAILuxj1b/rhxDE2pwil0Ht31LBwVl9aHUCekb", "3Va0stlf242U1Ue7o9TlJnm/36RIQrt1QqEOlEimTMw=", zzamhVar, zzx, zza, 25));
        arrayList.add(new zzaqz(zzaqbVar, "DPPujNohd+oH/T6ZUMbZzyhfnSIvMDgRP7rWCRWsFdFXxrCKVlDjhvyLuEdzf2o9", "/wkWLWdulpNS6Pk1iA7T64KnscjeHNN2RH4sHzd4WPM=", zzamhVar, zza, 1));
        arrayList.add(new zzarc(zzaqbVar, "aOe/514coVpPRQegN4yl3ZJgMMZH4bY8vGVrQ08DnDuyKsRCp48F+Zjpb0HjBNAa", "MvgiGujNJnCbH7w8ay+vn+9KOY0pB5PpnwUR2iVU8Do=", zzamhVar, zza, 31));
        arrayList.add(new zzarh(zzaqbVar, "YKKxGLeU2zJMORJ3CXggTVER1rcusEu7lPKzdwQPcOhmeA2WpnQ11LfNiSgl6FV6", "MxHq/OY7GLM9L/Opnjlc3PuDp6q7R1CL8i73K0Ziu0Y=", zzamhVar, zza, 33));
        arrayList.add(new zzaqn(zzaqbVar, "F65Z/VGvH3HDSqbisIa05H0FMwZRHR3bv6841vtkw2aGLMzg81raAO8ZiFdKkdju", "44x6GMcLLfXIM1YaNbXK6PVfUUKLz6aX2MRhfiwtXAQ=", zzamhVar, zza, 29, context));
        arrayList.add(new zzaqp(zzaqbVar, "6HNxGbhhczkgMwux7c1JnxuvU0wUTaRoejYXAQva8ckSWURuEsC3usSfONdhI0SW", "REWdnkKpTlNFb2GN/u4uBWWkAz1/VOT6KFxfIGHPouc=", zzamhVar, zza, 5));
        arrayList.add(new zzaqy(zzaqbVar, "755UReg2OebscDfMisTRcyHgU7deIoPFggCY/cxxLVcYt5+XFw3QcrbbBvUI25X2", "CL02Ci8InlBbRRgjjRnOk2pWpMOMnwG/UnnB/awQaM4=", zzamhVar, zza, 12));
        arrayList.add(new zzara(zzaqbVar, "dqHO3Wlmb7oighB5DEjImrvo4du4e9JtW0sm8RACV5Fa9z8v+l2/I/8OmoY2y3HK", "t8m4GxfDNdbzfvzmJ7ERdURqh0DgV49Pg4cGI5qmXbo=", zzamhVar, zza, 3));
        arrayList.add(new zzaqq(zzaqbVar, "tUt0vz6lOby0Kor5zovCxCU8AEhO9JFrPQ+FoHildCv7G6/grOfOnSHI07/MbEco", "fLYJ/dhEHiKfuxbMUjXGagNO9QZ/DvGDpPbugCyxqbI=", zzamhVar, zza, 44));
        arrayList.add(new zzaqv(zzaqbVar, "vwafhzajmko+XbfuSejKABEanPVybMYr0Xs0X58XDfD46RrNvhHs28KurUcFj7Vb", "F5L8q737MMzYnugLKIPW4LyN3bjV9SheOjBtsYe5lqc=", zzamhVar, zza, 22));
        arrayList.add(new zzari(zzaqbVar, "/a8iouG/Y9ItWmHyK+NdI47yR9GXTao7DXYLnnbCwVxe2vjd2Eu6bHNA7uqUktHm", "2ehMwY4NjAVCxKNUUqdAHAq3dStVMVXOFsI0kFp0ZW0=", zzamhVar, zza, 48));
        arrayList.add(new zzaqm(zzaqbVar, "kypbHNkssvP0JsUhutxcI0i2X1rFBFEIMdtXVFZfH9yzsagSauN+Hc+hylkiTIg3", "RsQqTTopQSSPcEVMkYGLNYEBC1gHD99rtwIfq5A0FgY=", zzamhVar, zza, 49));
        arrayList.add(new zzarf(zzaqbVar, "G1zSQHxSHVPUwoFnXHh/RUDU4HrWkkXkBz0C0L8MT/vMOwG2Z70Zze/sd76ItTPz", "Q7CPoNnCWDIIOccltii1S+O+2a/ZVRW78C0n4S9Y84k=", zzamhVar, zza, 51));
        arrayList.add(new zzard(zzaqbVar, "+MexfEnBZA7q7iZMuUPE2bpWWq7dZXL2urW+z97dpchqWh4hWOgUnbCk4z+Hbza8", "LyfKIPN68aZRbmvxI0qKlkb7QqDxcBxbJuROIg+7WFc=", zzamhVar, zza, 61));
        if (Build.VERSION.SDK_INT >= 24) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcz)).booleanValue()) {
                zzaqk zzaqkVar = zzz;
                if (zzaqkVar != null) {
                    j = zzaqkVar.zzc();
                    j2 = zzaqkVar.zzb();
                } else {
                    j = -1;
                    j2 = -1;
                }
                arrayList.add(new zzaqx(zzaqbVar, "fJzM8V+ATbwMqso1FfvPLoBhYN4ojhn6bStsV+iGZGXUcAQ5UdVWi/JBwFYSjW+Z", "EqhZfeJdagDkdmNpQFhVQ7G0RKq67vK3QlT8DoNCRs8=", zzamhVar, zza, 11, zzy, j, j2));
            }
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcx)).booleanValue()) {
            arrayList.add(new zzarb(zzaqbVar, "Vzvp2EkF2pEbKPIdCn3PFXroHmxxEvOZYSjqA8kd9hBeO5S3wxnRIFg589Z0eHEB", "WecnIv7/+ap7EIcAgJwinTe6aq/WkhqHwfnV+h6mDgk=", zzamhVar, zza, 73));
        }
        arrayList.add(new zzaqw(zzaqbVar, "NEFdqqAAKqlTQEHTmI6xaqDgpQZyg1pRnKumbN+eqhQCHZmsI8VG5KhrQKR43pSu", "89jQARCIO27CLJ3vyAIPxf7eWUDAKRbGk8uiXj6moxg=", zzamhVar, zza, 76));
        return arrayList;
    }

    protected final void zzp(zzaqb zzaqbVar, zzamh zzamhVar, View view, Activity activity, boolean z) {
        List list;
        if (!zzaqbVar.zzr()) {
            zzamhVar.zzC(16384L);
            list = Arrays.asList(new zzaqt(zzaqbVar, zzamhVar));
        } else {
            zzs(zzaqbVar, zzamhVar);
            ArrayList arrayList = new ArrayList();
            if (zzaqbVar.zzk() != null) {
                int zza = zzaqbVar.zza();
                arrayList.add(new zzaqt(zzaqbVar, zzamhVar));
                arrayList.add(new zzaqz(zzaqbVar, "DPPujNohd+oH/T6ZUMbZzyhfnSIvMDgRP7rWCRWsFdFXxrCKVlDjhvyLuEdzf2o9", "/wkWLWdulpNS6Pk1iA7T64KnscjeHNN2RH4sHzd4WPM=", zzamhVar, zza, 1));
                arrayList.add(new zzaqr(zzaqbVar, "xrMIVw+CZrXn1IekizTiUScI6wNAILuxj1b/rhxDE2pwil0Ht31LBwVl9aHUCekb", "3Va0stlf242U1Ue7o9TlJnm/36RIQrt1QqEOlEimTMw=", zzamhVar, zzx, zza, 25));
                arrayList.add(new zzaqq(zzaqbVar, "tUt0vz6lOby0Kor5zovCxCU8AEhO9JFrPQ+FoHildCv7G6/grOfOnSHI07/MbEco", "fLYJ/dhEHiKfuxbMUjXGagNO9QZ/DvGDpPbugCyxqbI=", zzamhVar, zza, 44));
                arrayList.add(new zzaqy(zzaqbVar, "755UReg2OebscDfMisTRcyHgU7deIoPFggCY/cxxLVcYt5+XFw3QcrbbBvUI25X2", "CL02Ci8InlBbRRgjjRnOk2pWpMOMnwG/UnnB/awQaM4=", zzamhVar, zza, 12));
                arrayList.add(new zzara(zzaqbVar, "dqHO3Wlmb7oighB5DEjImrvo4du4e9JtW0sm8RACV5Fa9z8v+l2/I/8OmoY2y3HK", "t8m4GxfDNdbzfvzmJ7ERdURqh0DgV49Pg4cGI5qmXbo=", zzamhVar, zza, 3));
                arrayList.add(new zzaqv(zzaqbVar, "vwafhzajmko+XbfuSejKABEanPVybMYr0Xs0X58XDfD46RrNvhHs28KurUcFj7Vb", "F5L8q737MMzYnugLKIPW4LyN3bjV9SheOjBtsYe5lqc=", zzamhVar, zza, 22));
                arrayList.add(new zzaqp(zzaqbVar, "6HNxGbhhczkgMwux7c1JnxuvU0wUTaRoejYXAQva8ckSWURuEsC3usSfONdhI0SW", "REWdnkKpTlNFb2GN/u4uBWWkAz1/VOT6KFxfIGHPouc=", zzamhVar, zza, 5));
                arrayList.add(new zzari(zzaqbVar, "/a8iouG/Y9ItWmHyK+NdI47yR9GXTao7DXYLnnbCwVxe2vjd2Eu6bHNA7uqUktHm", "2ehMwY4NjAVCxKNUUqdAHAq3dStVMVXOFsI0kFp0ZW0=", zzamhVar, zza, 48));
                arrayList.add(new zzaqm(zzaqbVar, "kypbHNkssvP0JsUhutxcI0i2X1rFBFEIMdtXVFZfH9yzsagSauN+Hc+hylkiTIg3", "RsQqTTopQSSPcEVMkYGLNYEBC1gHD99rtwIfq5A0FgY=", zzamhVar, zza, 49));
                arrayList.add(new zzarf(zzaqbVar, "G1zSQHxSHVPUwoFnXHh/RUDU4HrWkkXkBz0C0L8MT/vMOwG2Z70Zze/sd76ItTPz", "Q7CPoNnCWDIIOccltii1S+O+2a/ZVRW78C0n4S9Y84k=", zzamhVar, zza, 51));
                arrayList.add(new zzare(zzaqbVar, "mKZoVC4c2F/JqX9WIT6IUJ7O1rqDja2RmF+/au0SoJW0hEJbfdMFQna3+PNp9GA3", "5YZBRBaKVoc53PJNiLp/sxiQ4sgkDdqNOlYuo9Kj11A=", zzamhVar, zza, 45, new Throwable().getStackTrace()));
                arrayList.add(new zzarj(zzaqbVar, "I7Z8iinoDf65D6f8x6SJHqGD1Z2cIloE56napHJ3hKPe1zHuuQTwZLhUlKl9SuDr", "CMP58KUFBRi55MrO79QJf+iIcc+kMldspC1nSaWllCQ=", zzamhVar, zza, 57, view));
                arrayList.add(new zzard(zzaqbVar, "+MexfEnBZA7q7iZMuUPE2bpWWq7dZXL2urW+z97dpchqWh4hWOgUnbCk4z+Hbza8", "LyfKIPN68aZRbmvxI0qKlkb7QqDxcBxbJuROIg+7WFc=", zzamhVar, zza, 61));
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcd)).booleanValue()) {
                    arrayList.add(new zzaql(zzaqbVar, "SCpciX5wB77gNVd3QxtagyOGzU5F2jwXT8/fqGicur4e//OvUztB6/kDTY8ZtC7H", "1Cs3HyJ4gdHs1TY1t1mRXKXwLGulk9WztHFAT7PZCzg=", zzamhVar, zza, 62, view, activity));
                }
                if (z) {
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcf)).booleanValue()) {
                        arrayList.add(new zzarg(zzaqbVar, "U3RL9mJLLQMse/MZqcg0oM/eA70gzw1xT+w0micdYsejvCU5HnK+5K0knH1wagPk", "3AJSAiPIa+BfzMb1UB6dOa0g/yKIYU+RvBwyuDyaJrc=", zzamhVar, zza, 53, this.zzv));
                    }
                } else {
                    try {
                        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcg)).booleanValue()) {
                            arrayList.add(new zzaqu(zzaqbVar, "nPgtvuonjnkWdghv0SijHg6/i9Y34D7EpYJU2u9rcQe20YVAO3kNDyZXdnFoabyE", "8k8YzM5wR7o+JxVSif6W3gUExnUXRa+1SaNfeXoc/KI=", zzamhVar, zza, 85, this.zzA, view));
                        }
                    } catch (IllegalStateException unused) {
                    }
                }
            }
            list = arrayList;
        }
        zzr(list);
    }
}
