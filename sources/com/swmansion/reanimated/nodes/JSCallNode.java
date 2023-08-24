package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;

/* loaded from: classes3.dex */
public class JSCallNode extends Node {
    private final int[] mInputIDs;

    public JSCallNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mInputIDs = C4139Utils.processIntArray(readableMap.getArray("input"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public Double evaluate() {
        WritableArray createArray = Arguments.createArray();
        for (int r1 = 0; r1 < this.mInputIDs.length; r1++) {
            Node findNodeById = this.mNodesManager.findNodeById(this.mInputIDs[r1], Node.class);
            if (findNodeById.value() == null) {
                createArray.pushNull();
            } else {
                Object value = findNodeById.value();
                if (value instanceof String) {
                    createArray.pushString((String) value);
                } else {
                    createArray.pushDouble(findNodeById.doubleValue().doubleValue());
                }
            }
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.mNodeID);
        createMap.putArray("args", createArray);
        this.mNodesManager.sendEvent("onReanimatedCall", createMap);
        return ZERO;
    }
}
