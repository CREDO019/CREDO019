package org.apache.commons.fileupload.disk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ParameterParser;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.p028io.FileUtils;
import org.apache.commons.p028io.IOUtils;
import org.apache.commons.p028io.output.DeferredFileOutputStream;

/* loaded from: classes5.dex */
public class DiskFileItem implements FileItem {
    public static final String DEFAULT_CHARSET = "ISO-8859-1";
    private byte[] cachedContent;
    private final String contentType;
    private transient DeferredFileOutputStream dfos;
    private String fieldName;
    private final String fileName;
    private FileItemHeaders headers;
    private boolean isFormField;
    private final File repository;
    private final int sizeThreshold;
    private transient File tempFile;
    private static final String UID = UUID.randomUUID().toString().replace('-', '_');
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private long size = -1;
    private String defaultCharset = "ISO-8859-1";

    public DiskFileItem(String str, String str2, boolean z, String str3, int r7, File file) {
        this.fieldName = str;
        this.contentType = str2;
        this.isFormField = z;
        this.fileName = str3;
        this.sizeThreshold = r7;
        this.repository = file;
    }

    @Override // org.apache.commons.fileupload.FileItem
    public InputStream getInputStream() throws IOException {
        if (!isInMemory()) {
            return new FileInputStream(this.dfos.getFile());
        }
        if (this.cachedContent == null) {
            this.cachedContent = this.dfos.getData();
        }
        return new ByteArrayInputStream(this.cachedContent);
    }

    @Override // org.apache.commons.fileupload.FileItem
    public String getContentType() {
        return this.contentType;
    }

    public String getCharSet() {
        ParameterParser parameterParser = new ParameterParser();
        parameterParser.setLowerCaseNames(true);
        return parameterParser.parse(getContentType(), ';').get("charset");
    }

    @Override // org.apache.commons.fileupload.FileItem
    public String getName() {
        return Streams.checkFileName(this.fileName);
    }

    @Override // org.apache.commons.fileupload.FileItem
    public boolean isInMemory() {
        if (this.cachedContent != null) {
            return true;
        }
        return this.dfos.isInMemory();
    }

    @Override // org.apache.commons.fileupload.FileItem
    public long getSize() {
        long j = this.size;
        if (j >= 0) {
            return j;
        }
        byte[] bArr = this.cachedContent;
        if (bArr != null) {
            return bArr.length;
        }
        if (this.dfos.isInMemory()) {
            return this.dfos.getData().length;
        }
        return this.dfos.getFile().length();
    }

    @Override // org.apache.commons.fileupload.FileItem
    public byte[] get() {
        FileInputStream fileInputStream;
        DeferredFileOutputStream deferredFileOutputStream;
        if (isInMemory()) {
            if (this.cachedContent == null && (deferredFileOutputStream = this.dfos) != null) {
                this.cachedContent = deferredFileOutputStream.getData();
            }
            return this.cachedContent;
        }
        byte[] bArr = new byte[(int) getSize()];
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(this.dfos.getFile());
            try {
                IOUtils.readFully(fileInputStream, bArr);
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return bArr;
            } catch (IOException unused) {
                IOUtils.closeQuietly((InputStream) fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                IOUtils.closeQuietly((InputStream) fileInputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // org.apache.commons.fileupload.FileItem
    public String getString(String str) throws UnsupportedEncodingException {
        return new String(get(), str);
    }

    @Override // org.apache.commons.fileupload.FileItem
    public String getString() {
        byte[] bArr = get();
        String charSet = getCharSet();
        if (charSet == null) {
            charSet = this.defaultCharset;
        }
        try {
            return new String(bArr, charSet);
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    @Override // org.apache.commons.fileupload.FileItem
    public void write(File file) throws Exception {
        FileOutputStream fileOutputStream;
        if (isInMemory()) {
            FileOutputStream fileOutputStream2 = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(get());
                fileOutputStream.close();
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
                return;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                IOUtils.closeQuietly((OutputStream) fileOutputStream2);
                throw th;
            }
        }
        File storeLocation = getStoreLocation();
        if (storeLocation != null) {
            this.size = storeLocation.length();
            FileUtils.moveFile(storeLocation, file);
            return;
        }
        throw new FileUploadException("Cannot write uploaded file to disk!");
    }

    @Override // org.apache.commons.fileupload.FileItem
    public void delete() {
        this.cachedContent = null;
        File storeLocation = getStoreLocation();
        if (storeLocation == null || isInMemory() || !storeLocation.exists()) {
            return;
        }
        storeLocation.delete();
    }

    @Override // org.apache.commons.fileupload.FileItem
    public String getFieldName() {
        return this.fieldName;
    }

    @Override // org.apache.commons.fileupload.FileItem
    public void setFieldName(String str) {
        this.fieldName = str;
    }

    @Override // org.apache.commons.fileupload.FileItem
    public boolean isFormField() {
        return this.isFormField;
    }

    @Override // org.apache.commons.fileupload.FileItem
    public void setFormField(boolean z) {
        this.isFormField = z;
    }

    @Override // org.apache.commons.fileupload.FileItem
    public OutputStream getOutputStream() throws IOException {
        if (this.dfos == null) {
            this.dfos = new DeferredFileOutputStream(this.sizeThreshold, getTempFile());
        }
        return this.dfos;
    }

    public File getStoreLocation() {
        if (this.dfos == null || isInMemory()) {
            return null;
        }
        return this.dfos.getFile();
    }

    protected void finalize() {
        File file;
        DeferredFileOutputStream deferredFileOutputStream = this.dfos;
        if (deferredFileOutputStream == null || deferredFileOutputStream.isInMemory() || (file = this.dfos.getFile()) == null || !file.exists()) {
            return;
        }
        file.delete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public File getTempFile() {
        if (this.tempFile == null) {
            File file = this.repository;
            if (file == null) {
                file = new File(System.getProperty("java.io.tmpdir"));
            }
            this.tempFile = new File(file, String.format("upload_%s_%s.tmp", UID, getUniqueId()));
        }
        return this.tempFile;
    }

    private static String getUniqueId() {
        int andIncrement = COUNTER.getAndIncrement();
        String num = Integer.toString(andIncrement);
        if (andIncrement < 100000000) {
            return ("00000000" + num).substring(num.length());
        }
        return num;
    }

    public String toString() {
        return String.format("name=%s, StoreLocation=%s, size=%s bytes, isFormField=%s, FieldName=%s", getName(), getStoreLocation(), Long.valueOf(getSize()), Boolean.valueOf(isFormField()), getFieldName());
    }

    @Override // org.apache.commons.fileupload.FileItemHeadersSupport
    public FileItemHeaders getHeaders() {
        return this.headers;
    }

    @Override // org.apache.commons.fileupload.FileItemHeadersSupport
    public void setHeaders(FileItemHeaders fileItemHeaders) {
        this.headers = fileItemHeaders;
    }

    public String getDefaultCharset() {
        return this.defaultCharset;
    }

    public void setDefaultCharset(String str) {
        this.defaultCharset = str;
    }
}
