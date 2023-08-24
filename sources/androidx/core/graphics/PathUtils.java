package androidx.core.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public final class PathUtils {
    public static Collection<PathSegment> flatten(Path path) {
        return flatten(path, 0.5f);
    }

    public static Collection<PathSegment> flatten(Path path, float f) {
        float[] approximate = path.approximate(f);
        int length = approximate.length / 3;
        ArrayList arrayList = new ArrayList(length);
        for (int r1 = 1; r1 < length; r1++) {
            int r2 = r1 * 3;
            int r3 = (r1 - 1) * 3;
            float f2 = approximate[r2];
            float f3 = approximate[r2 + 1];
            float f4 = approximate[r2 + 2];
            float f5 = approximate[r3];
            float f6 = approximate[r3 + 1];
            float f7 = approximate[r3 + 2];
            if (f2 != f5 && (f3 != f6 || f4 != f7)) {
                arrayList.add(new PathSegment(new PointF(f6, f7), f5, new PointF(f3, f4), f2));
            }
        }
        return arrayList;
    }

    private PathUtils() {
    }
}
