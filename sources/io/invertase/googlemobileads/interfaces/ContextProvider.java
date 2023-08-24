package io.invertase.googlemobileads.interfaces;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.ReactContext;

/* loaded from: classes5.dex */
public interface ContextProvider {
    Activity getActivity();

    Context getApplicationContext();

    ReactContext getContext();
}
