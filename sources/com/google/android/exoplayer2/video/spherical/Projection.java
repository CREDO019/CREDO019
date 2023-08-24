package com.google.android.exoplayer2.video.spherical;

import com.google.android.exoplayer2.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
final class Projection {
    public static final int DRAW_MODE_TRIANGLES = 0;
    public static final int DRAW_MODE_TRIANGLES_FAN = 2;
    public static final int DRAW_MODE_TRIANGLES_STRIP = 1;
    public static final int POSITION_COORDS_PER_VERTEX = 3;
    public static final int TEXTURE_COORDS_PER_VERTEX = 2;
    public final Mesh leftMesh;
    public final Mesh rightMesh;
    public final boolean singleMesh;
    public final int stereoMode;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DrawMode {
    }

    public static Projection createEquirectangular(int r6) {
        return createEquirectangular(50.0f, 36, 72, 180.0f, 360.0f, r6);
    }

    public static Projection createEquirectangular(float f, int r32, int r33, float f2, float f3, int r36) {
        int r18;
        float f4;
        int r1;
        int r5;
        int r6;
        float[] fArr;
        int r12 = r32;
        int r2 = r33;
        Assertions.checkArgument(f > 0.0f);
        Assertions.checkArgument(r12 >= 1);
        Assertions.checkArgument(r2 >= 1);
        Assertions.checkArgument(f2 > 0.0f && f2 <= 180.0f);
        Assertions.checkArgument(f3 > 0.0f && f3 <= 360.0f);
        float radians = (float) Math.toRadians(f2);
        float radians2 = (float) Math.toRadians(f3);
        float f5 = radians / r12;
        float f6 = radians2 / r2;
        int r9 = r2 + 1;
        int r10 = ((r9 * 2) + 2) * r12;
        float[] fArr2 = new float[r10 * 3];
        float[] fArr3 = new float[r10 * 2];
        int r13 = 0;
        int r14 = 0;
        int r15 = 0;
        while (r13 < r12) {
            float f7 = radians / 2.0f;
            float f8 = (r13 * f5) - f7;
            int r7 = r13 + 1;
            float f9 = (r7 * f5) - f7;
            int r16 = 0;
            while (r16 < r9) {
                float f10 = f8;
                int r17 = r7;
                int r62 = 0;
                while (r62 < 2) {
                    if (r62 == 0) {
                        f4 = f10;
                        r18 = r9;
                    } else {
                        r18 = r9;
                        f4 = f9;
                    }
                    float f11 = r16 * f6;
                    float f12 = f6;
                    int r19 = r14 + 1;
                    int r20 = r16;
                    double d = f;
                    float f13 = f5;
                    int r23 = r62;
                    double d2 = (f11 + 3.1415927f) - (radians2 / 2.0f);
                    double d3 = f4;
                    float[] fArr4 = fArr3;
                    float f14 = f9;
                    fArr2[r14] = -((float) (Math.sin(d2) * d * Math.cos(d3)));
                    int r102 = r19 + 1;
                    int r11 = r13;
                    fArr2[r19] = (float) (d * Math.sin(d3));
                    int r132 = r102 + 1;
                    fArr2[r102] = (float) (d * Math.cos(d2) * Math.cos(d3));
                    int r110 = r15 + 1;
                    fArr4[r15] = f11 / radians2;
                    int r22 = r110 + 1;
                    fArr4[r110] = ((r11 + r23) * f13) / radians;
                    if (r20 == 0 && r23 == 0) {
                        r1 = r33;
                        r5 = r20;
                        r6 = r23;
                    } else {
                        r1 = r33;
                        r5 = r20;
                        r6 = r23;
                        if (r5 != r1 || r6 != 1) {
                            fArr = fArr4;
                            r15 = r22;
                            r14 = r132;
                            r62 = r6 + 1;
                            r2 = r1;
                            r16 = r5;
                            fArr3 = fArr;
                            r13 = r11;
                            r9 = r18;
                            f6 = f12;
                            f5 = f13;
                            f9 = f14;
                        }
                    }
                    System.arraycopy(fArr2, r132 - 3, fArr2, r132, 3);
                    r132 += 3;
                    fArr = fArr4;
                    System.arraycopy(fArr, r22 - 2, fArr, r22, 2);
                    r22 += 2;
                    r15 = r22;
                    r14 = r132;
                    r62 = r6 + 1;
                    r2 = r1;
                    r16 = r5;
                    fArr3 = fArr;
                    r13 = r11;
                    r9 = r18;
                    f6 = f12;
                    f5 = f13;
                    f9 = f14;
                }
                float f15 = f5;
                int r52 = r16;
                int r111 = r2;
                int r24 = r52 + 1;
                f8 = f10;
                r7 = r17;
                r9 = r9;
                f6 = f6;
                f5 = f15;
                f9 = f9;
                r2 = r111;
                r16 = r24;
            }
            r12 = r32;
            r13 = r7;
        }
        return new Projection(new Mesh(new SubMesh(0, fArr2, fArr3, 1)), r36);
    }

    public Projection(Mesh mesh, int r2) {
        this(mesh, mesh, r2);
    }

    public Projection(Mesh mesh, Mesh mesh2, int r3) {
        this.leftMesh = mesh;
        this.rightMesh = mesh2;
        this.stereoMode = r3;
        this.singleMesh = mesh == mesh2;
    }

    /* loaded from: classes2.dex */
    public static final class SubMesh {
        public static final int VIDEO_TEXTURE_ID = 0;
        public final int mode;
        public final float[] textureCoords;
        public final int textureId;
        public final float[] vertices;

        public SubMesh(int r7, float[] fArr, float[] fArr2, int r10) {
            this.textureId = r7;
            Assertions.checkArgument(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
            this.vertices = fArr;
            this.textureCoords = fArr2;
            this.mode = r10;
        }

        public int getVertexCount() {
            return this.vertices.length / 3;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Mesh {
        private final SubMesh[] subMeshes;

        public Mesh(SubMesh... subMeshArr) {
            this.subMeshes = subMeshArr;
        }

        public int getSubMeshCount() {
            return this.subMeshes.length;
        }

        public SubMesh getSubMesh(int r2) {
            return this.subMeshes[r2];
        }
    }
}
