package expo.modules.imagepicker.exporters;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.imagepicker.FailedToReadFileException;
import expo.modules.imagepicker.ImagePickerConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;
import kotlin.p023io.IOStreams;
import kotlinx.coroutines.Interruptible;

/* compiled from: ImageExporter.kt */
@Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/imagepicker/exporters/ImageExportResult;", "", "width", "", "height", "imageFile", "Ljava/io/File;", "(IILjava/io/File;)V", "getHeight", "()I", "getWidth", "data", "Ljava/io/ByteArrayOutputStream;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exif", "Landroid/os/Bundle;", "expo-image-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class ImageExportResult {
    private final int height;
    private final File imageFile;
    private final int width;

    public Object data(ContentResolver contentResolver, Continuation<? super ByteArrayOutputStream> continuation) {
        return data$suspendImpl(this, contentResolver, continuation);
    }

    public Object exif(ContentResolver contentResolver, Continuation<? super Bundle> continuation) {
        return exif$suspendImpl(this, contentResolver, continuation);
    }

    public ImageExportResult(int r2, int r3, File imageFile) {
        Intrinsics.checkNotNullParameter(imageFile, "imageFile");
        this.width = r2;
        this.height = r3;
        this.imageFile = imageFile;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    static /* synthetic */ Object data$suspendImpl(final ImageExportResult imageExportResult, final ContentResolver contentResolver, Continuation continuation) {
        return Interruptible.runInterruptible$default(null, new Functions<ByteArrayOutputStream>() { // from class: expo.modules.imagepicker.exporters.ImageExportResult$data$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final ByteArrayOutputStream invoke() {
                File file;
                File file2;
                ContentResolver contentResolver2 = contentResolver;
                file = imageExportResult.imageFile;
                Uri fromFile = Uri.fromFile(file);
                Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(this)");
                InputStream openInputStream = contentResolver2.openInputStream(fromFile);
                if (openInputStream == null) {
                    file2 = imageExportResult.imageFile;
                    throw new FailedToReadFileException(file2, null, 2, null);
                }
                InputStream inputStream = openInputStream;
                try {
                    InputStream inputStream2 = inputStream;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                    IOStreams.copyTo$default(inputStream2, byteArrayOutputStream2, 0, 2, null);
                    Closeable.closeFinally(byteArrayOutputStream, null);
                    Closeable.closeFinally(inputStream, null);
                    return byteArrayOutputStream2;
                } finally {
                }
            }
        }, continuation, 1, null);
    }

    static /* synthetic */ Object exif$suspendImpl(final ImageExportResult imageExportResult, final ContentResolver contentResolver, Continuation continuation) {
        return Interruptible.runInterruptible$default(null, new Functions<Bundle>() { // from class: expo.modules.imagepicker.exporters.ImageExportResult$exif$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Bundle invoke() {
                File file;
                File file2;
                ContentResolver contentResolver2 = contentResolver;
                file = imageExportResult.imageFile;
                Uri fromFile = Uri.fromFile(file);
                Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(this)");
                InputStream openInputStream = contentResolver2.openInputStream(fromFile);
                if (openInputStream == null) {
                    file2 = imageExportResult.imageFile;
                    throw new FailedToReadFileException(file2, null, 2, null);
                }
                InputStream inputStream = openInputStream;
                try {
                    Bundle bundle = new Bundle();
                    ExifInterface exifInterface = new ExifInterface(inputStream);
                    Iterable<Tuples<String, String>> iterable = ImagePickerConstants.INSTANCE.getEXIF_TAGS();
                    ArrayList<Tuples> arrayList = new ArrayList();
                    Iterator<Tuples<String, String>> it = iterable.iterator();
                    while (true) {
                        boolean z = true;
                        if (!it.hasNext()) {
                            break;
                        }
                        Tuples<String, String> next = it.next();
                        if (exifInterface.getAttribute(next.component2()) == null) {
                            z = false;
                        }
                        if (z) {
                            arrayList.add(next);
                        }
                    }
                    for (Tuples tuples : arrayList) {
                        String str = (String) tuples.component1();
                        String str2 = (String) tuples.component2();
                        int hashCode = str.hashCode();
                        if (hashCode != -1325958191) {
                            if (hashCode != -891985903) {
                                if (hashCode == 104431 && str.equals("int")) {
                                    bundle.putInt(str2, exifInterface.getAttributeInt(str2, 0));
                                }
                            } else if (str.equals("string")) {
                                bundle.putString(str2, exifInterface.getAttribute(str2));
                            }
                        } else if (str.equals("double")) {
                            bundle.putDouble(str2, exifInterface.getAttributeDouble(str2, 0.0d));
                        }
                    }
                    double[] latLong = exifInterface.getLatLong();
                    if (latLong != null) {
                        bundle.putDouble(ExifInterface.TAG_GPS_LATITUDE, latLong[0]);
                        bundle.putDouble(ExifInterface.TAG_GPS_LONGITUDE, latLong[1]);
                        bundle.putDouble(ExifInterface.TAG_GPS_ALTITUDE, exifInterface.getAltitude(0.0d));
                    }
                    Closeable.closeFinally(inputStream, null);
                    return bundle;
                } finally {
                }
            }
        }, continuation, 1, null);
    }
}
