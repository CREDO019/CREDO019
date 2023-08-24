package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzapb {
    private static final String[] zza = {"/aclk", "/pcs/click", "/dbm/clk"};
    private final String zzb = "ad.doubleclick.net";
    private final String[] zzc = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private final zzaox zzd;

    @Deprecated
    public zzapb(zzaox zzaoxVar) {
        this.zzd = zzaoxVar;
    }

    private final Uri zzg(Uri uri, String str) throws zzapc {
        Objects.requireNonNull(uri);
        try {
            try {
                if (uri.getHost().equals(this.zzb)) {
                    if (!uri.toString().contains("dc_ms=")) {
                        String uri2 = uri.toString();
                        int indexOf = uri2.indexOf(";adurl");
                        if (indexOf != -1) {
                            int r5 = indexOf + 1;
                            return Uri.parse(uri2.substring(0, r5) + "dc_ms=" + str + ";" + uri2.substring(r5));
                        }
                        String encodedPath = uri.getEncodedPath();
                        int indexOf2 = uri2.indexOf(encodedPath);
                        return Uri.parse(uri2.substring(0, encodedPath.length() + indexOf2) + ";dc_ms=" + str + ";" + uri2.substring(indexOf2 + encodedPath.length()));
                    }
                    throw new zzapc("Parameter already exists: dc_ms");
                }
            } catch (NullPointerException unused) {
            }
            if (uri.getQueryParameter("ms") == null) {
                String uri3 = uri.toString();
                int indexOf3 = uri3.indexOf("&adurl");
                if (indexOf3 == -1) {
                    indexOf3 = uri3.indexOf("?adurl");
                }
                if (indexOf3 != -1) {
                    int r52 = indexOf3 + 1;
                    return Uri.parse(uri3.substring(0, r52) + "ms=" + str + "&" + uri3.substring(r52));
                }
                return uri.buildUpon().appendQueryParameter("ms", str).build();
            }
            throw new zzapc("Query parameter already exists: ms");
        } catch (UnsupportedOperationException unused2) {
            throw new zzapc("Provided Uri is not in a valid state");
        }
    }

    @Deprecated
    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzapc {
        try {
            return zzg(uri, this.zzd.zzf(context, uri.getQueryParameter("ai"), view, activity));
        } catch (UnsupportedOperationException unused) {
            throw new zzapc("Provided Uri is not in a valid state");
        }
    }

    @Deprecated
    public final Uri zzb(Uri uri, Context context) throws zzapc {
        return zzg(uri, this.zzd.zzg(context));
    }

    @Deprecated
    public final zzaox zzc() {
        return this.zzd;
    }

    @Deprecated
    public final void zzd(MotionEvent motionEvent) {
        this.zzd.zzk(motionEvent);
    }

    public final boolean zze(Uri uri) {
        if (zzf(uri)) {
            String[] strArr = zza;
            for (int r2 = 0; r2 < 3; r2++) {
                if (uri.getPath().endsWith(strArr[r2])) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean zzf(Uri uri) {
        Objects.requireNonNull(uri);
        try {
            String host = uri.getHost();
            String[] strArr = this.zzc;
            for (int r2 = 0; r2 < 3; r2++) {
                if (host.endsWith(strArr[r2])) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }
}
