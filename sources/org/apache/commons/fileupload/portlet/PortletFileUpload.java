package org.apache.commons.fileupload.portlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.portlet.ActionRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

/* loaded from: classes5.dex */
public class PortletFileUpload extends FileUpload {
    public static final boolean isMultipartContent(ActionRequest actionRequest) {
        return FileUploadBase.isMultipartContent(new PortletRequestContext(actionRequest));
    }

    public PortletFileUpload() {
    }

    public PortletFileUpload(FileItemFactory fileItemFactory) {
        super(fileItemFactory);
    }

    public List<FileItem> parseRequest(ActionRequest actionRequest) throws FileUploadException {
        return parseRequest(new PortletRequestContext(actionRequest));
    }

    public Map<String, List<FileItem>> parseParameterMap(ActionRequest actionRequest) throws FileUploadException {
        return parseParameterMap(new PortletRequestContext(actionRequest));
    }

    public FileItemIterator getItemIterator(ActionRequest actionRequest) throws FileUploadException, IOException {
        return super.getItemIterator(new PortletRequestContext(actionRequest));
    }
}
