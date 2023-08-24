package com.facebook.react.uimanager.layoutanimation;

/* loaded from: classes.dex */
enum LayoutAnimationType {
    CREATE,
    UPDATE,
    DELETE;

    /* renamed from: com.facebook.react.uimanager.layoutanimation.LayoutAnimationType$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C17011 {

        /* renamed from: $SwitchMap$com$facebook$react$uimanager$layoutanimation$LayoutAnimationType */
        static final /* synthetic */ int[] f174x364296ff;

        static {
            int[] r0 = new int[LayoutAnimationType.values().length];
            f174x364296ff = r0;
            try {
                r0[LayoutAnimationType.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f174x364296ff[LayoutAnimationType.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f174x364296ff[LayoutAnimationType.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static String toString(LayoutAnimationType layoutAnimationType) {
        int r0 = C17011.f174x364296ff[layoutAnimationType.ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 == 3) {
                    return "delete";
                }
                throw new IllegalArgumentException("Unsupported LayoutAnimationType: " + layoutAnimationType);
            }
            return "update";
        }
        return "create";
    }
}
