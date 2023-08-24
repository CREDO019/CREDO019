package com.google.maps.android.p017ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.canhub.cropper.CropImageOptions;
import com.google.maps.android.C3346R;

/* renamed from: com.google.maps.android.ui.IconGenerator */
/* loaded from: classes3.dex */
public class IconGenerator {
    public static final int STYLE_BLUE = 4;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_ORANGE = 7;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_RED = 3;
    public static final int STYLE_WHITE = 2;
    private float mAnchorU = 0.5f;
    private float mAnchorV = 1.0f;
    private BubbleDrawable mBackground;
    private ViewGroup mContainer;
    private View mContentView;
    private final Context mContext;
    private int mRotation;
    private RotationLayout mRotationLayout;
    private TextView mTextView;

    private static int getStyleColor(int r1) {
        if (r1 != 3) {
            if (r1 != 4) {
                if (r1 != 5) {
                    if (r1 != 6) {
                        return r1 != 7 ? -1 : -30720;
                    }
                    return -6736948;
                }
                return -10053376;
            }
            return -16737844;
        }
        return -3407872;
    }

    public IconGenerator(Context context) {
        this.mContext = context;
        this.mBackground = new BubbleDrawable(context.getResources());
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(C3346R.layout.amu_text_bubble, (ViewGroup) null);
        this.mContainer = viewGroup;
        RotationLayout rotationLayout = (RotationLayout) viewGroup.getChildAt(0);
        this.mRotationLayout = rotationLayout;
        TextView textView = (TextView) rotationLayout.findViewById(C3346R.C3349id.amu_text);
        this.mTextView = textView;
        this.mContentView = textView;
        setStyle(1);
    }

    public Bitmap makeIcon(CharSequence charSequence) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(charSequence);
        }
        return makeIcon();
    }

    public Bitmap makeIcon() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContainer.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.mContainer.getMeasuredWidth();
        int measuredHeight = this.mContainer.getMeasuredHeight();
        this.mContainer.layout(0, 0, measuredWidth, measuredHeight);
        int r3 = this.mRotation;
        if (r3 == 1 || r3 == 3) {
            measuredHeight = this.mContainer.getMeasuredWidth();
            measuredWidth = this.mContainer.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        int r5 = this.mRotation;
        if (r5 != 0) {
            if (r5 == 1) {
                canvas.translate(measuredWidth, 0.0f);
                canvas.rotate(90.0f);
            } else if (r5 == 2) {
                canvas.rotate(180.0f, measuredWidth / 2, measuredHeight / 2);
            } else {
                canvas.translate(0.0f, measuredHeight);
                canvas.rotate(270.0f);
            }
        }
        this.mContainer.draw(canvas);
        return createBitmap;
    }

    public void setContentView(View view) {
        this.mRotationLayout.removeAllViews();
        this.mRotationLayout.addView(view);
        this.mContentView = view;
        View findViewById = this.mRotationLayout.findViewById(C3346R.C3349id.amu_text);
        this.mTextView = findViewById instanceof TextView ? (TextView) findViewById : null;
    }

    public void setContentRotation(int r2) {
        this.mRotationLayout.setViewRotation(r2);
    }

    public void setRotation(int r1) {
        this.mRotation = ((r1 + CropImageOptions.DEGREES_360) % CropImageOptions.DEGREES_360) / 90;
    }

    public float getAnchorU() {
        return rotateAnchor(this.mAnchorU, this.mAnchorV);
    }

    public float getAnchorV() {
        return rotateAnchor(this.mAnchorV, this.mAnchorU);
    }

    private float rotateAnchor(float f, float f2) {
        int r0 = this.mRotation;
        if (r0 != 0) {
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 == 3) {
                        return f2;
                    }
                    throw new IllegalStateException();
                }
                return 1.0f - f;
            }
            return 1.0f - f2;
        }
        return f;
    }

    public void setTextAppearance(Context context, int r3) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setTextAppearance(context, r3);
        }
    }

    public void setTextAppearance(int r2) {
        setTextAppearance(this.mContext, r2);
    }

    public void setStyle(int r2) {
        setColor(getStyleColor(r2));
        setTextAppearance(this.mContext, getTextStyle(r2));
    }

    public void setColor(int r2) {
        this.mBackground.setColor(r2);
        setBackground(this.mBackground);
    }

    public void setBackground(Drawable drawable) {
        this.mContainer.setBackgroundDrawable(drawable);
        if (drawable != null) {
            Rect rect = new Rect();
            drawable.getPadding(rect);
            this.mContainer.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
        this.mContainer.setPadding(0, 0, 0, 0);
    }

    public void setContentPadding(int r2, int r3, int r4, int r5) {
        this.mContentView.setPadding(r2, r3, r4, r5);
    }

    private static int getTextStyle(int r1) {
        if (r1 != 3 && r1 != 4 && r1 != 5 && r1 != 6 && r1 != 7) {
            return C3346R.C3351style.amu_Bubble_TextAppearance_Dark;
        }
        return C3346R.C3351style.amu_Bubble_TextAppearance_Light;
    }
}
