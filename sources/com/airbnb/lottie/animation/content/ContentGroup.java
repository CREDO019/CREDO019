package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.C1028Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ContentGroup implements DrawingContent, PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private final List<Content> contents;
    private final boolean hidden;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix;
    private final String name;
    private final Paint offScreenPaint;
    private final RectF offScreenRectF;
    private final Path path;
    private List<PathContent> pathContents;
    private final RectF rect;
    private TransformKeyframeAnimation transformAnimation;

    private static List<Content> contentsFromModels(LottieDrawable lottieDrawable, BaseLayer baseLayer, List<ContentModel> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int r1 = 0; r1 < list.size(); r1++) {
            Content content = list.get(r1).toContent(lottieDrawable, baseLayer);
            if (content != null) {
                arrayList.add(content);
            }
        }
        return arrayList;
    }

    static AnimatableTransform findTransform(List<ContentModel> list) {
        for (int r0 = 0; r0 < list.size(); r0++) {
            ContentModel contentModel = list.get(r0);
            if (contentModel instanceof AnimatableTransform) {
                return (AnimatableTransform) contentModel;
            }
        }
        return null;
    }

    public ContentGroup(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapeGroup shapeGroup) {
        this(lottieDrawable, baseLayer, shapeGroup.getName(), shapeGroup.isHidden(), contentsFromModels(lottieDrawable, baseLayer, shapeGroup.getItems()), findTransform(shapeGroup.getItems()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentGroup(LottieDrawable lottieDrawable, BaseLayer baseLayer, String str, boolean z, List<Content> list, AnimatableTransform animatableTransform) {
        this.offScreenPaint = new LPaint();
        this.offScreenRectF = new RectF();
        this.matrix = new Matrix();
        this.path = new Path();
        this.rect = new RectF();
        this.name = str;
        this.lottieDrawable = lottieDrawable;
        this.hidden = z;
        this.contents = list;
        if (animatableTransform != null) {
            TransformKeyframeAnimation createAnimation = animatableTransform.createAnimation();
            this.transformAnimation = createAnimation;
            createAnimation.addAnimationsToLayer(baseLayer);
            this.transformAnimation.addListener(this);
        }
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            Content content = list.get(size);
            if (content instanceof GreedyContent) {
                arrayList.add((GreedyContent) content);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((GreedyContent) arrayList.get(size2)).absorbContent(list.listIterator(list.size()));
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.contents.size());
        arrayList.addAll(list);
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = this.contents.get(size);
            content.setContents(arrayList, this.contents.subList(0, size));
            arrayList.add(content);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<PathContent> getPathList() {
        if (this.pathContents == null) {
            this.pathContents = new ArrayList();
            for (int r0 = 0; r0 < this.contents.size(); r0++) {
                Content content = this.contents.get(r0);
                if (content instanceof PathContent) {
                    this.pathContents.add((PathContent) content);
                }
            }
        }
        return this.pathContents;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Matrix getTransformationMatrix() {
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            return transformKeyframeAnimation.getMatrix();
        }
        this.matrix.reset();
        return this.matrix;
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        this.matrix.reset();
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.set(transformKeyframeAnimation.getMatrix());
        }
        this.path.reset();
        if (this.hidden) {
            return this.path;
        }
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = this.contents.get(size);
            if (content instanceof PathContent) {
                this.path.addPath(((PathContent) content).getPath(), this.matrix);
            }
        }
        return this.path;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix matrix, int r7) {
        if (this.hidden) {
            return;
        }
        this.matrix.set(matrix);
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.preConcat(transformKeyframeAnimation.getMatrix());
            r7 = (int) (((((this.transformAnimation.getOpacity() == null ? 100 : this.transformAnimation.getOpacity().getValue().intValue()) / 100.0f) * r7) / 255.0f) * 255.0f);
        }
        boolean z = this.lottieDrawable.isApplyingOpacityToLayersEnabled() && hasTwoOrMoreDrawableContent() && r7 != 255;
        if (z) {
            this.offScreenRectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            getBounds(this.offScreenRectF, this.matrix, true);
            this.offScreenPaint.setAlpha(r7);
            C1028Utils.saveLayerCompat(canvas, this.offScreenRectF, this.offScreenPaint);
        }
        if (z) {
            r7 = 255;
        }
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = this.contents.get(size);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).draw(canvas, this.matrix, r7);
            }
        }
        if (z) {
            canvas.restore();
        }
    }

    private boolean hasTwoOrMoreDrawableContent() {
        int r2 = 0;
        for (int r1 = 0; r1 < this.contents.size(); r1++) {
            if ((this.contents.get(r1) instanceof DrawingContent) && (r2 = r2 + 1) >= 2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF rectF, Matrix matrix, boolean z) {
        this.matrix.set(matrix);
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            this.matrix.preConcat(transformKeyframeAnimation.getMatrix());
        }
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = this.contents.size() - 1; size >= 0; size--) {
            Content content = this.contents.get(size);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).getBounds(this.rect, this.matrix, z);
                rectF.union(this.rect);
            }
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int r5, List<KeyPath> list, KeyPath keyPath2) {
        if (keyPath.matches(getName(), r5) || "__container".equals(getName())) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), r5)) {
                    list.add(keyPath2.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), r5)) {
                int incrementDepthBy = r5 + keyPath.incrementDepthBy(getName(), r5);
                for (int r0 = 0; r0 < this.contents.size(); r0++) {
                    Content content = this.contents.get(r0);
                    if (content instanceof KeyPathElement) {
                        ((KeyPathElement) content).resolveKeyPath(keyPath, incrementDepthBy, list, keyPath2);
                    }
                }
            }
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T t, LottieValueCallback<T> lottieValueCallback) {
        TransformKeyframeAnimation transformKeyframeAnimation = this.transformAnimation;
        if (transformKeyframeAnimation != null) {
            transformKeyframeAnimation.applyValueCallback(t, lottieValueCallback);
        }
    }
}