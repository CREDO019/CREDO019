package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;
import java.util.Map;

/* loaded from: classes3.dex */
public class StyleNode extends Node {
    private final Map<String, Integer> mMapping;

    public StyleNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mMapping = C4139Utils.processMapping(readableMap.getMap(TtmlNode.TAG_STYLE));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public WritableMap evaluate() {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        for (Map.Entry<String, Integer> entry : this.mMapping.entrySet()) {
            Node findNodeById = this.mNodesManager.findNodeById(entry.getValue().intValue(), Node.class);
            if (findNodeById instanceof TransformNode) {
                javaOnlyMap.putArray(entry.getKey(), (WritableArray) findNodeById.value());
            } else {
                Object value = findNodeById.value();
                if (value instanceof Double) {
                    javaOnlyMap.putDouble(entry.getKey(), ((Double) value).doubleValue());
                } else if (value instanceof String) {
                    javaOnlyMap.putString(entry.getKey(), (String) value);
                } else {
                    throw new IllegalStateException("Wrong style form");
                }
            }
        }
        return javaOnlyMap;
    }
}
