package com.facebook.react.uimanager;

import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class UIViewOperationQueue {
    public static final int DEFAULT_MIN_TIME_LEFT_IN_FRAME_FOR_NONBATCHED_OPERATION_MS = 8;
    private static final String TAG = "UIViewOperationQueue";
    private long mCreateViewCount;
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    private final NativeViewHierarchyManager mNativeViewHierarchyManager;
    private long mNonBatchedExecutionTotalTime;
    private long mProfiledBatchBatchedExecutionTime;
    private long mProfiledBatchCommitEndTime;
    private long mProfiledBatchCommitStartTime;
    private long mProfiledBatchDispatchViewUpdatesTime;
    private long mProfiledBatchLayoutTime;
    private long mProfiledBatchNonBatchedExecutionTime;
    private long mProfiledBatchRunEndTime;
    private long mProfiledBatchRunStartTime;
    private final ReactApplicationContext mReactApplicationContext;
    private long mThreadCpuTime;
    private long mUpdatePropertiesOperationCount;
    private NotThreadSafeViewHierarchyUpdateDebugListener mViewHierarchyUpdateDebugListener;
    private final int[] mMeasureBuffer = new int[4];
    private final Object mDispatchRunnablesLock = new Object();
    private final Object mNonBatchedOperationsLock = new Object();
    private ArrayList<DispatchCommandViewOperation> mViewCommandOperations = new ArrayList<>();
    private ArrayList<UIOperation> mOperations = new ArrayList<>();
    private ArrayList<Runnable> mDispatchUIRunnables = new ArrayList<>();
    private ArrayDeque<UIOperation> mNonBatchedOperations = new ArrayDeque<>();
    private boolean mIsDispatchUIFrameCallbackEnqueued = false;
    private boolean mIsInIllegalUIState = false;
    private boolean mIsProfilingNextBatch = false;

    /* loaded from: classes.dex */
    private interface DispatchCommandViewOperation {
        void executeWithExceptions();

        int getRetries();

        void incrementRetries();
    }

    /* loaded from: classes.dex */
    public interface UIOperation {
        void execute();
    }

    static /* synthetic */ long access$2914(UIViewOperationQueue uIViewOperationQueue, long j) {
        long j2 = uIViewOperationQueue.mNonBatchedExecutionTotalTime + j;
        uIViewOperationQueue.mNonBatchedExecutionTotalTime = j2;
        return j2;
    }

    /* loaded from: classes.dex */
    private abstract class ViewOperation implements UIOperation {
        public int mTag;

        public ViewOperation(int r2) {
            this.mTag = r2;
        }
    }

    /* loaded from: classes.dex */
    private final class RemoveRootViewOperation extends ViewOperation {
        public RemoveRootViewOperation(int r2) {
            super(r2);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.removeRootView(this.mTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class UpdatePropertiesOperation extends ViewOperation {
        private final ReactStylesDiffMap mProps;

        private UpdatePropertiesOperation(int r2, ReactStylesDiffMap reactStylesDiffMap) {
            super(r2);
            this.mProps = reactStylesDiffMap;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateProperties(this.mTag, this.mProps);
        }
    }

    /* loaded from: classes.dex */
    private final class EmitOnLayoutEventOperation extends ViewOperation {
        private final int mScreenHeight;
        private final int mScreenWidth;
        private final int mScreenX;
        private final int mScreenY;

        public EmitOnLayoutEventOperation(int r2, int r3, int r4, int r5, int r6) {
            super(r2);
            this.mScreenX = r3;
            this.mScreenY = r4;
            this.mScreenWidth = r5;
            this.mScreenHeight = r6;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIManagerModule uIManagerModule = (UIManagerModule) UIViewOperationQueue.this.mReactApplicationContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.getEventDispatcher().dispatchEvent(OnLayoutEvent.obtain(-1, this.mTag, this.mScreenX, this.mScreenY, this.mScreenWidth, this.mScreenHeight));
            }
        }
    }

    /* loaded from: classes.dex */
    private final class UpdateInstanceHandleOperation extends ViewOperation {
        private final long mInstanceHandle;

        private UpdateInstanceHandleOperation(int r2, long j) {
            super(r2);
            this.mInstanceHandle = j;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateInstanceHandle(this.mTag, this.mInstanceHandle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class UpdateLayoutOperation extends ViewOperation {
        private final int mHeight;
        private final int mParentTag;
        private final int mWidth;

        /* renamed from: mX */
        private final int f168mX;

        /* renamed from: mY */
        private final int f169mY;

        public UpdateLayoutOperation(int r2, int r3, int r4, int r5, int r6, int r7) {
            super(r3);
            this.mParentTag = r2;
            this.f168mX = r4;
            this.f169mY = r5;
            this.mWidth = r6;
            this.mHeight = r7;
            Systrace.startAsyncFlow(0L, "updateLayout", this.mTag);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Systrace.endAsyncFlow(0L, "updateLayout", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateLayout(this.mParentTag, this.mTag, this.f168mX, this.f169mY, this.mWidth, this.mHeight);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class CreateViewOperation extends ViewOperation {
        private final String mClassName;
        private final ReactStylesDiffMap mInitialProps;
        private final ThemedReactContext mThemedContext;

        public CreateViewOperation(ThemedReactContext themedReactContext, int r3, String str, ReactStylesDiffMap reactStylesDiffMap) {
            super(r3);
            this.mThemedContext = themedReactContext;
            this.mClassName = str;
            this.mInitialProps = reactStylesDiffMap;
            Systrace.startAsyncFlow(0L, "createView", this.mTag);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            Systrace.endAsyncFlow(0L, "createView", this.mTag);
            UIViewOperationQueue.this.mNativeViewHierarchyManager.createView(this.mThemedContext, this.mTag, this.mClassName, this.mInitialProps);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ManageChildrenOperation extends ViewOperation {
        private final int[] mIndicesToRemove;
        private final int[] mTagsToDelete;
        private final ViewAtIndex[] mViewsToAdd;

        public ManageChildrenOperation(int r2, int[] r3, ViewAtIndex[] viewAtIndexArr, int[] r5) {
            super(r2);
            this.mIndicesToRemove = r3;
            this.mViewsToAdd = viewAtIndexArr;
            this.mTagsToDelete = r5;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.manageChildren(this.mTag, this.mIndicesToRemove, this.mViewsToAdd, this.mTagsToDelete);
        }
    }

    /* loaded from: classes.dex */
    private final class SetChildrenOperation extends ViewOperation {
        private final ReadableArray mChildrenTags;

        public SetChildrenOperation(int r2, ReadableArray readableArray) {
            super(r2);
            this.mChildrenTags = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.setChildren(this.mTag, this.mChildrenTags);
        }
    }

    /* loaded from: classes.dex */
    private final class UpdateViewExtraData extends ViewOperation {
        private final Object mExtraData;

        public UpdateViewExtraData(int r2, Object obj) {
            super(r2);
            this.mExtraData = obj;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.updateViewExtraData(this.mTag, this.mExtraData);
        }
    }

    /* loaded from: classes.dex */
    private final class ChangeJSResponderOperation extends ViewOperation {
        private final boolean mBlockNativeResponder;
        private final boolean mClearResponder;
        private final int mInitialTag;

        public ChangeJSResponderOperation(int r2, int r3, boolean z, boolean z2) {
            super(r2);
            this.mInitialTag = r3;
            this.mClearResponder = z;
            this.mBlockNativeResponder = z2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            if (!this.mClearResponder) {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.setJSResponder(this.mTag, this.mInitialTag, this.mBlockNativeResponder);
            } else {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.clearJSResponder();
            }
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    private final class DispatchCommandOperation extends ViewOperation implements DispatchCommandViewOperation {
        private final ReadableArray mArgs;
        private final int mCommand;
        private int numRetries;

        public DispatchCommandOperation(int r2, int r3, ReadableArray readableArray) {
            super(r2);
            this.numRetries = 0;
            this.mCommand = r3;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
            } catch (Throwable th) {
                ReactSoftExceptionLogger.logSoftException(UIViewOperationQueue.TAG, new RuntimeException("Error dispatching View Command", th));
            }
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public void executeWithExceptions() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public void incrementRetries() {
            this.numRetries++;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public int getRetries() {
            return this.numRetries;
        }
    }

    /* loaded from: classes.dex */
    private final class DispatchStringCommandOperation extends ViewOperation implements DispatchCommandViewOperation {
        private final ReadableArray mArgs;
        private final String mCommand;
        private int numRetries;

        public DispatchStringCommandOperation(int r2, String str, ReadableArray readableArray) {
            super(r2);
            this.numRetries = 0;
            this.mCommand = str;
            this.mArgs = readableArray;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
            } catch (Throwable th) {
                ReactSoftExceptionLogger.logSoftException(UIViewOperationQueue.TAG, new RuntimeException("Error dispatching View Command", th));
            }
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public void executeWithExceptions() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dispatchCommand(this.mTag, this.mCommand, this.mArgs);
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public void incrementRetries() {
            this.numRetries++;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.DispatchCommandViewOperation
        public int getRetries() {
            return this.numRetries;
        }
    }

    /* loaded from: classes.dex */
    private final class ShowPopupMenuOperation extends ViewOperation {
        private final Callback mError;
        private final ReadableArray mItems;
        private final Callback mSuccess;

        public ShowPopupMenuOperation(int r2, ReadableArray readableArray, Callback callback, Callback callback2) {
            super(r2);
            this.mItems = readableArray;
            this.mError = callback;
            this.mSuccess = callback2;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.showPopupMenu(this.mTag, this.mItems, this.mSuccess, this.mError);
        }
    }

    /* loaded from: classes.dex */
    private final class DismissPopupMenuOperation implements UIOperation {
        private DismissPopupMenuOperation() {
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.dismissPopupMenu();
        }
    }

    /* loaded from: classes.dex */
    private static abstract class AnimationOperation implements UIOperation {
        protected final int mAnimationID;

        public AnimationOperation(int r1) {
            this.mAnimationID = r1;
        }
    }

    /* loaded from: classes.dex */
    private class SetLayoutAnimationEnabledOperation implements UIOperation {
        private final boolean mEnabled;

        private SetLayoutAnimationEnabledOperation(boolean z) {
            this.mEnabled = z;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.setLayoutAnimationEnabled(this.mEnabled);
        }
    }

    /* loaded from: classes.dex */
    private class ConfigureLayoutAnimationOperation implements UIOperation {
        private final Callback mAnimationComplete;
        private final ReadableMap mConfig;

        private ConfigureLayoutAnimationOperation(ReadableMap readableMap, Callback callback) {
            this.mConfig = readableMap;
            this.mAnimationComplete = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.configureLayoutAnimation(this.mConfig, this.mAnimationComplete);
        }
    }

    /* loaded from: classes.dex */
    private final class MeasureOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;

        private MeasureOperation(int r2, Callback callback) {
            this.mReactTag = r2;
            this.mCallback = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                float dIPFromPixel = PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0]);
                float dIPFromPixel2 = PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1]);
                this.mCallback.invoke(0, 0, Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])), Float.valueOf(dIPFromPixel), Float.valueOf(dIPFromPixel2));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    private final class MeasureInWindowOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;

        private MeasureInWindowOperation(int r2, Callback callback) {
            this.mReactTag = r2;
            this.mCallback = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                this.mCallback.invoke(Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
            } catch (NoSuchNativeViewException unused) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    private final class FindTargetForTouchOperation implements UIOperation {
        private final Callback mCallback;
        private final int mReactTag;
        private final float mTargetX;
        private final float mTargetY;

        private FindTargetForTouchOperation(int r2, float f, float f2, Callback callback) {
            this.mReactTag = r2;
            this.mTargetX = f;
            this.mTargetY = f2;
            this.mCallback = callback;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            try {
                UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(this.mReactTag, UIViewOperationQueue.this.mMeasureBuffer);
                float f = UIViewOperationQueue.this.mMeasureBuffer[0];
                float f2 = UIViewOperationQueue.this.mMeasureBuffer[1];
                int findTargetTagForTouch = UIViewOperationQueue.this.mNativeViewHierarchyManager.findTargetTagForTouch(this.mReactTag, this.mTargetX, this.mTargetY);
                try {
                    UIViewOperationQueue.this.mNativeViewHierarchyManager.measure(findTargetTagForTouch, UIViewOperationQueue.this.mMeasureBuffer);
                    this.mCallback.invoke(Integer.valueOf(findTargetTagForTouch), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[0] - f)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[1] - f2)), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[2])), Float.valueOf(PixelUtil.toDIPFromPixel(UIViewOperationQueue.this.mMeasureBuffer[3])));
                } catch (IllegalViewOperationException unused) {
                    this.mCallback.invoke(new Object[0]);
                }
            } catch (IllegalViewOperationException unused2) {
                this.mCallback.invoke(new Object[0]);
            }
        }
    }

    /* loaded from: classes.dex */
    private final class LayoutUpdateFinishedOperation implements UIOperation {
        private final UIImplementation.LayoutUpdateListener mListener;
        private final ReactShadowNode mNode;

        private LayoutUpdateFinishedOperation(ReactShadowNode reactShadowNode, UIImplementation.LayoutUpdateListener layoutUpdateListener) {
            this.mNode = reactShadowNode;
            this.mListener = layoutUpdateListener;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mListener.onLayoutUpdated(this.mNode);
        }
    }

    /* loaded from: classes.dex */
    private class UIBlockOperation implements UIOperation {
        private final UIBlock mBlock;

        public UIBlockOperation(UIBlock uIBlock) {
            this.mBlock = uIBlock;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            this.mBlock.execute(UIViewOperationQueue.this.mNativeViewHierarchyManager);
        }
    }

    /* loaded from: classes.dex */
    private final class SendAccessibilityEvent extends ViewOperation {
        private final int mEventType;

        private SendAccessibilityEvent(int r2, int r3) {
            super(r2);
            this.mEventType = r3;
        }

        @Override // com.facebook.react.uimanager.UIViewOperationQueue.UIOperation
        public void execute() {
            UIViewOperationQueue.this.mNativeViewHierarchyManager.sendAccessibilityEvent(this.mTag, this.mEventType);
        }
    }

    public UIViewOperationQueue(ReactApplicationContext reactApplicationContext, NativeViewHierarchyManager nativeViewHierarchyManager, int r4) {
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext, r4 == -1 ? 8 : r4);
        this.mReactApplicationContext = reactApplicationContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeViewHierarchyManager getNativeViewHierarchyManager() {
        return this.mNativeViewHierarchyManager;
    }

    public void setViewHierarchyUpdateDebugListener(NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mViewHierarchyUpdateDebugListener = notThreadSafeViewHierarchyUpdateDebugListener;
    }

    public void profileNextBatch() {
        this.mIsProfilingNextBatch = true;
        this.mProfiledBatchCommitStartTime = 0L;
        this.mCreateViewCount = 0L;
        this.mUpdatePropertiesOperationCount = 0L;
    }

    public Map<String, Long> getProfiledBatchPerfCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mProfiledBatchCommitStartTime));
        hashMap.put("CommitEndTime", Long.valueOf(this.mProfiledBatchCommitEndTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mProfiledBatchLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mProfiledBatchDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mProfiledBatchRunStartTime));
        hashMap.put("RunEndTime", Long.valueOf(this.mProfiledBatchRunEndTime));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.mProfiledBatchBatchedExecutionTime));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.mProfiledBatchNonBatchedExecutionTime));
        hashMap.put("NativeModulesThreadCpuTime", Long.valueOf(this.mThreadCpuTime));
        hashMap.put("CreateViewCount", Long.valueOf(this.mCreateViewCount));
        hashMap.put("UpdatePropsCount", Long.valueOf(this.mUpdatePropertiesOperationCount));
        return hashMap;
    }

    public boolean isEmpty() {
        return this.mOperations.isEmpty() && this.mViewCommandOperations.isEmpty();
    }

    public void addRootView(int r2, View view) {
        this.mNativeViewHierarchyManager.addRootView(r2, view);
    }

    protected void enqueueUIOperation(UIOperation uIOperation) {
        SoftAssertions.assertNotNull(uIOperation);
        this.mOperations.add(uIOperation);
    }

    public void enqueueRemoveRootView(int r3) {
        this.mOperations.add(new RemoveRootViewOperation(r3));
    }

    public void enqueueSetJSResponder(int r9, int r10, boolean z) {
        this.mOperations.add(new ChangeJSResponderOperation(r9, r10, false, z));
    }

    public void enqueueClearJSResponder() {
        this.mOperations.add(new ChangeJSResponderOperation(0, 0, true, false));
    }

    @Deprecated
    public void enqueueDispatchCommand(int r2, int r3, ReadableArray readableArray) {
        this.mViewCommandOperations.add(new DispatchCommandOperation(r2, r3, readableArray));
    }

    public void enqueueDispatchCommand(int r2, String str, ReadableArray readableArray) {
        this.mViewCommandOperations.add(new DispatchStringCommandOperation(r2, str, readableArray));
    }

    public void enqueueUpdateExtraData(int r3, Object obj) {
        this.mOperations.add(new UpdateViewExtraData(r3, obj));
    }

    public void enqueueShowPopupMenu(int r9, ReadableArray readableArray, Callback callback, Callback callback2) {
        this.mOperations.add(new ShowPopupMenuOperation(r9, readableArray, callback, callback2));
    }

    public void enqueueDismissPopupMenu() {
        this.mOperations.add(new DismissPopupMenuOperation());
    }

    public void enqueueCreateView(ThemedReactContext themedReactContext, int r11, String str, ReactStylesDiffMap reactStylesDiffMap) {
        synchronized (this.mNonBatchedOperationsLock) {
            this.mCreateViewCount++;
            this.mNonBatchedOperations.addLast(new CreateViewOperation(themedReactContext, r11, str, reactStylesDiffMap));
        }
    }

    public void enqueueUpdateInstanceHandle(int r9, long j) {
        this.mOperations.add(new UpdateInstanceHandleOperation(r9, j));
    }

    public void enqueueUpdateProperties(int r5, String str, ReactStylesDiffMap reactStylesDiffMap) {
        this.mUpdatePropertiesOperationCount++;
        this.mOperations.add(new UpdatePropertiesOperation(r5, reactStylesDiffMap));
    }

    public void enqueueOnLayoutEvent(int r10, int r11, int r12, int r13, int r14) {
        this.mOperations.add(new EmitOnLayoutEventOperation(r10, r11, r12, r13, r14));
    }

    public void enqueueUpdateLayout(int r12, int r13, int r14, int r15, int r16, int r17) {
        this.mOperations.add(new UpdateLayoutOperation(r12, r13, r14, r15, r16, r17));
    }

    public void enqueueManageChildren(int r9, int[] r10, ViewAtIndex[] viewAtIndexArr, int[] r12) {
        this.mOperations.add(new ManageChildrenOperation(r9, r10, viewAtIndexArr, r12));
    }

    public void enqueueSetChildren(int r3, ReadableArray readableArray) {
        this.mOperations.add(new SetChildrenOperation(r3, readableArray));
    }

    public void enqueueSetLayoutAnimationEnabled(boolean z) {
        this.mOperations.add(new SetLayoutAnimationEnabledOperation(z));
    }

    public void enqueueConfigureLayoutAnimation(ReadableMap readableMap, Callback callback) {
        this.mOperations.add(new ConfigureLayoutAnimationOperation(readableMap, callback));
    }

    public void enqueueMeasure(int r4, Callback callback) {
        this.mOperations.add(new MeasureOperation(r4, callback));
    }

    public void enqueueMeasureInWindow(int r4, Callback callback) {
        this.mOperations.add(new MeasureInWindowOperation(r4, callback));
    }

    public void enqueueFindTargetForTouch(int r10, float f, float f2, Callback callback) {
        this.mOperations.add(new FindTargetForTouchOperation(r10, f, f2, callback));
    }

    public void enqueueSendAccessibilityEvent(int r4, int r5) {
        this.mOperations.add(new SendAccessibilityEvent(r4, r5));
    }

    public void enqueueLayoutUpdateFinished(ReactShadowNode reactShadowNode, UIImplementation.LayoutUpdateListener layoutUpdateListener) {
        this.mOperations.add(new LayoutUpdateFinishedOperation(reactShadowNode, layoutUpdateListener));
    }

    public void enqueueUIBlock(UIBlock uIBlock) {
        this.mOperations.add(new UIBlockOperation(uIBlock));
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mOperations.add(0, new UIBlockOperation(uIBlock));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v8 */
    public void dispatchViewUpdates(final int r20, final long j, final long j2) {
        long j3;
        final ArrayList<DispatchCommandViewOperation> arrayList;
        final ArrayList<UIOperation> arrayList2;
        final ArrayDeque<UIOperation> arrayDeque;
        SystraceMessage.beginSection(0L, "UIViewOperationQueue.dispatchViewUpdates").arg("batchId", r20).flush();
        try {
            final long uptimeMillis = SystemClock.uptimeMillis();
            final long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            j3 = 0;
            ArrayDeque<UIOperation> arrayDeque2 = null;
            if (this.mViewCommandOperations.isEmpty()) {
                arrayList = null;
            } else {
                ArrayList<DispatchCommandViewOperation> arrayList3 = this.mViewCommandOperations;
                this.mViewCommandOperations = new ArrayList<>();
                arrayList = arrayList3;
            }
            if (this.mOperations.isEmpty()) {
                arrayList2 = null;
            } else {
                ArrayList<UIOperation> arrayList4 = this.mOperations;
                this.mOperations = new ArrayList<>();
                arrayList2 = arrayList4;
            }
            synchronized (this.mNonBatchedOperationsLock) {
                try {
                    if (!this.mNonBatchedOperations.isEmpty()) {
                        arrayDeque2 = this.mNonBatchedOperations;
                        this.mNonBatchedOperations = new ArrayDeque<>();
                    }
                    arrayDeque = arrayDeque2;
                } catch (Throwable th) {
                    th = th;
                }
            }
            NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener = this.mViewHierarchyUpdateDebugListener;
            if (notThreadSafeViewHierarchyUpdateDebugListener != null) {
                notThreadSafeViewHierarchyUpdateDebugListener.onViewHierarchyUpdateEnqueued();
            }
            try {
                Runnable runnable = new Runnable() { // from class: com.facebook.react.uimanager.UIViewOperationQueue.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SystraceMessage.beginSection(0L, "DispatchUI").arg("BatchId", r20).flush();
                        try {
                            try {
                                long uptimeMillis2 = SystemClock.uptimeMillis();
                                ArrayList arrayList5 = arrayList;
                                if (arrayList5 != null) {
                                    Iterator it = arrayList5.iterator();
                                    while (it.hasNext()) {
                                        DispatchCommandViewOperation dispatchCommandViewOperation = (DispatchCommandViewOperation) it.next();
                                        try {
                                            dispatchCommandViewOperation.executeWithExceptions();
                                        } catch (RetryableMountingLayerException e) {
                                            if (dispatchCommandViewOperation.getRetries() == 0) {
                                                dispatchCommandViewOperation.incrementRetries();
                                                UIViewOperationQueue.this.mViewCommandOperations.add(dispatchCommandViewOperation);
                                            } else {
                                                ReactSoftExceptionLogger.logSoftException(UIViewOperationQueue.TAG, new ReactNoCrashSoftException(e));
                                            }
                                        } catch (Throwable th2) {
                                            ReactSoftExceptionLogger.logSoftException(UIViewOperationQueue.TAG, th2);
                                        }
                                    }
                                }
                                ArrayDeque arrayDeque3 = arrayDeque;
                                if (arrayDeque3 != null) {
                                    Iterator it2 = arrayDeque3.iterator();
                                    while (it2.hasNext()) {
                                        ((UIOperation) it2.next()).execute();
                                    }
                                }
                                ArrayList arrayList6 = arrayList2;
                                if (arrayList6 != null) {
                                    Iterator it3 = arrayList6.iterator();
                                    while (it3.hasNext()) {
                                        ((UIOperation) it3.next()).execute();
                                    }
                                }
                                if (UIViewOperationQueue.this.mIsProfilingNextBatch && UIViewOperationQueue.this.mProfiledBatchCommitStartTime == 0) {
                                    UIViewOperationQueue.this.mProfiledBatchCommitStartTime = j;
                                    UIViewOperationQueue.this.mProfiledBatchCommitEndTime = SystemClock.uptimeMillis();
                                    UIViewOperationQueue.this.mProfiledBatchLayoutTime = j2;
                                    UIViewOperationQueue.this.mProfiledBatchDispatchViewUpdatesTime = uptimeMillis;
                                    UIViewOperationQueue.this.mProfiledBatchRunStartTime = uptimeMillis2;
                                    UIViewOperationQueue uIViewOperationQueue = UIViewOperationQueue.this;
                                    uIViewOperationQueue.mProfiledBatchRunEndTime = uIViewOperationQueue.mProfiledBatchCommitEndTime;
                                    UIViewOperationQueue.this.mThreadCpuTime = currentThreadTimeMillis;
                                    Systrace.beginAsyncSection(0L, "delayBeforeDispatchViewUpdates", 0, UIViewOperationQueue.this.mProfiledBatchCommitStartTime * 1000000);
                                    Systrace.endAsyncSection(0L, "delayBeforeDispatchViewUpdates", 0, UIViewOperationQueue.this.mProfiledBatchDispatchViewUpdatesTime * 1000000);
                                    Systrace.beginAsyncSection(0L, "delayBeforeBatchRunStart", 0, UIViewOperationQueue.this.mProfiledBatchDispatchViewUpdatesTime * 1000000);
                                    Systrace.endAsyncSection(0L, "delayBeforeBatchRunStart", 0, UIViewOperationQueue.this.mProfiledBatchRunStartTime * 1000000);
                                }
                                UIViewOperationQueue.this.mNativeViewHierarchyManager.clearLayoutAnimation();
                                if (UIViewOperationQueue.this.mViewHierarchyUpdateDebugListener != null) {
                                    UIViewOperationQueue.this.mViewHierarchyUpdateDebugListener.onViewHierarchyUpdateFinished();
                                }
                            } catch (Exception e2) {
                                UIViewOperationQueue.this.mIsInIllegalUIState = true;
                                throw e2;
                            }
                        } finally {
                            Systrace.endSection(0L);
                        }
                    }
                };
                j3 = 0;
                SystraceMessage.beginSection(0L, "acquiring mDispatchRunnablesLock").arg("batchId", r20).flush();
                synchronized (this.mDispatchRunnablesLock) {
                    Systrace.endSection(0L);
                    this.mDispatchUIRunnables.add(runnable);
                }
                if (!this.mIsDispatchUIFrameCallbackEnqueued) {
                    UiThreadUtil.runOnUiThread(new GuardedRunnable(this.mReactApplicationContext) { // from class: com.facebook.react.uimanager.UIViewOperationQueue.2
                        @Override // com.facebook.react.bridge.GuardedRunnable
                        public void runGuarded() {
                            UIViewOperationQueue.this.flushPendingBatches();
                        }
                    });
                }
                Systrace.endSection(0L);
            } catch (Throwable th2) {
                th = th2;
                j3 = 0;
                Systrace.endSection(j3);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            j3 = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resumeFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = true;
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pauseFrameCallback() {
        this.mIsDispatchUIFrameCallbackEnqueued = false;
        ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
        flushPendingBatches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flushPendingBatches() {
        if (this.mIsInIllegalUIState) {
            FLog.m1288w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
            return;
        }
        synchronized (this.mDispatchRunnablesLock) {
            if (this.mDispatchUIRunnables.isEmpty()) {
                return;
            }
            ArrayList<Runnable> arrayList = this.mDispatchUIRunnables;
            this.mDispatchUIRunnables = new ArrayList<>();
            long uptimeMillis = SystemClock.uptimeMillis();
            Iterator<Runnable> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
            if (this.mIsProfilingNextBatch) {
                this.mProfiledBatchBatchedExecutionTime = SystemClock.uptimeMillis() - uptimeMillis;
                this.mProfiledBatchNonBatchedExecutionTime = this.mNonBatchedExecutionTotalTime;
                this.mIsProfilingNextBatch = false;
                Systrace.beginAsyncSection(0L, "batchedExecutionTime", 0, 1000000 * uptimeMillis);
                Systrace.endAsyncSection(0L, "batchedExecutionTime", 0);
            }
            this.mNonBatchedExecutionTotalTime = 0L;
        }
    }

    /* loaded from: classes.dex */
    private class DispatchUIFrameCallback extends GuardedFrameCallback {
        private static final int FRAME_TIME_MS = 16;
        private final int mMinTimeLeftInFrameForNonBatchedOperationMs;

        private DispatchUIFrameCallback(ReactContext reactContext, int r3) {
            super(reactContext);
            this.mMinTimeLeftInFrameForNonBatchedOperationMs = r3;
        }

        @Override // com.facebook.react.uimanager.GuardedFrameCallback
        public void doFrameGuarded(long j) {
            if (UIViewOperationQueue.this.mIsInIllegalUIState) {
                FLog.m1288w(ReactConstants.TAG, "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            Systrace.beginSection(0L, "dispatchNonBatchedUIOperations");
            try {
                dispatchPendingNonBatchedOperations(j);
                Systrace.endSection(0L);
                UIViewOperationQueue.this.flushPendingBatches();
                ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this);
            } catch (Throwable th) {
                Systrace.endSection(0L);
                throw th;
            }
        }

        private void dispatchPendingNonBatchedOperations(long j) {
            UIOperation uIOperation;
            while (16 - ((System.nanoTime() - j) / 1000000) >= this.mMinTimeLeftInFrameForNonBatchedOperationMs) {
                synchronized (UIViewOperationQueue.this.mNonBatchedOperationsLock) {
                    if (UIViewOperationQueue.this.mNonBatchedOperations.isEmpty()) {
                        return;
                    }
                    uIOperation = (UIOperation) UIViewOperationQueue.this.mNonBatchedOperations.pollFirst();
                }
                try {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    uIOperation.execute();
                    UIViewOperationQueue.access$2914(UIViewOperationQueue.this, SystemClock.uptimeMillis() - uptimeMillis);
                } catch (Exception e) {
                    UIViewOperationQueue.this.mIsInIllegalUIState = true;
                    throw e;
                }
            }
        }
    }
}
