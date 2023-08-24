package expo.modules.interfaces.barcodescanner;

import java.util.List;

/* loaded from: classes4.dex */
public class BarCodeScannerResult {
    private List<Integer> mCornerPoints;
    private int mReferenceImageHeight;
    private int mReferenceImageWidth;
    private int mType;
    private String mValue;

    /* loaded from: classes4.dex */
    public static class BoundingBox {
        private final int height;
        private final int width;

        /* renamed from: x */
        private final int f1446x;

        /* renamed from: y */
        private final int f1447y;

        public BoundingBox(int r1, int r2, int r3, int r4) {
            this.f1446x = r1;
            this.f1447y = r2;
            this.width = r3;
            this.height = r4;
        }

        public int getX() {
            return this.f1446x;
        }

        public int getY() {
            return this.f1447y;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }
    }

    public BarCodeScannerResult(int r1, String str, List<Integer> list, int r4, int r5) {
        this.mType = r1;
        this.mValue = str;
        this.mCornerPoints = list;
        this.mReferenceImageHeight = r4;
        this.mReferenceImageWidth = r5;
    }

    public int getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    public List<Integer> getCornerPoints() {
        return this.mCornerPoints;
    }

    public void setCornerPoints(List<Integer> list) {
        this.mCornerPoints = list;
    }

    public int getReferenceImageHeight() {
        return this.mReferenceImageHeight;
    }

    public void setReferenceImageHeight(int r1) {
        this.mReferenceImageHeight = r1;
    }

    public int getReferenceImageWidth() {
        return this.mReferenceImageWidth;
    }

    public void setReferenceImageWidth(int r1) {
        this.mReferenceImageWidth = r1;
    }

    public BoundingBox getBoundingBox() {
        if (this.mCornerPoints.isEmpty()) {
            return new BoundingBox(0, 0, 0, 0);
        }
        int r0 = Integer.MIN_VALUE;
        int r1 = Integer.MIN_VALUE;
        int r3 = Integer.MAX_VALUE;
        int r4 = Integer.MAX_VALUE;
        for (int r2 = 0; r2 < this.mCornerPoints.size(); r2 += 2) {
            int intValue = this.mCornerPoints.get(r2).intValue();
            int intValue2 = this.mCornerPoints.get(r2 + 1).intValue();
            r3 = Math.min(r3, intValue);
            r4 = Math.min(r4, intValue2);
            r0 = Math.max(r0, intValue);
            r1 = Math.max(r1, intValue2);
        }
        return new BoundingBox(r3, r4, r0 - r3, r1 - r4);
    }
}
