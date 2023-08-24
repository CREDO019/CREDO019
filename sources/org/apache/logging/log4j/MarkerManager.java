package org.apache.logging.log4j;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes5.dex */
public final class MarkerManager {
    private static final ConcurrentMap<String, Marker> MARKERS = new ConcurrentHashMap();

    private MarkerManager() {
    }

    public static void clear() {
        MARKERS.clear();
    }

    public static Marker getMarker(String str) {
        ConcurrentMap<String, Marker> concurrentMap = MARKERS;
        concurrentMap.putIfAbsent(str, new Log4jMarker(str));
        return concurrentMap.get(str);
    }

    @Deprecated
    public static Marker getMarker(String str, String str2) {
        Marker marker = MARKERS.get(str2);
        if (marker == null) {
            throw new IllegalArgumentException("Parent Marker " + str2 + " has not been defined");
        }
        return getMarker(str, marker);
    }

    @Deprecated
    public static Marker getMarker(String str, Marker marker) {
        ConcurrentMap<String, Marker> concurrentMap = MARKERS;
        concurrentMap.putIfAbsent(str, new Log4jMarker(str));
        return concurrentMap.get(str).addParents(marker);
    }

    /* loaded from: classes5.dex */
    public static class Log4jMarker implements Marker {
        private static final long serialVersionUID = 100;
        private final String name;
        private volatile Marker[] parents;

        private Log4jMarker() {
            this.name = null;
            this.parents = null;
        }

        public Log4jMarker(String str) {
            if (str == null) {
                throw new IllegalArgumentException("Marker name cannot be null.");
            }
            this.name = str;
            this.parents = null;
        }

        @Override // org.apache.logging.log4j.Marker
        public synchronized Marker addParents(Marker... markerArr) {
            if (markerArr == null) {
                throw new IllegalArgumentException("A parent marker must be specified");
            }
            Marker[] markerArr2 = this.parents;
            int length = markerArr.length;
            if (markerArr2 != null) {
                int r4 = 0;
                for (Marker marker : markerArr) {
                    if (!contains(marker, markerArr2) && !marker.isInstanceOf(this)) {
                        r4++;
                    }
                }
                if (r4 == 0) {
                    return this;
                }
                length = markerArr2.length + r4;
            }
            Marker[] markerArr3 = new Marker[length];
            if (markerArr2 != null) {
                System.arraycopy(markerArr2, 0, markerArr3, 0, markerArr2.length);
            }
            int length2 = markerArr2 == null ? 0 : markerArr2.length;
            for (Marker marker2 : markerArr) {
                if (markerArr2 == null || (!contains(marker2, markerArr2) && !marker2.isInstanceOf(this))) {
                    markerArr3[length2] = marker2;
                    length2++;
                }
            }
            this.parents = markerArr3;
            return this;
        }

        @Override // org.apache.logging.log4j.Marker
        public synchronized boolean remove(Marker marker) {
            if (marker == null) {
                throw new IllegalArgumentException("A parent marker must be specified");
            }
            Marker[] markerArr = this.parents;
            if (markerArr == null) {
                return false;
            }
            int length = markerArr.length;
            if (length == 1) {
                if (markerArr[0].equals(marker)) {
                    this.parents = null;
                    return true;
                }
                return false;
            }
            int r4 = length - 1;
            Marker[] markerArr2 = new Marker[r4];
            int r7 = 0;
            for (Marker marker2 : markerArr) {
                if (!marker2.equals(marker)) {
                    if (r7 == r4) {
                        return false;
                    }
                    int r9 = r7 + 1;
                    markerArr2[r7] = marker2;
                    r7 = r9;
                }
            }
            this.parents = markerArr2;
            return true;
        }

        @Override // org.apache.logging.log4j.Marker
        public Marker setParents(Marker... markerArr) {
            if (markerArr == null || markerArr.length == 0) {
                this.parents = null;
            } else {
                Marker[] markerArr2 = new Marker[markerArr.length];
                System.arraycopy(markerArr, 0, markerArr2, 0, markerArr.length);
                this.parents = markerArr2;
            }
            return this;
        }

        @Override // org.apache.logging.log4j.Marker
        public String getName() {
            return this.name;
        }

        @Override // org.apache.logging.log4j.Marker
        public Marker[] getParents() {
            if (this.parents == null) {
                return null;
            }
            return (Marker[]) Arrays.copyOf(this.parents, this.parents.length);
        }

        @Override // org.apache.logging.log4j.Marker
        public boolean hasParents() {
            return this.parents != null;
        }

        @Override // org.apache.logging.log4j.Marker
        public boolean isInstanceOf(Marker marker) {
            if (marker != null) {
                if (this == marker) {
                    return true;
                }
                Marker[] markerArr = this.parents;
                if (markerArr != null) {
                    int length = markerArr.length;
                    if (length == 1) {
                        return checkParent(markerArr[0], marker);
                    }
                    if (length == 2) {
                        return checkParent(markerArr[0], marker) || checkParent(markerArr[1], marker);
                    }
                    for (Marker marker2 : markerArr) {
                        if (checkParent(marker2, marker)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            throw new IllegalArgumentException("A marker parameter is required");
        }

        @Override // org.apache.logging.log4j.Marker
        public boolean isInstanceOf(String str) {
            Marker[] markerArr;
            if (str == null) {
                throw new IllegalArgumentException("A marker name is required");
            }
            if (str.equals(getName())) {
                return true;
            }
            Marker marker = (Marker) MarkerManager.MARKERS.get(str);
            if (marker != null && (markerArr = this.parents) != null) {
                int length = markerArr.length;
                if (length == 1) {
                    return checkParent(markerArr[0], marker);
                }
                if (length == 2) {
                    return checkParent(markerArr[0], marker) || checkParent(markerArr[1], marker);
                }
                for (Marker marker2 : markerArr) {
                    if (checkParent(marker2, marker)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private static boolean checkParent(Marker marker, Marker marker2) {
            if (marker == marker2) {
                return true;
            }
            Marker[] parents = marker instanceof Log4jMarker ? ((Log4jMarker) marker).parents : marker.getParents();
            if (parents != null) {
                int length = parents.length;
                if (length == 1) {
                    return checkParent(parents[0], marker2);
                }
                if (length == 2) {
                    return checkParent(parents[0], marker2) || checkParent(parents[1], marker2);
                }
                for (Marker marker3 : parents) {
                    if (checkParent(marker3, marker2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private static boolean contains(Marker marker, Marker... markerArr) {
            for (Marker marker2 : markerArr) {
                if (marker2 == marker) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Marker)) {
                return false;
            }
            return this.name.equals(((Marker) obj).getName());
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(this.name);
            Marker[] markerArr = this.parents;
            if (markerArr != null) {
                addParentInfo(sb, markerArr);
            }
            return sb.toString();
        }

        private static void addParentInfo(StringBuilder sb, Marker... markerArr) {
            sb.append("[ ");
            int length = markerArr.length;
            boolean z = true;
            int r3 = 0;
            while (r3 < length) {
                Marker marker = markerArr[r3];
                if (!z) {
                    sb.append(", ");
                }
                sb.append(marker.getName());
                Marker[] parents = marker instanceof Log4jMarker ? ((Log4jMarker) marker).parents : marker.getParents();
                if (parents != null) {
                    addParentInfo(sb, parents);
                }
                r3++;
                z = false;
            }
            sb.append(" ]");
        }
    }
}
