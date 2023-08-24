package com.th3rdwave.safeareacontext;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.EnumSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: SafeAreaView.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0017H\u0014J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0016\u0010\u001a\u001a\u00020\u00172\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bJ\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u000fJ\b\u0010\u001e\u001a\u00020\u0017H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0002R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, m183d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "Lcom/facebook/react/uimanager/FabricViewStateManager$HasFabricViewStateManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mEdges", "Ljava/util/EnumSet;", "Lcom/th3rdwave/safeareacontext/SafeAreaViewEdges;", "mFabricViewStateManager", "Lcom/facebook/react/uimanager/FabricViewStateManager;", "mInsets", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "mMode", "Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "mProviderView", "Landroid/view/View;", "findProvider", "getFabricViewStateManager", "maybeUpdateInsets", "", "onAttachedToWindow", "", "onDetachedFromWindow", "onPreDraw", "setEdges", "edges", "setMode", "mode", "updateInsets", "waitForReactLayout", "react-native-safe-area-context_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SafeAreaView extends ReactViewGroup implements ViewTreeObserver.OnPreDrawListener, FabricViewStateManager.HasFabricViewStateManager {
    private EnumSet<SafeAreaViewEdges> mEdges;
    private final FabricViewStateManager mFabricViewStateManager;
    private EdgeInsets mInsets;
    private SafeAreaViewMode mMode;
    private View mProviderView;

    public SafeAreaView(Context context) {
        super(context);
        this.mMode = SafeAreaViewMode.PADDING;
        this.mFabricViewStateManager = new FabricViewStateManager();
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mFabricViewStateManager;
    }

    private final void updateInsets() {
        final EdgeInsets edgeInsets = this.mInsets;
        if (edgeInsets != null) {
            EnumSet<SafeAreaViewEdges> edges = this.mEdges;
            if (edges == null) {
                edges = EnumSet.allOf(SafeAreaViewEdges.class);
            }
            if (this.mFabricViewStateManager.hasStateWrapper()) {
                this.mFabricViewStateManager.setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.th3rdwave.safeareacontext.SafeAreaView$$ExternalSyntheticLambda0
                    @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
                    public final WritableMap getStateUpdate() {
                        WritableMap m1564updateInsets$lambda0;
                        m1564updateInsets$lambda0 = SafeAreaView.m1564updateInsets$lambda0(EdgeInsets.this);
                        return m1564updateInsets$lambda0;
                    }
                });
                return;
            }
            SafeAreaViewMode safeAreaViewMode = this.mMode;
            Intrinsics.checkNotNullExpressionValue(edges, "edges");
            SafeAreaViewLocalData safeAreaViewLocalData = new SafeAreaViewLocalData(edgeInsets, safeAreaViewMode, edges);
            ReactContext reactContext = UIManagerHelperCompat.getReactContext(this);
            final UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), safeAreaViewLocalData);
                reactContext.runOnNativeModulesQueueThread(new Runnable() { // from class: com.th3rdwave.safeareacontext.SafeAreaView$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SafeAreaView.m1565updateInsets$lambda1(UIManagerModule.this);
                    }
                });
                waitForReactLayout();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateInsets$lambda-0  reason: not valid java name */
    public static final WritableMap m1564updateInsets$lambda0(EdgeInsets edgeInsets) {
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("insets", SerializationUtils.edgeInsetsToJsMap(edgeInsets));
        return createMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateInsets$lambda-1  reason: not valid java name */
    public static final void m1565updateInsets$lambda1(UIManagerModule uIManagerModule) {
        uIManagerModule.getUIImplementation().dispatchViewUpdates(-1);
    }

    private final void waitForReactLayout() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition newCondition = reentrantLock.newCondition();
        long nanoTime = System.nanoTime();
        UIManagerHelperCompat.getReactContext(this).runOnNativeModulesQueueThread(new Runnable() { // from class: com.th3rdwave.safeareacontext.SafeAreaView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SafeAreaView.m1566waitForReactLayout$lambda3(reentrantLock, booleanRef, newCondition);
            }
        });
        ReentrantLock reentrantLock2 = reentrantLock;
        reentrantLock2.lock();
        long j = 0;
        while (!booleanRef.element && j < 500000000) {
            try {
                try {
                    newCondition.awaitNanos(500000000L);
                } catch (InterruptedException unused) {
                    booleanRef.element = true;
                }
                j += System.nanoTime() - nanoTime;
            } catch (Throwable th) {
                reentrantLock2.unlock();
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        reentrantLock2.unlock();
        if (j >= 500000000) {
            Log.w("SafeAreaView", "Timed out waiting for layout.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: waitForReactLayout$lambda-3  reason: not valid java name */
    public static final void m1566waitForReactLayout$lambda3(ReentrantLock lock, Ref.BooleanRef done, Condition condition) {
        Intrinsics.checkNotNullParameter(lock, "$lock");
        Intrinsics.checkNotNullParameter(done, "$done");
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            if (!done.element) {
                done.element = true;
                condition.signal();
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setMode(SafeAreaViewMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        this.mMode = mode;
        updateInsets();
    }

    public final void setEdges(EnumSet<SafeAreaViewEdges> enumSet) {
        this.mEdges = enumSet;
        updateInsets();
    }

    private final boolean maybeUpdateInsets() {
        EdgeInsets safeAreaInsets;
        View view = this.mProviderView;
        if (view == null || (safeAreaInsets = SafeAreaUtils.getSafeAreaInsets(view)) == null || Intrinsics.areEqual(this.mInsets, safeAreaInsets)) {
            return false;
        }
        this.mInsets = safeAreaInsets;
        updateInsets();
        return true;
    }

    private final View findProvider() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof SafeAreaProvider) {
                return (View) parent;
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        ViewTreeObserver viewTreeObserver;
        super.onAttachedToWindow();
        View findProvider = findProvider();
        this.mProviderView = findProvider;
        if (findProvider != null && (viewTreeObserver = findProvider.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnPreDrawListener(this);
        }
        maybeUpdateInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        ViewTreeObserver viewTreeObserver;
        super.onDetachedFromWindow();
        View view = this.mProviderView;
        if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnPreDrawListener(this);
        }
        this.mProviderView = null;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        boolean maybeUpdateInsets = maybeUpdateInsets();
        if (maybeUpdateInsets) {
            requestLayout();
        }
        return !maybeUpdateInsets;
    }
}
