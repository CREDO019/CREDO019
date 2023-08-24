package com.airbnb.lottie;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public class LottieImageAsset {
    private Bitmap bitmap;
    private final String dirName;
    private final String fileName;
    private final int height;

    /* renamed from: id */
    private final String f73id;
    private final int width;

    public LottieImageAsset(int r1, int r2, String str, String str2, String str3) {
        this.width = r1;
        this.height = r2;
        this.f73id = str;
        this.fileName = str2;
        this.dirName = str3;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getId() {
        return this.f73id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getDirName() {
        return this.dirName;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean hasBitmap() {
        return this.bitmap != null || (this.fileName.startsWith("data:") && this.fileName.indexOf("base64,") > 0);
    }
}