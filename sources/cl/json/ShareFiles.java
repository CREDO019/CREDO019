package cl.json;

import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.webkit.MimeTypeMap;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.imagepicker.MediaTypes;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ShareFiles {
    private ArrayList<String> filenames;
    private String intentType;
    private final ReactApplicationContext reactContext;
    private ArrayList<Uri> uris;
    private Boolean useInternalStorage;

    public ShareFiles(ReadableArray readableArray, ArrayList<String> arrayList, String str, Boolean bool, ReactApplicationContext reactApplicationContext) {
        this(readableArray, arrayList, bool, reactApplicationContext);
        this.intentType = str;
    }

    public ShareFiles(ReadableArray readableArray, ArrayList<String> arrayList, Boolean bool, ReactApplicationContext reactApplicationContext) {
        this.uris = new ArrayList<>();
        for (int r0 = 0; r0 < readableArray.size(); r0++) {
            String string = readableArray.getString(r0);
            if (string != null) {
                this.uris.add(Uri.parse(string));
            }
        }
        this.filenames = arrayList;
        this.useInternalStorage = bool;
        this.reactContext = reactApplicationContext;
    }

    private String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    public boolean isFile() {
        Iterator<Uri> it = this.uris.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Uri next = it.next();
            if (isBase64File(next) || isLocalFile(next)) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (!z) {
                break;
            }
        }
        return z;
    }

    private boolean isBase64File(Uri uri) {
        if (uri.getScheme() == null || !uri.getScheme().equals("data")) {
            return false;
        }
        String substring = uri.getSchemeSpecificPart().substring(0, uri.getSchemeSpecificPart().indexOf(";"));
        String str = this.intentType;
        if (str == null) {
            this.intentType = substring;
            return true;
        } else if (!str.equalsIgnoreCase(substring) && this.intentType.split("/")[0].equalsIgnoreCase(substring.split("/")[0])) {
            this.intentType = this.intentType.split("/")[0].concat("/*");
            return true;
        } else if (this.intentType.equalsIgnoreCase(substring)) {
            return true;
        } else {
            this.intentType = MediaTypes.AllMimeType;
            return true;
        }
    }

    private boolean isLocalFile(Uri uri) {
        if ((uri.getScheme() == null || !uri.getScheme().equals(UriUtil.LOCAL_CONTENT_SCHEME)) && !"file".equals(uri.getScheme())) {
            return false;
        }
        String mimeType = getMimeType(uri.toString());
        if (mimeType == null) {
            mimeType = getMimeType(getRealPathFromURI(uri));
        }
        if (mimeType == null) {
            mimeType = MediaTypes.AllMimeType;
        }
        String str = this.intentType;
        if (str == null) {
            this.intentType = mimeType;
            return true;
        } else if (!str.equalsIgnoreCase(mimeType) && this.intentType.split("/")[0].equalsIgnoreCase(mimeType.split("/")[0])) {
            this.intentType = this.intentType.split("/")[0].concat("/*");
            return true;
        } else if (this.intentType.equalsIgnoreCase(mimeType)) {
            return true;
        } else {
            this.intentType = MediaTypes.AllMimeType;
            return true;
        }
    }

    public String getType() {
        String str = this.intentType;
        return str == null ? MediaTypes.AllMimeType : str;
    }

    private String getRealPathFromURI(Uri uri) {
        return RNSharePathUtil.getRealPathFromURI(this.reactContext, uri, this.useInternalStorage);
    }

    public ArrayList<Uri> getURI() {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (int r3 = 0; r3 < this.uris.size(); r3++) {
            Uri uri = this.uris.get(r3);
            if (isBase64File(uri)) {
                String extensionFromMimeType = singleton.getExtensionFromMimeType(uri.getSchemeSpecificPart().substring(0, uri.getSchemeSpecificPart().indexOf(";")));
                String substring = uri.getSchemeSpecificPart().substring(uri.getSchemeSpecificPart().indexOf(";base64,") + 8);
                String str = this.filenames.size() >= r3 + 1 ? this.filenames.get(r3) : System.currentTimeMillis() + "." + extensionFromMimeType;
                try {
                    File file = new File(this.useInternalStorage.booleanValue() ? this.reactContext.getCacheDir() : this.reactContext.getExternalCacheDir(), Environment.DIRECTORY_DOWNLOADS);
                    if (!file.exists() && !file.mkdirs()) {
                        throw new IOException("mkdirs failed on " + file.getAbsolutePath());
                        break;
                    }
                    File file2 = new File(file, str);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    fileOutputStream.write(Base64.decode(substring, 0));
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    arrayList.add(RNSharePathUtil.compatUriFromFile(this.reactContext, file2));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (isLocalFile(uri) && uri.getPath() != null) {
                if (this.filenames.size() >= r3 + 1) {
                    arrayList.add(RNSharePathUtil.compatUriFromFile(this.reactContext, new File(uri.getPath(), this.filenames.get(r3))));
                } else {
                    arrayList.add(RNSharePathUtil.compatUriFromFile(this.reactContext, new File(uri.getPath())));
                }
            }
        }
        return arrayList;
    }
}
