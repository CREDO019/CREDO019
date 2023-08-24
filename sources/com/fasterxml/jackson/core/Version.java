package com.fasterxml.jackson.core;

import java.io.Serializable;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes.dex */
public class Version implements Comparable<Version>, Serializable {
    private static final Version UNKNOWN_VERSION = new Version(0, 0, 0, null, null, null);
    private static final long serialVersionUID = 1;
    protected final String _artifactId;
    protected final String _groupId;
    protected final int _majorVersion;
    protected final int _minorVersion;
    protected final int _patchLevel;
    protected final String _snapshotInfo;

    @Deprecated
    public Version(int r8, int r9, int r10, String str) {
        this(r8, r9, r10, str, null, null);
    }

    public Version(int r1, int r2, int r3, String str, String str2, String str3) {
        this._majorVersion = r1;
        this._minorVersion = r2;
        this._patchLevel = r3;
        this._snapshotInfo = str;
        this._groupId = str2 == null ? "" : str2;
        this._artifactId = str3 == null ? "" : str3;
    }

    public static Version unknownVersion() {
        return UNKNOWN_VERSION;
    }

    public boolean isUnknownVersion() {
        return this == UNKNOWN_VERSION;
    }

    public boolean isSnapshot() {
        String str = this._snapshotInfo;
        return str != null && str.length() > 0;
    }

    @Deprecated
    public boolean isUknownVersion() {
        return isUnknownVersion();
    }

    public int getMajorVersion() {
        return this._majorVersion;
    }

    public int getMinorVersion() {
        return this._minorVersion;
    }

    public int getPatchLevel() {
        return this._patchLevel;
    }

    public String getGroupId() {
        return this._groupId;
    }

    public String getArtifactId() {
        return this._artifactId;
    }

    public String toFullString() {
        return this._groupId + IOUtils.DIR_SEPARATOR_UNIX + this._artifactId + IOUtils.DIR_SEPARATOR_UNIX + toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._majorVersion);
        sb.append('.');
        sb.append(this._minorVersion);
        sb.append('.');
        sb.append(this._patchLevel);
        if (isSnapshot()) {
            sb.append('-');
            sb.append(this._snapshotInfo);
        }
        return sb.toString();
    }

    public int hashCode() {
        return this._artifactId.hashCode() ^ (((this._groupId.hashCode() + this._majorVersion) - this._minorVersion) + this._patchLevel);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            Version version = (Version) obj;
            return version._majorVersion == this._majorVersion && version._minorVersion == this._minorVersion && version._patchLevel == this._patchLevel && version._artifactId.equals(this._artifactId) && version._groupId.equals(this._groupId);
        }
        return false;
    }

    @Override // java.lang.Comparable
    public int compareTo(Version version) {
        if (version == this) {
            return 0;
        }
        int compareTo = this._groupId.compareTo(version._groupId);
        if (compareTo == 0) {
            int compareTo2 = this._artifactId.compareTo(version._artifactId);
            if (compareTo2 == 0) {
                int r0 = this._majorVersion - version._majorVersion;
                if (r0 == 0) {
                    int r02 = this._minorVersion - version._minorVersion;
                    return r02 == 0 ? this._patchLevel - version._patchLevel : r02;
                }
                return r0;
            }
            return compareTo2;
        }
        return compareTo;
    }
}
