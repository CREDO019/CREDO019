package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;
import com.swmansion.reanimated.UpdateContext;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes3.dex */
public class CallFuncNode extends Node {
    private final int[] mArgs;
    private final int[] mParams;
    private String mPreviousCallID;
    private final int mWhatNodeID;

    public CallFuncNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.mWhatNodeID = readableMap.getInt("what");
        this.mParams = C4139Utils.processIntArray(readableMap.getArray(OutcomeEventsTable.COLUMN_NAME_PARAMS));
        this.mArgs = C4139Utils.processIntArray(readableMap.getArray("args"));
    }

    private void beginContext() {
        this.mPreviousCallID = this.mNodesManager.updateContext.callID;
        UpdateContext updateContext = this.mNodesManager.updateContext;
        updateContext.callID = this.mNodesManager.updateContext.callID + IOUtils.DIR_SEPARATOR_UNIX + String.valueOf(this.mNodeID);
        int r0 = 0;
        while (true) {
            int[] r1 = this.mParams;
            if (r0 >= r1.length) {
                return;
            }
            ((ParamNode) this.mNodesManager.findNodeById(r1[r0], ParamNode.class)).beginContext(Integer.valueOf(this.mArgs[r0]), this.mPreviousCallID);
            r0++;
        }
    }

    private void endContext() {
        int r0 = 0;
        while (true) {
            int[] r1 = this.mParams;
            if (r0 < r1.length) {
                ((ParamNode) this.mNodesManager.findNodeById(r1[r0], ParamNode.class)).endContext();
                r0++;
            } else {
                this.mNodesManager.updateContext.callID = this.mPreviousCallID;
                return;
            }
        }
    }

    @Override // com.swmansion.reanimated.nodes.Node
    protected Object evaluate() {
        beginContext();
        Object value = this.mNodesManager.findNodeById(this.mWhatNodeID, Node.class).value();
        endContext();
        return value;
    }
}
