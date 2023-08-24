package org.bouncycastle.asn1.x509;

import java.util.Vector;

/* loaded from: classes5.dex */
public class GeneralNamesBuilder {
    private Vector names = new Vector();

    public GeneralNamesBuilder addName(GeneralName generalName) {
        this.names.addElement(generalName);
        return this;
    }

    public GeneralNamesBuilder addNames(GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int r0 = 0; r0 != names.length; r0++) {
            this.names.addElement(names[r0]);
        }
        return this;
    }

    public GeneralNames build() {
        int size = this.names.size();
        GeneralName[] generalNameArr = new GeneralName[size];
        for (int r2 = 0; r2 != size; r2++) {
            generalNameArr[r2] = (GeneralName) this.names.elementAt(r2);
        }
        return new GeneralNames(generalNameArr);
    }
}
