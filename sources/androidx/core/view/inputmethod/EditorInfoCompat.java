package androidx.core.view.inputmethod;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public final class EditorInfoCompat {
    private static final String CONTENT_MIME_TYPES_INTEROP_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_SELECTION_END_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END";
    private static final String CONTENT_SELECTION_HEAD_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD";
    private static final String CONTENT_SURROUNDING_TEXT_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;
    static final int MAX_INITIAL_SELECTION_LENGTH = 1024;
    static final int MEMORY_EFFICIENT_TEXT_LENGTH = 2048;

    private static boolean isPasswordInputType(int r1) {
        int r12 = r1 & 4095;
        return r12 == 129 || r12 == 225 || r12 == 18;
    }

    public static void setContentMimeTypes(EditorInfo editorInfo, String[] strArr) {
        if (Build.VERSION.SDK_INT >= 25) {
            editorInfo.contentMimeTypes = strArr;
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putStringArray(CONTENT_MIME_TYPES_KEY, strArr);
        editorInfo.extras.putStringArray(CONTENT_MIME_TYPES_INTEROP_KEY, strArr);
    }

    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        if (Build.VERSION.SDK_INT >= 25) {
            String[] strArr = editorInfo.contentMimeTypes;
            return strArr != null ? strArr : EMPTY_STRING_ARRAY;
        } else if (editorInfo.extras == null) {
            return EMPTY_STRING_ARRAY;
        } else {
            String[] stringArray = editorInfo.extras.getStringArray(CONTENT_MIME_TYPES_KEY);
            if (stringArray == null) {
                stringArray = editorInfo.extras.getStringArray(CONTENT_MIME_TYPES_INTEROP_KEY);
            }
            return stringArray != null ? stringArray : EMPTY_STRING_ARRAY;
        }
    }

    public static void setInitialSurroundingText(EditorInfo editorInfo, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setInitialSurroundingSubText(editorInfo, charSequence, 0);
        } else {
            setInitialSurroundingSubText(editorInfo, charSequence, 0);
        }
    }

    public static void setInitialSurroundingSubText(EditorInfo editorInfo, CharSequence charSequence, int r7) {
        int r0;
        int r1;
        Preconditions.checkNotNull(charSequence);
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setInitialSurroundingSubText(editorInfo, charSequence, r7);
            return;
        }
        if (editorInfo.initialSelStart > editorInfo.initialSelEnd) {
            r0 = editorInfo.initialSelEnd;
        } else {
            r0 = editorInfo.initialSelStart;
        }
        int r02 = r0 - r7;
        if (editorInfo.initialSelStart > editorInfo.initialSelEnd) {
            r1 = editorInfo.initialSelStart;
        } else {
            r1 = editorInfo.initialSelEnd;
        }
        int r12 = r1 - r7;
        int length = charSequence.length();
        if (r7 < 0 || r02 < 0 || r12 > length) {
            setSurroundingText(editorInfo, null, 0, 0);
        } else if (isPasswordInputType(editorInfo.inputType)) {
            setSurroundingText(editorInfo, null, 0, 0);
        } else if (length <= 2048) {
            setSurroundingText(editorInfo, charSequence, r02, r12);
        } else {
            trimLongSurroundingText(editorInfo, charSequence, r02, r12);
        }
    }

    private static void trimLongSurroundingText(EditorInfo editorInfo, CharSequence charSequence, int r11, int r12) {
        CharSequence subSequence;
        int r0 = r12 - r11;
        int r2 = r0 > 1024 ? 0 : r0;
        int r4 = 2048 - r2;
        int min = Math.min(charSequence.length() - r12, r4 - Math.min(r11, (int) (r4 * 0.8d)));
        int min2 = Math.min(r11, r4 - min);
        int r112 = r11 - min2;
        if (isCutOnSurrogate(charSequence, r112, 0)) {
            r112++;
            min2--;
        }
        if (isCutOnSurrogate(charSequence, (r12 + min) - 1, 1)) {
            min--;
        }
        int r5 = min2 + r2 + min;
        if (r2 != r0) {
            subSequence = TextUtils.concat(charSequence.subSequence(r112, r112 + min2), charSequence.subSequence(r12, min + r12));
        } else {
            subSequence = charSequence.subSequence(r112, r5 + r112);
        }
        int r42 = min2 + 0;
        setSurroundingText(editorInfo, subSequence, r42, r2 + r42);
    }

    public static CharSequence getInitialTextBeforeCursor(EditorInfo editorInfo, int r4, int r5) {
        CharSequence charSequence;
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getInitialTextBeforeCursor(editorInfo, r4, r5);
        }
        if (editorInfo.extras == null || (charSequence = editorInfo.extras.getCharSequence(CONTENT_SURROUNDING_TEXT_KEY)) == null) {
            return null;
        }
        int r3 = editorInfo.extras.getInt(CONTENT_SELECTION_HEAD_KEY);
        int min = Math.min(r4, r3);
        if ((r5 & 1) != 0) {
            return charSequence.subSequence(r3 - min, r3);
        }
        return TextUtils.substring(charSequence, r3 - min, r3);
    }

    public static CharSequence getInitialSelectedText(EditorInfo editorInfo, int r7) {
        CharSequence charSequence;
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getInitialSelectedText(editorInfo, r7);
        }
        if (editorInfo.extras == null) {
            return null;
        }
        int min = Math.min(editorInfo.initialSelStart, editorInfo.initialSelEnd);
        int max = Math.max(editorInfo.initialSelStart, editorInfo.initialSelEnd);
        int r3 = editorInfo.extras.getInt(CONTENT_SELECTION_HEAD_KEY);
        int r4 = editorInfo.extras.getInt(CONTENT_SELECTION_END_KEY);
        int r2 = max - min;
        if (editorInfo.initialSelStart < 0 || editorInfo.initialSelEnd < 0 || r4 - r3 != r2 || (charSequence = editorInfo.extras.getCharSequence(CONTENT_SURROUNDING_TEXT_KEY)) == null) {
            return null;
        }
        if ((r7 & 1) != 0) {
            return charSequence.subSequence(r3, r4);
        }
        return TextUtils.substring(charSequence, r3, r4);
    }

    public static CharSequence getInitialTextAfterCursor(EditorInfo editorInfo, int r4, int r5) {
        CharSequence charSequence;
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getInitialTextAfterCursor(editorInfo, r4, r5);
        }
        if (editorInfo.extras == null || (charSequence = editorInfo.extras.getCharSequence(CONTENT_SURROUNDING_TEXT_KEY)) == null) {
            return null;
        }
        int r3 = editorInfo.extras.getInt(CONTENT_SELECTION_END_KEY);
        int min = Math.min(r4, charSequence.length() - r3);
        if ((r5 & 1) != 0) {
            return charSequence.subSequence(r3, min + r3);
        }
        return TextUtils.substring(charSequence, r3, min + r3);
    }

    private static boolean isCutOnSurrogate(CharSequence charSequence, int r2, int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                return false;
            }
            return Character.isHighSurrogate(charSequence.charAt(r2));
        }
        return Character.isLowSurrogate(charSequence.charAt(r2));
    }

    private static void setSurroundingText(EditorInfo editorInfo, CharSequence charSequence, int r4, int r5) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence(CONTENT_SURROUNDING_TEXT_KEY, charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt(CONTENT_SELECTION_HEAD_KEY, r4);
        editorInfo.extras.putInt(CONTENT_SELECTION_END_KEY, r5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getProtocol(EditorInfo editorInfo) {
        if (Build.VERSION.SDK_INT >= 25) {
            return 1;
        }
        if (editorInfo.extras == null) {
            return 0;
        }
        boolean containsKey = editorInfo.extras.containsKey(CONTENT_MIME_TYPES_KEY);
        boolean containsKey2 = editorInfo.extras.containsKey(CONTENT_MIME_TYPES_INTEROP_KEY);
        if (containsKey && containsKey2) {
            return 4;
        }
        if (containsKey) {
            return 3;
        }
        return containsKey2 ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api30Impl {
        private Api30Impl() {
        }

        static void setInitialSurroundingSubText(EditorInfo editorInfo, CharSequence charSequence, int r2) {
            editorInfo.setInitialSurroundingSubText(charSequence, r2);
        }

        static CharSequence getInitialTextBeforeCursor(EditorInfo editorInfo, int r1, int r2) {
            return editorInfo.getInitialTextBeforeCursor(r1, r2);
        }

        static CharSequence getInitialSelectedText(EditorInfo editorInfo, int r1) {
            return editorInfo.getInitialSelectedText(r1);
        }

        static CharSequence getInitialTextAfterCursor(EditorInfo editorInfo, int r1, int r2) {
            return editorInfo.getInitialTextAfterCursor(r1, r2);
        }
    }
}
