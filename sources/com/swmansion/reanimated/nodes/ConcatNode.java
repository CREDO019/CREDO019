package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;
import java.text.NumberFormat;
import java.util.Locale;

/* loaded from: classes3.dex */
public class ConcatNode extends Node {
    private static final NumberFormat sFormatter;
    private final int[] mInputIDs;

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.ENGLISH);
        sFormatter = numberFormat;
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setGroupingUsed(false);
    }

    public ConcatNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mInputIDs = C4139Utils.processIntArray(readableMap.getArray("input"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public String evaluate() {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 0; r1 < this.mInputIDs.length; r1++) {
            Object value = this.mNodesManager.findNodeById(this.mInputIDs[r1], Node.class).value();
            if (value instanceof Double) {
                value = sFormatter.format((Double) value);
            }
            sb.append(value);
        }
        return sb.toString();
    }
}
