package com.facebook.react.views.modal;

import android.graphics.Point;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;

/* loaded from: classes.dex */
class ModalHostShadowNode extends LayoutShadowNode {
    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void addChildAt(ReactShadowNodeImpl reactShadowNodeImpl, int r3) {
        super.addChildAt(reactShadowNodeImpl, r3);
        Point modalHostSize = ModalHostHelper.getModalHostSize(getThemedContext());
        reactShadowNodeImpl.setStyleWidth(modalHostSize.x);
        reactShadowNodeImpl.setStyleHeight(modalHostSize.y);
    }
}
