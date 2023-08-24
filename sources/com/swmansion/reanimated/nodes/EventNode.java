package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.reanimated.NodesManager;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class EventNode extends Node implements RCTEventEmitter {
    private final List<EventMap> mMapping;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class EventMap {
        private final int nodeID;
        private final String[] path;

        public EventMap(ReadableArray readableArray) {
            int size = readableArray.size() - 1;
            this.path = new String[size];
            for (int r1 = 0; r1 < size; r1++) {
                this.path[r1] = readableArray.getString(r1);
            }
            this.nodeID = readableArray.getInt(size);
        }

        public Double lookupValue(ReadableMap readableMap) {
            int r0 = 0;
            while (readableMap != null) {
                String[] strArr = this.path;
                if (r0 >= strArr.length - 1) {
                    break;
                }
                String str = strArr[r0];
                readableMap = readableMap.hasKey(str) ? readableMap.getMap(str) : null;
                r0++;
            }
            if (readableMap != null) {
                String[] strArr2 = this.path;
                String str2 = strArr2[strArr2.length - 1];
                if (readableMap.hasKey(str2)) {
                    return Double.valueOf(readableMap.getDouble(str2));
                }
                return null;
            }
            return null;
        }
    }

    private static List<EventMap> processMapping(ReadableArray readableArray) {
        int size = readableArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int r2 = 0; r2 < size; r2++) {
            arrayList.add(new EventMap(readableArray.getArray(r2)));
        }
        return arrayList;
    }

    public EventNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mMapping = processMapping(readableMap.getArray("argMapping"));
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int r4, String str, @Nullable WritableMap writableMap) {
        if (writableMap == null) {
            throw new IllegalArgumentException("Animated events must have event data.");
        }
        for (int r42 = 0; r42 < this.mMapping.size(); r42++) {
            EventMap eventMap = this.mMapping.get(r42);
            Double lookupValue = eventMap.lookupValue(writableMap);
            if (lookupValue != null) {
                ((ValueNode) this.mNodesManager.findNodeById(eventMap.nodeID, ValueNode.class)).setValue(lookupValue);
            }
        }
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by animated events");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public Double evaluate() {
        return ZERO;
    }
}
