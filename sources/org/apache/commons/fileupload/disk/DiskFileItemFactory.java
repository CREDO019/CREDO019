package org.apache.commons.fileupload.disk;

import java.io.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.p028io.FileCleaningTracker;

/* loaded from: classes5.dex */
public class DiskFileItemFactory implements FileItemFactory {
    public static final int DEFAULT_SIZE_THRESHOLD = 10240;
    private String defaultCharset;
    private FileCleaningTracker fileCleaningTracker;
    private File repository;
    private int sizeThreshold;

    public DiskFileItemFactory() {
        this(10240, null);
    }

    public DiskFileItemFactory(int r2, File file) {
        this.defaultCharset = "ISO-8859-1";
        this.sizeThreshold = r2;
        this.repository = file;
    }

    public File getRepository() {
        return this.repository;
    }

    public void setRepository(File file) {
        this.repository = file;
    }

    public int getSizeThreshold() {
        return this.sizeThreshold;
    }

    public void setSizeThreshold(int r1) {
        this.sizeThreshold = r1;
    }

    @Override // org.apache.commons.fileupload.FileItemFactory
    public FileItem createItem(String str, String str2, boolean z, String str3) {
        DiskFileItem diskFileItem = new DiskFileItem(str, str2, z, str3, this.sizeThreshold, this.repository);
        diskFileItem.setDefaultCharset(this.defaultCharset);
        FileCleaningTracker fileCleaningTracker = getFileCleaningTracker();
        if (fileCleaningTracker != null) {
            fileCleaningTracker.track(diskFileItem.getTempFile(), diskFileItem);
        }
        return diskFileItem;
    }

    public FileCleaningTracker getFileCleaningTracker() {
        return this.fileCleaningTracker;
    }

    public void setFileCleaningTracker(FileCleaningTracker fileCleaningTracker) {
        this.fileCleaningTracker = fileCleaningTracker;
    }

    public String getDefaultCharset() {
        return this.defaultCharset;
    }

    public void setDefaultCharset(String str) {
        this.defaultCharset = str;
    }
}
