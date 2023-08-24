package com.google.android.play.core.missingsplits;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.play.core.internal.C2494af;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.google.android.play.core.missingsplits.b */
/* loaded from: classes3.dex */
final class C2583b implements MissingSplitsManager {

    /* renamed from: a */
    private static final C2494af f878a = new C2494af("MissingSplitsManagerImpl");

    /* renamed from: b */
    private final Context f879b;

    /* renamed from: c */
    private final Runtime f880c;

    /* renamed from: d */
    private final C2582a f881d;

    /* renamed from: e */
    private final AtomicReference<Boolean> f882e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2583b(Context context, Runtime runtime, C2582a c2582a, AtomicReference<Boolean> atomicReference) {
        this.f879b = context;
        this.f880c = runtime;
        this.f881d = c2582a;
        this.f882e = atomicReference;
    }

    /* renamed from: a */
    private final boolean m628a() {
        try {
            ApplicationInfo applicationInfo = this.f879b.getPackageManager().getApplicationInfo(this.f879b.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                if (Boolean.TRUE.equals(applicationInfo.metaData.get("com.android.vending.splits.required"))) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            f878a.m804d("App '%s' is not found in the PackageManager", this.f879b.getPackageName());
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m627a(Set<String> set) {
        return set.isEmpty() || (set.size() == 1 && set.contains(""));
    }

    /* renamed from: b */
    private final Set<String> m626b() {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                PackageInfo packageInfo = this.f879b.getPackageManager().getPackageInfo(this.f879b.getPackageName(), 0);
                HashSet hashSet = new HashSet();
                if (packageInfo == null || packageInfo.splitNames == null) {
                    return hashSet;
                }
                Collections.addAll(hashSet, packageInfo.splitNames);
                return hashSet;
            } catch (PackageManager.NameNotFoundException unused) {
                f878a.m804d("App '%s' is not found in PackageManager", this.f879b.getPackageName());
                return Collections.emptySet();
            }
        }
        return Collections.emptySet();
    }

    /* renamed from: c */
    private final List<ActivityManager.AppTask> m625c() {
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) this.f879b.getSystemService("activity")).getAppTasks();
        return appTasks != null ? appTasks : Collections.emptyList();
    }

    @Override // com.google.android.play.core.missingsplits.MissingSplitsManager
    public final boolean disableAppIfMissingRequiredSplits() {
        boolean booleanValue;
        boolean z;
        Class<?> cls;
        ApplicationInfo applicationInfo;
        Set set;
        boolean z2;
        if (Build.VERSION.SDK_INT >= 21) {
            synchronized (this.f882e) {
                if (this.f882e.get() == null) {
                    AtomicReference<Boolean> atomicReference = this.f882e;
                    if (Build.VERSION.SDK_INT >= 21) {
                        try {
                            applicationInfo = this.f879b.getPackageManager().getApplicationInfo(this.f879b.getPackageName(), 128);
                        } catch (PackageManager.NameNotFoundException unused) {
                            f878a.m804d("App '%s' is not found in the PackageManager", this.f879b.getPackageName());
                        }
                        if (applicationInfo != null && applicationInfo.metaData != null) {
                            if (Boolean.TRUE.equals(applicationInfo.metaData.get("com.android.vending.splits.required"))) {
                                if (Build.VERSION.SDK_INT >= 21) {
                                    try {
                                        PackageInfo packageInfo = this.f879b.getPackageManager().getPackageInfo(this.f879b.getPackageName(), 0);
                                        HashSet hashSet = new HashSet();
                                        if (packageInfo != null && packageInfo.splitNames != null) {
                                            Collections.addAll(hashSet, packageInfo.splitNames);
                                        }
                                        set = hashSet;
                                    } catch (PackageManager.NameNotFoundException unused2) {
                                        f878a.m804d("App '%s' is not found in PackageManager", this.f879b.getPackageName());
                                    }
                                    if (!set.isEmpty() || (set.size() == 1 && set.contains(""))) {
                                        z2 = true;
                                        atomicReference.set(Boolean.valueOf(z2));
                                    }
                                }
                                set = Collections.emptySet();
                                if (!set.isEmpty()) {
                                }
                                z2 = true;
                                atomicReference.set(Boolean.valueOf(z2));
                            }
                        }
                    }
                    z2 = false;
                    atomicReference.set(Boolean.valueOf(z2));
                }
                booleanValue = this.f882e.get().booleanValue();
            }
            if (!booleanValue) {
                if (this.f881d.m633a()) {
                    this.f881d.m630c();
                    this.f880c.exit(0);
                }
                return false;
            }
            Iterator<ActivityManager.AppTask> it = m625c().iterator();
            while (true) {
                if (it.hasNext()) {
                    ActivityManager.AppTask next = it.next();
                    if (next.getTaskInfo() != null && next.getTaskInfo().baseIntent != null && next.getTaskInfo().baseIntent.getComponent() != null && PlayCoreMissingSplitsActivity.class.getName().equals(next.getTaskInfo().baseIntent.getComponent().getClassName())) {
                        break;
                    }
                } else {
                    loop1: for (ActivityManager.AppTask appTask : m625c()) {
                        ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
                        if (taskInfo != null && taskInfo.baseIntent != null && taskInfo.baseIntent.getComponent() != null) {
                            ComponentName component = taskInfo.baseIntent.getComponent();
                            String className = component.getClassName();
                            try {
                                cls = Class.forName(className);
                            } catch (ClassNotFoundException unused3) {
                                f878a.m804d("ClassNotFoundException when scanning class hierarchy of '%s'", className);
                                try {
                                    if (this.f879b.getPackageManager().getActivityInfo(component, 0) != null) {
                                    }
                                } catch (PackageManager.NameNotFoundException unused4) {
                                }
                            }
                            while (cls != null) {
                                if (cls.equals(Activity.class)) {
                                    z = true;
                                    break loop1;
                                }
                                Class<? super Object> superclass = cls.getSuperclass();
                                cls = superclass != cls ? superclass : null;
                            }
                            continue;
                        }
                    }
                    z = false;
                    this.f881d.m631b();
                    for (ActivityManager.AppTask appTask2 : m625c()) {
                        appTask2.finishAndRemoveTask();
                    }
                    if (z) {
                        this.f879b.getPackageManager().setComponentEnabledSetting(new ComponentName(this.f879b, PlayCoreMissingSplitsActivity.class), 1, 1);
                        this.f879b.startActivity(new Intent(this.f879b, PlayCoreMissingSplitsActivity.class).addFlags(884998144));
                    }
                    this.f880c.exit(0);
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.play.core.missingsplits.MissingSplitsManager
    public final boolean isMissingRequiredSplits() {
        boolean booleanValue;
        synchronized (this.f882e) {
            if (this.f882e.get() == null) {
                AtomicReference<Boolean> atomicReference = this.f882e;
                boolean z = false;
                if (Build.VERSION.SDK_INT >= 21 && m628a() && m627a(m626b())) {
                    z = true;
                }
                atomicReference.set(Boolean.valueOf(z));
            }
            booleanValue = this.f882e.get().booleanValue();
        }
        return booleanValue;
    }
}
