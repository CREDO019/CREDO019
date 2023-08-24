package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.reanimated.NodesManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class TransformNode extends Node {
    private List<TransformConfig> mTransforms;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class TransformConfig {
        public String propertyName;

        public abstract Object getValue(NodesManager nodesManager);

        private TransformConfig() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class AnimatedTransformConfig extends TransformConfig {
        public int nodeID;

        private AnimatedTransformConfig() {
            super();
        }

        @Override // com.swmansion.reanimated.nodes.TransformNode.TransformConfig
        public Object getValue(NodesManager nodesManager) {
            return nodesManager.getNodeValue(this.nodeID);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class StaticTransformConfig extends TransformConfig {
        public Object value;

        private StaticTransformConfig() {
            super();
        }

        @Override // com.swmansion.reanimated.nodes.TransformNode.TransformConfig
        public Object getValue(NodesManager nodesManager) {
            return this.value;
        }
    }

    private static List<TransformConfig> processTransforms(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            ReadableMap map = readableArray.getMap(r1);
            String string = map.getString("property");
            if (map.hasKey("nodeID")) {
                AnimatedTransformConfig animatedTransformConfig = new AnimatedTransformConfig();
                animatedTransformConfig.propertyName = string;
                animatedTransformConfig.nodeID = map.getInt("nodeID");
                arrayList.add(animatedTransformConfig);
            } else {
                StaticTransformConfig staticTransformConfig = new StaticTransformConfig();
                staticTransformConfig.propertyName = string;
                ReadableType type = map.getType("value");
                if (type == ReadableType.String) {
                    staticTransformConfig.value = map.getString("value");
                } else if (type == ReadableType.Array) {
                    staticTransformConfig.value = map.getArray("value");
                } else {
                    staticTransformConfig.value = Double.valueOf(map.getDouble("value"));
                }
                arrayList.add(staticTransformConfig);
            }
        }
        return arrayList;
    }

    public TransformNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mTransforms = processTransforms(readableMap.getArray(ViewProps.TRANSFORM));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public WritableArray evaluate() {
        ArrayList arrayList = new ArrayList(this.mTransforms.size());
        for (TransformConfig transformConfig : this.mTransforms) {
            arrayList.add(JavaOnlyMap.m1263of(transformConfig.propertyName, transformConfig.getValue(this.mNodesManager)));
        }
        return JavaOnlyArray.from(arrayList);
    }
}
