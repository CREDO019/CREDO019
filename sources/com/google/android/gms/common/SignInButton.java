package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.base.C2127R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zaaa;
import com.google.android.gms.common.internal.zaz;
import com.google.android.gms.dynamic.RemoteCreator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int zaa;
    private int zab;
    private View zac;
    private View.OnClickListener zad;

    /* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ButtonSize {
    }

    /* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, null);
    }

    private final void zaa(Context context) {
        View view = this.zac;
        if (view != null) {
            removeView(view);
        }
        try {
            this.zac = zaz.zaa(context, this.zaa, this.zab);
        } catch (RemoteCreator.RemoteCreatorException unused) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            int r0 = this.zaa;
            int r1 = this.zab;
            zaaa zaaaVar = new zaaa(context, null);
            zaaaVar.zaa(context.getResources(), r0, r1);
            this.zac = zaaaVar;
        }
        addView(this.zac);
        this.zac.setEnabled(isEnabled());
        this.zac.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        View.OnClickListener onClickListener = this.zad;
        if (onClickListener == null || view != this.zac) {
            return;
        }
        onClickListener.onClick(this);
    }

    public void setColorScheme(int r2) {
        setStyle(this.zaa, r2);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.zac.setEnabled(z);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.zad = onClickListener;
        View view = this.zac;
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    @Deprecated
    public void setScopes(Scope[] scopeArr) {
        setStyle(this.zaa, this.zab);
    }

    public void setSize(int r2) {
        setStyle(r2, this.zab);
    }

    public void setStyle(int r1, int r2) {
        this.zaa = r1;
        this.zab = r2;
        zaa(getContext());
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public void setStyle(int r1, int r2, Scope[] scopeArr) {
        setStyle(r1, r2);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int r4) {
        super(context, attributeSet, r4);
        this.zad = null;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2127R.styleable.SignInButton, 0, 0);
        try {
            this.zaa = obtainStyledAttributes.getInt(C2127R.styleable.SignInButton_buttonSize, 0);
            this.zab = obtainStyledAttributes.getInt(C2127R.styleable.SignInButton_colorScheme, 2);
            obtainStyledAttributes.recycle();
            setStyle(this.zaa, this.zab);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
