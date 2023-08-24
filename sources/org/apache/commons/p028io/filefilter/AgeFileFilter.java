package org.apache.commons.p028io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.p028io.FileUtils;

/* renamed from: org.apache.commons.io.filefilter.AgeFileFilter */
/* loaded from: classes5.dex */
public class AgeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -2132740084016138541L;
    private final boolean acceptOlder;
    private final long cutoff;

    public AgeFileFilter(long j) {
        this(j, true);
    }

    public AgeFileFilter(long j, boolean z) {
        this.acceptOlder = z;
        this.cutoff = j;
    }

    public AgeFileFilter(Date date) {
        this(date, true);
    }

    public AgeFileFilter(Date date, boolean z) {
        this(date.getTime(), z);
    }

    public AgeFileFilter(File file) {
        this(file, true);
    }

    public AgeFileFilter(File file, boolean z) {
        this(file.lastModified(), z);
    }

    @Override // org.apache.commons.p028io.filefilter.AbstractFileFilter, org.apache.commons.p028io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        boolean isFileNewer = FileUtils.isFileNewer(file, this.cutoff);
        return this.acceptOlder ? !isFileNewer : isFileNewer;
    }

    @Override // org.apache.commons.p028io.filefilter.AbstractFileFilter
    public String toString() {
        String str = this.acceptOlder ? "<=" : ">";
        return super.toString() + "(" + str + this.cutoff + ")";
    }
}
