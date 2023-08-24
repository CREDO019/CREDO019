package org.apache.commons.p028io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import org.apache.commons.p028io.filefilter.FileFilterUtils;
import org.apache.commons.p028io.filefilter.IOFileFilter;
import org.apache.commons.p028io.filefilter.TrueFileFilter;

/* renamed from: org.apache.commons.io.DirectoryWalker */
/* loaded from: classes5.dex */
public abstract class DirectoryWalker<T> {
    private final int depthLimit;
    private final FileFilter filter;

    protected File[] filterDirectoryContents(File file, int r2, File[] fileArr) throws IOException {
        return fileArr;
    }

    protected boolean handleDirectory(File file, int r2, Collection<T> collection) throws IOException {
        return true;
    }

    protected void handleDirectoryEnd(File file, int r2, Collection<T> collection) throws IOException {
    }

    protected void handleDirectoryStart(File file, int r2, Collection<T> collection) throws IOException {
    }

    protected void handleEnd(Collection<T> collection) throws IOException {
    }

    protected void handleFile(File file, int r2, Collection<T> collection) throws IOException {
    }

    protected boolean handleIsCancelled(File file, int r2, Collection<T> collection) throws IOException {
        return false;
    }

    protected void handleRestricted(File file, int r2, Collection<T> collection) throws IOException {
    }

    protected void handleStart(File file, Collection<T> collection) throws IOException {
    }

    protected DirectoryWalker() {
        this(null, -1);
    }

    protected DirectoryWalker(FileFilter fileFilter, int r2) {
        this.filter = fileFilter;
        this.depthLimit = r2;
    }

    protected DirectoryWalker(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2, int r5) {
        if (iOFileFilter == null && iOFileFilter2 == null) {
            this.filter = null;
        } else {
            this.filter = FileFilterUtils.m143or(FileFilterUtils.makeDirectoryOnly(iOFileFilter == null ? TrueFileFilter.TRUE : iOFileFilter), FileFilterUtils.makeFileOnly(iOFileFilter2 == null ? TrueFileFilter.TRUE : iOFileFilter2));
        }
        this.depthLimit = r5;
    }

    protected final void walk(File file, Collection<T> collection) throws IOException {
        Objects.requireNonNull(file, "Start Directory is null");
        try {
            handleStart(file, collection);
            walk(file, 0, collection);
            handleEnd(collection);
        } catch (CancelException e) {
            handleCancelled(file, collection, e);
        }
    }

    private void walk(File file, int r8, Collection<T> collection) throws IOException {
        checkIfCancelled(file, r8, collection);
        if (handleDirectory(file, r8, collection)) {
            handleDirectoryStart(file, r8, collection);
            int r0 = r8 + 1;
            int r1 = this.depthLimit;
            if (r1 < 0 || r0 <= r1) {
                checkIfCancelled(file, r8, collection);
                FileFilter fileFilter = this.filter;
                File[] filterDirectoryContents = filterDirectoryContents(file, r8, fileFilter == null ? file.listFiles() : file.listFiles(fileFilter));
                if (filterDirectoryContents == null) {
                    handleRestricted(file, r0, collection);
                } else {
                    for (File file2 : filterDirectoryContents) {
                        if (file2.isDirectory()) {
                            walk(file2, r0, collection);
                        } else {
                            checkIfCancelled(file2, r0, collection);
                            handleFile(file2, r0, collection);
                            checkIfCancelled(file2, r0, collection);
                        }
                    }
                }
            }
            handleDirectoryEnd(file, r8, collection);
        }
        checkIfCancelled(file, r8, collection);
    }

    protected final void checkIfCancelled(File file, int r2, Collection<T> collection) throws IOException {
        if (handleIsCancelled(file, r2, collection)) {
            throw new CancelException(file, r2);
        }
    }

    protected void handleCancelled(File file, Collection<T> collection, CancelException cancelException) throws IOException {
        throw cancelException;
    }

    /* renamed from: org.apache.commons.io.DirectoryWalker$CancelException */
    /* loaded from: classes5.dex */
    public static class CancelException extends IOException {
        private static final long serialVersionUID = 1347339620135041008L;
        private final int depth;
        private final File file;

        public CancelException(File file, int r3) {
            this("Operation Cancelled", file, r3);
        }

        public CancelException(String str, File file, int r3) {
            super(str);
            this.file = file;
            this.depth = r3;
        }

        public File getFile() {
            return this.file;
        }

        public int getDepth() {
            return this.depth;
        }
    }
}
