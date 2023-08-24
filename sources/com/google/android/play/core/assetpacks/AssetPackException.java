package com.google.android.play.core.assetpacks;

import com.google.android.play.core.assetpacks.model.C2468a;
import com.google.android.play.core.tasks.AbstractC2683j;

/* loaded from: classes3.dex */
public class AssetPackException extends AbstractC2683j {

    /* renamed from: a */
    private final int f370a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssetPackException(int r4) {
        super(String.format("Asset Pack Download Error(%d): %s", Integer.valueOf(r4), C2468a.m843a(r4)));
        if (r4 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
        this.f370a = r4;
    }

    @Override // com.google.android.play.core.tasks.AbstractC2683j
    public int getErrorCode() {
        return this.f370a;
    }
}
