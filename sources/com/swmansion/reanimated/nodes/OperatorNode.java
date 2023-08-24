package com.swmansion.reanimated.nodes;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.swmansion.reanimated.C4139Utils;
import com.swmansion.reanimated.NodesManager;
import io.jsonwebtoken.Claims;

/* loaded from: classes3.dex */
public class OperatorNode extends Node {
    private final int[] mInputIDs;
    private final Node[] mInputNodes;
    private final Operator mOperator;
    private static final Operator ADD = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.1
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return d.doubleValue() + d2.doubleValue();
        }
    };
    private static final Operator SUB = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.2
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return d.doubleValue() - d2.doubleValue();
        }
    };
    private static final Operator MULTIPLY = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.3
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return d.doubleValue() * d2.doubleValue();
        }
    };
    private static final Operator DIVIDE = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.4
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return d.doubleValue() / d2.doubleValue();
        }
    };
    private static final Operator POW = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.5
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return Math.pow(d.doubleValue(), d2.doubleValue());
        }
    };
    private static final Operator MODULO = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.6
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return ((d.doubleValue() % d2.doubleValue()) + d2.doubleValue()) % d2.doubleValue();
        }
    };
    private static final Operator SQRT = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.7
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.sqrt(d.doubleValue());
        }
    };
    private static final Operator LOG = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.8
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.log(d.doubleValue());
        }
    };
    private static final Operator SIN = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.9
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.sin(d.doubleValue());
        }
    };
    private static final Operator COS = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.10
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.cos(d.doubleValue());
        }
    };
    private static final Operator TAN = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.11
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.tan(d.doubleValue());
        }
    };
    private static final Operator ACOS = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.12
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.acos(d.doubleValue());
        }
    };
    private static final Operator ASIN = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.13
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.asin(d.doubleValue());
        }
    };
    private static final Operator ATAN = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.14
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.atan(d.doubleValue());
        }
    };
    private static final Operator EXP = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.15
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.exp(d.doubleValue());
        }
    };
    private static final Operator ROUND = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.16
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.round(d.doubleValue());
        }
    };
    private static final Operator ABS = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.17
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.abs(d.doubleValue());
        }
    };
    private static final Operator FLOOR = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.18
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.floor(d.doubleValue());
        }
    };
    private static final Operator CEIL = new SingleOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.19
        @Override // com.swmansion.reanimated.nodes.OperatorNode.SingleOperator
        public double eval(Double d) {
            return Math.ceil(d.doubleValue());
        }
    };
    private static final Operator MIN = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.20
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return Math.min(d.doubleValue(), d2.doubleValue());
        }
    };
    private static final Operator MAX = new ReduceOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.21
        @Override // com.swmansion.reanimated.nodes.OperatorNode.ReduceOperator
        public double reduce(Double d, Double d2) {
            return Math.max(d.doubleValue(), d2.doubleValue());
        }
    };
    private static final Operator AND = new Operator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.22
        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            boolean truthy = OperatorNode.truthy(nodeArr[0].value());
            for (int r3 = 1; r3 < nodeArr.length && truthy; r3++) {
                truthy = truthy && OperatorNode.truthy(nodeArr[r3].value());
            }
            return truthy ? 1.0d : 0.0d;
        }
    };

    /* renamed from: OR */
    private static final Operator f1359OR = new Operator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.23
        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            boolean truthy = OperatorNode.truthy(nodeArr[0].value());
            for (int r3 = 1; r3 < nodeArr.length && !truthy; r3++) {
                truthy = truthy || OperatorNode.truthy(nodeArr[r3].value());
            }
            return truthy ? 1.0d : 0.0d;
        }
    };
    private static final Operator NOT = new Operator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.24
        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            return OperatorNode.truthy(nodeArr[0].value()) ? 0.0d : 1.0d;
        }
    };
    private static final Operator DEFINED = new Operator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.25
        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            Object value = nodeArr[0].value();
            return (value == null || ((value instanceof Double) && ((Double) value).isNaN())) ? 0.0d : 1.0d;
        }
    };
    private static final Operator LESS_THAN = new CompOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.26
        @Override // com.swmansion.reanimated.nodes.OperatorNode.CompOperator
        public boolean eval(Double d, Double d2) {
            return (d == null || d2 == null || d.doubleValue() >= d2.doubleValue()) ? false : true;
        }
    };

    /* renamed from: EQ */
    private static final Operator f1358EQ = new CompOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.27
        @Override // com.swmansion.reanimated.nodes.OperatorNode.CompOperator
        public boolean eval(Double d, Double d2) {
            return (d == null || d2 == null) ? d == d2 : d.doubleValue() == d2.doubleValue();
        }
    };
    private static final Operator GREATER_THAN = new CompOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.28
        @Override // com.swmansion.reanimated.nodes.OperatorNode.CompOperator
        public boolean eval(Double d, Double d2) {
            return (d == null || d2 == null || d.doubleValue() <= d2.doubleValue()) ? false : true;
        }
    };
    private static final Operator LESS_OR_EQ = new CompOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.29
        @Override // com.swmansion.reanimated.nodes.OperatorNode.CompOperator
        public boolean eval(Double d, Double d2) {
            return d.doubleValue() <= d2.doubleValue();
        }
    };
    private static final Operator GREATER_OR_EQ = new CompOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.30
        @Override // com.swmansion.reanimated.nodes.OperatorNode.CompOperator
        public boolean eval(Double d, Double d2) {
            return d.doubleValue() >= d2.doubleValue();
        }
    };
    private static final Operator NEQ = new CompOperator() { // from class: com.swmansion.reanimated.nodes.OperatorNode.31
        @Override // com.swmansion.reanimated.nodes.OperatorNode.CompOperator
        public boolean eval(Double d, Double d2) {
            return (d == null || d2 == null) ? d == d2 : d.doubleValue() != d2.doubleValue();
        }
    };

    /* loaded from: classes3.dex */
    private interface Operator {
        double evaluate(Node[] nodeArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean truthy(Object obj) {
        return (obj == null || obj.equals(Double.valueOf(0.0d))) ? false : true;
    }

    /* loaded from: classes3.dex */
    private static abstract class ReduceOperator implements Operator {
        public abstract double reduce(Double d, Double d2);

        private ReduceOperator() {
        }

        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            double doubleValue = nodeArr[0].doubleValue().doubleValue();
            for (int r2 = 1; r2 < nodeArr.length; r2++) {
                doubleValue = reduce(Double.valueOf(doubleValue), nodeArr[r2].doubleValue());
            }
            return doubleValue;
        }
    }

    /* loaded from: classes3.dex */
    private static abstract class SingleOperator implements Operator {
        public abstract double eval(Double d);

        private SingleOperator() {
        }

        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            return eval((Double) nodeArr[0].value());
        }
    }

    /* loaded from: classes3.dex */
    private static abstract class CompOperator implements Operator {
        public abstract boolean eval(Double d, Double d2);

        private CompOperator() {
        }

        @Override // com.swmansion.reanimated.nodes.OperatorNode.Operator
        public double evaluate(Node[] nodeArr) {
            return eval((Double) nodeArr[0].value(), (Double) nodeArr[1].value()) ? 1.0d : 0.0d;
        }
    }

    public OperatorNode(int r2, ReadableMap readableMap, NodesManager nodesManager) {
        super(r2, readableMap, nodesManager);
        int[] processIntArray = C4139Utils.processIntArray(readableMap.getArray("input"));
        this.mInputIDs = processIntArray;
        this.mInputNodes = new Node[processIntArray.length];
        String string = readableMap.getString("op");
        if ("add".equals(string)) {
            this.mOperator = ADD;
        } else if (Claims.SUBJECT.equals(string)) {
            this.mOperator = SUB;
        } else if ("multiply".equals(string)) {
            this.mOperator = MULTIPLY;
        } else if ("divide".equals(string)) {
            this.mOperator = DIVIDE;
        } else if ("pow".equals(string)) {
            this.mOperator = POW;
        } else if ("modulo".equals(string)) {
            this.mOperator = MODULO;
        } else if ("sqrt".equals(string)) {
            this.mOperator = SQRT;
        } else if ("log".equals(string)) {
            this.mOperator = LOG;
        } else if ("sin".equals(string)) {
            this.mOperator = SIN;
        } else if ("cos".equals(string)) {
            this.mOperator = COS;
        } else if ("tan".equals(string)) {
            this.mOperator = TAN;
        } else if ("acos".equals(string)) {
            this.mOperator = ACOS;
        } else if ("asin".equals(string)) {
            this.mOperator = ASIN;
        } else if ("atan".equals(string)) {
            this.mOperator = ATAN;
        } else if (Claims.EXPIRATION.equals(string)) {
            this.mOperator = EXP;
        } else if ("round".equals(string)) {
            this.mOperator = ROUND;
        } else if ("and".equals(string)) {
            this.mOperator = AND;
        } else if ("or".equals(string)) {
            this.mOperator = f1359OR;
        } else if ("not".equals(string)) {
            this.mOperator = NOT;
        } else if ("defined".equals(string)) {
            this.mOperator = DEFINED;
        } else if ("lessThan".equals(string)) {
            this.mOperator = LESS_THAN;
        } else if ("eq".equals(string)) {
            this.mOperator = f1358EQ;
        } else if ("greaterThan".equals(string)) {
            this.mOperator = GREATER_THAN;
        } else if ("lessOrEq".equals(string)) {
            this.mOperator = LESS_OR_EQ;
        } else if ("greaterOrEq".equals(string)) {
            this.mOperator = GREATER_OR_EQ;
        } else if ("neq".equals(string)) {
            this.mOperator = NEQ;
        } else if ("abs".equals(string)) {
            this.mOperator = ABS;
        } else if ("floor".equals(string)) {
            this.mOperator = FLOOR;
        } else if ("ceil".equals(string)) {
            this.mOperator = CEIL;
        } else if ("max".equals(string)) {
            this.mOperator = MAX;
        } else if ("min".equals(string)) {
            this.mOperator = MIN;
        } else {
            throw new JSApplicationIllegalArgumentException("Unrecognized operator " + string);
        }
    }

    @Override // com.swmansion.reanimated.nodes.Node
    protected Object evaluate() {
        for (int r0 = 0; r0 < this.mInputIDs.length; r0++) {
            this.mInputNodes[r0] = this.mNodesManager.findNodeById(this.mInputIDs[r0], Node.class);
        }
        return Double.valueOf(this.mOperator.evaluate(this.mInputNodes));
    }
}
