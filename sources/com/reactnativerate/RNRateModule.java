package com.reactnativerate;

import android.app.Activity;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;

@ReactModule(name = RNRateModule.NAME)
/* loaded from: classes3.dex */
public class RNRateModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNRate";
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public RNRateModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void rate(ReadableMap readableMap, final Callback callback) {
        final ReviewManager create = ReviewManagerFactory.create(this.reactContext);
        create.requestReviewFlow().addOnCompleteListener(new OnCompleteListener<ReviewInfo>() { // from class: com.reactnativerate.RNRateModule.1
            @Override // com.google.android.play.core.tasks.OnCompleteListener
            public void onComplete(final Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    ReviewInfo result = task.getResult();
                    Activity currentActivity = RNRateModule.this.getCurrentActivity();
                    if (currentActivity == null) {
                        callback.invoke(false, "getCurrentActivity() unsuccessful");
                        return;
                    } else {
                        create.launchReviewFlow(currentActivity, result).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.reactnativerate.RNRateModule.1.1
                            @Override // com.google.android.play.core.tasks.OnCompleteListener
                            public void onComplete(Task<Void> task2) {
                                if (task.isSuccessful()) {
                                    callback.invoke(true);
                                } else {
                                    callback.invoke(false, "launchReviewFlow() unsuccessful");
                                }
                            }
                        });
                        return;
                    }
                }
                callback.invoke(false, "requestReviewFlow() unsuccessful");
            }
        });
    }
}
