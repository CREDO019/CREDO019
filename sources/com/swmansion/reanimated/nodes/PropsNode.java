package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.UIImplementation;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;
import java.util.Map;

/* loaded from: classes3.dex */
public class PropsNode extends Node implements FinalNode {
    private int mConnectedViewTag;
    private final ReactStylesDiffMap mDiffMap;
    private final Map<String, Integer> mMapping;
    private final JavaOnlyMap mPropMap;
    private final UIImplementation mUIImplementation;

    private static void addProp(WritableMap writableMap, String str, Object obj) {
        if (obj == null) {
            writableMap.putNull(str);
        } else if (obj instanceof Double) {
            writableMap.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Integer) {
            writableMap.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Number) {
            writableMap.putDouble(str, ((Number) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof String) {
            writableMap.putString(str, (String) obj);
        } else if (obj instanceof WritableArray) {
            writableMap.putArray(str, (WritableArray) obj);
        } else if (obj instanceof WritableMap) {
            writableMap.putMap(str, (WritableMap) obj);
        } else {
            throw new IllegalStateException("Unknown type of animated value");
        }
    }

    public PropsNode(int r1, ReadableMap readableMap, NodesManager nodesManager, UIImplementation uIImplementation) {
        super(r1, readableMap, nodesManager);
        this.mConnectedViewTag = -1;
        this.mMapping = C4139Utils.processMapping(readableMap.getMap("props"));
        this.mUIImplementation = uIImplementation;
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        this.mPropMap = javaOnlyMap;
        this.mDiffMap = new ReactStylesDiffMap(javaOnlyMap);
    }

    public void connectToView(int r1) {
        this.mConnectedViewTag = r1;
        dangerouslyRescheduleEvaluate();
    }

    public void disconnectFromView(int r1) {
        this.mConnectedViewTag = -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public Double evaluate() {
        boolean z;
        boolean z2;
        boolean z3;
        WritableMap writableMap;
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        for (Map.Entry<String, Integer> entry : this.mMapping.entrySet()) {
            Node findNodeById = this.mNodesManager.findNodeById(entry.getValue().intValue(), Node.class);
            if (findNodeById instanceof StyleNode) {
                WritableMap writableMap2 = (WritableMap) findNodeById.value();
                ReadableMapKeySetIterator keySetIterator = writableMap2.keySetIterator();
                while (keySetIterator.hasNextKey()) {
                    String nextKey = keySetIterator.nextKey();
                    if (this.mNodesManager.uiProps.contains(nextKey)) {
                        writableMap = this.mPropMap;
                        z2 = z6;
                        z = z5;
                        z3 = true;
                    } else if (this.mNodesManager.nativeProps.contains(nextKey)) {
                        z3 = z4;
                        z2 = z6;
                        z = true;
                        writableMap = createMap2;
                    } else {
                        z = z5;
                        z2 = true;
                        z3 = z4;
                        writableMap = createMap;
                    }
                    ReadableType type = writableMap2.getType(nextKey);
                    int r13 = C41721.$SwitchMap$com$facebook$react$bridge$ReadableType[type.ordinal()];
                    if (r13 == 1) {
                        writableMap.putDouble(nextKey, writableMap2.getDouble(nextKey));
                    } else if (r13 == 2) {
                        writableMap.putString(nextKey, writableMap2.getString(nextKey));
                    } else if (r13 == 3) {
                        writableMap.putArray(nextKey, (WritableArray) writableMap2.getArray(nextKey));
                    } else {
                        throw new IllegalArgumentException("Unexpected type " + type);
                    }
                    z4 = z3;
                    z5 = z;
                    z6 = z2;
                }
                continue;
            } else {
                String key = entry.getKey();
                Object value = findNodeById.value();
                if (this.mNodesManager.uiProps.contains(key)) {
                    addProp(this.mPropMap, key, value);
                    z4 = true;
                } else {
                    addProp(createMap2, key, value);
                    z5 = true;
                }
            }
        }
        int r2 = this.mConnectedViewTag;
        if (r2 != -1) {
            if (z4) {
                this.mUIImplementation.synchronouslyUpdateViewOnUIThread(r2, this.mDiffMap);
            }
            if (z5) {
                this.mNodesManager.enqueueUpdateViewOnNativeThread(this.mConnectedViewTag, createMap2, false);
            }
            if (z6) {
                WritableMap createMap3 = Arguments.createMap();
                createMap3.putInt("viewTag", this.mConnectedViewTag);
                createMap3.putMap("props", createMap);
                this.mNodesManager.sendEvent("onReanimatedPropsChange", createMap3);
            }
        }
        return ZERO;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.swmansion.reanimated.nodes.PropsNode$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C41721 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] r0 = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = r0;
            try {
                r0[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.swmansion.reanimated.nodes.FinalNode
    public void update() {
        if (this.mConnectedViewTag == -1) {
            return;
        }
        value();
    }
}
