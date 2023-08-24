package com.horcrux.svg;

/* compiled from: PathParser.java */
/* loaded from: classes3.dex */
class PathElement {
    Point[] points;
    ElementType type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PathElement(ElementType elementType, Point[] pointArr) {
        this.type = elementType;
        this.points = pointArr;
    }
}
