package com.airbnb.lottie;

/* loaded from: classes.dex */
public enum RenderMode {
    AUTOMATIC,
    HARDWARE,
    SOFTWARE;

    /* renamed from: com.airbnb.lottie.RenderMode$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C10101 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$RenderMode;

        static {
            int[] r0 = new int[RenderMode.values().length];
            $SwitchMap$com$airbnb$lottie$RenderMode = r0;
            try {
                r0[RenderMode.HARDWARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$RenderMode[RenderMode.SOFTWARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$RenderMode[RenderMode.AUTOMATIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public boolean useSoftwareRendering(int r5, boolean z, int r7) {
        int r0 = C10101.$SwitchMap$com$airbnb$lottie$RenderMode[ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                return (z && r5 < 28) || r7 > 4 || r5 <= 25;
            }
            return true;
        }
        return false;
    }
}
