package com.facebook.imagepipeline.cache;

import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.imagepipeline.cache.MemoryCache;

/* loaded from: classes.dex */
public class NativeMemoryCacheTrimStrategy implements MemoryCache.CacheTrimStrategy {
    private static final String TAG = "NativeMemoryCacheTrimStrategy";

    /* renamed from: com.facebook.imagepipeline.cache.NativeMemoryCacheTrimStrategy$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C13111 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$memory$MemoryTrimType;

        static {
            int[] r0 = new int[MemoryTrimType.values().length];
            $SwitchMap$com$facebook$common$memory$MemoryTrimType = r0;
            try {
                r0[MemoryTrimType.OnCloseToDalvikHeapLimit.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnAppBackgrounded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemMemoryCriticallyLowWhileAppInForeground.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$common$memory$MemoryTrimType[MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache.CacheTrimStrategy
    public double getTrimRatio(MemoryTrimType trimType) {
        int r0 = C13111.$SwitchMap$com$facebook$common$memory$MemoryTrimType[trimType.ordinal()];
        if (r0 != 1) {
            if (r0 == 2 || r0 == 3 || r0 == 4 || r0 == 5) {
                return 1.0d;
            }
            FLog.wtf(TAG, "unknown trim type: %s", trimType);
            return 0.0d;
        }
        return 0.0d;
    }
}
