package com.google.android.gms.internal.ads;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcgg {
    public static final Handler zza = new zzfph(Looper.getMainLooper());
    private static final String zzb = AdView.class.getName();
    private static final String zzc = InterstitialAd.class.getName();
    private static final String zzd = AdManagerAdView.class.getName();
    private static final String zze = AdManagerInterstitialAd.class.getName();
    private static final String zzf = SearchAdView.class.getName();
    private static final String zzg = AdLoader.class.getName();
    private float zzh = -1.0f;

    private final void zzA(JSONObject jSONObject, String str, Object obj) throws JSONException {
        Boolean[] boolArr;
        Long[] lArr;
        Double[] dArr;
        Integer[] numArr;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzr)).booleanValue()) {
            str = String.valueOf(str);
        }
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzh((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzi((Map) obj));
        } else if (obj instanceof Collection) {
            jSONObject.put(String.valueOf(str), zzy((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zzy(Arrays.asList((Object[]) obj)));
        } else {
            int r1 = 0;
            if (obj instanceof int[]) {
                int[] r8 = (int[]) obj;
                if (r8 == null) {
                    numArr = new Integer[0];
                } else {
                    int length = r8.length;
                    Integer[] numArr2 = new Integer[length];
                    while (r1 < length) {
                        numArr2[r1] = Integer.valueOf(r8[r1]);
                        r1++;
                    }
                    numArr = numArr2;
                }
                jSONObject.put(str, zzg(numArr));
            } else if (obj instanceof double[]) {
                double[] dArr2 = (double[]) obj;
                if (dArr2 == null) {
                    dArr = new Double[0];
                } else {
                    int length2 = dArr2.length;
                    Double[] dArr3 = new Double[length2];
                    while (r1 < length2) {
                        dArr3[r1] = Double.valueOf(dArr2[r1]);
                        r1++;
                    }
                    dArr = dArr3;
                }
                jSONObject.put(str, zzg(dArr));
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                if (jArr == null) {
                    lArr = new Long[0];
                } else {
                    int length3 = jArr.length;
                    Long[] lArr2 = new Long[length3];
                    while (r1 < length3) {
                        lArr2[r1] = Long.valueOf(jArr[r1]);
                        r1++;
                    }
                    lArr = lArr2;
                }
                jSONObject.put(str, zzg(lArr));
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                if (zArr == null) {
                    boolArr = new Boolean[0];
                } else {
                    int length4 = zArr.length;
                    Boolean[] boolArr2 = new Boolean[length4];
                    while (r1 < length4) {
                        boolArr2[r1] = Boolean.valueOf(zArr[r1]);
                        r1++;
                    }
                    boolArr = boolArr2;
                }
                jSONObject.put(str, zzg(boolArr));
            } else {
                jSONObject.put(str, obj);
            }
        }
    }

    private static final void zzB(ViewGroup viewGroup, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, int r7, int r8) {
        if (viewGroup.getChildCount() != 0) {
            return;
        }
        Context context = viewGroup.getContext();
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setText(str);
        textView.setTextColor(r7);
        textView.setBackgroundColor(r8);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(r7);
        int zzw = zzw(context, 3);
        frameLayout.addView(textView, new FrameLayout.LayoutParams(zzqVar.zzf - zzw, zzqVar.zzc - zzw, 17));
        viewGroup.addView(frameLayout, zzqVar.zzf, zzqVar.zzc);
    }

    public static int zza(Context context, int r3) {
        DisplayMetrics displayMetrics;
        Configuration configuration;
        if (context == null) {
            return -1;
        }
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        Resources resources = context.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null || (configuration = resources.getConfiguration()) == null) {
            return -1;
        }
        int r2 = configuration.orientation;
        if (r3 == 0) {
            r3 = r2;
        }
        if (r3 == r2) {
            return Math.round(displayMetrics.heightPixels / displayMetrics.density);
        }
        return Math.round(displayMetrics.widthPixels / displayMetrics.density);
    }

    public static AdSize zzc(Context context, int r1, int r2, int r3) {
        int round;
        int zza2 = zza(context, r3);
        if (zza2 == -1) {
            return AdSize.INVALID;
        }
        int min = Math.min(90, Math.round(zza2 * 0.15f));
        if (r1 > 655) {
            round = Math.round((r1 / 728.0f) * 90.0f);
        } else if (r1 > 632) {
            round = 81;
        } else if (r1 > 526) {
            round = Math.round((r1 / 468.0f) * 60.0f);
        } else {
            round = r1 > 432 ? 68 : Math.round((r1 / 320.0f) * 50.0f);
        }
        return new AdSize(r1, Math.max(Math.min(round, min), 50));
    }

    public static String zzd() {
        UUID randomUUID = UUID.randomUUID();
        byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String bigInteger = new BigInteger(1, byteArray).toString();
        for (int r5 = 0; r5 < 2; r5++) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                messageDigest.update(byteArray);
                messageDigest.update(byteArray2);
                byte[] bArr = new byte[8];
                System.arraycopy(messageDigest.digest(), 0, bArr, 0, 8);
                bigInteger = new BigInteger(1, bArr).toString();
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return bigInteger;
    }

    public static String zze(String str) {
        for (int r1 = 0; r1 < 2; r1++) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                messageDigest.update(str.getBytes());
                return String.format(Locale.US, "%032X", new BigInteger(1, messageDigest.digest()));
            } catch (ArithmeticException unused) {
                return null;
            } catch (NoSuchAlgorithmException unused2) {
            }
        }
        return null;
    }

    public static Throwable zzf(Throwable th) {
        if (((Boolean) zzbkw.zzf.zze()).booleanValue()) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th2 = null;
        while (!linkedList.isEmpty()) {
            Throwable th3 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th3.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th3.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (zzn(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    z = true;
                } else {
                    String className = stackTraceElement.getClassName();
                    if (TextUtils.isEmpty(className) || (!className.startsWith("android.") && !className.startsWith("java."))) {
                        arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                    } else {
                        arrayList.add(stackTraceElement);
                    }
                }
            }
            if (z) {
                if (th2 == null) {
                    th2 = new Throwable(th3.getMessage());
                } else {
                    th2 = new Throwable(th3.getMessage(), th2);
                }
                th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            }
        }
        return th2;
    }

    public static boolean zzn(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith((String) zzbkw.zzd.zze());
    }

    public static final int zzo(DisplayMetrics displayMetrics, int r2) {
        return (int) TypedValue.applyDimension(1, r2, displayMetrics);
    }

    public static final String zzp(StackTraceElement[] stackTraceElementArr, String str) {
        int r0;
        int r1;
        String str2;
        while (true) {
            r1 = r0 + 1;
            if (r1 >= stackTraceElementArr.length) {
                str2 = null;
                break;
            }
            StackTraceElement stackTraceElement = stackTraceElementArr[r0];
            String className = stackTraceElement.getClassName();
            r0 = ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (zzb.equalsIgnoreCase(className) || zzc.equalsIgnoreCase(className) || zzd.equalsIgnoreCase(className) || zze.equalsIgnoreCase(className) || zzf.equalsIgnoreCase(className) || zzg.equalsIgnoreCase(className))) ? 0 : r1;
        }
        str2 = stackTraceElementArr[r1].getClassName();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            StringBuilder sb = new StringBuilder();
            if (stringTokenizer.hasMoreElements()) {
                sb.append(stringTokenizer.nextToken());
                int r6 = 2;
                while (true) {
                    int r4 = r6 - 1;
                    if (r6 <= 0 || !stringTokenizer.hasMoreElements()) {
                        break;
                    }
                    sb.append(".");
                    sb.append(stringTokenizer.nextToken());
                    r6 = r4;
                }
                str = sb.toString();
            }
            if (str2 != null && !str2.contains(str)) {
                return str2;
            }
        }
        return null;
    }

    public static final boolean zzq() {
        if (Build.VERSION.SDK_INT >= 31) {
            return Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.contains("emulator");
        }
        return Build.DEVICE.startsWith("generic");
    }

    public static final boolean zzr(Context context, int r2) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, r2) == 0;
    }

    public static final boolean zzs(Context context) {
        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        return isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2;
    }

    public static final boolean zzt() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static final int zzu(DisplayMetrics displayMetrics, int r1) {
        return Math.round(r1 / displayMetrics.density);
    }

    public static final void zzv(Context context, String str, String str2, Bundle bundle, boolean z, zzcgf zzcgfVar) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        bundle.putString("os", Build.VERSION.RELEASE);
        bundle.putString("api", String.valueOf(Build.VERSION.SDK_INT));
        bundle.putString("appid", applicationContext.getPackageName());
        if (str == null) {
            str = GoogleApiAvailabilityLight.getInstance().getApkVersion(context) + ".222508000";
        }
        bundle.putString("js", str);
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps");
        for (String str3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(str3, bundle.getString(str3));
        }
        zzcgfVar.zza(appendQueryParameter.toString());
    }

    public static final int zzw(Context context, int r1) {
        return zzo(context.getResources().getDisplayMetrics(), r1);
    }

    public static final String zzx(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return zze(((contentResolver == null ? null : Settings.Secure.getString(contentResolver, "android_id")) == null || zzq()) ? "emulator" : "emulator");
    }

    private final JSONArray zzy(Collection collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : collection) {
            zzz(jSONArray, obj);
        }
        return jSONArray;
    }

    private final void zzz(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzh((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzi((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zzy((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONArray.put(zzg((Object[]) obj));
        } else {
            jSONArray.put(obj);
        }
    }

    public final int zzb(Context context, int r4) {
        if (this.zzh < 0.0f) {
            synchronized (this) {
                if (this.zzh < 0.0f) {
                    WindowManager windowManager = (WindowManager) context.getSystemService("window");
                    if (windowManager == null) {
                        return 0;
                    }
                    Display defaultDisplay = windowManager.getDefaultDisplay();
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    defaultDisplay.getMetrics(displayMetrics);
                    this.zzh = displayMetrics.density;
                }
            }
        }
        return Math.round(r4 / this.zzh);
    }

    final JSONArray zzg(Object[] objArr) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : objArr) {
            zzz(jSONArray, obj);
        }
        return jSONArray;
    }

    public final JSONObject zzh(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zzA(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public final JSONObject zzi(Map map) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : map.keySet()) {
                zzA(jSONObject, str, map.get(str));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            throw new JSONException("Could not convert map to JSON: ".concat(String.valueOf(e.getMessage())));
        }
    }

    public final JSONObject zzj(Bundle bundle, JSONObject jSONObject) {
        if (bundle != null) {
            try {
                return zzh(bundle);
            } catch (JSONException e) {
                zzcgn.zzh("Error converting Bundle to JSON", e);
                return null;
            }
        }
        return null;
    }

    public final void zzk(ViewGroup viewGroup, com.google.android.gms.ads.internal.client.zzq zzqVar, String str, String str2) {
        if (str2 != null) {
            zzcgn.zzj(str2);
        }
        zzB(viewGroup, zzqVar, str, SupportMenu.CATEGORY_MASK, ViewCompat.MEASURED_STATE_MASK);
    }

    public final void zzl(ViewGroup viewGroup, com.google.android.gms.ads.internal.client.zzq zzqVar, String str) {
        zzB(viewGroup, zzqVar, "Ads by Google", ViewCompat.MEASURED_STATE_MASK, -1);
    }

    public final void zzm(Context context, String str, String str2, Bundle bundle, boolean z) {
        zzv(context, str, "gmob-apps", bundle, true, new zzcgf() { // from class: com.google.android.gms.internal.ads.zzcgd
            @Override // com.google.android.gms.internal.ads.zzcgf
            public final boolean zza(String str3) {
                new zzcge(zzcgg.this, str3).start();
                return true;
            }
        });
    }
}
