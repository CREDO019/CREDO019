package com.facebook.fresco.p008ui.common;

import android.util.Log;
import com.facebook.fresco.p008ui.common.ControllerListener2;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: com.facebook.fresco.ui.common.ForwardingControllerListener2 */
/* loaded from: classes.dex */
public class ForwardingControllerListener2<I> extends BaseControllerListener2<I> {
    private static final String TAG = "FwdControllerListener2";
    private final List<ControllerListener2<I>> mListeners = new ArrayList(2);

    public synchronized void addListener(ControllerListener2<I> listener) {
        this.mListeners.add(listener);
    }

    public synchronized void removeListener(ControllerListener2<I> listener) {
        int indexOf = this.mListeners.indexOf(listener);
        if (indexOf != -1) {
            this.mListeners.remove(indexOf);
        }
    }

    public synchronized void removeAllListeners() {
        this.mListeners.clear();
    }

    private synchronized void onException(String message, Throwable t) {
        Log.e(TAG, message, t);
    }

    @Override // com.facebook.fresco.p008ui.common.BaseControllerListener2, com.facebook.fresco.p008ui.common.ControllerListener2
    public void onSubmit(String id, @Nullable Object callerContext, @Nullable ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(r1);
                if (controllerListener2 != null) {
                    controllerListener2.onSubmit(id, callerContext, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onSubmit", e);
            }
        }
    }

    @Override // com.facebook.fresco.p008ui.common.BaseControllerListener2, com.facebook.fresco.p008ui.common.ControllerListener2
    public void onFinalImageSet(String id, @Nullable I imageInfo, @Nullable ControllerListener2.Extras extraData) {
        int size = this.mListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(r1);
                if (controllerListener2 != null) {
                    controllerListener2.onFinalImageSet(id, imageInfo, extraData);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onFinalImageSet", e);
            }
        }
    }

    @Override // com.facebook.fresco.p008ui.common.BaseControllerListener2, com.facebook.fresco.p008ui.common.ControllerListener2
    public void onFailure(String id, @Nullable Throwable throwable, @Nullable ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(r1);
                if (controllerListener2 != null) {
                    controllerListener2.onFailure(id, throwable, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onFailure", e);
            }
        }
    }

    @Override // com.facebook.fresco.p008ui.common.BaseControllerListener2, com.facebook.fresco.p008ui.common.ControllerListener2
    public void onRelease(String id, @Nullable ControllerListener2.Extras extras) {
        int size = this.mListeners.size();
        for (int r1 = 0; r1 < size; r1++) {
            try {
                ControllerListener2<I> controllerListener2 = this.mListeners.get(r1);
                if (controllerListener2 != null) {
                    controllerListener2.onRelease(id, extras);
                }
            } catch (Exception e) {
                onException("ForwardingControllerListener2 exception in onRelease", e);
            }
        }
    }
}
