package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Stack;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class RopeByteString extends ByteString {
    private static final int[] minLengthByDepth;
    private int hash;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    static {
        ArrayList arrayList = new ArrayList();
        int r1 = 1;
        int r2 = 1;
        while (r1 > 0) {
            arrayList.add(Integer.valueOf(r1));
            int r22 = r2 + r1;
            r2 = r1;
            r1 = r22;
        }
        arrayList.add(Integer.MAX_VALUE);
        minLengthByDepth = new int[arrayList.size()];
        int r12 = 0;
        while (true) {
            int[] r23 = minLengthByDepth;
            if (r12 >= r23.length) {
                return;
            }
            r23[r12] = ((Integer) arrayList.get(r12)).intValue();
            r12++;
        }
    }

    private RopeByteString(ByteString byteString, ByteString byteString2) {
        this.hash = 0;
        this.left = byteString;
        this.right = byteString2;
        int size = byteString.size();
        this.leftLength = size;
        this.totalLength = size + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        RopeByteString ropeByteString = byteString instanceof RopeByteString ? (RopeByteString) byteString : null;
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() != 0) {
            int size = byteString.size() + byteString2.size();
            if (size < 128) {
                return concatenateBytes(byteString, byteString2);
            }
            if (ropeByteString != null && ropeByteString.right.size() + byteString2.size() < 128) {
                byteString2 = new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
            } else if (ropeByteString != null && ropeByteString.left.getTreeDepth() > ropeByteString.right.getTreeDepth() && ropeByteString.getTreeDepth() > byteString2.getTreeDepth()) {
                byteString2 = new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
            } else {
                if (size >= minLengthByDepth[Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1]) {
                    return new RopeByteString(byteString, byteString2);
                }
                return new Balancer().balance(byteString, byteString2);
            }
        }
        return byteString2;
    }

    private static LiteralByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return new LiteralByteString(bArr);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int getTreeDepth() {
        return this.treeDepth;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected boolean isBalanced() {
        return this.totalLength >= minLengthByDepth[this.treeDepth];
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected void copyToInternal(byte[] bArr, int r4, int r5, int r6) {
        int r0 = r4 + r6;
        int r1 = this.leftLength;
        if (r0 <= r1) {
            this.left.copyToInternal(bArr, r4, r5, r6);
        } else if (r4 >= r1) {
            this.right.copyToInternal(bArr, r4 - r1, r5, r6);
        } else {
            int r12 = r1 - r4;
            this.left.copyToInternal(bArr, r4, r5, r12);
            this.right.copyToInternal(bArr, 0, r5 + r12, r6 - r12);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    void writeToInternal(OutputStream outputStream, int r4, int r5) throws IOException {
        int r0 = r4 + r5;
        int r1 = this.leftLength;
        if (r0 <= r1) {
            this.left.writeToInternal(outputStream, r4, r5);
        } else if (r4 >= r1) {
            this.right.writeToInternal(outputStream, r4 - r1, r5);
        } else {
            int r12 = r1 - r4;
            this.left.writeToInternal(outputStream, r4, r12);
            this.right.writeToInternal(outputStream, 0, r5 - r12);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public String toString(String str) throws UnsupportedEncodingException {
        return new String(toByteArray(), str);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public boolean isValidUtf8() {
        int partialIsValidUtf8 = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        ByteString byteString = this.right;
        return byteString.partialIsValidUtf8(partialIsValidUtf8, 0, byteString.size()) == 0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int partialIsValidUtf8(int r3, int r4, int r5) {
        int r0 = r4 + r5;
        int r1 = this.leftLength;
        if (r0 <= r1) {
            return this.left.partialIsValidUtf8(r3, r4, r5);
        }
        if (r4 >= r1) {
            return this.right.partialIsValidUtf8(r3, r4 - r1, r5);
        }
        int r12 = r1 - r4;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(r3, r4, r12), 0, r5 - r12);
    }

    public boolean equals(Object obj) {
        int peekCachedHashCode;
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (this.totalLength != byteString.size()) {
                return false;
            }
            if (this.totalLength == 0) {
                return true;
            }
            if (this.hash == 0 || (peekCachedHashCode = byteString.peekCachedHashCode()) == 0 || this.hash == peekCachedHashCode) {
                return equalsFragments(byteString);
            }
            return false;
        }
        return false;
    }

    private boolean equalsFragments(ByteString byteString) {
        PieceIterator pieceIterator = new PieceIterator(this);
        LiteralByteString next = pieceIterator.next();
        PieceIterator pieceIterator2 = new PieceIterator(byteString);
        LiteralByteString next2 = pieceIterator2.next();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        while (true) {
            int size = next.size() - r4;
            int size2 = next2.size() - r5;
            int min = Math.min(size, size2);
            if (!(r4 == 0 ? next.equalsRange(next2, r5, min) : next2.equalsRange(next, r4, min))) {
                return false;
            }
            r6 += min;
            int r10 = this.totalLength;
            if (r6 >= r10) {
                if (r6 == r10) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == size) {
                next = pieceIterator.next();
                r4 = 0;
            } else {
                r4 += min;
            }
            if (min == size2) {
                next2 = pieceIterator2.next();
                r5 = 0;
            } else {
                r5 += min;
            }
        }
    }

    public int hashCode() {
        int r0 = this.hash;
        if (r0 == 0) {
            int r02 = this.totalLength;
            r0 = partialHash(r02, 0, r02);
            if (r0 == 0) {
                r0 = 1;
            }
            this.hash = r0;
        }
        return r0;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int peekCachedHashCode() {
        return this.hash;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int partialHash(int r3, int r4, int r5) {
        int r0 = r4 + r5;
        int r1 = this.leftLength;
        if (r0 <= r1) {
            return this.left.partialHash(r3, r4, r5);
        }
        if (r4 >= r1) {
            return this.right.partialHash(r3, r4 - r1, r5);
        }
        int r12 = r1 - r4;
        return this.right.partialHash(this.left.partialHash(r3, r4, r12), 0, r5 - r12);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new RopeInputStream());
    }

    /* loaded from: classes5.dex */
    private static class Balancer {
        private final Stack<ByteString> prefixesStack;

        private Balancer() {
            this.prefixesStack = new Stack<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ByteString balance(ByteString byteString, ByteString byteString2) {
            doBalance(byteString);
            doBalance(byteString2);
            ByteString pop = this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty()) {
                pop = new RopeByteString(this.prefixesStack.pop(), pop);
            }
            return pop;
        }

        private void doBalance(ByteString byteString) {
            if (byteString.isBalanced()) {
                insert(byteString);
            } else if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                doBalance(ropeByteString.left);
                doBalance(ropeByteString.right);
            } else {
                String valueOf = String.valueOf(String.valueOf(byteString.getClass()));
                StringBuilder sb = new StringBuilder(valueOf.length() + 49);
                sb.append("Has a new type of ByteString been created? Found ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }

        private void insert(ByteString byteString) {
            int depthBinForLength = getDepthBinForLength(byteString.size());
            int r1 = RopeByteString.minLengthByDepth[depthBinForLength + 1];
            if (!this.prefixesStack.isEmpty() && this.prefixesStack.peek().size() < r1) {
                int r0 = RopeByteString.minLengthByDepth[depthBinForLength];
                ByteString pop = this.prefixesStack.pop();
                while (!this.prefixesStack.isEmpty() && this.prefixesStack.peek().size() < r0) {
                    pop = new RopeByteString(this.prefixesStack.pop(), pop);
                }
                RopeByteString ropeByteString = new RopeByteString(pop, byteString);
                while (!this.prefixesStack.isEmpty()) {
                    if (this.prefixesStack.peek().size() >= RopeByteString.minLengthByDepth[getDepthBinForLength(ropeByteString.size()) + 1]) {
                        break;
                    }
                    ropeByteString = new RopeByteString(this.prefixesStack.pop(), ropeByteString);
                }
                this.prefixesStack.push(ropeByteString);
                return;
            }
            this.prefixesStack.push(byteString);
        }

        private int getDepthBinForLength(int r2) {
            int binarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, r2);
            return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class PieceIterator implements Iterator<LiteralByteString> {
        private final Stack<RopeByteString> breadCrumbs;
        private LiteralByteString next;

        private PieceIterator(ByteString byteString) {
            this.breadCrumbs = new Stack<>();
            this.next = getLeafByLeft(byteString);
        }

        private LiteralByteString getLeafByLeft(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.breadCrumbs.push(ropeByteString);
                byteString = ropeByteString.left;
            }
            return (LiteralByteString) byteString;
        }

        private LiteralByteString getNextNonEmptyLeaf() {
            while (!this.breadCrumbs.isEmpty()) {
                LiteralByteString leafByLeft = getLeafByLeft(this.breadCrumbs.pop().right);
                if (!leafByLeft.isEmpty()) {
                    return leafByLeft;
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public LiteralByteString next() {
            LiteralByteString literalByteString = this.next;
            if (literalByteString == null) {
                throw new NoSuchElementException();
            }
            this.next = getNextNonEmptyLeaf();
            return literalByteString;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString, java.lang.Iterable
    public Iterator<Byte> iterator() {
        return new RopeByteIterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class RopeByteIterator implements ByteString.ByteIterator {
        private ByteString.ByteIterator bytes;
        int bytesRemaining;
        private final PieceIterator pieces;

        /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.reflect.jvm.internal.impl.protobuf.ByteString$ByteIterator] */
        private RopeByteIterator() {
            PieceIterator pieceIterator = new PieceIterator(RopeByteString.this);
            this.pieces = pieceIterator;
            this.bytes = pieceIterator.next().iterator();
            this.bytesRemaining = RopeByteString.this.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.bytesRemaining > 0;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }

        /* JADX WARN: Type inference failed for: r0v8, types: [kotlin.reflect.jvm.internal.impl.protobuf.ByteString$ByteIterator] */
        @Override // kotlin.reflect.jvm.internal.impl.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            if (!this.bytes.hasNext()) {
                this.bytes = this.pieces.next().iterator();
            }
            this.bytesRemaining--;
            return this.bytes.nextByte();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes5.dex */
    private class RopeInputStream extends InputStream {
        private LiteralByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        public RopeInputStream() {
            initialize();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int r3, int r4) {
            Objects.requireNonNull(bArr);
            if (r3 < 0 || r4 < 0 || r4 > bArr.length - r3) {
                throw new IndexOutOfBoundsException();
            }
            return readSkipInternal(bArr, r3, r4);
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            if (j < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (j > 2147483647L) {
                j = 2147483647L;
            }
            return readSkipInternal(null, 0, (int) j);
        }

        private int readSkipInternal(byte[] bArr, int r6, int r7) {
            int r0 = r7;
            while (true) {
                if (r0 <= 0) {
                    break;
                }
                advanceIfCurrentPieceFullyRead();
                if (this.currentPiece != null) {
                    int min = Math.min(this.currentPieceSize - this.currentPieceIndex, r0);
                    if (bArr != null) {
                        this.currentPiece.copyTo(bArr, this.currentPieceIndex, r6, min);
                        r6 += min;
                    }
                    this.currentPieceIndex += min;
                    r0 -= min;
                } else if (r0 == r7) {
                    return -1;
                }
            }
            return r7 - r0;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            advanceIfCurrentPieceFullyRead();
            LiteralByteString literalByteString = this.currentPiece;
            if (literalByteString == null) {
                return -1;
            }
            int r1 = this.currentPieceIndex;
            this.currentPieceIndex = r1 + 1;
            return literalByteString.byteAt(r1) & 255;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        @Override // java.io.InputStream
        public void mark(int r2) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            initialize();
            readSkipInternal(null, 0, this.mark);
        }

        private void initialize() {
            PieceIterator pieceIterator = new PieceIterator(RopeByteString.this);
            this.pieceIterator = pieceIterator;
            LiteralByteString next = pieceIterator.next();
            this.currentPiece = next;
            this.currentPieceSize = next.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        private void advanceIfCurrentPieceFullyRead() {
            if (this.currentPiece != null) {
                int r0 = this.currentPieceIndex;
                int r1 = this.currentPieceSize;
                if (r0 == r1) {
                    this.currentPieceOffsetInRope += r1;
                    this.currentPieceIndex = 0;
                    if (this.pieceIterator.hasNext()) {
                        LiteralByteString next = this.pieceIterator.next();
                        this.currentPiece = next;
                        this.currentPieceSize = next.size();
                        return;
                    }
                    this.currentPiece = null;
                    this.currentPieceSize = 0;
                }
            }
        }
    }
}
