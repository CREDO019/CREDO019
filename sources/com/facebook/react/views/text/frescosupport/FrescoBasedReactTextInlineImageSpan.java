package com.facebook.react.views.text.frescosupport;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.text.TextInlineImageSpan;

/* loaded from: classes.dex */
public class FrescoBasedReactTextInlineImageSpan extends TextInlineImageSpan {
    private final Object mCallerContext;
    private Drawable mDrawable;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private final DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
    private ReadableMap mHeaders;
    private int mHeight;
    private String mResizeMode;
    private TextView mTextView;
    private int mTintColor;
    private Uri mUri;
    private int mWidth;

    public FrescoBasedReactTextInlineImageSpan(Resources resources, int r3, int r4, int r5, Uri uri, ReadableMap readableMap, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj, String str) {
        this.mDraweeHolder = new DraweeHolder<>(GenericDraweeHierarchyBuilder.newInstance(resources).build());
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
        this.mTintColor = r5;
        this.mUri = uri == null ? Uri.EMPTY : uri;
        this.mHeaders = readableMap;
        this.mWidth = (int) PixelUtil.toPixelFromDIP(r4);
        this.mHeight = (int) PixelUtil.toPixelFromDIP(r3);
        this.mResizeMode = str;
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onDetachedFromWindow() {
        this.mDraweeHolder.onDetach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onStartTemporaryDetach() {
        this.mDraweeHolder.onDetach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onAttachedToWindow() {
        this.mDraweeHolder.onAttach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void onFinishTemporaryDetach() {
        this.mDraweeHolder.onAttach();
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public Drawable getDrawable() {
        return this.mDrawable;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int r3, int r4, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = -this.mHeight;
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = 0;
        }
        return this.mWidth;
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public void setTextView(TextView textView) {
        this.mTextView = textView;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int r3, int r4, float f, int r6, int r7, int r8, Paint paint) {
        if (this.mDrawable == null) {
            ReactNetworkImageRequest fromBuilderWithHeaders = ReactNetworkImageRequest.fromBuilderWithHeaders(ImageRequestBuilder.newBuilderWithSource(this.mUri), this.mHeaders);
            this.mDraweeHolder.getHierarchy().setActualImageScaleType(getResizeMode(this.mResizeMode));
            this.mDraweeHolder.setController(this.mDraweeControllerBuilder.reset().setOldController(this.mDraweeHolder.getController()).setCallerContext(this.mCallerContext).setImageRequest(fromBuilderWithHeaders).build());
            this.mDraweeControllerBuilder.reset();
            Drawable topLevelDrawable = this.mDraweeHolder.getTopLevelDrawable();
            this.mDrawable = topLevelDrawable;
            topLevelDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
            int r2 = this.mTintColor;
            if (r2 != 0) {
                this.mDrawable.setColorFilter(r2, PorterDuff.Mode.SRC_IN);
            }
            this.mDrawable.setCallback(this.mTextView);
        }
        canvas.save();
        canvas.translate(f, ((r7 + ((int) paint.descent())) - (((int) (paint.descent() - paint.ascent())) / 2)) - ((this.mDrawable.getBounds().bottom - this.mDrawable.getBounds().top) / 2));
        this.mDrawable.draw(canvas);
        canvas.restore();
    }

    private ScalingUtils.ScaleType getResizeMode(String str) {
        return ImageResizeMode.toScaleType(str);
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public int getWidth() {
        return this.mWidth;
    }

    @Override // com.facebook.react.views.text.TextInlineImageSpan
    public int getHeight() {
        return this.mHeight;
    }
}
