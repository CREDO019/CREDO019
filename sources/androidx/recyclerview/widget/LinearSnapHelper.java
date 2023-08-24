package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class LinearSnapHelper extends SnapHelper {
    private static final float INVALID_DISTANCE = 1.0f;
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    @Override // androidx.recyclerview.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] r0 = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            r0[0] = distanceToCenter(view, getHorizontalHelper(layoutManager));
        } else {
            r0[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            r0[1] = distanceToCenter(view, getVerticalHelper(layoutManager));
        } else {
            r0[1] = 0;
        }
        return r0;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int r10, int r11) {
        int itemCount;
        View findSnapView;
        int position;
        int r4;
        PointF computeScrollVectorForPosition;
        int r102;
        int r112;
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) || (itemCount = layoutManager.getItemCount()) == 0 || (findSnapView = findSnapView(layoutManager)) == null || (position = layoutManager.getPosition(findSnapView)) == -1 || (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return -1;
        }
        if (layoutManager.canScrollHorizontally()) {
            r102 = estimateNextPositionDiffForFling(layoutManager, getHorizontalHelper(layoutManager), r10, 0);
            if (computeScrollVectorForPosition.x < 0.0f) {
                r102 = -r102;
            }
        } else {
            r102 = 0;
        }
        if (layoutManager.canScrollVertically()) {
            r112 = estimateNextPositionDiffForFling(layoutManager, getVerticalHelper(layoutManager), 0, r11);
            if (computeScrollVectorForPosition.y < 0.0f) {
                r112 = -r112;
            }
        } else {
            r112 = 0;
        }
        if (layoutManager.canScrollVertically()) {
            r102 = r112;
        }
        if (r102 == 0) {
            return -1;
        }
        int r2 = position + r102;
        int r7 = r2 >= 0 ? r2 : 0;
        return r7 >= itemCount ? r4 : r7;
    }

    @Override // androidx.recyclerview.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    private int distanceToCenter(View view, OrientationHelper orientationHelper) {
        return (orientationHelper.getDecoratedStart(view) + (orientationHelper.getDecoratedMeasurement(view) / 2)) - (orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2));
    }

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper, int r5, int r6) {
        int[] calculateScrollDistance = calculateScrollDistance(r5, r6);
        float computeDistancePerChild = computeDistancePerChild(layoutManager, orientationHelper);
        if (computeDistancePerChild <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(calculateScrollDistance[0]) > Math.abs(calculateScrollDistance[1]) ? calculateScrollDistance[0] : calculateScrollDistance[1]) / computeDistancePerChild);
    }

    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int startAfterPadding = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        int r3 = Integer.MAX_VALUE;
        for (int r4 = 0; r4 < childCount; r4++) {
            View childAt = layoutManager.getChildAt(r4);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - startAfterPadding);
            if (abs < r3) {
                view = childAt;
                r3 = abs;
            }
        }
        return view;
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view = null;
        View view2 = null;
        int r5 = Integer.MAX_VALUE;
        int r6 = Integer.MIN_VALUE;
        for (int r2 = 0; r2 < childCount; r2++) {
            View childAt = layoutManager.getChildAt(r2);
            int position = layoutManager.getPosition(childAt);
            if (position != -1) {
                if (position < r5) {
                    view = childAt;
                    r5 = position;
                }
                if (position > r6) {
                    view2 = childAt;
                    r6 = position;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int max = Math.max(orientationHelper.getDecoratedEnd(view), orientationHelper.getDecoratedEnd(view2)) - Math.min(orientationHelper.getDecoratedStart(view), orientationHelper.getDecoratedStart(view2));
        if (max == 0) {
            return 1.0f;
        }
        return (max * 1.0f) / ((r6 - r5) + 1);
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
}
