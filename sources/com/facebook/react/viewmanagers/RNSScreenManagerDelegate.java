package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSScreenManagerInterface;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class RNSScreenManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSScreenManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSScreenManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1937389126:
                if (str.equals("homeIndicatorHidden")) {
                    c = 0;
                    break;
                }
                break;
            case -1853558344:
                if (str.equals("gestureEnabled")) {
                    c = 1;
                    break;
                }
                break;
            case -1734097646:
                if (str.equals("hideKeyboardOnSwipe")) {
                    c = 2;
                    break;
                }
                break;
            case -1322084375:
                if (str.equals("navigationBarHidden")) {
                    c = 3;
                    break;
                }
                break;
            case -1156137512:
                if (str.equals("statusBarTranslucent")) {
                    c = 4;
                    break;
                }
                break;
            case -1150711358:
                if (str.equals("stackPresentation")) {
                    c = 5;
                    break;
                }
                break;
            case -1047235902:
                if (str.equals("activityState")) {
                    c = 6;
                    break;
                }
                break;
            case -973702878:
                if (str.equals("statusBarColor")) {
                    c = 7;
                    break;
                }
                break;
            case -958765200:
                if (str.equals("statusBarStyle")) {
                    c = '\b';
                    break;
                }
                break;
            case -577711652:
                if (str.equals("stackAnimation")) {
                    c = '\t';
                    break;
                }
                break;
            case -462720700:
                if (str.equals("navigationBarColor")) {
                    c = '\n';
                    break;
                }
                break;
            case -257141968:
                if (str.equals("replaceAnimation")) {
                    c = 11;
                    break;
                }
                break;
            case -166356101:
                if (str.equals("preventNativeDismiss")) {
                    c = '\f';
                    break;
                }
                break;
            case 17337291:
                if (str.equals("statusBarHidden")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case 129956386:
                if (str.equals("fullScreenSwipeEnabled")) {
                    c = 14;
                    break;
                }
                break;
            case 187703999:
                if (str.equals("gestureResponseDistance")) {
                    c = 15;
                    break;
                }
                break;
            case 227582404:
                if (str.equals("screenOrientation")) {
                    c = 16;
                    break;
                }
                break;
            case 425064969:
                if (str.equals("transitionDuration")) {
                    c = 17;
                    break;
                }
                break;
            case 1082157413:
                if (str.equals("swipeDirection")) {
                    c = 18;
                    break;
                }
                break;
            case 1110843912:
                if (str.equals("customAnimationOnSwipe")) {
                    c = 19;
                    break;
                }
                break;
            case 1387359683:
                if (str.equals("statusBarAnimation")) {
                    c = 20;
                    break;
                }
                break;
            case 1729091548:
                if (str.equals("nativeBackButtonDismissalEnabled")) {
                    c = 21;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSScreenManagerInterface) this.mViewManager).setHomeIndicatorHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 1:
                ((RNSScreenManagerInterface) this.mViewManager).setGestureEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : true);
                return;
            case 2:
                ((RNSScreenManagerInterface) this.mViewManager).setHideKeyboardOnSwipe(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 3:
                ((RNSScreenManagerInterface) this.mViewManager).setNavigationBarHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 4:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarTranslucent(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 5:
                ((RNSScreenManagerInterface) this.mViewManager).setStackPresentation(t, (String) obj);
                return;
            case 6:
                ((RNSScreenManagerInterface) this.mViewManager).setActivityState(t, obj == null ? -1.0f : ((Double) obj).floatValue());
                return;
            case 7:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '\b':
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarStyle(t, obj != null ? (String) obj : null);
                return;
            case '\t':
                ((RNSScreenManagerInterface) this.mViewManager).setStackAnimation(t, (String) obj);
                return;
            case '\n':
                ((RNSScreenManagerInterface) this.mViewManager).setNavigationBarColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 11:
                ((RNSScreenManagerInterface) this.mViewManager).setReplaceAnimation(t, (String) obj);
                return;
            case '\f':
                ((RNSScreenManagerInterface) this.mViewManager).setPreventNativeDismiss(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '\r':
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarHidden(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 14:
                ((RNSScreenManagerInterface) this.mViewManager).setFullScreenSwipeEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 15:
                ((RNSScreenManagerInterface) this.mViewManager).setGestureResponseDistance(t, (ReadableMap) obj);
                return;
            case 16:
                ((RNSScreenManagerInterface) this.mViewManager).setScreenOrientation(t, obj != null ? (String) obj : null);
                return;
            case 17:
                ((RNSScreenManagerInterface) this.mViewManager).setTransitionDuration(t, obj == null ? 350 : ((Double) obj).intValue());
                return;
            case 18:
                ((RNSScreenManagerInterface) this.mViewManager).setSwipeDirection(t, (String) obj);
                return;
            case 19:
                ((RNSScreenManagerInterface) this.mViewManager).setCustomAnimationOnSwipe(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 20:
                ((RNSScreenManagerInterface) this.mViewManager).setStatusBarAnimation(t, obj != null ? (String) obj : null);
                return;
            case 21:
                ((RNSScreenManagerInterface) this.mViewManager).setNativeBackButtonDismissalEnabled(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
