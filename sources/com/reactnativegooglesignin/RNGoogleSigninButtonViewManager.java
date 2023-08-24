package com.reactnativegooglesignin;

import android.view.View;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.common.SignInButton;

/* loaded from: classes3.dex */
public class RNGoogleSigninButtonViewManager extends SimpleViewManager<SignInButton> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNGoogleSigninButton";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public SignInButton createViewInstance(final ThemedReactContext themedReactContext) {
        SignInButton signInButton = new SignInButton(themedReactContext);
        signInButton.setSize(0);
        signInButton.setColorScheme(2);
        signInButton.setOnClickListener(new View.OnClickListener() { // from class: com.reactnativegooglesignin.RNGoogleSigninButtonViewManager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) themedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNGoogleSigninButtonClicked", null);
            }
        });
        return signInButton;
    }

    @ReactProp(name = "size")
    public void setSize(SignInButton signInButton, int r2) {
        signInButton.setSize(r2);
    }

    @ReactProp(name = "color")
    public void setColor(SignInButton signInButton, int r2) {
        signInButton.setColorScheme(r2);
    }

    @ReactProp(name = "disabled")
    public void setDisabled(SignInButton signInButton, boolean z) {
        signInButton.setEnabled(!z);
    }
}
