package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SubtractionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public SubtractionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        int r4 = 0;
        while (true) {
            int[] r0 = this.mInputNodes;
            if (r4 >= r0.length) {
                return;
            }
            r0[r4] = array.getInt(r4);
            r4++;
        }
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        int r0 = 0;
        while (true) {
            int[] r1 = this.mInputNodes;
            if (r0 >= r1.length) {
                return;
            }
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(r1[r0]);
            if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
                break;
            }
            double value = ((ValueAnimatedNode) nodeById).getValue();
            if (r0 == 0) {
                this.mValue = value;
            } else {
                this.mValue -= value;
            }
            r0++;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.subtract node");
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("SubtractionAnimatedNode[");
        sb.append(this.mTag);
        sb.append("]: input nodes: ");
        int[] r1 = this.mInputNodes;
        sb.append(r1 != null ? r1.toString() : "null");
        sb.append(" - super: ");
        sb.append(super.prettyPrint());
        return sb.toString();
    }
}
