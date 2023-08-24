package androidx.customview.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
class FocusStrategy {

    /* loaded from: classes.dex */
    public interface BoundsAdapter<T> {
        void obtainBounds(T t, Rect rect);
    }

    /* loaded from: classes.dex */
    public interface CollectionAdapter<T, V> {
        V get(T t, int r2);

        int size(T t);
    }

    private static int getWeightedDistanceFor(int r1, int r2) {
        return (r1 * 13 * r1) + (r2 * r2);
    }

    public static <L, T> T findNextFocusInRelativeDirection(L l, CollectionAdapter<L, T> collectionAdapter, BoundsAdapter<T> boundsAdapter, T t, int r8, boolean z, boolean z2) {
        int size = collectionAdapter.size(l);
        ArrayList arrayList = new ArrayList(size);
        for (int r2 = 0; r2 < size; r2++) {
            arrayList.add(collectionAdapter.get(l, r2));
        }
        Collections.sort(arrayList, new SequentialComparator(z, boundsAdapter));
        if (r8 != 1) {
            if (r8 == 2) {
                return (T) getNextFocusable(t, arrayList, z2);
            }
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
        }
        return (T) getPreviousFocusable(t, arrayList, z2);
    }

    private static <T> T getNextFocusable(T t, ArrayList<T> arrayList, boolean z) {
        int size = arrayList.size();
        int lastIndexOf = (t == null ? -1 : arrayList.lastIndexOf(t)) + 1;
        if (lastIndexOf < size) {
            return arrayList.get(lastIndexOf);
        }
        if (!z || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    private static <T> T getPreviousFocusable(T t, ArrayList<T> arrayList, boolean z) {
        int size = arrayList.size();
        int indexOf = (t == null ? size : arrayList.indexOf(t)) - 1;
        if (indexOf >= 0) {
            return arrayList.get(indexOf);
        }
        if (!z || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    /* loaded from: classes.dex */
    private static class SequentialComparator<T> implements Comparator<T> {
        private final BoundsAdapter<T> mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1 = new Rect();
        private final Rect mTemp2 = new Rect();

        SequentialComparator(boolean z, BoundsAdapter<T> boundsAdapter) {
            this.mIsLayoutRtl = z;
            this.mAdapter = boundsAdapter;
        }

        @Override // java.util.Comparator
        public int compare(T t, T t2) {
            Rect rect = this.mTemp1;
            Rect rect2 = this.mTemp2;
            this.mAdapter.obtainBounds(t, rect);
            this.mAdapter.obtainBounds(t2, rect2);
            if (rect.top < rect2.top) {
                return -1;
            }
            if (rect.top > rect2.top) {
                return 1;
            }
            if (rect.left < rect2.left) {
                return this.mIsLayoutRtl ? 1 : -1;
            } else if (rect.left > rect2.left) {
                return this.mIsLayoutRtl ? -1 : 1;
            } else if (rect.bottom < rect2.bottom) {
                return -1;
            } else {
                if (rect.bottom > rect2.bottom) {
                    return 1;
                }
                if (rect.right < rect2.right) {
                    return this.mIsLayoutRtl ? 1 : -1;
                } else if (rect.right > rect2.right) {
                    return this.mIsLayoutRtl ? -1 : 1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static <L, T> T findNextFocusInAbsoluteDirection(L l, CollectionAdapter<L, T> collectionAdapter, BoundsAdapter<T> boundsAdapter, T t, Rect rect, int r12) {
        Rect rect2 = new Rect(rect);
        if (r12 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (r12 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (r12 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else if (r12 == 130) {
            rect2.offset(0, -(rect.height() + 1));
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        T t2 = null;
        int size = collectionAdapter.size(l);
        Rect rect3 = new Rect();
        for (int r2 = 0; r2 < size; r2++) {
            T t3 = collectionAdapter.get(l, r2);
            if (t3 != t) {
                boundsAdapter.obtainBounds(t3, rect3);
                if (isBetterCandidate(r12, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t2 = t3;
                }
            }
        }
        return t2;
    }

    private static boolean isBetterCandidate(int r3, Rect rect, Rect rect2, Rect rect3) {
        if (isCandidate(rect, rect2, r3)) {
            if (isCandidate(rect, rect3, r3) && !beamBeats(r3, rect, rect2, rect3)) {
                return !beamBeats(r3, rect, rect3, rect2) && getWeightedDistanceFor(majorAxisDistance(r3, rect, rect2), minorAxisDistance(r3, rect, rect2)) < getWeightedDistanceFor(majorAxisDistance(r3, rect, rect3), minorAxisDistance(r3, rect, rect3));
            }
            return true;
        }
        return false;
    }

    private static boolean beamBeats(int r3, Rect rect, Rect rect2, Rect rect3) {
        boolean beamsOverlap = beamsOverlap(r3, rect, rect2);
        if (beamsOverlap(r3, rect, rect3) || !beamsOverlap) {
            return false;
        }
        return !isToDirectionOf(r3, rect, rect3) || r3 == 17 || r3 == 66 || majorAxisDistance(r3, rect, rect2) < majorAxisDistanceToFarEdge(r3, rect, rect3);
    }

    private static boolean isCandidate(Rect rect, Rect rect2, int r5) {
        if (r5 == 17) {
            return (rect.right > rect2.right || rect.left >= rect2.right) && rect.left > rect2.left;
        } else if (r5 == 33) {
            return (rect.bottom > rect2.bottom || rect.top >= rect2.bottom) && rect.top > rect2.top;
        } else if (r5 == 66) {
            return (rect.left < rect2.left || rect.right <= rect2.left) && rect.right < rect2.right;
        } else if (r5 == 130) {
            return (rect.top < rect2.top || rect.bottom <= rect2.top) && rect.bottom < rect2.bottom;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
    }

    private static boolean beamsOverlap(int r3, Rect rect, Rect rect2) {
        if (r3 != 17) {
            if (r3 != 33) {
                if (r3 != 66) {
                    if (r3 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    private static boolean isToDirectionOf(int r3, Rect rect, Rect rect2) {
        if (r3 == 17) {
            return rect.left >= rect2.right;
        } else if (r3 == 33) {
            return rect.top >= rect2.bottom;
        } else if (r3 == 66) {
            return rect.right <= rect2.left;
        } else if (r3 == 130) {
            return rect.bottom <= rect2.top;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
    }

    private static int majorAxisDistance(int r0, Rect rect, Rect rect2) {
        return Math.max(0, majorAxisDistanceRaw(r0, rect, rect2));
    }

    private static int majorAxisDistanceRaw(int r1, Rect rect, Rect rect2) {
        int r12;
        int r2;
        if (r1 == 17) {
            r12 = rect.left;
            r2 = rect2.right;
        } else if (r1 == 33) {
            r12 = rect.top;
            r2 = rect2.bottom;
        } else if (r1 == 66) {
            r12 = rect2.left;
            r2 = rect.right;
        } else if (r1 == 130) {
            r12 = rect2.top;
            r2 = rect.bottom;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return r12 - r2;
    }

    private static int majorAxisDistanceToFarEdge(int r0, Rect rect, Rect rect2) {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(r0, rect, rect2));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int r1, Rect rect, Rect rect2) {
        int r12;
        int r2;
        if (r1 == 17) {
            r12 = rect.left;
            r2 = rect2.left;
        } else if (r1 == 33) {
            r12 = rect.top;
            r2 = rect2.top;
        } else if (r1 == 66) {
            r12 = rect2.right;
            r2 = rect.right;
        } else if (r1 == 130) {
            r12 = rect2.bottom;
            r2 = rect.bottom;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return r12 - r2;
    }

    private static int minorAxisDistance(int r1, Rect rect, Rect rect2) {
        if (r1 != 17) {
            if (r1 != 33) {
                if (r1 != 66) {
                    if (r1 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }

    private FocusStrategy() {
    }
}
