package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.utils.C1028Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CompoundTrimPathContent {
    private final List<TrimPathContent> contents = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTrimPath(TrimPathContent trimPathContent) {
        this.contents.add(trimPathContent);
    }

    public void apply(Path path) {
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            C1028Utils.applyTrimPathIfNeeded(path, this.contents.get(size));
        }
    }
}
