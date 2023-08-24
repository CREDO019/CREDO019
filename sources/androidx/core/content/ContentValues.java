package androidx.core.content;

import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;

@Metadata(m184d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m183d2 = {"contentValuesOf", "Landroid/content/ContentValues;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "core-ktx_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.core.content.ContentValuesKt */
/* loaded from: classes.dex */
public final class ContentValues {
    public static final android.content.ContentValues contentValuesOf(Tuples<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        android.content.ContentValues contentValues = new android.content.ContentValues(pairs.length);
        int length = pairs.length;
        int r2 = 0;
        while (r2 < length) {
            Tuples<String, ? extends Object> tuples = pairs[r2];
            r2++;
            String component1 = tuples.component1();
            Object component2 = tuples.component2();
            if (component2 == null) {
                contentValues.putNull(component1);
            } else if (component2 instanceof String) {
                contentValues.put(component1, (String) component2);
            } else if (component2 instanceof Integer) {
                contentValues.put(component1, (Integer) component2);
            } else if (component2 instanceof Long) {
                contentValues.put(component1, (Long) component2);
            } else if (component2 instanceof Boolean) {
                contentValues.put(component1, (Boolean) component2);
            } else if (component2 instanceof Float) {
                contentValues.put(component1, (Float) component2);
            } else if (component2 instanceof Double) {
                contentValues.put(component1, (Double) component2);
            } else if (component2 instanceof byte[]) {
                contentValues.put(component1, (byte[]) component2);
            } else if (component2 instanceof Byte) {
                contentValues.put(component1, (Byte) component2);
            } else if (!(component2 instanceof Short)) {
                String canonicalName = component2.getClass().getCanonicalName();
                throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName) + " for key \"" + component1 + Typography.quote);
            } else {
                contentValues.put(component1, (Short) component2);
            }
        }
        return contentValues;
    }
}
