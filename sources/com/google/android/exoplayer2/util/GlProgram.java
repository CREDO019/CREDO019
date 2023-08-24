package com.google.android.exoplayer2.util;

import android.content.Context;
import android.opengl.GLES20;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class GlProgram {
    private static final int GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT = 35815;
    private final Map<String, Attribute> attributeByName;
    private final Attribute[] attributes;
    private final int programId;
    private final Map<String, Uniform> uniformByName;
    private final Uniform[] uniforms;

    public GlProgram(Context context, String str, String str2) throws IOException {
        this(GlUtil.loadAsset(context, str), GlUtil.loadAsset(context, str2));
    }

    public GlProgram(String str, String str2) {
        int glCreateProgram = GLES20.glCreateProgram();
        this.programId = glCreateProgram;
        GlUtil.checkGlError();
        addShader(glCreateProgram, 35633, str);
        addShader(glCreateProgram, 35632, str2);
        GLES20.glLinkProgram(glCreateProgram);
        int[] r7 = {0};
        GLES20.glGetProgramiv(glCreateProgram, 35714, r7, 0);
        if (r7[0] != 1) {
            GlUtil.throwGlException("Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(glCreateProgram));
        }
        GLES20.glUseProgram(glCreateProgram);
        this.attributeByName = new HashMap();
        int[] r72 = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35721, r72, 0);
        this.attributes = new Attribute[r72[0]];
        for (int r0 = 0; r0 < r72[0]; r0++) {
            Attribute create = Attribute.create(this.programId, r0);
            this.attributes[r0] = create;
            this.attributeByName.put(create.name, create);
        }
        this.uniformByName = new HashMap();
        int[] r6 = new int[1];
        GLES20.glGetProgramiv(this.programId, 35718, r6, 0);
        this.uniforms = new Uniform[r6[0]];
        for (int r73 = 0; r73 < r6[0]; r73++) {
            Uniform create2 = Uniform.create(this.programId, r73);
            this.uniforms[r73] = create2;
            this.uniformByName.put(create2.name, create2);
        }
        GlUtil.checkGlError();
    }

    private static void addShader(int r4, int r5, String str) {
        int glCreateShader = GLES20.glCreateShader(r5);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] r1 = {0};
        GLES20.glGetShaderiv(glCreateShader, 35713, r1, 0);
        if (r1[0] != 1) {
            GlUtil.throwGlException(GLES20.glGetShaderInfoLog(glCreateShader) + ", source: " + str);
        }
        GLES20.glAttachShader(r4, glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        GlUtil.checkGlError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getAttributeLocation(int r0, String str) {
        return GLES20.glGetAttribLocation(r0, str);
    }

    private int getAttributeLocation(String str) {
        return getAttributeLocation(this.programId, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getUniformLocation(int r0, String str) {
        return GLES20.glGetUniformLocation(r0, str);
    }

    public int getUniformLocation(String str) {
        return getUniformLocation(this.programId, str);
    }

    public void use() {
        GLES20.glUseProgram(this.programId);
        GlUtil.checkGlError();
    }

    public void delete() {
        GLES20.glDeleteProgram(this.programId);
        GlUtil.checkGlError();
    }

    public int getAttributeArrayLocationAndEnable(String str) {
        int attributeLocation = getAttributeLocation(str);
        GLES20.glEnableVertexAttribArray(attributeLocation);
        GlUtil.checkGlError();
        return attributeLocation;
    }

    public void setBufferAttribute(String str, float[] fArr, int r4) {
        ((Attribute) Assertions.checkNotNull(this.attributeByName.get(str))).setBuffer(fArr, r4);
    }

    public void setSamplerTexIdUniform(String str, int r3, int r4) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setSamplerTexId(r3, r4);
    }

    public void setFloatUniform(String str, float f) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setFloat(f);
    }

    public void setFloatsUniform(String str, float[] fArr) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setFloats(fArr);
    }

    public void bindAttributesAndUniforms() {
        for (Attribute attribute : this.attributes) {
            attribute.bind();
        }
        for (Uniform uniform : this.uniforms) {
            uniform.bind();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getCStringLength(byte[] bArr) {
        for (int r0 = 0; r0 < bArr.length; r0++) {
            if (bArr[r0] == 0) {
                return r0;
            }
        }
        return bArr.length;
    }

    /* loaded from: classes2.dex */
    private static final class Attribute {
        private Buffer buffer;
        private final int index;
        private final int location;
        public final String name;
        private int size;

        public static Attribute create(int r13, int r14) {
            int[] r1 = new int[1];
            GLES20.glGetProgramiv(r13, 35722, r1, 0);
            byte[] bArr = new byte[r1[0]];
            GLES20.glGetActiveAttrib(r13, r14, r1[0], new int[1], 0, new int[1], 0, new int[1], 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Attribute(str, r14, GlProgram.getAttributeLocation(r13, str));
        }

        private Attribute(String str, int r2, int r3) {
            this.name = str;
            this.index = r2;
            this.location = r3;
        }

        public void setBuffer(float[] fArr, int r2) {
            this.buffer = GlUtil.createBuffer(fArr);
            this.size = r2;
        }

        public void bind() {
            GLES20.glBindBuffer(34962, 0);
            GLES20.glVertexAttribPointer(this.location, this.size, 5126, false, 0, (Buffer) Assertions.checkNotNull(this.buffer, "call setBuffer before bind"));
            GLES20.glEnableVertexAttribArray(this.index);
            GlUtil.checkGlError();
        }
    }

    /* loaded from: classes2.dex */
    private static final class Uniform {
        private final int location;
        public final String name;
        private int texId;
        private int texUnitIndex;
        private final int type;
        private final float[] value = new float[16];

        public static Uniform create(int r15, int r16) {
            int[] r1 = new int[1];
            GLES20.glGetProgramiv(r15, 35719, r1, 0);
            int[] r13 = new int[1];
            byte[] bArr = new byte[r1[0]];
            GLES20.glGetActiveUniform(r15, r16, r1[0], new int[1], 0, new int[1], 0, r13, 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Uniform(str, GlProgram.getUniformLocation(r15, str), r13[0]);
        }

        private Uniform(String str, int r2, int r3) {
            this.name = str;
            this.location = r2;
            this.type = r3;
        }

        public void setSamplerTexId(int r1, int r2) {
            this.texId = r1;
            this.texUnitIndex = r2;
        }

        public void setFloat(float f) {
            this.value[0] = f;
        }

        public void setFloats(float[] fArr) {
            System.arraycopy(fArr, 0, this.value, 0, fArr.length);
        }

        public void bind() {
            switch (this.type) {
                case 5126:
                    GLES20.glUniform1fv(this.location, 1, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35664:
                    GLES20.glUniform2fv(this.location, 1, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35665:
                    GLES20.glUniform3fv(this.location, 1, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35675:
                    GLES20.glUniformMatrix3fv(this.location, 1, false, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35676:
                    GLES20.glUniformMatrix4fv(this.location, 1, false, this.value, 0);
                    GlUtil.checkGlError();
                    return;
                case 35678:
                case GlProgram.GL_SAMPLER_EXTERNAL_2D_Y2Y_EXT /* 35815 */:
                case 36198:
                    if (this.texId == 0) {
                        throw new IllegalStateException("No call to setSamplerTexId() before bind.");
                    }
                    GLES20.glActiveTexture(this.texUnitIndex + 33984);
                    GlUtil.checkGlError();
                    GlUtil.bindTexture(this.type == 35678 ? 3553 : 36197, this.texId);
                    GLES20.glUniform1i(this.location, this.texUnitIndex);
                    GlUtil.checkGlError();
                    return;
                default:
                    throw new IllegalStateException("Unexpected uniform type: " + this.type);
            }
        }
    }
}
