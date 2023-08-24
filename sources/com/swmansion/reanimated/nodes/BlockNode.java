package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;

/* loaded from: classes3.dex */
public class BlockNode extends Node {
    private final int[] mBlock;

    public BlockNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mBlock = C4139Utils.processIntArray(readableMap.getArray("block"));
    }

    @Override // com.swmansion.reanimated.nodes.Node
    protected Object evaluate() {
        Object obj = null;
        for (int r1 = 0; r1 < this.mBlock.length; r1++) {
            obj = this.mNodesManager.findNodeById(this.mBlock[r1], Node.class).value();
        }
        return obj;
    }
}
