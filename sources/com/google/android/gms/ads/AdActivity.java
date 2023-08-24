package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.client.zzaw;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzbza;
import com.google.android.gms.internal.ads.zzcgn;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    private zzbza zza;

    private final void zza() {
        zzbza zzbzaVar = this.zza;
        if (zzbzaVar != null) {
            try {
                zzbzaVar.zzv();
            } catch (RemoteException e) {
                zzcgn.zzl("#007 Could not call remote method.", e);
            }
        }
    }

    @Override // android.app.Activity
    protected final void onActivityResult(int r3, int r4, Intent intent) {
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzg(r3, r4, intent);
            }
        } catch (Exception e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
        }
        super.onActivityResult(r3, r4, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                if (!zzbzaVar.zzE()) {
                    return;
                }
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
        }
        super.onBackPressed();
        try {
            zzbza zzbzaVar2 = this.zza;
            if (zzbzaVar2 != null) {
                zzbzaVar2.zzh();
            }
        } catch (RemoteException e2) {
            zzcgn.zzl("#007 Could not call remote method.", e2);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzj(ObjectWrapper.wrap(configuration));
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzbza zzn = zzaw.zza().zzn(this);
        this.zza = zzn;
        if (zzn != null) {
            try {
                zzn.zzk(bundle);
                return;
            } catch (RemoteException e) {
                zzcgn.zzl("#007 Could not call remote method.", e);
                finish();
                return;
            }
        }
        zzcgn.zzl("#007 Could not call remote method.", null);
        finish();
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzl();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected final void onPause() {
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzn();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected final void onRestart() {
        super.onRestart();
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzo();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onResume() {
        super.onResume();
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzp();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle) {
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzq(bundle);
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected final void onStart() {
        super.onStart();
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzr();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected final void onStop() {
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzs();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    protected final void onUserLeaveHint() {
        super.onUserLeaveHint();
        try {
            zzbza zzbzaVar = this.zza;
            if (zzbzaVar != null) {
                zzbzaVar.zzt();
            }
        } catch (RemoteException e) {
            zzcgn.zzl("#007 Could not call remote method.", e);
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int r1) {
        super.setContentView(r1);
        zza();
    }

    @Override // android.app.Activity
    public final void setContentView(View view) {
        super.setContentView(view);
        zza();
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zza();
    }
}
