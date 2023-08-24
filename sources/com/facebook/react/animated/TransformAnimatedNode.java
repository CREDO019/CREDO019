package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class TransformAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final List<TransformConfig> mTransformConfigs;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TransformConfig {
        public String mProperty;

        private TransformConfig() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AnimatedTransformConfig extends TransformConfig {
        public int mNodeTag;

        private AnimatedTransformConfig() {
            super();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class StaticTransformConfig extends TransformConfig {
        public double mValue;

        private StaticTransformConfig() {
            super();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransformAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableArray array = readableMap.getArray("transforms");
        this.mTransformConfigs = new ArrayList(array.size());
        for (int r0 = 0; r0 < array.size(); r0++) {
            ReadableMap map = array.getMap(r0);
            String string = map.getString("property");
            if (map.getString(SessionDescription.ATTR_TYPE).equals("animated")) {
                AnimatedTransformConfig animatedTransformConfig = new AnimatedTransformConfig();
                animatedTransformConfig.mProperty = string;
                animatedTransformConfig.mNodeTag = map.getInt("nodeTag");
                this.mTransformConfigs.add(animatedTransformConfig);
            } else {
                StaticTransformConfig staticTransformConfig = new StaticTransformConfig();
                staticTransformConfig.mProperty = string;
                staticTransformConfig.mValue = map.getDouble("value");
                this.mTransformConfigs.add(staticTransformConfig);
            }
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        double d;
        ArrayList arrayList = new ArrayList(this.mTransformConfigs.size());
        for (TransformConfig transformConfig : this.mTransformConfigs) {
            if (transformConfig instanceof AnimatedTransformConfig) {
                AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(((AnimatedTransformConfig) transformConfig).mNodeTag);
                if (nodeById == null) {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                }
                if (nodeById instanceof ValueAnimatedNode) {
                    d = ((ValueAnimatedNode) nodeById).getValue();
                } else {
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + nodeById.getClass());
                }
            } else {
                d = ((StaticTransformConfig) transformConfig).mValue;
            }
            arrayList.add(JavaOnlyMap.m1263of(transformConfig.mProperty, Double.valueOf(d)));
        }
        javaOnlyMap.putArray(ViewProps.TRANSFORM, JavaOnlyArray.from(arrayList));
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransformAnimatedNode[");
        sb.append(this.mTag);
        sb.append("]: mTransformConfigs: ");
        List<TransformConfig> list = this.mTransformConfigs;
        sb.append(list != null ? list.toString() : "null");
        return sb.toString();
    }
}
