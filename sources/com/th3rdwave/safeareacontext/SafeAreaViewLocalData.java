package com.th3rdwave.safeareacontext;

import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafeAreaViewLocalData.kt */
@Metadata(m184d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, m183d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaViewLocalData;", "", "insets", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "mode", "Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "edges", "Ljava/util/EnumSet;", "Lcom/th3rdwave/safeareacontext/SafeAreaViewEdges;", "(Lcom/th3rdwave/safeareacontext/EdgeInsets;Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;Ljava/util/EnumSet;)V", "getEdges", "()Ljava/util/EnumSet;", "getInsets", "()Lcom/th3rdwave/safeareacontext/EdgeInsets;", "getMode", "()Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-safe-area-context_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SafeAreaViewLocalData {
    private final EnumSet<SafeAreaViewEdges> edges;
    private final EdgeInsets insets;
    private final SafeAreaViewMode mode;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SafeAreaViewLocalData copy$default(SafeAreaViewLocalData safeAreaViewLocalData, EdgeInsets edgeInsets, SafeAreaViewMode safeAreaViewMode, EnumSet enumSet, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            edgeInsets = safeAreaViewLocalData.insets;
        }
        if ((r4 & 2) != 0) {
            safeAreaViewMode = safeAreaViewLocalData.mode;
        }
        if ((r4 & 4) != 0) {
            enumSet = safeAreaViewLocalData.edges;
        }
        return safeAreaViewLocalData.copy(edgeInsets, safeAreaViewMode, enumSet);
    }

    public final EdgeInsets component1() {
        return this.insets;
    }

    public final SafeAreaViewMode component2() {
        return this.mode;
    }

    public final EnumSet<SafeAreaViewEdges> component3() {
        return this.edges;
    }

    public final SafeAreaViewLocalData copy(EdgeInsets insets, SafeAreaViewMode mode, EnumSet<SafeAreaViewEdges> edges) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(edges, "edges");
        return new SafeAreaViewLocalData(insets, mode, edges);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SafeAreaViewLocalData) {
            SafeAreaViewLocalData safeAreaViewLocalData = (SafeAreaViewLocalData) obj;
            return Intrinsics.areEqual(this.insets, safeAreaViewLocalData.insets) && this.mode == safeAreaViewLocalData.mode && Intrinsics.areEqual(this.edges, safeAreaViewLocalData.edges);
        }
        return false;
    }

    public int hashCode() {
        return (((this.insets.hashCode() * 31) + this.mode.hashCode()) * 31) + this.edges.hashCode();
    }

    public String toString() {
        return "SafeAreaViewLocalData(insets=" + this.insets + ", mode=" + this.mode + ", edges=" + this.edges + ')';
    }

    public SafeAreaViewLocalData(EdgeInsets insets, SafeAreaViewMode mode, EnumSet<SafeAreaViewEdges> edges) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(edges, "edges");
        this.insets = insets;
        this.mode = mode;
        this.edges = edges;
    }

    public final EdgeInsets getInsets() {
        return this.insets;
    }

    public final SafeAreaViewMode getMode() {
        return this.mode;
    }

    public final EnumSet<SafeAreaViewEdges> getEdges() {
        return this.edges;
    }
}
