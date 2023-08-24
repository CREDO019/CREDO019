package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGRadialGradientManagerInterface;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class RNSVGRadialGradientManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGRadialGradientManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGRadialGradientManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1932235233:
                if (str.equals("gradientUnits")) {
                    c = 0;
                    break;
                }
                break;
            case -1267206133:
                if (str.equals(ViewProps.OPACITY)) {
                    c = 1;
                    break;
                }
                break;
            case -1081239615:
                if (str.equals("matrix")) {
                    c = 2;
                    break;
                }
                break;
            case -933864895:
                if (str.equals("markerEnd")) {
                    c = 3;
                    break;
                }
                break;
            case -933857362:
                if (str.equals("markerMid")) {
                    c = 4;
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = 5;
                    break;
                }
                break;
            case 3189:
                if (str.equals("cx")) {
                    c = 6;
                    break;
                }
                break;
            case 3190:
                if (str.equals("cy")) {
                    c = 7;
                    break;
                }
                break;
            case 3282:
                if (str.equals("fx")) {
                    c = '\b';
                    break;
                }
                break;
            case 3283:
                if (str.equals("fy")) {
                    c = '\t';
                    break;
                }
                break;
            case 3654:
                if (str.equals("rx")) {
                    c = '\n';
                    break;
                }
                break;
            case 3655:
                if (str.equals("ry")) {
                    c = 11;
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = '\f';
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case 89650992:
                if (str.equals("gradient")) {
                    c = 14;
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = 15;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 16;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = 17;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = 18;
                    break;
                }
                break;
            case 1822665244:
                if (str.equals("gradientTransform")) {
                    c = 19;
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = 20;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setGradientUnits(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 1:
                this.mViewManager.setOpacity(t, obj == null ? 1.0f : ((Double) obj).floatValue());
                return;
            case 2:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                return;
            case 3:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                return;
            case 4:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                return;
            case 5:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case 6:
                if (obj instanceof String) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setCx((RNSVGRadialGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setCx((RNSVGRadialGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setCx((RNSVGRadialGradientManagerInterface) t, (Double) null);
                    return;
                }
            case 7:
                if (obj instanceof String) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setCy((RNSVGRadialGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setCy((RNSVGRadialGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setCy((RNSVGRadialGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\b':
                if (obj instanceof String) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setFx((RNSVGRadialGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setFx((RNSVGRadialGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setFx((RNSVGRadialGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\t':
                if (obj instanceof String) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setFy((RNSVGRadialGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setFy((RNSVGRadialGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setFy((RNSVGRadialGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\n':
                if (obj instanceof String) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setRx((RNSVGRadialGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setRx((RNSVGRadialGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setRx((RNSVGRadialGradientManagerInterface) t, (Double) null);
                    return;
                }
            case 11:
                if (obj instanceof String) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setRy((RNSVGRadialGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setRy((RNSVGRadialGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGRadialGradientManagerInterface) this.mViewManager).setRy((RNSVGRadialGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\f':
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                return;
            case '\r':
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                return;
            case 14:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setGradient(t, (ReadableArray) obj);
                return;
            case 15:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                return;
            case 16:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                return;
            case 17:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 18:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                return;
            case 19:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setGradientTransform(t, (ReadableArray) obj);
                return;
            case 20:
                ((RNSVGRadialGradientManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
