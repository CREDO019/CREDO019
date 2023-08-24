package com.canhub.cropper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.canhub.cropper.C1150R;
import com.canhub.cropper.CropOverlayView;
import java.util.Objects;

/* loaded from: classes.dex */
public final class CropImageViewBinding implements ViewBinding {
    public final CropOverlayView CropOverlayView;
    public final ProgressBar CropProgressBar;
    public final ImageView ImageViewImage;
    private final View rootView;

    private CropImageViewBinding(View view, CropOverlayView cropOverlayView, ProgressBar progressBar, ImageView imageView) {
        this.rootView = view;
        this.CropOverlayView = cropOverlayView;
        this.CropProgressBar = progressBar;
        this.ImageViewImage = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static CropImageViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Objects.requireNonNull(viewGroup, "parent");
        layoutInflater.inflate(C1150R.layout.crop_image_view, viewGroup);
        return bind(viewGroup);
    }

    public static CropImageViewBinding bind(View view) {
        int r0 = C1150R.C1154id.CropOverlayView;
        CropOverlayView cropOverlayView = (CropOverlayView) ViewBindings.findChildViewById(view, r0);
        if (cropOverlayView != null) {
            r0 = C1150R.C1154id.CropProgressBar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, r0);
            if (progressBar != null) {
                r0 = C1150R.C1154id.ImageView_image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, r0);
                if (imageView != null) {
                    return new CropImageViewBinding(view, cropOverlayView, progressBar, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(r0)));
    }
}
