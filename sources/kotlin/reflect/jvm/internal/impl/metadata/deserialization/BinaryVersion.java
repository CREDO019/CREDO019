package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import androidx.core.p005os.EnvironmentCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BinaryVersion.kt */
/* loaded from: classes5.dex */
public abstract class BinaryVersion {
    public static final Companion Companion = new Companion(null);
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    private final List<Integer> rest;

    public BinaryVersion(int... numbers) {
        Intrinsics.checkNotNullParameter(numbers, "numbers");
        this.numbers = numbers;
        Integer orNull = ArraysKt.getOrNull(numbers, 0);
        this.major = orNull == null ? -1 : orNull.intValue();
        Integer orNull2 = ArraysKt.getOrNull(numbers, 1);
        this.minor = orNull2 == null ? -1 : orNull2.intValue();
        Integer orNull3 = ArraysKt.getOrNull(numbers, 2);
        this.patch = orNull3 != null ? orNull3.intValue() : -1;
        this.rest = numbers.length > 3 ? CollectionsKt.toList(ArraysKt.asList(numbers).subList(3, numbers.length)) : CollectionsKt.emptyList();
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int[] toArray() {
        return this.numbers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isCompatibleTo(BinaryVersion ourVersion) {
        Intrinsics.checkNotNullParameter(ourVersion, "ourVersion");
        int r0 = this.major;
        if (r0 == 0) {
            if (ourVersion.major == 0 && this.minor == ourVersion.minor) {
                return true;
            }
        } else if (r0 == ourVersion.major && this.minor <= ourVersion.minor) {
            return true;
        }
        return false;
    }

    public final boolean isAtLeast(BinaryVersion version) {
        Intrinsics.checkNotNullParameter(version, "version");
        return isAtLeast(version.major, version.minor, version.patch);
    }

    public final boolean isAtLeast(int r4, int r5, int r6) {
        int r0 = this.major;
        if (r0 > r4) {
            return true;
        }
        if (r0 < r4) {
            return false;
        }
        int r42 = this.minor;
        if (r42 > r5) {
            return true;
        }
        return r42 >= r5 && this.patch >= r6;
    }

    public final boolean isAtMost(int r4, int r5, int r6) {
        int r0 = this.major;
        if (r0 < r4) {
            return true;
        }
        if (r0 > r4) {
            return false;
        }
        int r42 = this.minor;
        if (r42 < r5) {
            return true;
        }
        return r42 <= r5 && this.patch <= r6;
    }

    public String toString() {
        int[] array = toArray();
        ArrayList arrayList = new ArrayList();
        int length = array.length;
        int r4 = 0;
        while (r4 < length) {
            int r5 = array[r4];
            r4++;
            if (!(r5 != -1)) {
                break;
            }
            arrayList.add(Integer.valueOf(r5));
        }
        ArrayList arrayList2 = arrayList;
        return arrayList2.isEmpty() ? EnvironmentCompat.MEDIA_UNKNOWN : CollectionsKt.joinToString$default(arrayList2, ".", null, null, 0, null, null, 62, null);
    }

    public boolean equals(Object obj) {
        if (obj != null && Intrinsics.areEqual(getClass(), obj.getClass())) {
            BinaryVersion binaryVersion = (BinaryVersion) obj;
            if (this.major == binaryVersion.major && this.minor == binaryVersion.minor && this.patch == binaryVersion.patch && Intrinsics.areEqual(this.rest, binaryVersion.rest)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int r0 = this.major;
        int r02 = r0 + (r0 * 31) + this.minor;
        int r03 = r02 + (r02 * 31) + this.patch;
        return r03 + (r03 * 31) + this.rest.hashCode();
    }

    /* compiled from: BinaryVersion.kt */
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
