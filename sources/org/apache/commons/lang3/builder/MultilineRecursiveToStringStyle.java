package org.apache.commons.lang3.builder;

import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes5.dex */
public class MultilineRecursiveToStringStyle extends RecursiveToStringStyle {
    private static final int INDENT = 2;
    private static final long serialVersionUID = 1;
    private int spaces = 2;

    public MultilineRecursiveToStringStyle() {
        resetIndent();
    }

    private void resetIndent() {
        setArrayStart("{" + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setArraySeparator("," + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setArrayEnd(System.lineSeparator() + ((Object) spacer(this.spaces + (-2))) + "}");
        setContentStart("[" + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setFieldSeparator("," + System.lineSeparator() + ((Object) spacer(this.spaces)));
        setContentEnd(System.lineSeparator() + ((Object) spacer(this.spaces + (-2))) + "]");
    }

    private StringBuilder spacer(int r4) {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 0; r1 < r4; r1++) {
            sb.append(" ");
        }
        return sb;
    }

    @Override // org.apache.commons.lang3.builder.RecursiveToStringStyle, org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object obj) {
        if (!ClassUtils.isPrimitiveWrapper(obj.getClass()) && !String.class.equals(obj.getClass()) && accept(obj.getClass())) {
            this.spaces += 2;
            resetIndent();
            stringBuffer.append(ReflectionToStringBuilder.toString(obj, this));
            this.spaces -= 2;
            resetIndent();
            return;
        }
        super.appendDetail(stringBuffer, str, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, Object[] objArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, objArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void reflectionAppendArrayDetail(StringBuffer stringBuffer, String str, Object obj) {
        this.spaces += 2;
        resetIndent();
        super.reflectionAppendArrayDetail(stringBuffer, str, obj);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, long[] jArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, jArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, int[] r4) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, r4);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, short[] sArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, sArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, byte[] bArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, bArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, char[] cArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, cArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, double[] dArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, dArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, float[] fArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, fArr);
        this.spaces -= 2;
        resetIndent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.builder.ToStringStyle
    public void appendDetail(StringBuffer stringBuffer, String str, boolean[] zArr) {
        this.spaces += 2;
        resetIndent();
        super.appendDetail(stringBuffer, str, zArr);
        this.spaces -= 2;
        resetIndent();
    }
}
