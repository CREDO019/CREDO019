package org.apache.commons.p028io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.FileFileFilter */
/* loaded from: classes5.dex */
public class FileFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter FILE = new FileFileFilter();
    private static final long serialVersionUID = 5345244090827540862L;

    protected FileFileFilter() {
    }

    @Override // org.apache.commons.p028io.filefilter.AbstractFileFilter, org.apache.commons.p028io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file.isFile();
    }
}
