package org.bouncycastle.crypto.params;

/* loaded from: classes5.dex */
public class CramerShoupKeyParameters extends AsymmetricKeyParameter {
    private CramerShoupParameters params;

    /* JADX INFO: Access modifiers changed from: protected */
    public CramerShoupKeyParameters(boolean z, CramerShoupParameters cramerShoupParameters) {
        super(z);
        this.params = cramerShoupParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupKeyParameters) {
            CramerShoupParameters cramerShoupParameters = this.params;
            CramerShoupParameters parameters = ((CramerShoupKeyParameters) obj).getParameters();
            return cramerShoupParameters == null ? parameters == null : cramerShoupParameters.equals(parameters);
        }
        return false;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        int r0 = !isPrivate();
        CramerShoupParameters cramerShoupParameters = this.params;
        return cramerShoupParameters != null ? r0 ^ cramerShoupParameters.hashCode() : r0;
    }
}
