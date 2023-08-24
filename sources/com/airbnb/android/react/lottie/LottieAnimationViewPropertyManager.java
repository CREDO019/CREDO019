package com.airbnb.android.react.lottie;

import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.RenderMode;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class LottieAnimationViewPropertyManager {
    private String animationJson;
    private String animationName;
    private boolean animationNameDirty;
    private ReadableArray colorFilters;
    private Boolean enableMergePaths;
    private String imageAssetsFolder;
    private Boolean loop;
    private Float progress;
    private RenderMode renderMode;
    private ImageView.ScaleType scaleType;
    private Float speed;
    private ReadableArray textFilters;
    private final WeakReference<LottieAnimationView> viewWeakReference;

    public LottieAnimationViewPropertyManager(LottieAnimationView lottieAnimationView) {
        this.viewWeakReference = new WeakReference<>(lottieAnimationView);
    }

    public void setAnimationName(String str) {
        this.animationName = str;
        this.animationNameDirty = true;
    }

    public void setAnimationJson(String str) {
        this.animationJson = str;
    }

    public void setProgress(Float f) {
        this.progress = f;
    }

    public void setSpeed(float f) {
        this.speed = Float.valueOf(f);
    }

    public void setLoop(boolean z) {
        this.loop = Boolean.valueOf(z);
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
    }

    public void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
    }

    public void setImageAssetsFolder(String str) {
        this.imageAssetsFolder = str;
    }

    public void setEnableMergePaths(boolean z) {
        this.enableMergePaths = Boolean.valueOf(z);
    }

    public void setColorFilters(ReadableArray readableArray) {
        this.colorFilters = readableArray;
    }

    public void setTextFilters(ReadableArray readableArray) {
        this.textFilters = readableArray;
    }

    public void commitChanges() {
        int r3;
        LottieAnimationView lottieAnimationView = this.viewWeakReference.get();
        if (lottieAnimationView == null) {
            return;
        }
        ReadableArray readableArray = this.textFilters;
        if (readableArray != null && readableArray.size() > 0) {
            TextDelegate textDelegate = new TextDelegate(lottieAnimationView);
            for (int r32 = 0; r32 < this.textFilters.size(); r32++) {
                ReadableMap map = this.textFilters.getMap(r32);
                textDelegate.setText(map.getString("find"), map.getString("replace"));
            }
            lottieAnimationView.setTextDelegate(textDelegate);
        }
        String str = this.animationJson;
        if (str != null) {
            lottieAnimationView.setAnimationFromJson(str, Integer.toString(str.hashCode()));
            this.animationJson = null;
        }
        if (this.animationNameDirty) {
            lottieAnimationView.setAnimation(this.animationName);
            this.animationNameDirty = false;
        }
        Float f = this.progress;
        if (f != null) {
            lottieAnimationView.setProgress(f.floatValue());
            this.progress = null;
        }
        Boolean bool = this.loop;
        if (bool != null) {
            lottieAnimationView.setRepeatCount(bool.booleanValue() ? -1 : 0);
            this.loop = null;
        }
        Float f2 = this.speed;
        if (f2 != null) {
            lottieAnimationView.setSpeed(f2.floatValue());
            this.speed = null;
        }
        ImageView.ScaleType scaleType = this.scaleType;
        if (scaleType != null) {
            lottieAnimationView.setScaleType(scaleType);
            this.scaleType = null;
        }
        RenderMode renderMode = this.renderMode;
        if (renderMode != null) {
            lottieAnimationView.setRenderMode(renderMode);
            this.renderMode = null;
        }
        String str2 = this.imageAssetsFolder;
        if (str2 != null) {
            lottieAnimationView.setImageAssetsFolder(str2);
            this.imageAssetsFolder = null;
        }
        Boolean bool2 = this.enableMergePaths;
        if (bool2 != null) {
            lottieAnimationView.enableMergePathsForKitKatAndAbove(bool2.booleanValue());
            this.enableMergePaths = null;
        }
        ReadableArray readableArray2 = this.colorFilters;
        if (readableArray2 == null || readableArray2.size() <= 0) {
            return;
        }
        for (int r2 = 0; r2 < this.colorFilters.size(); r2++) {
            ReadableMap map2 = this.colorFilters.getMap(r2);
            if (map2.getType("color") == ReadableType.Map) {
                r3 = ColorPropConverter.getColor(map2.getMap("color"), lottieAnimationView.getContext()).intValue();
            } else {
                r3 = map2.getInt("color");
            }
            String string = map2.getString("keypath");
            SimpleColorFilter simpleColorFilter = new SimpleColorFilter(r3);
            lottieAnimationView.addValueCallback(new KeyPath((string + ".**").split(Pattern.quote("."))), (KeyPath) LottieProperty.COLOR_FILTER, (LottieValueCallback<KeyPath>) new LottieValueCallback(simpleColorFilter));
        }
    }
}
