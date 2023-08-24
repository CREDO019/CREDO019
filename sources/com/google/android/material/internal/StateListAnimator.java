package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public final class StateListAnimator {
    private final ArrayList<Tuple> tuples = new ArrayList<>();
    private Tuple lastMatch = null;
    ValueAnimator runningAnimator = null;
    private final Animator.AnimatorListener animationListener = new AnimatorListenerAdapter() { // from class: com.google.android.material.internal.StateListAnimator.1
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (StateListAnimator.this.runningAnimator == animator) {
                StateListAnimator.this.runningAnimator = null;
            }
        }
    };

    public void addState(int[] r2, ValueAnimator valueAnimator) {
        Tuple tuple = new Tuple(r2, valueAnimator);
        valueAnimator.addListener(this.animationListener);
        this.tuples.add(tuple);
    }

    public void setState(int[] r5) {
        Tuple tuple;
        int size = this.tuples.size();
        int r1 = 0;
        while (true) {
            if (r1 >= size) {
                tuple = null;
                break;
            }
            tuple = this.tuples.get(r1);
            if (StateSet.stateSetMatches(tuple.specs, r5)) {
                break;
            }
            r1++;
        }
        Tuple tuple2 = this.lastMatch;
        if (tuple == tuple2) {
            return;
        }
        if (tuple2 != null) {
            cancel();
        }
        this.lastMatch = tuple;
        if (tuple != null) {
            start(tuple);
        }
    }

    private void start(Tuple tuple) {
        ValueAnimator valueAnimator = tuple.animator;
        this.runningAnimator = valueAnimator;
        valueAnimator.start();
    }

    private void cancel() {
        ValueAnimator valueAnimator = this.runningAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.runningAnimator = null;
        }
    }

    public void jumpToCurrentState() {
        ValueAnimator valueAnimator = this.runningAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.runningAnimator = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Tuple {
        final ValueAnimator animator;
        final int[] specs;

        Tuple(int[] r1, ValueAnimator valueAnimator) {
            this.specs = r1;
            this.animator = valueAnimator;
        }
    }
}