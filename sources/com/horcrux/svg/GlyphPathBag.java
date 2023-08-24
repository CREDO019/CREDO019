package com.horcrux.svg;

import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;

/* loaded from: classes3.dex */
class GlyphPathBag {
    private final int[][] data;
    private final Paint paint;
    private final ArrayList<Path> paths;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlyphPathBag(Paint paint) {
        ArrayList<Path> arrayList = new ArrayList<>();
        this.paths = arrayList;
        this.data = new int[256];
        this.paint = paint;
        arrayList.add(new Path());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path getOrCreateAndCache(char c, String str) {
        Path path;
        int index = getIndex(c);
        if (index != 0) {
            path = this.paths.get(index);
        } else {
            Path path2 = new Path();
            this.paint.getTextPath(str, 0, 1, 0.0f, 0.0f, path2);
            int[][] r10 = this.data;
            int r0 = c >> '\b';
            int[] r1 = r10[r0];
            if (r1 == null) {
                r1 = new int[256];
                r10[r0] = r1;
            }
            r1[c & 255] = this.paths.size();
            this.paths.add(path2);
            path = path2;
        }
        Path path3 = new Path();
        path3.addPath(path);
        return path3;
    }

    private int getIndex(char c) {
        int[] r0 = this.data[c >> '\b'];
        if (r0 == null) {
            return 0;
        }
        return r0[c & 255];
    }
}
