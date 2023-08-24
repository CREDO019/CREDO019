package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGTextManagerInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import kotlin.text.Typography;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class RNSVGTextManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGTextManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGTextManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1603134955:
                if (str.equals("lengthAdjust")) {
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
            case -1171891896:
                if (str.equals("alignmentBaseline")) {
                    c = 2;
                    break;
                }
                break;
            case -1139902161:
                if (str.equals("verticalAlign")) {
                    c = 3;
                    break;
                }
                break;
            case -1081239615:
                if (str.equals("matrix")) {
                    c = 4;
                    break;
                }
                break;
            case -993894751:
                if (str.equals("propList")) {
                    c = 5;
                    break;
                }
                break;
            case -933864895:
                if (str.equals("markerEnd")) {
                    c = 6;
                    break;
                }
                break;
            case -933857362:
                if (str.equals("markerMid")) {
                    c = 7;
                    break;
                }
                break;
            case -925180581:
                if (str.equals("rotate")) {
                    c = '\b';
                    break;
                }
                break;
            case -891980232:
                if (str.equals("stroke")) {
                    c = '\t';
                    break;
                }
                break;
            case -734428249:
                if (str.equals("fontWeight")) {
                    c = '\n';
                    break;
                }
                break;
            case -729118945:
                if (str.equals("fillRule")) {
                    c = 11;
                    break;
                }
                break;
            case -416535885:
                if (str.equals("strokeOpacity")) {
                    c = '\f';
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case -53677816:
                if (str.equals("fillOpacity")) {
                    c = 14;
                    break;
                }
                break;
            case -44578051:
                if (str.equals("strokeDashoffset")) {
                    c = 15;
                    break;
                }
                break;
            case 120:
                if (str.equals("x")) {
                    c = 16;
                    break;
                }
                break;
            case 121:
                if (str.equals("y")) {
                    c = 17;
                    break;
                }
                break;
            case 3220:
                if (str.equals("dx")) {
                    c = 18;
                    break;
                }
                break;
            case 3221:
                if (str.equals("dy")) {
                    c = 19;
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c = 20;
                    break;
                }
                break;
            case 3148879:
                if (str.equals("font")) {
                    c = 21;
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = 22;
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 23;
                    break;
                }
                break;
            case 78845486:
                if (str.equals("strokeMiterlimit")) {
                    c = 24;
                    break;
                }
                break;
            case 104482996:
                if (str.equals("vectorEffect")) {
                    c = 25;
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = 26;
                    break;
                }
                break;
            case 275888445:
                if (str.equals("baselineShift")) {
                    c = 27;
                    break;
                }
                break;
            case 365601008:
                if (str.equals("fontSize")) {
                    c = 28;
                    break;
                }
                break;
            case 401643183:
                if (str.equals("strokeDasharray")) {
                    c = 29;
                    break;
                }
                break;
            case 778043962:
                if (str.equals("inlineSize")) {
                    c = 30;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 31;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = ' ';
                    break;
                }
                break;
            case 1027575302:
                if (str.equals("strokeLinecap")) {
                    c = '!';
                    break;
                }
                break;
            case 1637488243:
                if (str.equals("textLength")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = '#';
                    break;
                }
                break;
            case 1790285174:
                if (str.equals("strokeLinejoin")) {
                    c = '$';
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = '%';
                    break;
                }
                break;
            case 1924065902:
                if (str.equals("strokeWidth")) {
                    c = Typography.amp;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNSVGTextManagerInterface) this.mViewManager).setLengthAdjust(t, obj != null ? (String) obj : null);
                return;
            case 1:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 2:
                ((RNSVGTextManagerInterface) this.mViewManager).setAlignmentBaseline(t, obj != null ? (String) obj : null);
                return;
            case 3:
                ((RNSVGTextManagerInterface) this.mViewManager).setVerticalAlign(t, obj != null ? (String) obj : null);
                return;
            case 4:
                ((RNSVGTextManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                return;
            case 5:
                ((RNSVGTextManagerInterface) this.mViewManager).setPropList(t, (ReadableArray) obj);
                return;
            case 6:
                ((RNSVGTextManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                return;
            case 7:
                ((RNSVGTextManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                return;
            case '\b':
                ((RNSVGTextManagerInterface) this.mViewManager).setRotate(t, (ReadableArray) obj);
                return;
            case '\t':
                ((RNSVGTextManagerInterface) this.mViewManager).setStroke(t, (ReadableMap) obj);
                return;
            case '\n':
                if (obj instanceof String) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setFontWeight((RNSVGTextManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setFontWeight((RNSVGTextManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGTextManagerInterface) this.mViewManager).setFontWeight((RNSVGTextManagerInterface) t, (Double) null);
                    return;
                }
            case 11:
                ((RNSVGTextManagerInterface) this.mViewManager).setFillRule(t, obj != null ? ((Double) obj).intValue() : 1);
                return;
            case '\f':
                ((RNSVGTextManagerInterface) this.mViewManager).setStrokeOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case '\r':
                ((RNSVGTextManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case 14:
                ((RNSVGTextManagerInterface) this.mViewManager).setFillOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 15:
                ((RNSVGTextManagerInterface) this.mViewManager).setStrokeDashoffset(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 16:
                ((RNSVGTextManagerInterface) this.mViewManager).setX(t, (ReadableArray) obj);
                return;
            case 17:
                ((RNSVGTextManagerInterface) this.mViewManager).setY(t, (ReadableArray) obj);
                return;
            case 18:
                ((RNSVGTextManagerInterface) this.mViewManager).setDx(t, (ReadableArray) obj);
                return;
            case 19:
                ((RNSVGTextManagerInterface) this.mViewManager).setDy(t, (ReadableArray) obj);
                return;
            case 20:
                ((RNSVGTextManagerInterface) this.mViewManager).setFill(t, (ReadableMap) obj);
                return;
            case 21:
                ((RNSVGTextManagerInterface) this.mViewManager).setFont(t, (ReadableMap) obj);
                return;
            case 22:
                ((RNSVGTextManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                return;
            case 23:
                ((RNSVGTextManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                return;
            case 24:
                ((RNSVGTextManagerInterface) this.mViewManager).setStrokeMiterlimit(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 25:
                ((RNSVGTextManagerInterface) this.mViewManager).setVectorEffect(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 26:
                ((RNSVGTextManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                return;
            case 27:
                if (obj instanceof String) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setBaselineShift((RNSVGTextManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setBaselineShift((RNSVGTextManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGTextManagerInterface) this.mViewManager).setBaselineShift((RNSVGTextManagerInterface) t, (Double) null);
                    return;
                }
            case 28:
                if (obj instanceof String) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setFontSize((RNSVGTextManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setFontSize((RNSVGTextManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGTextManagerInterface) this.mViewManager).setFontSize((RNSVGTextManagerInterface) t, (Double) null);
                    return;
                }
            case 29:
                ((RNSVGTextManagerInterface) this.mViewManager).setStrokeDasharray(t, (ReadableArray) obj);
                return;
            case 30:
                if (obj instanceof String) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setInlineSize((RNSVGTextManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setInlineSize((RNSVGTextManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGTextManagerInterface) this.mViewManager).setInlineSize((RNSVGTextManagerInterface) t, (Double) null);
                    return;
                }
            case 31:
                ((RNSVGTextManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                return;
            case ' ':
                ((RNSVGTextManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '!':
                ((RNSVGTextManagerInterface) this.mViewManager).setStrokeLinecap(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '\"':
                if (obj instanceof String) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setTextLength((RNSVGTextManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setTextLength((RNSVGTextManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGTextManagerInterface) this.mViewManager).setTextLength((RNSVGTextManagerInterface) t, (Double) null);
                    return;
                }
            case '#':
                ((RNSVGTextManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                return;
            case '$':
                ((RNSVGTextManagerInterface) this.mViewManager).setStrokeLinejoin(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case '%':
                ((RNSVGTextManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case '&':
                if (obj instanceof String) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setStrokeWidth((RNSVGTextManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGTextManagerInterface) this.mViewManager).setStrokeWidth((RNSVGTextManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGTextManagerInterface) this.mViewManager).setStrokeWidth((RNSVGTextManagerInterface) t, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                    return;
                }
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
