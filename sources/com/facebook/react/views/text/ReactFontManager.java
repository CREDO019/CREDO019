package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.content.res.ResourcesCompat;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ReactFontManager {
    private static final String[] EXTENSIONS = {"", "_bold", "_italic", "_bold_italic"};
    private static final String[] FILE_EXTENSIONS = {".ttf", ".otf"};
    private static final String FONTS_ASSET_PATH = "fonts/";
    private static ReactFontManager sReactFontManagerInstance;
    private final Map<String, AssetFontFamily> mFontCache = new HashMap();
    private final Map<String, Typeface> mCustomTypefaceCache = new HashMap();

    private ReactFontManager() {
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager();
        }
        return sReactFontManagerInstance;
    }

    public Typeface getTypeface(String str, int r3, AssetManager assetManager) {
        return getTypeface(str, new TypefaceStyle(r3), assetManager);
    }

    public Typeface getTypeface(String str, int r3, boolean z, AssetManager assetManager) {
        return getTypeface(str, new TypefaceStyle(r3, z), assetManager);
    }

    public Typeface getTypeface(String str, int r3, int r4, AssetManager assetManager) {
        return getTypeface(str, new TypefaceStyle(r3, r4), assetManager);
    }

    public Typeface getTypeface(String str, TypefaceStyle typefaceStyle, AssetManager assetManager) {
        if (this.mCustomTypefaceCache.containsKey(str)) {
            return typefaceStyle.apply(this.mCustomTypefaceCache.get(str));
        }
        AssetFontFamily assetFontFamily = this.mFontCache.get(str);
        if (assetFontFamily == null) {
            assetFontFamily = new AssetFontFamily();
            this.mFontCache.put(str, assetFontFamily);
        }
        int nearestStyle = typefaceStyle.getNearestStyle();
        Typeface typefaceForStyle = assetFontFamily.getTypefaceForStyle(nearestStyle);
        if (typefaceForStyle == null) {
            Typeface createAssetTypeface = createAssetTypeface(str, nearestStyle, assetManager);
            assetFontFamily.setTypefaceForStyle(nearestStyle, createAssetTypeface);
            return createAssetTypeface;
        }
        return typefaceForStyle;
    }

    public void addCustomFont(Context context, String str, int r3) {
        Typeface font = ResourcesCompat.getFont(context, r3);
        if (font != null) {
            this.mCustomTypefaceCache.put(str, font);
        }
    }

    public void addCustomFont(String str, Typeface typeface) {
        if (typeface != null) {
            this.mCustomTypefaceCache.put(str, typeface);
        }
    }

    public void setTypeface(String str, int r4, Typeface typeface) {
        if (typeface != null) {
            AssetFontFamily assetFontFamily = this.mFontCache.get(str);
            if (assetFontFamily == null) {
                assetFontFamily = new AssetFontFamily();
                this.mFontCache.put(str, assetFontFamily);
            }
            assetFontFamily.setTypefaceForStyle(r4, typeface);
        }
    }

    private static Typeface createAssetTypeface(String str, int r8, AssetManager assetManager) {
        String[] strArr;
        String str2 = EXTENSIONS[r8];
        for (String str3 : FILE_EXTENSIONS) {
            try {
                return Typeface.createFromAsset(assetManager, FONTS_ASSET_PATH + str + str2 + str3);
            } catch (RuntimeException unused) {
            }
        }
        return Typeface.create(str, r8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AssetFontFamily {
        private SparseArray<Typeface> mTypefaceSparseArray;

        private AssetFontFamily() {
            this.mTypefaceSparseArray = new SparseArray<>(4);
        }

        public Typeface getTypefaceForStyle(int r2) {
            return this.mTypefaceSparseArray.get(r2);
        }

        public void setTypefaceForStyle(int r2, Typeface typeface) {
            this.mTypefaceSparseArray.put(r2, typeface);
        }
    }
}
