package com.polidea.reactnativeble;

/* loaded from: classes3.dex */
public enum Event {
    ScanEvent("ScanEvent"),
    ReadEvent("ReadEvent"),
    StateChangeEvent("StateChangeEvent"),
    RestoreStateEvent("RestoreStateEvent"),
    DisconnectionEvent("DisconnectionEvent");
    
    public String name;

    Event(String str) {
        this.name = str;
    }
}
