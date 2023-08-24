package androidx.biometric;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.exoplayer2.ExoPlayer;

/* loaded from: classes.dex */
public class FingerprintDialogFragment extends DialogFragment {
    private static final int MESSAGE_DISPLAY_TIME_MS = 2000;
    static final int STATE_FINGERPRINT = 1;
    static final int STATE_FINGERPRINT_AUTHENTICATED = 3;
    static final int STATE_FINGERPRINT_ERROR = 2;
    static final int STATE_NONE = 0;
    private static final String TAG = "FingerprintFragment";
    private int mErrorTextColor;
    private ImageView mFingerprintIcon;
    TextView mHelpMessageView;
    private int mNormalTextColor;
    BiometricViewModel mViewModel;
    final Handler mHandler = new Handler(Looper.getMainLooper());
    final Runnable mResetDialogRunnable = new Runnable() { // from class: androidx.biometric.FingerprintDialogFragment.1
        @Override // java.lang.Runnable
        public void run() {
            FingerprintDialogFragment.this.resetDialog();
        }
    };

    private boolean shouldAnimateForTransition(int r4, int r5) {
        if (r4 == 0 && r5 == 1) {
            return false;
        }
        if (r4 == 1 && r5 == 2) {
            return true;
        }
        return r4 == 2 && r5 == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FingerprintDialogFragment newInstance() {
        return new FingerprintDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        connectViewModel();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mErrorTextColor = getThemedColorFor(Api26Impl.getColorErrorAttr());
        } else {
            Context context = getContext();
            this.mErrorTextColor = context != null ? ContextCompat.getColor(context, C0214R.C0215color.biometric_error_color) : 0;
        }
        this.mNormalTextColor = getThemedColorFor(16842808);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence negativeButtonText;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(this.mViewModel.getTitle());
        View inflate = LayoutInflater.from(builder.getContext()).inflate(C0214R.layout.fingerprint_dialog_layout, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(C0214R.C0217id.fingerprint_subtitle);
        if (textView != null) {
            CharSequence subtitle = this.mViewModel.getSubtitle();
            if (TextUtils.isEmpty(subtitle)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(subtitle);
            }
        }
        TextView textView2 = (TextView) inflate.findViewById(C0214R.C0217id.fingerprint_description);
        if (textView2 != null) {
            CharSequence description = this.mViewModel.getDescription();
            if (TextUtils.isEmpty(description)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
                textView2.setText(description);
            }
        }
        this.mFingerprintIcon = (ImageView) inflate.findViewById(C0214R.C0217id.fingerprint_icon);
        this.mHelpMessageView = (TextView) inflate.findViewById(C0214R.C0217id.fingerprint_error);
        if (AuthenticatorUtils.isDeviceCredentialAllowed(this.mViewModel.getAllowedAuthenticators())) {
            negativeButtonText = getString(C0214R.string.confirm_device_credential_password);
        } else {
            negativeButtonText = this.mViewModel.getNegativeButtonText();
        }
        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() { // from class: androidx.biometric.FingerprintDialogFragment.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int r2) {
                FingerprintDialogFragment.this.mViewModel.setNegativeButtonPressPending(true);
            }
        });
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mViewModel.setFingerprintDialogPreviousState(0);
        this.mViewModel.setFingerprintDialogState(1);
        this.mViewModel.setFingerprintDialogHelpMessage(getString(C0214R.string.fingerprint_dialog_touch_sensor));
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.mViewModel.setFingerprintDialogCancelPending(true);
    }

    private void connectViewModel() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        BiometricViewModel biometricViewModel = (BiometricViewModel) new ViewModelProvider(activity).get(BiometricViewModel.class);
        this.mViewModel = biometricViewModel;
        biometricViewModel.getFingerprintDialogState().observe(this, new Observer<Integer>() { // from class: androidx.biometric.FingerprintDialogFragment.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Integer num) {
                FingerprintDialogFragment.this.mHandler.removeCallbacks(FingerprintDialogFragment.this.mResetDialogRunnable);
                FingerprintDialogFragment.this.updateFingerprintIcon(num.intValue());
                FingerprintDialogFragment.this.updateHelpMessageColor(num.intValue());
                FingerprintDialogFragment.this.mHandler.postDelayed(FingerprintDialogFragment.this.mResetDialogRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
            }
        });
        this.mViewModel.getFingerprintDialogHelpMessage().observe(this, new Observer<CharSequence>() { // from class: androidx.biometric.FingerprintDialogFragment.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(CharSequence charSequence) {
                FingerprintDialogFragment.this.mHandler.removeCallbacks(FingerprintDialogFragment.this.mResetDialogRunnable);
                FingerprintDialogFragment.this.updateHelpMessageText(charSequence);
                FingerprintDialogFragment.this.mHandler.postDelayed(FingerprintDialogFragment.this.mResetDialogRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
            }
        });
    }

    void updateFingerprintIcon(int r4) {
        int fingerprintDialogPreviousState;
        Drawable assetForTransition;
        if (this.mFingerprintIcon == null || Build.VERSION.SDK_INT < 23 || (assetForTransition = getAssetForTransition((fingerprintDialogPreviousState = this.mViewModel.getFingerprintDialogPreviousState()), r4)) == null) {
            return;
        }
        this.mFingerprintIcon.setImageDrawable(assetForTransition);
        if (shouldAnimateForTransition(fingerprintDialogPreviousState, r4)) {
            Api21Impl.startAnimation(assetForTransition);
        }
        this.mViewModel.setFingerprintDialogPreviousState(r4);
    }

    void updateHelpMessageColor(int r3) {
        TextView textView = this.mHelpMessageView;
        if (textView != null) {
            textView.setTextColor(r3 == 2 ? this.mErrorTextColor : this.mNormalTextColor);
        }
    }

    void updateHelpMessageText(CharSequence charSequence) {
        TextView textView = this.mHelpMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    void resetDialog() {
        Context context = getContext();
        if (context == null) {
            Log.w(TAG, "Not resetting the dialog. Context is null.");
            return;
        }
        this.mViewModel.setFingerprintDialogState(1);
        this.mViewModel.setFingerprintDialogHelpMessage(context.getString(C0214R.string.fingerprint_dialog_touch_sensor));
    }

    private int getThemedColorFor(int r6) {
        Context context = getContext();
        FragmentActivity activity = getActivity();
        if (context == null || activity == null) {
            Log.w(TAG, "Unable to get themed color. Context or activity is null.");
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(r6, typedValue, true);
        TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(typedValue.data, new int[]{r6});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    private Drawable getAssetForTransition(int r5, int r6) {
        int r52;
        Context context = getContext();
        if (context == null) {
            Log.w(TAG, "Unable to get asset. Context is null.");
            return null;
        }
        if (r5 == 0 && r6 == 1) {
            r52 = C0214R.C0216drawable.fingerprint_dialog_fp_icon;
        } else if (r5 == 1 && r6 == 2) {
            r52 = C0214R.C0216drawable.fingerprint_dialog_error;
        } else if (r5 == 2 && r6 == 1) {
            r52 = C0214R.C0216drawable.fingerprint_dialog_fp_icon;
        } else if (r5 != 1 || r6 != 3) {
            return null;
        } else {
            r52 = C0214R.C0216drawable.fingerprint_dialog_fp_icon;
        }
        return ContextCompat.getDrawable(context, r52);
    }

    /* loaded from: classes.dex */
    private static class Api26Impl {
        private Api26Impl() {
        }

        static int getColorErrorAttr() {
            return C0214R.attr.colorError;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api21Impl {
        private Api21Impl() {
        }

        static void startAnimation(Drawable drawable) {
            if (drawable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable).start();
            }
        }
    }
}