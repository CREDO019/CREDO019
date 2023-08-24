package com.google.android.gms.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzdk;
import com.google.android.gms.ads.internal.client.zzfe;
import com.google.android.gms.internal.ads.zzcgn;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class VideoController {
    public static final int PLAYBACK_STATE_ENDED = 3;
    public static final int PLAYBACK_STATE_PAUSED = 2;
    public static final int PLAYBACK_STATE_PLAYING = 1;
    public static final int PLAYBACK_STATE_READY = 5;
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object zza = new Object();
    private zzdk zzb;
    private VideoLifecycleCallbacks zzc;

    /* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
    /* loaded from: classes2.dex */
    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.zza) {
            videoLifecycleCallbacks = this.zzc;
        }
        return videoLifecycleCallbacks;
    }

    public boolean hasVideoContent() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzb != null;
        }
        return z;
    }

    public void mute(boolean z) {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar != null) {
                try {
                    zzdkVar.zzj(z);
                } catch (RemoteException e) {
                    zzcgn.zzh("Unable to call mute on video controller.", e);
                }
            }
        }
    }

    public void pause() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar != null) {
                try {
                    zzdkVar.zzk();
                } catch (RemoteException e) {
                    zzcgn.zzh("Unable to call pause on video controller.", e);
                }
            }
        }
    }

    public void play() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar != null) {
                try {
                    zzdkVar.zzl();
                } catch (RemoteException e) {
                    zzcgn.zzh("Unable to call play on video controller.", e);
                }
            }
        }
    }

    public void stop() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar != null) {
                try {
                    zzdkVar.zzn();
                } catch (RemoteException e) {
                    zzcgn.zzh("Unable to call stop on video controller.", e);
                }
            }
        }
    }

    public final zzdk zza() {
        zzdk zzdkVar;
        synchronized (this.zza) {
            zzdkVar = this.zzb;
        }
        return zzdkVar;
    }

    public final void zzb(zzdk zzdkVar) {
        synchronized (this.zza) {
            this.zzb = zzdkVar;
            VideoLifecycleCallbacks videoLifecycleCallbacks = this.zzc;
            if (videoLifecycleCallbacks != null) {
                setVideoLifecycleCallbacks(videoLifecycleCallbacks);
            }
        }
    }

    public void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzfe zzfeVar;
        synchronized (this.zza) {
            this.zzc = videoLifecycleCallbacks;
            zzdk zzdkVar = this.zzb;
            if (zzdkVar != null) {
                if (videoLifecycleCallbacks == null) {
                    zzfeVar = null;
                } else {
                    try {
                        zzfeVar = new zzfe(videoLifecycleCallbacks);
                    } catch (RemoteException e) {
                        zzcgn.zzh("Unable to call setVideoLifecycleCallbacks on video controller.", e);
                    }
                }
                zzdkVar.zzm(zzfeVar);
            }
        }
    }

    public int getPlaybackState() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar == null) {
                return 0;
            }
            try {
                return zzdkVar.zzh();
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to call getPlaybackState on video controller.", e);
                return 0;
            }
        }
    }

    public boolean isClickToExpandEnabled() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar == null) {
                return false;
            }
            try {
                return zzdkVar.zzo();
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to call isClickToExpandEnabled.", e);
                return false;
            }
        }
    }

    public boolean isCustomControlsEnabled() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar == null) {
                return false;
            }
            try {
                return zzdkVar.zzp();
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to call isUsingCustomPlayerControls.", e);
                return false;
            }
        }
    }

    public boolean isMuted() {
        synchronized (this.zza) {
            zzdk zzdkVar = this.zzb;
            if (zzdkVar == null) {
                return true;
            }
            try {
                return zzdkVar.zzq();
            } catch (RemoteException e) {
                zzcgn.zzh("Unable to call isMuted on video controller.", e);
                return true;
            }
        }
    }
}
