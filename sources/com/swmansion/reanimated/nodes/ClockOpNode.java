package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.MapUtils;
import com.swmansion.reanimated.NodesManager;

/* loaded from: classes3.dex */
public abstract class ClockOpNode extends Node {
    private int clockID;

    protected abstract Double eval(Node node);

    /* loaded from: classes3.dex */
    public static class ClockStartNode extends ClockOpNode {
        @Override // com.swmansion.reanimated.nodes.ClockOpNode, com.swmansion.reanimated.nodes.Node
        protected /* bridge */ /* synthetic */ Object evaluate() {
            return super.evaluate();
        }

        public ClockStartNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
            super(r1, readableMap, nodesManager);
        }

        @Override // com.swmansion.reanimated.nodes.ClockOpNode
        protected Double eval(Node node) {
            if (node instanceof ParamNode) {
                ((ParamNode) node).start();
            } else {
                ((ClockNode) node).start();
            }
            return ZERO;
        }
    }

    /* loaded from: classes3.dex */
    public static class ClockStopNode extends ClockOpNode {
        @Override // com.swmansion.reanimated.nodes.ClockOpNode, com.swmansion.reanimated.nodes.Node
        protected /* bridge */ /* synthetic */ Object evaluate() {
            return super.evaluate();
        }

        public ClockStopNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
            super(r1, readableMap, nodesManager);
        }

        @Override // com.swmansion.reanimated.nodes.ClockOpNode
        protected Double eval(Node node) {
            if (node instanceof ParamNode) {
                ((ParamNode) node).stop();
            } else {
                ((ClockNode) node).stop();
            }
            return ZERO;
        }
    }

    /* loaded from: classes3.dex */
    public static class ClockTestNode extends ClockOpNode {
        @Override // com.swmansion.reanimated.nodes.ClockOpNode, com.swmansion.reanimated.nodes.Node
        protected /* bridge */ /* synthetic */ Object evaluate() {
            return super.evaluate();
        }

        public ClockTestNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
            super(r1, readableMap, nodesManager);
        }

        @Override // com.swmansion.reanimated.nodes.ClockOpNode
        protected Double eval(Node node) {
            if (node instanceof ParamNode) {
                return Double.valueOf(((ParamNode) node).isRunning() ? 1.0d : 0.0d);
            }
            return Double.valueOf(((ClockNode) node).isRunning ? 1.0d : 0.0d);
        }
    }

    public ClockOpNode(int r1, ReadableMap readableMap, NodesManager nodesManager) {
        super(r1, readableMap, nodesManager);
        this.clockID = MapUtils.getInt(readableMap, "clock", "Reanimated: Argument passed to clock node is either of wrong type or is missing.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.swmansion.reanimated.nodes.Node
    public Double evaluate() {
        return eval(this.mNodesManager.findNodeById(this.clockID, Node.class));
    }
}
