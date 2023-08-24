package com.swmansion.reanimated.layoutReanimation;

import java.util.HashMap;

/* loaded from: classes3.dex */
public interface NativeMethodsHolder {
    boolean isLayoutAnimationEnabled();

    void removeConfigForTag(int r1);

    void startAnimationForTag(int r1, String str, HashMap<String, Float> hashMap);
}
