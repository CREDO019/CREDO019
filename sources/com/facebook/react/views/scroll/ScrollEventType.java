package com.facebook.react.views.scroll;

/* loaded from: classes.dex */
public enum ScrollEventType {
    BEGIN_DRAG,
    END_DRAG,
    SCROLL,
    MOMENTUM_BEGIN,
    MOMENTUM_END;

    /* renamed from: com.facebook.react.views.scroll.ScrollEventType$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C17141 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$views$scroll$ScrollEventType;

        static {
            int[] r0 = new int[ScrollEventType.values().length];
            $SwitchMap$com$facebook$react$views$scroll$ScrollEventType = r0;
            try {
                r0[ScrollEventType.BEGIN_DRAG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.END_DRAG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.SCROLL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.MOMENTUM_BEGIN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$views$scroll$ScrollEventType[ScrollEventType.MOMENTUM_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static String getJSEventName(ScrollEventType scrollEventType) {
        int r0 = C17141.$SwitchMap$com$facebook$react$views$scroll$ScrollEventType[scrollEventType.ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 != 4) {
                        if (r0 == 5) {
                            return "topMomentumScrollEnd";
                        }
                        throw new IllegalArgumentException("Unsupported ScrollEventType: " + scrollEventType);
                    }
                    return "topMomentumScrollBegin";
                }
                return "topScroll";
            }
            return "topScrollEndDrag";
        }
        return "topScrollBeginDrag";
    }
}
