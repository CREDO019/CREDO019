package okio;

import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Options.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001f\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000eH\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, m183d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", "get", "index", "Companion", "okio"}, m182k = 1, m181mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public static final Companion Companion = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    @JvmStatic
    /* renamed from: of */
    public static final Options m145of(ByteString... byteStringArr) {
        return Companion.m144of(byteStringArr);
    }

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] r2, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, r2);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains((Options) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf((Options) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf((Options) byteString);
    }

    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    public final int[] getTrie$okio() {
        return this.trie;
    }

    private Options(ByteString[] byteStringArr, int[] r2) {
        this.byteStrings = byteStringArr;
        this.trie = r2;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.byteStrings.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ByteString get(int r2) {
        return this.byteStrings[r2];
    }

    /* compiled from: Options.kt */
    @Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, m183d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"}, m182k = 1, m181mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Code restructure failed: missing block: B:55:0x00ff, code lost:
            continue;
         */
        @kotlin.jvm.JvmStatic
        /* renamed from: of */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final okio.Options m144of(okio.ByteString... r19) {
            /*
                Method dump skipped, instructions count: 350
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Options.Companion.m144of(okio.ByteString[]):okio.Options");
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int r15, List list, int r17, int r18, List list2, int r20, Object obj) {
            companion.buildTrieRecursive((r20 & 1) != 0 ? 0L : j, buffer, (r20 & 4) != 0 ? 0 : r15, list, (r20 & 16) != 0 ? 0 : r17, (r20 & 32) != 0 ? list.size() : r18, list2);
        }

        private final void buildTrieRecursive(long j, Buffer buffer, int r24, List<? extends ByteString> list, int r26, int r27, List<Integer> list2) {
            int r6;
            int r0;
            int r7;
            int r18;
            Buffer buffer2;
            int r11 = r24;
            if (!(r26 < r27)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int r3 = r26; r3 < r27; r3++) {
                if (!(list.get(r3).size() >= r11)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            ByteString byteString = list.get(r26);
            ByteString byteString2 = list.get(r27 - 1);
            if (r11 == byteString.size()) {
                int r02 = r26 + 1;
                r6 = r02;
                r0 = list2.get(r26).intValue();
                byteString = list.get(r02);
            } else {
                r6 = r26;
                r0 = -1;
            }
            if (byteString.getByte(r11) != byteString2.getByte(r11)) {
                int r2 = 1;
                for (int r1 = r6 + 1; r1 < r27; r1++) {
                    if (list.get(r1 - 1).getByte(r11) != list.get(r1).getByte(r11)) {
                        r2++;
                    }
                }
                long intCount = j + getIntCount(buffer) + 2 + (r2 * 2);
                buffer.writeInt(r2);
                buffer.writeInt(r0);
                for (int r03 = r6; r03 < r27; r03++) {
                    byte b = list.get(r03).getByte(r11);
                    if (r03 == r6 || b != list.get(r03 - 1).getByte(r11)) {
                        buffer.writeInt(b & 255);
                    }
                }
                Buffer buffer3 = new Buffer();
                while (r6 < r27) {
                    byte b2 = list.get(r6).getByte(r11);
                    int r12 = r6 + 1;
                    int r22 = r12;
                    while (true) {
                        if (r22 >= r27) {
                            r7 = r27;
                            break;
                        } else if (b2 != list.get(r22).getByte(r11)) {
                            r7 = r22;
                            break;
                        } else {
                            r22++;
                        }
                    }
                    if (r12 == r7 && r11 + 1 == list.get(r6).size()) {
                        buffer.writeInt(list2.get(r6).intValue());
                        r18 = r7;
                        buffer2 = buffer3;
                    } else {
                        buffer.writeInt(((int) (intCount + getIntCount(buffer3))) * (-1));
                        r18 = r7;
                        buffer2 = buffer3;
                        buildTrieRecursive(intCount, buffer3, r11 + 1, list, r6, r7, list2);
                    }
                    r6 = r18;
                    buffer3 = buffer2;
                }
                buffer.writeAll(buffer3);
                return;
            }
            int min = Math.min(byteString.size(), byteString2.size());
            int r13 = 0;
            for (int r72 = r11; r72 < min && byteString.getByte(r72) == byteString2.getByte(r72); r72++) {
                r13++;
            }
            long intCount2 = j + getIntCount(buffer) + 2 + r13 + 1;
            buffer.writeInt(-r13);
            buffer.writeInt(r0);
            int r73 = r11 + r13;
            while (r11 < r73) {
                buffer.writeInt(byteString.getByte(r11) & 255);
                r11++;
            }
            if (r6 + 1 == r27) {
                if (!(r73 == list.get(r6).size())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                buffer.writeInt(list2.get(r6).intValue());
                return;
            }
            Buffer buffer4 = new Buffer();
            buffer.writeInt(((int) (getIntCount(buffer4) + intCount2)) * (-1));
            buildTrieRecursive(intCount2, buffer4, r73, list, r6, r27, list2);
            buffer.writeAll(buffer4);
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / 4;
        }
    }
}
