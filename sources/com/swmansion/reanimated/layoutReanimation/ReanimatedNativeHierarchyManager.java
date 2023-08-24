package com.swmansion.reanimated.layoutReanimation;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.ViewAtIndex;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.layoutanimation.LayoutAnimationController;
import com.swmansion.rnscreens.ScreenStackViewManager;
import com.swmansion.rnscreens.ScreenViewManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes3.dex */
public class ReanimatedNativeHierarchyManager extends NativeViewHierarchyManager {
    private final HashMap<Integer, Runnable> cleanerCallback;
    private boolean initOk;
    private HashMap<Integer, Set<Integer>> mPendingDeletionsForTag;
    private LayoutAnimationController mReaLayoutAnimator;
    private final HashMap<Integer, ArrayList<View>> toBeRemoved;

    public ReanimatedNativeHierarchyManager(ViewManagerRegistry viewManagerRegistry, ReactApplicationContext reactApplicationContext) {
        super(viewManagerRegistry);
        this.toBeRemoved = new HashMap<>();
        this.cleanerCallback = new HashMap<>();
        this.mReaLayoutAnimator = null;
        this.mPendingDeletionsForTag = new HashMap<>();
        this.initOk = true;
        this.mReaLayoutAnimator = new ReaLayoutAnimator(reactApplicationContext, this);
        Class<? super Object> superclass = getClass().getSuperclass();
        if (superclass == null) {
            Log.e("reanimated", "unable to resolve super class of ReanimatedNativeHierarchyManager");
            return;
        }
        try {
            Field declaredField = superclass.getDeclaredField("mLayoutAnimator");
            declaredField.setAccessible(true);
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Field declaredField2 = Field.class.getDeclaredField("accessFlags");
                    declaredField2.setAccessible(true);
                    declaredField2.setInt(declaredField, declaredField.getModifiers() & (-17));
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            declaredField.set(this, this.mReaLayoutAnimator);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            this.initOk = false;
            e2.printStackTrace();
        }
        try {
            Field declaredField3 = superclass.getDeclaredField("mPendingDeletionsForTag");
            declaredField3.setAccessible(true);
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    Field declaredField4 = Field.class.getDeclaredField("accessFlags");
                    declaredField4.setAccessible(true);
                    declaredField4.setInt(declaredField3, declaredField3.getModifiers() & (-17));
                } catch (IllegalAccessException | NoSuchFieldException e3) {
                    e3.printStackTrace();
                }
            }
            declaredField3.set(this, this.mPendingDeletionsForTag);
        } catch (IllegalAccessException | NoSuchFieldException e4) {
            this.initOk = false;
            e4.printStackTrace();
        }
        if (this.initOk) {
            setLayoutAnimationEnabled(true);
        }
    }

    public ReanimatedNativeHierarchyManager(ViewManagerRegistry viewManagerRegistry, RootViewManager rootViewManager) {
        super(viewManagerRegistry, rootViewManager);
        this.toBeRemoved = new HashMap<>();
        this.cleanerCallback = new HashMap<>();
        this.mReaLayoutAnimator = null;
        this.mPendingDeletionsForTag = new HashMap<>();
        this.initOk = true;
    }

    private boolean isLayoutAnimationDisabled() {
        return (this.initOk && ((ReaLayoutAnimator) this.mReaLayoutAnimator).isLayoutAnimationEnabled()) ? false : true;
    }

    @Override // com.facebook.react.uimanager.NativeViewHierarchyManager
    public synchronized void updateLayout(int r7, int r8, int r9, int r10, int r11, int r12) {
        LayoutAnimationController layoutAnimationController;
        super.updateLayout(r7, r8, r9, r10, r11, r12);
        if (isLayoutAnimationDisabled()) {
            return;
        }
        try {
            View resolveView = resolveView(r8);
            String name = resolveViewManager(r8).getName();
            View resolveView2 = resolveView(r7);
            if (resolveView2 != null && name.equals(ScreenViewManager.REACT_CLASS) && (layoutAnimationController = this.mReaLayoutAnimator) != null) {
                layoutAnimationController.applyLayoutUpdate(resolveView, (int) resolveView2.getX(), (int) resolveView2.getY(), resolveView2.getWidth(), resolveView2.getHeight());
            }
        } catch (IllegalViewOperationException e) {
            e.printStackTrace();
        }
    }

    @Override // com.facebook.react.uimanager.NativeViewHierarchyManager
    public synchronized void manageChildren(int r18, int[] r19, ViewAtIndex[] viewAtIndexArr, int[] r21) {
        Set<Integer> set;
        ArrayList<View> arrayList;
        if (isLayoutAnimationDisabled()) {
            super.manageChildren(r18, r19, viewAtIndexArr, r21);
            return;
        }
        try {
            final ViewGroup viewGroup = (ViewGroup) resolveView(r18);
            final ViewGroupManager viewGroupManager = (ViewGroupManager) resolveViewManager(r18);
            if (viewGroupManager.getName().equals(ScreenStackViewManager.REACT_CLASS)) {
                super.manageChildren(r18, r19, viewAtIndexArr, r21);
                return;
            }
            if (this.toBeRemoved.containsKey(Integer.valueOf(r18))) {
                HashSet hashSet = new HashSet();
                Iterator<View> it = this.toBeRemoved.get(Integer.valueOf(r18)).iterator();
                while (it.hasNext()) {
                    hashSet.add(Integer.valueOf(it.next().getId()));
                }
                while (viewGroupManager.getChildCount(viewGroup) != 0 && hashSet.contains(Integer.valueOf(viewGroupManager.getChildAt(viewGroup, viewGroupManager.getChildCount(viewGroup) - 1).getId()))) {
                    viewGroupManager.removeViewAt(viewGroup, viewGroupManager.getChildCount(viewGroup) - 1);
                }
            }
            if (r21 != null) {
                if (!this.toBeRemoved.containsKey(Integer.valueOf(r18))) {
                    this.toBeRemoved.put(Integer.valueOf(r18), new ArrayList<>());
                }
                ArrayList<View> arrayList2 = this.toBeRemoved.get(Integer.valueOf(r18));
                int length = r21.length;
                int r14 = 0;
                while (r14 < length) {
                    try {
                        final View resolveView = resolveView(Integer.valueOf(r21[r14]).intValue());
                        arrayList2.add(resolveView);
                        final ArrayList<View> arrayList3 = arrayList2;
                        arrayList = arrayList2;
                        this.cleanerCallback.put(Integer.valueOf(resolveView.getId()), new Runnable() { // from class: com.swmansion.reanimated.layoutReanimation.ReanimatedNativeHierarchyManager.1
                            @Override // java.lang.Runnable
                            public void run() {
                                arrayList3.remove(resolveView);
                                viewGroupManager.removeView(viewGroup, resolveView);
                            }
                        });
                    } catch (IllegalViewOperationException e) {
                        arrayList = arrayList2;
                        e.printStackTrace();
                    }
                    r14++;
                    arrayList2 = arrayList;
                }
            }
            HashMap<Integer, Set<Integer>> hashMap = this.mPendingDeletionsForTag;
            if (hashMap != null && (set = hashMap.get(Integer.valueOf(r18))) != null) {
                set.clear();
            }
            super.manageChildren(r18, r19, viewAtIndexArr, null);
            if (this.toBeRemoved.containsKey(Integer.valueOf(r18))) {
                Iterator<View> it2 = this.toBeRemoved.get(Integer.valueOf(r18)).iterator();
                while (it2.hasNext()) {
                    viewGroupManager.addView(viewGroup, it2.next(), viewGroupManager.getChildCount(viewGroup));
                }
            }
            super.manageChildren(r18, null, null, r21);
        } catch (IllegalViewOperationException e2) {
            e2.printStackTrace();
            super.manageChildren(r18, r19, viewAtIndexArr, r21);
        }
    }

    public void publicDropView(View view) {
        dropView(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.NativeViewHierarchyManager
    public synchronized void dropView(View view) {
        if (isLayoutAnimationDisabled()) {
            super.dropView(view);
            return;
        }
        if (this.toBeRemoved.containsKey(Integer.valueOf(view.getId()))) {
            this.toBeRemoved.remove(Integer.valueOf(view.getId()));
        }
        if (this.cleanerCallback.containsKey(Integer.valueOf(view.getId()))) {
            this.cleanerCallback.remove(Integer.valueOf(view.getId()));
            this.cleanerCallback.get(Integer.valueOf(view.getId())).run();
        }
        super.dropView(view);
    }
}
