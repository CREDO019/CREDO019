package org.apache.commons.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.fileupload.util.Closeable;
import org.apache.commons.fileupload.util.FileItemHeadersImpl;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes5.dex */
public abstract class FileUploadBase {
    public static final String ATTACHMENT = "attachment";
    public static final String CONTENT_DISPOSITION = "Content-disposition";
    public static final String CONTENT_LENGTH = "Content-length";
    public static final String CONTENT_TYPE = "Content-type";
    public static final String FORM_DATA = "form-data";
    @Deprecated
    public static final int MAX_HEADER_SIZE = 1024;
    public static final String MULTIPART = "multipart/";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    public static final String MULTIPART_MIXED = "multipart/mixed";
    private String headerEncoding;
    private ProgressListener listener;
    private long sizeMax = -1;
    private long fileSizeMax = -1;

    public abstract FileItemFactory getFileItemFactory();

    public abstract void setFileItemFactory(FileItemFactory fileItemFactory);

    public static final boolean isMultipartContent(RequestContext requestContext) {
        String contentType = requestContext.getContentType();
        return contentType != null && contentType.toLowerCase(Locale.ENGLISH).startsWith(MULTIPART);
    }

    @Deprecated
    public static boolean isMultipartContent(HttpServletRequest httpServletRequest) {
        return ServletFileUpload.isMultipartContent(httpServletRequest);
    }

    public long getSizeMax() {
        return this.sizeMax;
    }

    public void setSizeMax(long j) {
        this.sizeMax = j;
    }

    public long getFileSizeMax() {
        return this.fileSizeMax;
    }

    public void setFileSizeMax(long j) {
        this.fileSizeMax = j;
    }

    public String getHeaderEncoding() {
        return this.headerEncoding;
    }

    public void setHeaderEncoding(String str) {
        this.headerEncoding = str;
    }

    @Deprecated
    public List<FileItem> parseRequest(HttpServletRequest httpServletRequest) throws FileUploadException {
        return parseRequest(new ServletRequestContext(httpServletRequest));
    }

    public FileItemIterator getItemIterator(RequestContext requestContext) throws FileUploadException, IOException {
        try {
            return new FileItemIteratorImpl(requestContext);
        } catch (FileUploadIOException e) {
            throw ((FileUploadException) e.getCause());
        }
    }

    public List<FileItem> parseRequest(RequestContext requestContext) throws FileUploadException {
        ArrayList<FileItem> arrayList = new ArrayList();
        try {
            try {
                FileItemIterator itemIterator = getItemIterator(requestContext);
                FileItemFactory fileItemFactory = getFileItemFactory();
                if (fileItemFactory == null) {
                    throw new NullPointerException("No FileItemFactory has been set.");
                }
                while (itemIterator.hasNext()) {
                    FileItemStream next = itemIterator.next();
                    FileItem createItem = fileItemFactory.createItem(next.getFieldName(), next.getContentType(), next.isFormField(), ((FileItemIteratorImpl.FileItemStreamImpl) next).name);
                    arrayList.add(createItem);
                    try {
                        Streams.copy(next.openStream(), createItem.getOutputStream(), true);
                        createItem.setHeaders(next.getHeaders());
                    } catch (FileUploadIOException e) {
                        throw ((FileUploadException) e.getCause());
                    } catch (IOException e2) {
                        throw new IOFileUploadException(String.format("Processing of %s request failed. %s", "multipart/form-data", e2.getMessage()), e2);
                    }
                }
                return arrayList;
            } catch (FileUploadIOException e3) {
                throw ((FileUploadException) e3.getCause());
            } catch (IOException e4) {
                throw new FileUploadException(e4.getMessage(), e4);
            }
        } catch (Throwable th) {
            for (FileItem fileItem : arrayList) {
                try {
                    fileItem.delete();
                } catch (Exception unused) {
                }
            }
            throw th;
        }
    }

    public Map<String, List<FileItem>> parseParameterMap(RequestContext requestContext) throws FileUploadException {
        List<FileItem> parseRequest = parseRequest(requestContext);
        HashMap hashMap = new HashMap(parseRequest.size());
        for (FileItem fileItem : parseRequest) {
            String fieldName = fileItem.getFieldName();
            List list = (List) hashMap.get(fieldName);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(fieldName, list);
            }
            list.add(fileItem);
        }
        return hashMap;
    }

    protected byte[] getBoundary(String str) {
        ParameterParser parameterParser = new ParameterParser();
        parameterParser.setLowerCaseNames(true);
        String str2 = parameterParser.parse(str, new char[]{';', ','}).get("boundary");
        if (str2 == null) {
            return null;
        }
        try {
            return str2.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            return str2.getBytes();
        }
    }

    @Deprecated
    protected String getFileName(Map<String, String> map) {
        return getFileName(getHeader(map, CONTENT_DISPOSITION));
    }

    protected String getFileName(FileItemHeaders fileItemHeaders) {
        return getFileName(fileItemHeaders.getHeader(CONTENT_DISPOSITION));
    }

    private String getFileName(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            if (lowerCase.startsWith(FORM_DATA) || lowerCase.startsWith(ATTACHMENT)) {
                ParameterParser parameterParser = new ParameterParser();
                parameterParser.setLowerCaseNames(true);
                Map<String, String> parse = parameterParser.parse(str, ';');
                if (parse.containsKey("filename")) {
                    String str2 = parse.get("filename");
                    return str2 != null ? str2.trim() : "";
                }
            }
        }
        return null;
    }

    protected String getFieldName(FileItemHeaders fileItemHeaders) {
        return getFieldName(fileItemHeaders.getHeader(CONTENT_DISPOSITION));
    }

    private String getFieldName(String str) {
        if (str == null || !str.toLowerCase(Locale.ENGLISH).startsWith(FORM_DATA)) {
            return null;
        }
        ParameterParser parameterParser = new ParameterParser();
        parameterParser.setLowerCaseNames(true);
        String str2 = parameterParser.parse(str, ';').get("name");
        return str2 != null ? str2.trim() : str2;
    }

    @Deprecated
    protected String getFieldName(Map<String, String> map) {
        return getFieldName(getHeader(map, CONTENT_DISPOSITION));
    }

    @Deprecated
    protected FileItem createItem(Map<String, String> map, boolean z) throws FileUploadException {
        return getFileItemFactory().createItem(getFieldName(map), getHeader(map, CONTENT_TYPE), z, getFileName(map));
    }

    protected FileItemHeaders getParsedHeaders(String str) {
        int length = str.length();
        FileItemHeadersImpl newFileItemHeaders = newFileItemHeaders();
        int r2 = 0;
        while (true) {
            int parseEndOfLine = parseEndOfLine(str, r2);
            if (r2 == parseEndOfLine) {
                return newFileItemHeaders;
            }
            StringBuilder sb = new StringBuilder(str.substring(r2, parseEndOfLine));
            r2 = parseEndOfLine + 2;
            while (r2 < length) {
                int r3 = r2;
                while (r3 < length) {
                    char charAt = str.charAt(r3);
                    if (charAt != ' ' && charAt != '\t') {
                        break;
                    }
                    r3++;
                }
                if (r3 == r2) {
                    break;
                }
                int parseEndOfLine2 = parseEndOfLine(str, r3);
                sb.append(" ");
                sb.append(str.substring(r3, parseEndOfLine2));
                r2 = parseEndOfLine2 + 2;
            }
            parseHeaderLine(newFileItemHeaders, sb.toString());
        }
    }

    protected FileItemHeadersImpl newFileItemHeaders() {
        return new FileItemHeadersImpl();
    }

    @Deprecated
    protected Map<String, String> parseHeaders(String str) {
        FileItemHeaders parsedHeaders = getParsedHeaders(str);
        HashMap hashMap = new HashMap();
        Iterator<String> headerNames = parsedHeaders.getHeaderNames();
        while (headerNames.hasNext()) {
            String next = headerNames.next();
            Iterator<String> headers = parsedHeaders.getHeaders(next);
            StringBuilder sb = new StringBuilder(headers.next());
            while (headers.hasNext()) {
                sb.append(",");
                sb.append(headers.next());
            }
            hashMap.put(next, sb.toString());
        }
        return hashMap;
    }

    private int parseEndOfLine(String str, int r5) {
        int r0;
        while (true) {
            int indexOf = str.indexOf(13, r5);
            if (indexOf == -1 || (r0 = indexOf + 1) >= str.length()) {
                break;
            } else if (str.charAt(r0) == '\n') {
                return indexOf;
            } else {
                r5 = r0;
            }
        }
        throw new IllegalStateException("Expected headers to be terminated by an empty line.");
    }

    private void parseHeaderLine(FileItemHeadersImpl fileItemHeadersImpl, String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            return;
        }
        fileItemHeadersImpl.addHeader(str.substring(0, indexOf).trim(), str.substring(str.indexOf(58) + 1).trim());
    }

    @Deprecated
    protected final String getHeader(Map<String, String> map, String str) {
        return map.get(str.toLowerCase(Locale.ENGLISH));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class FileItemIteratorImpl implements FileItemIterator {
        private final byte[] boundary;
        private String currentFieldName;
        private FileItemStreamImpl currentItem;
        private boolean eof;
        private boolean itemValid;
        private final MultipartStream multi;
        private final MultipartStream.ProgressNotifier notifier;
        private boolean skipPreamble;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes5.dex */
        public class FileItemStreamImpl implements FileItemStream {
            private final String contentType;
            private final String fieldName;
            private final boolean formField;
            private FileItemHeaders headers;
            private final String name;
            private boolean opened;
            private final InputStream stream;

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r8v1, types: [org.apache.commons.fileupload.FileUploadBase$FileItemIteratorImpl$FileItemStreamImpl$1] */
            FileItemStreamImpl(String str, String str2, String str3, boolean z, long j) throws IOException {
                this.name = str;
                this.fieldName = str2;
                this.contentType = str3;
                this.formField = z;
                if (FileUploadBase.this.fileSizeMax == -1 || j == -1 || j <= FileUploadBase.this.fileSizeMax) {
                    final MultipartStream.ItemInputStream newInputStream = FileItemIteratorImpl.this.multi.newInputStream();
                    this.stream = FileUploadBase.this.fileSizeMax != -1 ? new LimitedInputStream(newInputStream, FileUploadBase.this.fileSizeMax) { // from class: org.apache.commons.fileupload.FileUploadBase.FileItemIteratorImpl.FileItemStreamImpl.1
                        @Override // org.apache.commons.fileupload.util.LimitedInputStream
                        protected void raiseError(long j2, long j3) throws IOException {
                            newInputStream.close(true);
                            FileSizeLimitExceededException fileSizeLimitExceededException = new FileSizeLimitExceededException(String.format("The field %s exceeds its maximum permitted size of %s bytes.", FileItemStreamImpl.this.fieldName, Long.valueOf(j2)), j3, j2);
                            fileSizeLimitExceededException.setFieldName(FileItemStreamImpl.this.fieldName);
                            fileSizeLimitExceededException.setFileName(FileItemStreamImpl.this.name);
                            throw new FileUploadIOException(fileSizeLimitExceededException);
                        }
                    } : newInputStream;
                    return;
                }
                FileSizeLimitExceededException fileSizeLimitExceededException = new FileSizeLimitExceededException(String.format("The field %s exceeds its maximum permitted size of %s bytes.", str2, Long.valueOf(FileUploadBase.this.fileSizeMax)), j, FileUploadBase.this.fileSizeMax);
                fileSizeLimitExceededException.setFileName(str);
                fileSizeLimitExceededException.setFieldName(str2);
                throw new FileUploadIOException(fileSizeLimitExceededException);
            }

            @Override // org.apache.commons.fileupload.FileItemStream
            public String getContentType() {
                return this.contentType;
            }

            @Override // org.apache.commons.fileupload.FileItemStream
            public String getFieldName() {
                return this.fieldName;
            }

            @Override // org.apache.commons.fileupload.FileItemStream
            public String getName() {
                return Streams.checkFileName(this.name);
            }

            @Override // org.apache.commons.fileupload.FileItemStream
            public boolean isFormField() {
                return this.formField;
            }

            @Override // org.apache.commons.fileupload.FileItemStream
            public InputStream openStream() throws IOException {
                if (this.opened) {
                    throw new IllegalStateException("The stream was already opened.");
                }
                if (((Closeable) this.stream).isClosed()) {
                    throw new FileItemStream.ItemSkippedException();
                }
                return this.stream;
            }

            void close() throws IOException {
                this.stream.close();
            }

            @Override // org.apache.commons.fileupload.FileItemHeadersSupport
            public FileItemHeaders getHeaders() {
                return this.headers;
            }

            @Override // org.apache.commons.fileupload.FileItemHeadersSupport
            public void setHeaders(FileItemHeaders fileItemHeaders) {
                this.headers = fileItemHeaders;
            }
        }

        FileItemIteratorImpl(RequestContext requestContext) throws FileUploadException, IOException {
            InputStream inputStream;
            Objects.requireNonNull(requestContext, "ctx parameter");
            String contentType = requestContext.getContentType();
            if (contentType == null || !contentType.toLowerCase(Locale.ENGLISH).startsWith(FileUploadBase.MULTIPART)) {
                throw new InvalidContentTypeException(String.format("the request doesn't contain a %s or %s stream, content type header is %s", "multipart/form-data", FileUploadBase.MULTIPART_MIXED, contentType));
            }
            long contentLength = UploadContext.class.isAssignableFrom(requestContext.getClass()) ? ((UploadContext) requestContext).contentLength() : requestContext.getContentLength();
            if (FileUploadBase.this.sizeMax >= 0) {
                if (contentLength != -1 && contentLength > FileUploadBase.this.sizeMax) {
                    throw new SizeLimitExceededException(String.format("the request was rejected because its size (%s) exceeds the configured maximum (%s)", Long.valueOf(contentLength), Long.valueOf(FileUploadBase.this.sizeMax)), contentLength, FileUploadBase.this.sizeMax);
                }
                inputStream = new LimitedInputStream(requestContext.getInputStream(), FileUploadBase.this.sizeMax) { // from class: org.apache.commons.fileupload.FileUploadBase.FileItemIteratorImpl.1
                    @Override // org.apache.commons.fileupload.util.LimitedInputStream
                    protected void raiseError(long j, long j2) throws IOException {
                        throw new FileUploadIOException(new SizeLimitExceededException(String.format("the request was rejected because its size (%s) exceeds the configured maximum (%s)", Long.valueOf(j2), Long.valueOf(j)), j2, j));
                    }
                };
            } else {
                inputStream = requestContext.getInputStream();
            }
            String str = FileUploadBase.this.headerEncoding;
            str = str == null ? requestContext.getCharacterEncoding() : str;
            byte[] boundary = FileUploadBase.this.getBoundary(contentType);
            this.boundary = boundary;
            if (boundary == null) {
                IOUtils.closeQuietly(inputStream);
                throw new FileUploadException("the request was rejected because no multipart boundary was found");
            }
            MultipartStream.ProgressNotifier progressNotifier = new MultipartStream.ProgressNotifier(FileUploadBase.this.listener, contentLength);
            this.notifier = progressNotifier;
            try {
                MultipartStream multipartStream = new MultipartStream(inputStream, boundary, progressNotifier);
                this.multi = multipartStream;
                multipartStream.setHeaderEncoding(str);
                this.skipPreamble = true;
                findNextItem();
            } catch (IllegalArgumentException e) {
                IOUtils.closeQuietly(inputStream);
                throw new InvalidContentTypeException(String.format("The boundary specified in the %s header is too long", FileUploadBase.CONTENT_TYPE), e);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
            r8 = r14.this$0.getFileName(r0);
            r10 = r0.getHeader(org.apache.commons.fileupload.FileUploadBase.CONTENT_TYPE);
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x007f, code lost:
            if (r8 != null) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0081, code lost:
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0083, code lost:
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0084, code lost:
            r2 = new org.apache.commons.fileupload.FileUploadBase.FileItemIteratorImpl.FileItemStreamImpl(r14, r8, r9, r10, r11, getContentLength(r0));
            r14.currentItem = r2;
            r2.setHeaders(r0);
            r14.notifier.noteItem();
            r14.itemValid = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0099, code lost:
            return true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean findNextItem() throws java.io.IOException {
            /*
                r14 = this;
                boolean r0 = r14.eof
                r1 = 0
                if (r0 == 0) goto L6
                return r1
            L6:
                org.apache.commons.fileupload.FileUploadBase$FileItemIteratorImpl$FileItemStreamImpl r0 = r14.currentItem
                r2 = 0
                if (r0 == 0) goto L10
                r0.close()
                r14.currentItem = r2
            L10:
                boolean r0 = r14.skipPreamble
                if (r0 == 0) goto L1b
                org.apache.commons.fileupload.MultipartStream r0 = r14.multi
                boolean r0 = r0.skipPreamble()
                goto L21
            L1b:
                org.apache.commons.fileupload.MultipartStream r0 = r14.multi
                boolean r0 = r0.readBoundary()
            L21:
                r3 = 1
                if (r0 != 0) goto L35
                java.lang.String r0 = r14.currentFieldName
                if (r0 != 0) goto L2b
                r14.eof = r3
                return r1
            L2b:
                org.apache.commons.fileupload.MultipartStream r0 = r14.multi
                byte[] r3 = r14.boundary
                r0.setBoundary(r3)
                r14.currentFieldName = r2
                goto L10
            L35:
                org.apache.commons.fileupload.FileUploadBase r0 = org.apache.commons.fileupload.FileUploadBase.this
                org.apache.commons.fileupload.MultipartStream r4 = r14.multi
                java.lang.String r4 = r4.readHeaders()
                org.apache.commons.fileupload.FileItemHeaders r0 = r0.getParsedHeaders(r4)
                java.lang.String r4 = r14.currentFieldName
                java.lang.String r5 = "Content-type"
                if (r4 != 0) goto L9a
                org.apache.commons.fileupload.FileUploadBase r4 = org.apache.commons.fileupload.FileUploadBase.this
                java.lang.String r9 = r4.getFieldName(r0)
                if (r9 == 0) goto Lc1
                java.lang.String r4 = r0.getHeader(r5)
                if (r4 == 0) goto L73
                java.util.Locale r6 = java.util.Locale.ENGLISH
                java.lang.String r6 = r4.toLowerCase(r6)
                java.lang.String r7 = "multipart/mixed"
                boolean r6 = r6.startsWith(r7)
                if (r6 == 0) goto L73
                r14.currentFieldName = r9
                org.apache.commons.fileupload.FileUploadBase r0 = org.apache.commons.fileupload.FileUploadBase.this
                byte[] r0 = r0.getBoundary(r4)
                org.apache.commons.fileupload.MultipartStream r4 = r14.multi
                r4.setBoundary(r0)
                r14.skipPreamble = r3
                goto L10
            L73:
                org.apache.commons.fileupload.FileUploadBase r2 = org.apache.commons.fileupload.FileUploadBase.this
                java.lang.String r8 = r2.getFileName(r0)
                org.apache.commons.fileupload.FileUploadBase$FileItemIteratorImpl$FileItemStreamImpl r2 = new org.apache.commons.fileupload.FileUploadBase$FileItemIteratorImpl$FileItemStreamImpl
                java.lang.String r10 = r0.getHeader(r5)
                if (r8 != 0) goto L83
                r11 = 1
                goto L84
            L83:
                r11 = 0
            L84:
                long r12 = r14.getContentLength(r0)
                r6 = r2
                r7 = r14
                r6.<init>(r8, r9, r10, r11, r12)
                r14.currentItem = r2
                r2.setHeaders(r0)
                org.apache.commons.fileupload.MultipartStream$ProgressNotifier r0 = r14.notifier
                r0.noteItem()
                r14.itemValid = r3
                return r3
            L9a:
                org.apache.commons.fileupload.FileUploadBase r4 = org.apache.commons.fileupload.FileUploadBase.this
                java.lang.String r8 = r4.getFileName(r0)
                if (r8 == 0) goto Lc1
                org.apache.commons.fileupload.FileUploadBase$FileItemIteratorImpl$FileItemStreamImpl r1 = new org.apache.commons.fileupload.FileUploadBase$FileItemIteratorImpl$FileItemStreamImpl
                java.lang.String r9 = r14.currentFieldName
                java.lang.String r10 = r0.getHeader(r5)
                r11 = 0
                long r12 = r14.getContentLength(r0)
                r6 = r1
                r7 = r14
                r6.<init>(r8, r9, r10, r11, r12)
                r14.currentItem = r1
                r1.setHeaders(r0)
                org.apache.commons.fileupload.MultipartStream$ProgressNotifier r0 = r14.notifier
                r0.noteItem()
                r14.itemValid = r3
                return r3
            Lc1:
                org.apache.commons.fileupload.MultipartStream r0 = r14.multi
                r0.discardBodyData()
                goto L10
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.fileupload.FileUploadBase.FileItemIteratorImpl.findNextItem():boolean");
        }

        private long getContentLength(FileItemHeaders fileItemHeaders) {
            try {
                return Long.parseLong(fileItemHeaders.getHeader(FileUploadBase.CONTENT_LENGTH));
            } catch (Exception unused) {
                return -1L;
            }
        }

        @Override // org.apache.commons.fileupload.FileItemIterator
        public boolean hasNext() throws FileUploadException, IOException {
            if (this.eof) {
                return false;
            }
            if (this.itemValid) {
                return true;
            }
            try {
                return findNextItem();
            } catch (FileUploadIOException e) {
                throw ((FileUploadException) e.getCause());
            }
        }

        @Override // org.apache.commons.fileupload.FileItemIterator
        public FileItemStream next() throws FileUploadException, IOException {
            if (this.eof || (!this.itemValid && !hasNext())) {
                throw new NoSuchElementException();
            }
            this.itemValid = false;
            return this.currentItem;
        }
    }

    /* loaded from: classes5.dex */
    public static class FileUploadIOException extends IOException {
        private static final long serialVersionUID = -7047616958165584154L;
        private final FileUploadException cause;

        public FileUploadIOException(FileUploadException fileUploadException) {
            this.cause = fileUploadException;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    /* loaded from: classes5.dex */
    public static class InvalidContentTypeException extends FileUploadException {
        private static final long serialVersionUID = -9073026332015646668L;

        public InvalidContentTypeException() {
        }

        public InvalidContentTypeException(String str) {
            super(str);
        }

        public InvalidContentTypeException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* loaded from: classes5.dex */
    public static class IOFileUploadException extends FileUploadException {
        private static final long serialVersionUID = 1749796615868477269L;
        private final IOException cause;

        public IOFileUploadException(String str, IOException iOException) {
            super(str);
            this.cause = iOException;
        }

        @Override // org.apache.commons.fileupload.FileUploadException, java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    /* loaded from: classes5.dex */
    protected static abstract class SizeException extends FileUploadException {
        private static final long serialVersionUID = -8776225574705254126L;
        private final long actual;
        private final long permitted;

        protected SizeException(String str, long j, long j2) {
            super(str);
            this.actual = j;
            this.permitted = j2;
        }

        public long getActualSize() {
            return this.actual;
        }

        public long getPermittedSize() {
            return this.permitted;
        }
    }

    @Deprecated
    /* loaded from: classes5.dex */
    public static class UnknownSizeException extends FileUploadException {
        private static final long serialVersionUID = 7062279004812015273L;

        public UnknownSizeException() {
        }

        public UnknownSizeException(String str) {
            super(str);
        }
    }

    /* loaded from: classes5.dex */
    public static class SizeLimitExceededException extends SizeException {
        private static final long serialVersionUID = -2474893167098052828L;

        @Override // org.apache.commons.fileupload.FileUploadBase.SizeException
        public /* bridge */ /* synthetic */ long getActualSize() {
            return super.getActualSize();
        }

        @Override // org.apache.commons.fileupload.FileUploadBase.SizeException
        public /* bridge */ /* synthetic */ long getPermittedSize() {
            return super.getPermittedSize();
        }

        @Deprecated
        public SizeLimitExceededException() {
            this(null, 0L, 0L);
        }

        @Deprecated
        public SizeLimitExceededException(String str) {
            this(str, 0L, 0L);
        }

        public SizeLimitExceededException(String str, long j, long j2) {
            super(str, j, j2);
        }
    }

    /* loaded from: classes5.dex */
    public static class FileSizeLimitExceededException extends SizeException {
        private static final long serialVersionUID = 8150776562029630058L;
        private String fieldName;
        private String fileName;

        @Override // org.apache.commons.fileupload.FileUploadBase.SizeException
        public /* bridge */ /* synthetic */ long getActualSize() {
            return super.getActualSize();
        }

        @Override // org.apache.commons.fileupload.FileUploadBase.SizeException
        public /* bridge */ /* synthetic */ long getPermittedSize() {
            return super.getPermittedSize();
        }

        public FileSizeLimitExceededException(String str, long j, long j2) {
            super(str, j, j2);
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String str) {
            this.fileName = str;
        }

        public String getFieldName() {
            return this.fieldName;
        }

        public void setFieldName(String str) {
            this.fieldName = str;
        }
    }

    public ProgressListener getProgressListener() {
        return this.listener;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.listener = progressListener;
    }
}
