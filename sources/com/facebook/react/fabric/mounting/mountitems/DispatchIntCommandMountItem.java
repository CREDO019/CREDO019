package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;

/* loaded from: classes.dex */
public class DispatchIntCommandMountItem extends DispatchCommandMountItem {
    private final ReadableArray mCommandArgs;
    private final int mCommandId;
    private final int mReactTag;
    private final int mSurfaceId;

    public DispatchIntCommandMountItem(int r1, int r2, int r3, ReadableArray readableArray) {
        this.mSurfaceId = r1;
        this.mReactTag = r2;
        this.mCommandId = r3;
        this.mCommandArgs = readableArray;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        mountingManager.receiveCommand(this.mSurfaceId, this.mReactTag, this.mCommandId, this.mCommandArgs);
    }

    public String toString() {
        return "DispatchIntCommandMountItem [" + this.mReactTag + "] " + this.mCommandId;
    }
}
