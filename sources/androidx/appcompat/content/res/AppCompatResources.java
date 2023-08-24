package androidx.appcompat.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public final class AppCompatResources {
    private AppCompatResources() {
    }

    public static ColorStateList getColorStateList(Context context, int r1) {
        return ContextCompat.getColorStateList(context, r1);
    }

    public static Drawable getDrawable(Context context, int r2) {
        return ResourceManagerInternal.get().getDrawable(context, r2);
    }
}
