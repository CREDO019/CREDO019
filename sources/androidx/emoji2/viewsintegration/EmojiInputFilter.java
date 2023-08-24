package androidx.emoji2.viewsintegration;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class EmojiInputFilter implements InputFilter {
    private EmojiCompat.InitCallback mInitCallback;
    private final TextView mTextView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmojiInputFilter(TextView textView) {
        this.mTextView = textView;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int r4, int r5, Spanned spanned, int r7, int r8) {
        if (this.mTextView.isInEditMode()) {
            return charSequence;
        }
        int loadState = EmojiCompat.get().getLoadState();
        if (loadState != 0) {
            boolean z = true;
            if (loadState == 1) {
                if (r8 == 0 && r7 == 0 && spanned.length() == 0 && charSequence == this.mTextView.getText()) {
                    z = false;
                }
                if (!z || charSequence == null) {
                    return charSequence;
                }
                if (r4 != 0 || r5 != charSequence.length()) {
                    charSequence = charSequence.subSequence(r4, r5);
                }
                return EmojiCompat.get().process(charSequence, 0, charSequence.length());
            } else if (loadState != 3) {
                return charSequence;
            }
        }
        EmojiCompat.get().registerInitCallback(getInitCallback());
        return charSequence;
    }

    private EmojiCompat.InitCallback getInitCallback() {
        if (this.mInitCallback == null) {
            this.mInitCallback = new InitCallbackImpl(this.mTextView, this);
        }
        return this.mInitCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InitCallbackImpl extends EmojiCompat.InitCallback {
        private final Reference<EmojiInputFilter> mEmojiInputFilterReference;
        private final Reference<TextView> mViewRef;

        InitCallbackImpl(TextView textView, EmojiInputFilter emojiInputFilter) {
            this.mViewRef = new WeakReference(textView);
            this.mEmojiInputFilterReference = new WeakReference(emojiInputFilter);
        }

        @Override // androidx.emoji2.text.EmojiCompat.InitCallback
        public void onInitialized() {
            super.onInitialized();
            TextView textView = this.mViewRef.get();
            if (isInputFilterCurrentlyRegisteredOnTextView(textView, this.mEmojiInputFilterReference.get()) && textView.isAttachedToWindow()) {
                CharSequence process = EmojiCompat.get().process(textView.getText());
                int selectionStart = Selection.getSelectionStart(process);
                int selectionEnd = Selection.getSelectionEnd(process);
                textView.setText(process);
                if (process instanceof Spannable) {
                    EmojiInputFilter.updateSelection((Spannable) process, selectionStart, selectionEnd);
                }
            }
        }

        private boolean isInputFilterCurrentlyRegisteredOnTextView(TextView textView, InputFilter inputFilter) {
            InputFilter[] filters;
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                return false;
            }
            for (InputFilter inputFilter2 : filters) {
                if (inputFilter2 == inputFilter) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateSelection(Spannable spannable, int r1, int r2) {
        if (r1 >= 0 && r2 >= 0) {
            Selection.setSelection(spannable, r1, r2);
        } else if (r1 >= 0) {
            Selection.setSelection(spannable, r1);
        } else if (r2 >= 0) {
            Selection.setSelection(spannable, r2);
        }
    }
}
