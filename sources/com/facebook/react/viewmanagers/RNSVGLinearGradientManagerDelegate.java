package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGLinearGradientManagerInterface;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class RNSVGLinearGradientManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGLinearGradientManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGLinearGradientManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
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
            case 3769:
                if (str.equals("x1")) {
                    c = 6;
                    break;
                }
                break;
            case 3770:
                if (str.equals("x2")) {
                    c = 7;
                    break;
                }
                break;
            case 3800:
                if (str.equals("y1")) {
                    c = '\b';
                    break;
                }
                break;
            case 3801:
                if (str.equals("y2")) {
                    c = '\t';
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = '\n';
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 11;
                    break;
                }
                break;
            case 89650992:
                if (str.equals("gradient")) {
                    c = '\f';
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 14;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = 15;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = 16;
                    break;
                }
                break;
            case 1822665244:
                if (str.equals("gradientTransform")) {
                    c = 17;
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = 18;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setGradientUnits(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 1:
                this.mViewManager.setOpacity(t, obj == null ? 1.0f : ((Double) obj).floatValue());
                return;
            case 2:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                return;
            case 3:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                return;
            case 4:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                return;
            case 5:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case 6:
                if (obj instanceof String) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setX1((RNSVGLinearGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setX1((RNSVGLinearGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setX1((RNSVGLinearGradientManagerInterface) t, (Double) null);
                    return;
                }
            case 7:
                if (obj instanceof String) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setX2((RNSVGLinearGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setX2((RNSVGLinearGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setX2((RNSVGLinearGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\b':
                if (obj instanceof String) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setY1((RNSVGLinearGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setY1((RNSVGLinearGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setY1((RNSVGLinearGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\t':
                if (obj instanceof String) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setY2((RNSVGLinearGradientManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setY2((RNSVGLinearGradientManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLinearGradientManagerInterface) this.mViewManager).setY2((RNSVGLinearGradientManagerInterface) t, (Double) null);
                    return;
                }
            case '\n':
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                return;
            case 11:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                return;
            case '\f':
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setGradient(t, (ReadableArray) obj);
                return;
            case '\r':
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                return;
            case 14:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                return;
            case 15:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 16:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                return;
            case 17:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setGradientTransform(t, (ReadableArray) obj);
                return;
            case 18:
                ((RNSVGLinearGradientManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
