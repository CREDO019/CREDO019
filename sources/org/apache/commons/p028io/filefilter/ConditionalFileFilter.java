package org.apache.commons.p028io.filefilter;

import java.util.List;

/* renamed from: org.apache.commons.io.filefilter.ConditionalFileFilter */
/* loaded from: classes5.dex */
public interface ConditionalFileFilter {
    void addFileFilter(IOFileFilter iOFileFilter);

    List<IOFileFilter> getFileFilters();

    boolean removeFileFilter(IOFileFilter iOFileFilter);

    void setFileFilters(List<IOFileFilter> list);
}
