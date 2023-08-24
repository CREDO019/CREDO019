package com.facebook.react.uimanager.layoutanimation;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;

/* loaded from: classes.dex */
public class LayoutAnimationController {
    private static Handler sCompletionHandler;
    private Runnable mCompletionRunnable;
    private boolean mShouldAnimateLayout;
    private final AbstractLayoutAnimation mLayoutCreateAnimation = new LayoutCreateAnimation();
    private final AbstractLayoutAnimation mLayoutUpdateAnimation = new LayoutUpdateAnimation();
    private final AbstractLayoutAnimation mLayoutDeleteAnimation = new LayoutDeleteAnimation();
    private final SparseArray<LayoutHandlingAnimation> mLayoutHandlers = new SparseArray<>(0);
    private long mMaxAnimationDuration = -1;

    public void initializeFromConfig(ReadableMap readableMap, final Callback callback) {
        if (readableMap == null) {
            reset();
            return;
        }
        this.mShouldAnimateLayout = false;
        int r0 = readableMap.hasKey("duration") ? readableMap.getInt("duration") : 0;
        if (readableMap.hasKey(LayoutAnimationType.toString(LayoutAnimationType.CREATE))) {
            this.mLayoutCreateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(LayoutAnimationType.CREATE)), r0);
            this.mShouldAnimateLayout = true;
        }
        if (readableMap.hasKey(LayoutAnimationType.toString(LayoutAnimationType.UPDATE))) {
            this.mLayoutUpdateAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(LayoutAnimationType.UPDATE)), r0);
            this.mShouldAnimateLayout = true;
        }
        if (readableMap.hasKey(LayoutAnimationType.toString(LayoutAnimationType.DELETE))) {
            this.mLayoutDeleteAnimation.initializeFromConfig(readableMap.getMap(LayoutAnimationType.toString(LayoutAnimationType.DELETE)), r0);
            this.mShouldAnimateLayout = true;
        }
        if (!this.mShouldAnimateLayout || callback == null) {
            return;
        }
        this.mCompletionRunnable = new Runnable() { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.1
            @Override // java.lang.Runnable
            public void run() {
                callback.invoke(Boolean.TRUE);
            }
        };
    }

    public void reset() {
        this.mLayoutCreateAnimation.reset();
        this.mLayoutUpdateAnimation.reset();
        this.mLayoutDeleteAnimation.reset();
        this.mCompletionRunnable = null;
        this.mShouldAnimateLayout = false;
        this.mMaxAnimationDuration = -1L;
    }

    public boolean shouldAnimateLayout(View view) {
        if (view == null) {
            return false;
        }
        return (this.mShouldAnimateLayout && view.getParent() != null) || this.mLayoutHandlers.get(view.getId()) != null;
    }

    public void applyLayoutUpdate(View view, int r10, int r11, int r12, int r13) {
        AbstractLayoutAnimation abstractLayoutAnimation;
        UiThreadUtil.assertOnUiThread();
        final int id = view.getId();
        LayoutHandlingAnimation layoutHandlingAnimation = this.mLayoutHandlers.get(id);
        if (layoutHandlingAnimation != null) {
            layoutHandlingAnimation.onLayoutUpdate(r10, r11, r12, r13);
            return;
        }
        if (view.getWidth() == 0 || view.getHeight() == 0) {
            abstractLayoutAnimation = this.mLayoutCreateAnimation;
        } else {
            abstractLayoutAnimation = this.mLayoutUpdateAnimation;
        }
        Animation createAnimation = abstractLayoutAnimation.createAnimation(view, r10, r11, r12, r13);
        if (createAnimation instanceof LayoutHandlingAnimation) {
            createAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.put(id, (LayoutHandlingAnimation) animation);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LayoutAnimationController.this.mLayoutHandlers.remove(id);
                }
            });
        } else {
            view.layout(r10, r11, r12 + r10, r13 + r11);
        }
        if (createAnimation != null) {
            long duration = createAnimation.getDuration();
            if (duration > this.mMaxAnimationDuration) {
                this.mMaxAnimationDuration = duration;
                scheduleCompletionCallback(duration);
            }
            view.startAnimation(createAnimation);
        }
    }

    public void deleteView(View view, final LayoutAnimationListener layoutAnimationListener) {
        UiThreadUtil.assertOnUiThread();
        Animation createAnimation = this.mLayoutDeleteAnimation.createAnimation(view, view.getLeft(), view.getTop(), view.getWidth(), view.getHeight());
        if (createAnimation != null) {
            disableUserInteractions(view);
            createAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.facebook.react.uimanager.layoutanimation.LayoutAnimationController.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    layoutAnimationListener.onAnimationEnd();
                }
            });
            long duration = createAnimation.getDuration();
            if (duration > this.mMaxAnimationDuration) {
                scheduleCompletionCallback(duration);
                this.mMaxAnimationDuration = duration;
            }
            view.startAnimation(createAnimation);
            return;
        }
        layoutAnimationListener.onAnimationEnd();
    }

    private void disableUserInteractions(View view) {
        view.setClickable(false);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int r0 = 0; r0 < viewGroup.getChildCount(); r0++) {
                disableUserInteractions(viewGroup.getChildAt(r0));
            }
        }
    }

    private void scheduleCompletionCallback(long j) {
        if (sCompletionHandler == null) {
            sCompletionHandler = new Handler(Looper.getMainLooper());
        }
        Runnable runnable = this.mCompletionRunnable;
        if (runnable != null) {
            sCompletionHandler.removeCallbacks(runnable);
            sCompletionHandler.postDelayed(this.mCompletionRunnable, j);
        }
    }
}
