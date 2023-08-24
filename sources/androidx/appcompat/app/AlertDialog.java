package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.C0079R;
import androidx.appcompat.app.AlertController;

/* loaded from: classes.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    protected AlertDialog(Context context) {
        this(context, 0);
    }

    protected AlertDialog(Context context, int r3) {
        super(context, resolveDialogTheme(context, r3));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0);
        setCancelable(z);
        setOnCancelListener(onCancelListener);
    }

    static int resolveDialogTheme(Context context, int r3) {
        if (((r3 >>> 24) & 255) >= 1) {
            return r3;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0079R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int r2) {
        return this.mAlert.getButton(r2);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }

    public void setCustomTitle(View view) {
        this.mAlert.setCustomTitle(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.setMessage(charSequence);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int r8, int r9, int r10, int r11) {
        this.mAlert.setView(view, r8, r9, r10, r11);
    }

    void setButtonPanelLayoutHint(int r2) {
        this.mAlert.setButtonPanelLayoutHint(r2);
    }

    public void setButton(int r7, CharSequence charSequence, Message message) {
        this.mAlert.setButton(r7, charSequence, null, message, null);
    }

    public void setButton(int r7, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(r7, charSequence, onClickListener, null, null);
    }

    public void setButton(int r7, CharSequence charSequence, Drawable drawable, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(r7, charSequence, onClickListener, null, drawable);
    }

    public void setIcon(int r2) {
        this.mAlert.setIcon(r2);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.setIcon(drawable);
    }

    public void setIconAttribute(int r4) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(r4, typedValue, true);
        this.mAlert.setIcon(typedValue.resourceId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.installContent();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r2, KeyEvent keyEvent) {
        if (this.mAlert.onKeyDown(r2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(r2, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent keyEvent) {
        if (this.mAlert.onKeyUp(r2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(r2, keyEvent);
    }

    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: P */
        private final AlertController.AlertParams f8P;
        private final int mTheme;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(Context context, int r5) {
            this.f8P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, r5)));
            this.mTheme = r5;
        }

        public Context getContext() {
            return this.f8P.mContext;
        }

        public Builder setTitle(int r3) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mTitle = alertParams.mContext.getText(r3);
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.f8P.mTitle = charSequence;
            return this;
        }

        public Builder setCustomTitle(View view) {
            this.f8P.mCustomTitleView = view;
            return this;
        }

        public Builder setMessage(int r3) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mMessage = alertParams.mContext.getText(r3);
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.f8P.mMessage = charSequence;
            return this;
        }

        public Builder setIcon(int r2) {
            this.f8P.mIconId = r2;
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.f8P.mIcon = drawable;
            return this;
        }

        public Builder setIconAttribute(int r4) {
            TypedValue typedValue = new TypedValue();
            this.f8P.mContext.getTheme().resolveAttribute(r4, typedValue, true);
            this.f8P.mIconId = typedValue.resourceId;
            return this;
        }

        public Builder setPositiveButton(int r3, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(r3);
            this.f8P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mPositiveButtonText = charSequence;
            this.f8P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable drawable) {
            this.f8P.mPositiveButtonIcon = drawable;
            return this;
        }

        public Builder setNegativeButton(int r3, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(r3);
            this.f8P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mNegativeButtonText = charSequence;
            this.f8P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable drawable) {
            this.f8P.mNegativeButtonIcon = drawable;
            return this;
        }

        public Builder setNeutralButton(int r3, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mNeutralButtonText = alertParams.mContext.getText(r3);
            this.f8P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mNeutralButtonText = charSequence;
            this.f8P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable drawable) {
            this.f8P.mNeutralButtonIcon = drawable;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.f8P.mCancelable = z;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f8P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f8P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f8P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int r3, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(r3);
            this.f8P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mItems = charSequenceArr;
            this.f8P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mAdapter = listAdapter;
            this.f8P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            this.f8P.mCursor = cursor;
            this.f8P.mLabelColumn = str;
            this.f8P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMultiChoiceItems(int r3, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(r3);
            this.f8P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f8P.mCheckedItems = zArr;
            this.f8P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f8P.mItems = charSequenceArr;
            this.f8P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f8P.mCheckedItems = zArr;
            this.f8P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f8P.mCursor = cursor;
            this.f8P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f8P.mIsCheckedColumn = str;
            this.f8P.mLabelColumn = str2;
            this.f8P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int r3, int r4, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f8P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(r3);
            this.f8P.mOnClickListener = onClickListener;
            this.f8P.mCheckedItem = r4;
            this.f8P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int r3, String str, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mCursor = cursor;
            this.f8P.mOnClickListener = onClickListener;
            this.f8P.mCheckedItem = r3;
            this.f8P.mLabelColumn = str;
            this.f8P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int r3, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mItems = charSequenceArr;
            this.f8P.mOnClickListener = onClickListener;
            this.f8P.mCheckedItem = r3;
            this.f8P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int r3, DialogInterface.OnClickListener onClickListener) {
            this.f8P.mAdapter = listAdapter;
            this.f8P.mOnClickListener = onClickListener;
            this.f8P.mCheckedItem = r3;
            this.f8P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f8P.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder setView(int r3) {
            this.f8P.mView = null;
            this.f8P.mViewLayoutResId = r3;
            this.f8P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f8P.mView = view;
            this.f8P.mViewLayoutResId = 0;
            this.f8P.mViewSpacingSpecified = false;
            return this;
        }

        @Deprecated
        public Builder setView(View view, int r3, int r4, int r5, int r6) {
            this.f8P.mView = view;
            this.f8P.mViewLayoutResId = 0;
            this.f8P.mViewSpacingSpecified = true;
            this.f8P.mViewSpacingLeft = r3;
            this.f8P.mViewSpacingTop = r4;
            this.f8P.mViewSpacingRight = r5;
            this.f8P.mViewSpacingBottom = r6;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean z) {
            this.f8P.mForceInverseBackground = z;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean z) {
            this.f8P.mRecycleOnMeasure = z;
            return this;
        }

        public AlertDialog create() {
            AlertDialog alertDialog = new AlertDialog(this.f8P.mContext, this.mTheme);
            this.f8P.apply(alertDialog.mAlert);
            alertDialog.setCancelable(this.f8P.mCancelable);
            if (this.f8P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f8P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f8P.mOnDismissListener);
            if (this.f8P.mOnKeyListener != null) {
                alertDialog.setOnKeyListener(this.f8P.mOnKeyListener);
            }
            return alertDialog;
        }

        public AlertDialog show() {
            AlertDialog create = create();
            create.show();
            return create;
        }
    }
}
