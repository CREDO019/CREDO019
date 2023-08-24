package com.canhub.cropper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.exifinterface.media.ExifInterface;
import com.canhub.cropper.BitmapCroppingWorkerJob;
import com.canhub.cropper.BitmapLoadingWorkerJob;
import com.canhub.cropper.BitmapUtils;
import com.canhub.cropper.CropOverlayView;
import com.canhub.cropper.utils.GetFilePathFromUri;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropImageView.kt */
@Metadata(m184d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b#\u0018\u0000 \u0081\u00022\u00020\u00012\u00020\u0002:\u0018\u0081\u0002\u0082\u0002\u0083\u0002\u0084\u0002\u0085\u0002\u0086\u0002\u0087\u0002\u0088\u0002\u0089\u0002\u008a\u0002\u008b\u0002\u008c\u0002B\u001b\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J.\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0007\u0010®\u0001\u001a\u00020$2\u0007\u0010¯\u0001\u001a\u00020$2\u0007\u0010°\u0001\u001a\u00020Y2\u0007\u0010±\u0001\u001a\u00020YH\u0002J\b\u0010²\u0001\u001a\u00030\u00ad\u0001J\b\u0010³\u0001\u001a\u00030\u00ad\u0001J\n\u0010´\u0001\u001a\u00030\u00ad\u0001H\u0002JM\u0010µ\u0001\u001a\u00030\u00ad\u00012\n\b\u0002\u0010¶\u0001\u001a\u00030·\u00012\t\b\u0002\u0010¸\u0001\u001a\u00020\n2\t\b\u0002\u0010¹\u0001\u001a\u00020\n2\t\b\u0002\u0010º\u0001\u001a\u00020\n2\n\b\u0002\u0010»\u0001\u001a\u00030¼\u00012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DJ\b\u0010½\u0001\u001a\u00030\u00ad\u0001J\b\u0010¾\u0001\u001a\u00030\u00ad\u0001J\u001a\u0010A\u001a\u0004\u0018\u00010@2\u0007\u0010¹\u0001\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\nJ$\u0010A\u001a\u0004\u0018\u00010@2\u0007\u0010¹\u0001\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\n2\b\u0010»\u0001\u001a\u00030¼\u0001J\u001c\u0010¿\u0001\u001a\u00030\u00ad\u00012\u0007\u0010À\u0001\u001a\u00020Y2\u0007\u0010±\u0001\u001a\u00020YH\u0002J\n\u0010Á\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0013\u0010Â\u0001\u001a\u00030\u00ad\u00012\u0007\u0010À\u0001\u001a\u00020YH\u0016J\u0012\u0010Ã\u0001\u001a\u00030\u00ad\u00012\b\u0010Ä\u0001\u001a\u00030Å\u0001J7\u0010Æ\u0001\u001a\u00030\u00ad\u00012\u0007\u0010Ç\u0001\u001a\u00020Y2\u0007\u0010È\u0001\u001a\u00020\n2\u0007\u0010É\u0001\u001a\u00020\n2\u0007\u0010Ê\u0001\u001a\u00020\n2\u0007\u0010Ë\u0001\u001a\u00020\nH\u0014J\u001c\u0010Ì\u0001\u001a\u00030\u00ad\u00012\u0007\u0010Í\u0001\u001a\u00020\n2\u0007\u0010Î\u0001\u001a\u00020\nH\u0014J\u0014\u0010Ï\u0001\u001a\u00030\u00ad\u00012\b\u0010Ð\u0001\u001a\u00030Ñ\u0001H\u0016J\f\u0010Ò\u0001\u001a\u0005\u0018\u00010Ñ\u0001H\u0016J\u0012\u0010Ó\u0001\u001a\u00030\u00ad\u00012\b\u0010Ä\u0001\u001a\u00030Ô\u0001J.\u0010Õ\u0001\u001a\u00030\u00ad\u00012\u0007\u0010Ö\u0001\u001a\u00020\n2\u0007\u0010×\u0001\u001a\u00020\n2\u0007\u0010Ø\u0001\u001a\u00020\n2\u0007\u0010Ù\u0001\u001a\u00020\nH\u0014J\b\u0010Ú\u0001\u001a\u00030\u00ad\u0001J\u0011\u0010Û\u0001\u001a\u00030\u00ad\u00012\u0007\u0010¡\u0001\u001a\u00020\nJ\u001a\u0010Ü\u0001\u001a\u00030\u00ad\u00012\u0007\u0010Ý\u0001\u001a\u00020\n2\u0007\u0010Þ\u0001\u001a\u00020\nJ9\u0010ß\u0001\u001a\u00030\u00ad\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010@2\u0006\u0010P\u001a\u00020\n2\b\u0010T\u001a\u0004\u0018\u00010D2\u0007\u0010á\u0001\u001a\u00020\n2\u0007\u0010â\u0001\u001a\u00020\nH\u0002J\u0011\u0010ã\u0001\u001a\u00030\u00ad\u00012\u0007\u0010ä\u0001\u001a\u00020YJ\n\u0010å\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0011\u0010æ\u0001\u001a\u00030\u00ad\u00012\u0007\u0010ç\u0001\u001a\u00020YJ\u0013\u0010è\u0001\u001a\u00030\u00ad\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010@J\u001f\u0010è\u0001\u001a\u00030\u00ad\u00012\t\u0010à\u0001\u001a\u0004\u0018\u00010@2\n\u0010é\u0001\u001a\u0005\u0018\u00010ê\u0001J\u0013\u0010ë\u0001\u001a\u00030\u00ad\u00012\t\u0010ì\u0001\u001a\u0004\u0018\u00010DJ\u001a\u0010í\u0001\u001a\u00030\u00ad\u00012\u0007\u0010î\u0001\u001a\u00020\n2\u0007\u0010ï\u0001\u001a\u00020\nJ\u001a\u0010ð\u0001\u001a\u00030\u00ad\u00012\u0007\u0010ñ\u0001\u001a\u00020\n2\u0007\u0010ò\u0001\u001a\u00020\nJ\u0011\u0010ó\u0001\u001a\u00030\u00ad\u00012\u0007\u0010ô\u0001\u001a\u00020YJ\u0014\u0010õ\u0001\u001a\u00030\u00ad\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0086\u0001J\u0014\u0010÷\u0001\u001a\u00030\u00ad\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u008c\u0001J\u0014\u0010ø\u0001\u001a\u00030\u00ad\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u008a\u0001J\u0014\u0010ù\u0001\u001a\u00030\u00ad\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u0088\u0001J\u0014\u0010ú\u0001\u001a\u00030\u00ad\u00012\n\u0010ö\u0001\u001a\u0005\u0018\u00010\u008e\u0001J\n\u0010û\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0011\u0010ü\u0001\u001a\u00030\u00ad\u00012\u0007\u0010ý\u0001\u001a\u00020$JA\u0010þ\u0001\u001a\u00030\u00ad\u00012\u0007\u0010¹\u0001\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\n2\b\u0010»\u0001\u001a\u00030¼\u00012\b\u0010¶\u0001\u001a\u00030·\u00012\u0007\u0010¸\u0001\u001a\u00020\n2\b\u0010C\u001a\u0004\u0018\u00010DJ\u0013\u0010ÿ\u0001\u001a\u00030\u00ad\u00012\u0007\u0010\u0080\u0002\u001a\u00020YH\u0002R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00198F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020$8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b,\u0010-R(\u00100\u001a\u0004\u0018\u00010/2\b\u0010.\u001a\u0004\u0018\u00010/8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104R(\u00105\u001a\u0004\u0018\u0001062\b\u00105\u001a\u0004\u0018\u0001068F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0013\u0010;\u001a\u0004\u0018\u00010<8F¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0013\u0010?\u001a\u0004\u0018\u00010@8F¢\u0006\u0006\u001a\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR(\u0010I\u001a\u0004\u0018\u00010J2\b\u0010I\u001a\u0004\u0018\u00010J8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR$\u0010P\u001a\u00020\n2\u0006\u0010O\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bQ\u0010 \"\u0004\bR\u0010\"R\"\u0010T\u001a\u0004\u0018\u00010D2\b\u0010S\u001a\u0004\u0018\u00010D@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bU\u0010FR\u000e\u0010V\u001a\u00020WX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010Z\u001a\u00020Y2\u0006\u0010X\u001a\u00020Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0011\u0010^\u001a\u00020Y8F¢\u0006\u0006\u001a\u0004\b^\u0010[R$\u0010`\u001a\u00020Y2\u0006\u0010_\u001a\u00020Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b`\u0010[\"\u0004\ba\u0010]R$\u0010c\u001a\u00020Y2\u0006\u0010b\u001a\u00020Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bc\u0010[\"\u0004\bd\u0010]R\u001a\u0010e\u001a\u00020YX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010[\"\u0004\bf\u0010]R$\u0010h\u001a\u00020Y2\u0006\u0010g\u001a\u00020Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bh\u0010[\"\u0004\bi\u0010]R$\u0010k\u001a\u00020Y2\u0006\u0010j\u001a\u00020Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bk\u0010[\"\u0004\bl\u0010]R$\u0010n\u001a\u00020Y2\u0006\u0010m\u001a\u00020Y8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bn\u0010[\"\u0004\bo\u0010]R\u000e\u0010p\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010q\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u0004\u0018\u00010wX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020}X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020}X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u007f\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0080\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0081\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0084\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u008b\u0001\u001a\u0005\u0018\u00010\u008c\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0091\u0001\u001a\u0004\u0018\u00010<X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0092\u0001\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0093\u0001\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0096\u0001\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0097\u0001\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0098\u0001\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0099\u0001\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009a\u0001\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009b\u0001\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u009c\u0001\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u009d\u0001\u001a\u00020\n2\u0007\u0010\u009d\u0001\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b\u009e\u0001\u0010 \"\u0005\b\u009f\u0001\u0010\"R\u0011\u0010 \u0001\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010¢\u0001\u001a\u00020\n2\u0007\u0010¡\u0001\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\u000e\u001a\u0005\b£\u0001\u0010 \"\u0005\b¤\u0001\u0010\"R,\u0010¥\u0001\u001a\u00030\u0095\u00012\b\u0010¥\u0001\u001a\u00030\u0095\u00018F@FX\u0086\u000e¢\u0006\u0010\u001a\u0006\b¦\u0001\u0010§\u0001\"\u0006\b¨\u0001\u0010©\u0001R\u0015\u0010ª\u0001\u001a\u0004\u0018\u00010/8F¢\u0006\u0007\u001a\u0005\b«\u0001\u00102¨\u0006\u008d\u0002"}, m183d2 = {"Lcom/canhub/cropper/CropImageView;", "Landroid/widget/FrameLayout;", "Lcom/canhub/cropper/CropOverlayView$CropWindowChangeListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", ViewProps.ASPECT_RATIO, "Landroid/util/Pair;", "", "getAspectRatio", "()Landroid/util/Pair;", "bitmapCroppingWorkerJob", "Ljava/lang/ref/WeakReference;", "Lcom/canhub/cropper/BitmapCroppingWorkerJob;", "bitmapLoadingWorkerJob", "Lcom/canhub/cropper/BitmapLoadingWorkerJob;", "cornerShape", "Lcom/canhub/cropper/CropImageView$CropCornerShape;", "getCornerShape", "()Lcom/canhub/cropper/CropImageView$CropCornerShape;", "setCornerShape", "(Lcom/canhub/cropper/CropImageView$CropCornerShape;)V", "cropLabelText", "", "getCropLabelText", "()Ljava/lang/String;", "setCropLabelText", "(Ljava/lang/String;)V", "cropLabelTextColor", "getCropLabelTextColor", "()I", "setCropLabelTextColor", "(I)V", "textSize", "", "cropLabelTextSize", "getCropLabelTextSize", "()F", "setCropLabelTextSize", "(F)V", "cropPoints", "", "getCropPoints", "()[F", "rect", "Landroid/graphics/Rect;", "cropRect", "getCropRect", "()Landroid/graphics/Rect;", "setCropRect", "(Landroid/graphics/Rect;)V", "cropShape", "Lcom/canhub/cropper/CropImageView$CropShape;", "getCropShape", "()Lcom/canhub/cropper/CropImageView$CropShape;", "setCropShape", "(Lcom/canhub/cropper/CropImageView$CropShape;)V", "cropWindowRect", "Landroid/graphics/RectF;", "getCropWindowRect", "()Landroid/graphics/RectF;", "croppedImage", "Landroid/graphics/Bitmap;", "getCroppedImage", "()Landroid/graphics/Bitmap;", "customOutputUri", "Landroid/net/Uri;", "getCustomOutputUri", "()Landroid/net/Uri;", "setCustomOutputUri", "(Landroid/net/Uri;)V", "guidelines", "Lcom/canhub/cropper/CropImageView$Guidelines;", "getGuidelines", "()Lcom/canhub/cropper/CropImageView$Guidelines;", "setGuidelines", "(Lcom/canhub/cropper/CropImageView$Guidelines;)V", "resId", "imageResource", "getImageResource", "setImageResource", "<set-?>", "imageUri", "getImageUri", "imageView", "Landroid/widget/ImageView;", "autoZoomEnabled", "", "isAutoZoomEnabled", "()Z", "setAutoZoomEnabled", "(Z)V", "isFixAspectRatio", "flipHorizontally", "isFlippedHorizontally", "setFlippedHorizontally", "flipVertically", "isFlippedVertically", "setFlippedVertically", "isSaveBitmapToInstanceState", "setSaveBitmapToInstanceState", "showCropLabel", "isShowCropLabel", "setShowCropLabel", "showCropOverlay", "isShowCropOverlay", "setShowCropOverlay", "showProgressBar", "isShowProgressBar", "setShowProgressBar", "loadedSampleSize", "mAnimation", "Lcom/canhub/cropper/CropImageAnimation;", "mAutoZoomEnabled", "mCropLabelTextColor", "mCropLabelTextSize", "mCropOverlayView", "Lcom/canhub/cropper/CropOverlayView;", "mCropTextLabel", "mDegreesRotated", "mFlipHorizontally", "mFlipVertically", "mImageInverseMatrix", "Landroid/graphics/Matrix;", "mImageMatrix", "mImagePoints", "mImageResource", "mInitialDegreesRotated", "mLayoutHeight", "mLayoutWidth", "mMaxZoom", "mOnCropImageCompleteListener", "Lcom/canhub/cropper/CropImageView$OnCropImageCompleteListener;", "mOnCropOverlayReleasedListener", "Lcom/canhub/cropper/CropImageView$OnSetCropOverlayReleasedListener;", "mOnSetCropOverlayMovedListener", "Lcom/canhub/cropper/CropImageView$OnSetCropOverlayMovedListener;", "mOnSetCropWindowChangeListener", "Lcom/canhub/cropper/CropImageView$OnSetCropWindowChangeListener;", "mOnSetImageUriCompleteListener", "Lcom/canhub/cropper/CropImageView$OnSetImageUriCompleteListener;", "mProgressBar", "Landroid/widget/ProgressBar;", "mRestoreCropWindowRect", "mRestoreDegreesRotated", "mScaleImagePoints", "mScaleType", "Lcom/canhub/cropper/CropImageView$ScaleType;", "mShowCropLabel", "mShowCropOverlay", "mShowProgressBar", "mSizeChanged", "mZoom", "mZoomOffsetX", "mZoomOffsetY", "maxZoom", "getMaxZoom", "setMaxZoom", "originalBitmap", "degrees", "rotatedDegrees", "getRotatedDegrees", "setRotatedDegrees", "scaleType", "getScaleType", "()Lcom/canhub/cropper/CropImageView$ScaleType;", "setScaleType", "(Lcom/canhub/cropper/CropImageView$ScaleType;)V", "wholeImageRect", "getWholeImageRect", "applyImageMatrix", "", "width", "height", TtmlNode.CENTER, "animate", "clearAspectRatio", "clearImage", "clearImageInt", "croppedImageAsync", "saveCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "saveCompressQuality", "reqWidth", "reqHeight", "options", "Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "flipImageHorizontally", "flipImageVertically", "handleCropWindowChanged", "inProgress", "mapImagePointsByImageMatrix", "onCropWindowChanged", "onImageCroppingAsyncComplete", "result", "Lcom/canhub/cropper/BitmapCroppingWorkerJob$Result;", ViewProps.ON_LAYOUT, "changed", "l", "t", "r", "b", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onRestoreInstanceState", "state", "Landroid/os/Parcelable;", "onSaveInstanceState", "onSetImageUriAsyncComplete", "Lcom/canhub/cropper/BitmapLoadingWorkerJob$Result;", "onSizeChanged", "w", "h", "oldw", "oldh", "resetCropRect", "rotateImage", "setAspectRatio", "aspectRatioX", "aspectRatioY", "setBitmap", "bitmap", "loadSampleSize", "degreesRotated", "setCenterMoveEnabled", "centerMoveEnabled", "setCropOverlayVisibility", "setFixedAspectRatio", "fixAspectRatio", "setImageBitmap", "exif", "Landroidx/exifinterface/media/ExifInterface;", "setImageUriAsync", "uri", "setMaxCropResultSize", "maxCropResultWidth", "maxCropResultHeight", "setMinCropResultSize", "minCropResultWidth", "minCropResultHeight", "setMultiTouchEnabled", "multiTouchEnabled", "setOnCropImageCompleteListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnCropWindowChangedListener", "setOnSetCropOverlayMovedListener", "setOnSetCropOverlayReleasedListener", "setOnSetImageUriCompleteListener", "setProgressBarVisibility", "setSnapRadius", "snapRadius", "startCropWorkerTask", "updateImageBounds", "clear", "Companion", "CropCornerShape", "CropResult", "CropShape", "Guidelines", "OnCropImageCompleteListener", "OnSetCropOverlayMovedListener", "OnSetCropOverlayReleasedListener", "OnSetCropWindowChangeListener", "OnSetImageUriCompleteListener", "RequestSizeOptions", "ScaleType", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CropImageView extends FrameLayout implements CropOverlayView.CropWindowChangeListener {
    public static final Companion Companion = new Companion(null);
    private WeakReference<BitmapCroppingWorkerJob> bitmapCroppingWorkerJob;
    private WeakReference<BitmapLoadingWorkerJob> bitmapLoadingWorkerJob;
    private Uri customOutputUri;
    private Uri imageUri;
    private final ImageView imageView;
    private boolean isSaveBitmapToInstanceState;
    private int loadedSampleSize;
    private CropImageAnimation mAnimation;
    private boolean mAutoZoomEnabled;
    private int mCropLabelTextColor;
    private float mCropLabelTextSize;
    private final CropOverlayView mCropOverlayView;
    private String mCropTextLabel;
    private int mDegreesRotated;
    private boolean mFlipHorizontally;
    private boolean mFlipVertically;
    private final Matrix mImageInverseMatrix;
    private final Matrix mImageMatrix;
    private final float[] mImagePoints;
    private int mImageResource;
    private int mInitialDegreesRotated;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private int mMaxZoom;
    private OnCropImageCompleteListener mOnCropImageCompleteListener;
    private OnSetCropOverlayReleasedListener mOnCropOverlayReleasedListener;
    private OnSetCropOverlayMovedListener mOnSetCropOverlayMovedListener;
    private OnSetCropWindowChangeListener mOnSetCropWindowChangeListener;
    private OnSetImageUriCompleteListener mOnSetImageUriCompleteListener;
    private final ProgressBar mProgressBar;
    private RectF mRestoreCropWindowRect;
    private int mRestoreDegreesRotated;
    private final float[] mScaleImagePoints;
    private ScaleType mScaleType;
    private boolean mShowCropLabel;
    private boolean mShowCropOverlay;
    private boolean mShowProgressBar;
    private boolean mSizeChanged;
    private float mZoom;
    private float mZoomOffsetX;
    private float mZoomOffsetY;
    private Bitmap originalBitmap;

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$CropCornerShape;", "", "(Ljava/lang/String;I)V", "RECTANGLE", "OVAL", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum CropCornerShape {
        RECTANGLE,
        OVAL
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$CropShape;", "", "(Ljava/lang/String;I)V", "RECTANGLE", "OVAL", "RECTANGLE_VERTICAL_ONLY", "RECTANGLE_HORIZONTAL_ONLY", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum CropShape {
        RECTANGLE,
        OVAL,
        RECTANGLE_VERTICAL_ONLY,
        RECTANGLE_HORIZONTAL_ONLY
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$Guidelines;", "", "(Ljava/lang/String;I)V", "OFF", "ON_TOUCH", "ON", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum Guidelines {
        OFF,
        ON_TOUCH,
        ON
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$OnCropImageCompleteListener;", "", "onCropImageComplete", "", "view", "Lcom/canhub/cropper/CropImageView;", "result", "Lcom/canhub/cropper/CropImageView$CropResult;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public interface OnCropImageCompleteListener {
        void onCropImageComplete(CropImageView cropImageView, CropResult cropResult);
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$OnSetCropOverlayMovedListener;", "", "onCropOverlayMoved", "", "rect", "Landroid/graphics/Rect;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public interface OnSetCropOverlayMovedListener {
        void onCropOverlayMoved(Rect rect);
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0006"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$OnSetCropOverlayReleasedListener;", "", "onCropOverlayReleased", "", "rect", "Landroid/graphics/Rect;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public interface OnSetCropOverlayReleasedListener {
        void onCropOverlayReleased(Rect rect);
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$OnSetCropWindowChangeListener;", "", "onCropWindowChanged", "", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public interface OnSetCropWindowChangeListener {
        void onCropWindowChanged();
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nH&¨\u0006\u000b"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$OnSetImageUriCompleteListener;", "", "onSetImageUriComplete", "", "view", "Lcom/canhub/cropper/CropImageView;", "uri", "Landroid/net/Uri;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public interface OnSetImageUriCompleteListener {
        void onSetImageUriComplete(CropImageView cropImageView, Uri uri, Exception exc);
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$RequestSizeOptions;", "", "(Ljava/lang/String;I)V", "NONE", "SAMPLING", "RESIZE_INSIDE", "RESIZE_FIT", "RESIZE_EXACT", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum RequestSizeOptions {
        NONE,
        SAMPLING,
        RESIZE_INSIDE,
        RESIZE_FIT,
        RESIZE_EXACT
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$ScaleType;", "", "(Ljava/lang/String;I)V", "FIT_CENTER", "CENTER", "CENTER_CROP", "CENTER_INSIDE", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum ScaleType {
        FIT_CENTER,
        CENTER,
        CENTER_CROP,
        CENTER_INSIDE
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public CropImageView(Context context) {
        this(context, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ CropImageView(Context context, AttributeSet attributeSet, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Bundle bundleExtra;
        Intrinsics.checkNotNullParameter(context, "context");
        this.mImageMatrix = new Matrix();
        this.mImageInverseMatrix = new Matrix();
        this.mImagePoints = new float[8];
        this.mScaleImagePoints = new float[8];
        this.mShowCropOverlay = true;
        this.mCropTextLabel = "";
        this.mCropLabelTextSize = 20.0f;
        this.mCropLabelTextColor = -1;
        this.mShowProgressBar = true;
        this.mAutoZoomEnabled = true;
        this.loadedSampleSize = 1;
        this.mZoom = 1.0f;
        CropImageOptions cropImageOptions = null;
        Intent intent = context instanceof Activity ? ((Activity) context).getIntent() : null;
        if (intent != null && (bundleExtra = intent.getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE)) != null) {
            cropImageOptions = (CropImageOptions) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        }
        if (cropImageOptions == null) {
            cropImageOptions = new CropImageOptions();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1150R.styleable.CropImageView, 0, 0);
                Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttributes(attrs, R.styleable.CropImageView, 0, 0)");
                try {
                    cropImageOptions.fixAspectRatio = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropFixAspectRatio, cropImageOptions.fixAspectRatio);
                    cropImageOptions.aspectRatioX = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropAspectRatioX, cropImageOptions.aspectRatioX);
                    cropImageOptions.aspectRatioY = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropAspectRatioY, cropImageOptions.aspectRatioY);
                    cropImageOptions.scaleType = ScaleType.values()[obtainStyledAttributes.getInt(C1150R.styleable.CropImageView_cropScaleType, cropImageOptions.scaleType.ordinal())];
                    cropImageOptions.autoZoomEnabled = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropAutoZoomEnabled, cropImageOptions.autoZoomEnabled);
                    cropImageOptions.multiTouchEnabled = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropMultiTouchEnabled, cropImageOptions.multiTouchEnabled);
                    cropImageOptions.centerMoveEnabled = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropCenterMoveEnabled, cropImageOptions.centerMoveEnabled);
                    cropImageOptions.maxZoom = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropMaxZoom, cropImageOptions.maxZoom);
                    cropImageOptions.cropShape = CropShape.values()[obtainStyledAttributes.getInt(C1150R.styleable.CropImageView_cropShape, cropImageOptions.cropShape.ordinal())];
                    cropImageOptions.cornerShape = CropCornerShape.values()[obtainStyledAttributes.getInt(C1150R.styleable.CropImageView_cornerShape, cropImageOptions.cornerShape.ordinal())];
                    cropImageOptions.cropCornerRadius = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropCornerRadius, cropImageOptions.cropCornerRadius);
                    cropImageOptions.guidelines = Guidelines.values()[obtainStyledAttributes.getInt(C1150R.styleable.CropImageView_cropGuidelines, cropImageOptions.guidelines.ordinal())];
                    cropImageOptions.snapRadius = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropSnapRadius, cropImageOptions.snapRadius);
                    cropImageOptions.touchRadius = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropTouchRadius, cropImageOptions.touchRadius);
                    cropImageOptions.initialCropWindowPaddingRatio = obtainStyledAttributes.getFloat(C1150R.styleable.CropImageView_cropInitialCropWindowPaddingRatio, cropImageOptions.initialCropWindowPaddingRatio);
                    cropImageOptions.circleCornerFillColorHexValue = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropCornerCircleFillColor, cropImageOptions.circleCornerFillColorHexValue);
                    cropImageOptions.borderLineThickness = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropBorderLineThickness, cropImageOptions.borderLineThickness);
                    cropImageOptions.borderLineColor = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropBorderLineColor, cropImageOptions.borderLineColor);
                    cropImageOptions.borderCornerThickness = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropBorderCornerThickness, cropImageOptions.borderCornerThickness);
                    cropImageOptions.borderCornerOffset = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropBorderCornerOffset, cropImageOptions.borderCornerOffset);
                    cropImageOptions.borderCornerLength = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropBorderCornerLength, cropImageOptions.borderCornerLength);
                    cropImageOptions.borderCornerColor = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropBorderCornerColor, cropImageOptions.borderCornerColor);
                    cropImageOptions.guidelinesThickness = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropGuidelinesThickness, cropImageOptions.guidelinesThickness);
                    cropImageOptions.guidelinesColor = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropGuidelinesColor, cropImageOptions.guidelinesColor);
                    cropImageOptions.backgroundColor = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropBackgroundColor, cropImageOptions.backgroundColor);
                    cropImageOptions.showCropOverlay = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropShowCropOverlay, this.mShowCropOverlay);
                    cropImageOptions.showProgressBar = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropShowProgressBar, this.mShowProgressBar);
                    cropImageOptions.borderCornerThickness = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropBorderCornerThickness, cropImageOptions.borderCornerThickness);
                    cropImageOptions.minCropWindowWidth = (int) obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropMinCropWindowWidth, cropImageOptions.minCropWindowWidth);
                    cropImageOptions.minCropWindowHeight = (int) obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropMinCropWindowHeight, cropImageOptions.minCropWindowHeight);
                    cropImageOptions.minCropResultWidth = (int) obtainStyledAttributes.getFloat(C1150R.styleable.CropImageView_cropMinCropResultWidthPX, cropImageOptions.minCropResultWidth);
                    cropImageOptions.minCropResultHeight = (int) obtainStyledAttributes.getFloat(C1150R.styleable.CropImageView_cropMinCropResultHeightPX, cropImageOptions.minCropResultHeight);
                    cropImageOptions.maxCropResultWidth = (int) obtainStyledAttributes.getFloat(C1150R.styleable.CropImageView_cropMaxCropResultWidthPX, cropImageOptions.maxCropResultWidth);
                    cropImageOptions.maxCropResultHeight = (int) obtainStyledAttributes.getFloat(C1150R.styleable.CropImageView_cropMaxCropResultHeightPX, cropImageOptions.maxCropResultHeight);
                    cropImageOptions.flipHorizontally = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropFlipHorizontally, cropImageOptions.flipHorizontally);
                    cropImageOptions.flipVertically = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropFlipHorizontally, cropImageOptions.flipVertically);
                    cropImageOptions.cropperLabelTextSize = obtainStyledAttributes.getDimension(C1150R.styleable.CropImageView_cropperLabelTextSize, cropImageOptions.cropperLabelTextSize);
                    cropImageOptions.cropperLabelTextColor = obtainStyledAttributes.getInteger(C1150R.styleable.CropImageView_cropperLabelTextColor, cropImageOptions.cropperLabelTextColor);
                    cropImageOptions.cropperLabelText = obtainStyledAttributes.getString(C1150R.styleable.CropImageView_cropperLabelText);
                    cropImageOptions.showCropLabel = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropShowLabel, cropImageOptions.showCropLabel);
                    this.isSaveBitmapToInstanceState = obtainStyledAttributes.getBoolean(C1150R.styleable.CropImageView_cropSaveBitmapToInstanceState, this.isSaveBitmapToInstanceState);
                    if (obtainStyledAttributes.hasValue(C1150R.styleable.CropImageView_cropAspectRatioX) && obtainStyledAttributes.hasValue(C1150R.styleable.CropImageView_cropAspectRatioX) && !obtainStyledAttributes.hasValue(C1150R.styleable.CropImageView_cropFixAspectRatio)) {
                        cropImageOptions.fixAspectRatio = true;
                    }
                } finally {
                    obtainStyledAttributes.recycle();
                }
            }
        }
        cropImageOptions.validate();
        this.mScaleType = cropImageOptions.scaleType;
        this.mAutoZoomEnabled = cropImageOptions.autoZoomEnabled;
        this.mMaxZoom = cropImageOptions.maxZoom;
        this.mCropLabelTextSize = cropImageOptions.cropperLabelTextSize;
        this.mShowCropLabel = cropImageOptions.showCropLabel;
        this.mShowCropOverlay = cropImageOptions.showCropOverlay;
        this.mShowProgressBar = cropImageOptions.showProgressBar;
        this.mFlipHorizontally = cropImageOptions.flipHorizontally;
        this.mFlipVertically = cropImageOptions.flipVertically;
        View inflate = LayoutInflater.from(context).inflate(C1150R.layout.crop_image_view, (ViewGroup) this, true);
        View findViewById = inflate.findViewById(C1150R.C1154id.ImageView_image);
        Intrinsics.checkNotNullExpressionValue(findViewById, "v.findViewById(R.id.ImageView_image)");
        ImageView imageView = (ImageView) findViewById;
        this.imageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        CropOverlayView cropOverlayView = (CropOverlayView) inflate.findViewById(C1150R.C1154id.CropOverlayView);
        this.mCropOverlayView = cropOverlayView;
        cropOverlayView.setCropWindowChangeListener(this);
        cropOverlayView.setInitialAttributeValues(cropImageOptions);
        View findViewById2 = inflate.findViewById(C1150R.C1154id.CropProgressBar);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "v.findViewById(R.id.CropProgressBar)");
        this.mProgressBar = (ProgressBar) findViewById2;
        setProgressBarVisibility();
    }

    public final boolean isSaveBitmapToInstanceState() {
        return this.isSaveBitmapToInstanceState;
    }

    public final void setSaveBitmapToInstanceState(boolean z) {
        this.isSaveBitmapToInstanceState = z;
    }

    public final Uri getImageUri() {
        return this.imageUri;
    }

    public final ScaleType getScaleType() {
        return this.mScaleType;
    }

    public final void setScaleType(ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        if (scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            this.mZoom = 1.0f;
            this.mZoomOffsetY = 0.0f;
            this.mZoomOffsetX = 0.0f;
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
            }
            requestLayout();
        }
    }

    public final CropShape getCropShape() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.getCropShape();
    }

    public final void setCropShape(CropShape cropShape) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        Intrinsics.checkNotNull(cropShape);
        cropOverlayView.setCropShape(cropShape);
    }

    public final CropCornerShape getCornerShape() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.getCornerShape();
    }

    public final void setCornerShape(CropCornerShape cropCornerShape) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        Intrinsics.checkNotNull(cropCornerShape);
        cropOverlayView.setCropCornerShape(cropCornerShape);
    }

    public final boolean isAutoZoomEnabled() {
        return this.mAutoZoomEnabled;
    }

    public final void setAutoZoomEnabled(boolean z) {
        if (this.mAutoZoomEnabled != z) {
            this.mAutoZoomEnabled = z;
            handleCropWindowChanged(false, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.invalidate();
        }
    }

    public final void setMultiTouchEnabled(boolean z) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        if (cropOverlayView.setMultiTouchEnabled(z)) {
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public final void setCenterMoveEnabled(boolean z) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        if (cropOverlayView.setCenterMoveEnabled(z)) {
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public final int getMaxZoom() {
        return this.mMaxZoom;
    }

    public final void setMaxZoom(int r2) {
        if (this.mMaxZoom == r2 || r2 <= 0) {
            return;
        }
        this.mMaxZoom = r2;
        handleCropWindowChanged(false, false);
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.invalidate();
    }

    public final void setMinCropResultSize(int r2, int r3) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setMinCropResultSize(r2, r3);
    }

    public final void setMaxCropResultSize(int r2, int r3) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setMaxCropResultSize(r2, r3);
    }

    public final int getRotatedDegrees() {
        return this.mDegreesRotated;
    }

    public final void setRotatedDegrees(int r2) {
        int r0 = this.mDegreesRotated;
        if (r0 != r2) {
            rotateImage(r2 - r0);
        }
    }

    public final boolean isFixAspectRatio() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.isFixAspectRatio();
    }

    public final void setFixedAspectRatio(boolean z) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setFixedAspectRatio(z);
    }

    public final boolean isFlippedHorizontally() {
        return this.mFlipHorizontally;
    }

    public final void setFlippedHorizontally(boolean z) {
        if (this.mFlipHorizontally != z) {
            this.mFlipHorizontally = z;
            applyImageMatrix(getWidth(), getHeight(), true, false);
        }
    }

    public final Uri getCustomOutputUri() {
        return this.customOutputUri;
    }

    public final void setCustomOutputUri(Uri uri) {
        this.customOutputUri = uri;
    }

    public final boolean isFlippedVertically() {
        return this.mFlipVertically;
    }

    public final void setFlippedVertically(boolean z) {
        if (this.mFlipVertically != z) {
            this.mFlipVertically = z;
            applyImageMatrix(getWidth(), getHeight(), true, false);
        }
    }

    public final Guidelines getGuidelines() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return cropOverlayView.getGuidelines();
    }

    public final void setGuidelines(Guidelines guidelines) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        Intrinsics.checkNotNull(guidelines);
        cropOverlayView.setGuidelines(guidelines);
    }

    public final Pair<Integer, Integer> getAspectRatio() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return new Pair<>(Integer.valueOf(cropOverlayView.getAspectRatioX()), Integer.valueOf(this.mCropOverlayView.getAspectRatioY()));
    }

    public final void setAspectRatio(int r2, int r3) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setAspectRatioX(r2);
        this.mCropOverlayView.setAspectRatioY(r3);
        setFixedAspectRatio(true);
    }

    public final void clearAspectRatio() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setAspectRatioX(1);
        this.mCropOverlayView.setAspectRatioY(1);
        setFixedAspectRatio(false);
    }

    public final void setSnapRadius(float f) {
        if (f >= 0.0f) {
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setSnapRadius(f);
        }
    }

    public final boolean isShowProgressBar() {
        return this.mShowProgressBar;
    }

    public final void setShowProgressBar(boolean z) {
        if (this.mShowProgressBar != z) {
            this.mShowProgressBar = z;
            setProgressBarVisibility();
        }
    }

    public final boolean isShowCropOverlay() {
        return this.mShowCropOverlay;
    }

    public final void setShowCropOverlay(boolean z) {
        if (this.mShowCropOverlay != z) {
            this.mShowCropOverlay = z;
            setCropOverlayVisibility();
        }
    }

    public final boolean isShowCropLabel() {
        return this.mShowCropLabel;
    }

    public final void setShowCropLabel(boolean z) {
        if (this.mShowCropLabel != z) {
            this.mShowCropLabel = z;
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView == null) {
                return;
            }
            cropOverlayView.setCropperTextLabelVisibility(z);
        }
    }

    public final String getCropLabelText() {
        return this.mCropTextLabel;
    }

    public final void setCropLabelText(String cropLabelText) {
        Intrinsics.checkNotNullParameter(cropLabelText, "cropLabelText");
        this.mCropTextLabel = cropLabelText;
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView == null) {
            return;
        }
        cropOverlayView.setCropLabelText(cropLabelText);
    }

    public final float getCropLabelTextSize() {
        return this.mCropLabelTextSize;
    }

    public final void setCropLabelTextSize(float f) {
        this.mCropLabelTextSize = getCropLabelTextSize();
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView == null) {
            return;
        }
        cropOverlayView.setCropLabelTextSize(f);
    }

    public final int getCropLabelTextColor() {
        return this.mCropLabelTextColor;
    }

    public final void setCropLabelTextColor(int r2) {
        this.mCropLabelTextColor = r2;
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView == null) {
            return;
        }
        cropOverlayView.setCropLabelTextColor(r2);
    }

    public final int getImageResource() {
        return this.mImageResource;
    }

    public final void setImageResource(int r8) {
        if (r8 != 0) {
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setInitialCropWindowRect(null);
            setBitmap(BitmapFactory.decodeResource(getResources(), r8), r8, null, 1, 0);
        }
    }

    public final Rect getWholeImageRect() {
        int r0 = this.loadedSampleSize;
        Bitmap bitmap = this.originalBitmap;
        if (bitmap == null) {
            return null;
        }
        return new Rect(0, 0, bitmap.getWidth() * r0, bitmap.getHeight() * r0);
    }

    public final Rect getCropRect() {
        int r0 = this.loadedSampleSize;
        Bitmap bitmap = this.originalBitmap;
        if (bitmap == null) {
            return null;
        }
        float[] cropPoints = getCropPoints();
        int width = bitmap.getWidth() * r0;
        int height = bitmap.getHeight() * r0;
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        return bitmapUtils.getRectFromPoints(cropPoints, width, height, cropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY());
    }

    public final void setCropRect(Rect rect) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setInitialCropWindowRect(rect);
    }

    public final RectF getCropWindowRect() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView == null) {
            return null;
        }
        return cropOverlayView.getCropWindowRect();
    }

    public final float[] getCropPoints() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        RectF cropWindowRect = cropOverlayView.getCropWindowRect();
        int r4 = 0;
        float[] fArr = {cropWindowRect.left, cropWindowRect.top, cropWindowRect.right, cropWindowRect.top, cropWindowRect.right, cropWindowRect.bottom, cropWindowRect.left, cropWindowRect.bottom};
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapPoints(fArr);
        float[] fArr2 = new float[8];
        while (true) {
            int r1 = r4 + 1;
            fArr2[r4] = fArr[r4] * this.loadedSampleSize;
            if (r1 > 7) {
                return fArr2;
            }
            r4 = r1;
        }
    }

    public final void resetCropRect() {
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mDegreesRotated = this.mInitialDegreesRotated;
        this.mFlipHorizontally = false;
        this.mFlipVertically = false;
        applyImageMatrix(getWidth(), getHeight(), false, false);
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.resetCropWindowRect();
    }

    public final Bitmap getCroppedImage() {
        return getCroppedImage(0, 0, RequestSizeOptions.NONE);
    }

    public final Bitmap getCroppedImage(int r2, int r3) {
        return getCroppedImage(r2, r3, RequestSizeOptions.RESIZE_INSIDE);
    }

    public final Bitmap getCroppedImage(int r19, int r20, RequestSizeOptions options) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(options, "options");
        if (this.originalBitmap != null) {
            int r2 = options != RequestSizeOptions.NONE ? r19 : 0;
            int r3 = options != RequestSizeOptions.NONE ? r20 : 0;
            if (this.imageUri != null && (this.loadedSampleSize > 1 || options == RequestSizeOptions.SAMPLING)) {
                Bitmap bitmap2 = this.originalBitmap;
                Intrinsics.checkNotNull(bitmap2);
                int width = bitmap2.getWidth() * this.loadedSampleSize;
                Bitmap bitmap3 = this.originalBitmap;
                Intrinsics.checkNotNull(bitmap3);
                int height = bitmap3.getHeight() * this.loadedSampleSize;
                BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                Uri uri = this.imageUri;
                float[] cropPoints = getCropPoints();
                int r8 = this.mDegreesRotated;
                CropOverlayView cropOverlayView = this.mCropOverlayView;
                Intrinsics.checkNotNull(cropOverlayView);
                bitmap = bitmapUtils.cropBitmap(context, uri, cropPoints, r8, width, height, cropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), r2, r3, this.mFlipHorizontally, this.mFlipVertically).getBitmap();
            } else {
                BitmapUtils bitmapUtils2 = BitmapUtils.INSTANCE;
                Bitmap bitmap4 = this.originalBitmap;
                float[] cropPoints2 = getCropPoints();
                int r82 = this.mDegreesRotated;
                CropOverlayView cropOverlayView2 = this.mCropOverlayView;
                Intrinsics.checkNotNull(cropOverlayView2);
                bitmap = bitmapUtils2.cropBitmapObjectHandleOOM(bitmap4, cropPoints2, r82, cropOverlayView2.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), this.mFlipHorizontally, this.mFlipVertically).getBitmap();
            }
            return BitmapUtils.INSTANCE.resizeBitmap(bitmap, r2, r3, options);
        }
        return null;
    }

    public static /* synthetic */ void croppedImageAsync$default(CropImageView cropImageView, Bitmap.CompressFormat compressFormat, int r6, int r7, int r8, RequestSizeOptions requestSizeOptions, Uri uri, int r11, Object obj) {
        if ((r11 & 1) != 0) {
            compressFormat = Bitmap.CompressFormat.JPEG;
        }
        int r12 = (r11 & 2) != 0 ? 90 : r6;
        int r1 = (r11 & 4) != 0 ? 0 : r7;
        int r0 = (r11 & 8) == 0 ? r8 : 0;
        if ((r11 & 16) != 0) {
            requestSizeOptions = RequestSizeOptions.RESIZE_INSIDE;
        }
        RequestSizeOptions requestSizeOptions2 = requestSizeOptions;
        if ((r11 & 32) != 0) {
            uri = null;
        }
        cropImageView.croppedImageAsync(compressFormat, r12, r1, r0, requestSizeOptions2, uri);
    }

    public final void croppedImageAsync(Bitmap.CompressFormat saveCompressFormat, int r9, int r10, int r11, RequestSizeOptions options, Uri uri) {
        Intrinsics.checkNotNullParameter(saveCompressFormat, "saveCompressFormat");
        Intrinsics.checkNotNullParameter(options, "options");
        if (this.mOnCropImageCompleteListener == null) {
            throw new IllegalArgumentException("mOnCropImageCompleteListener is not set".toString());
        }
        startCropWorkerTask(r10, r11, options, saveCompressFormat, r9, uri);
    }

    public final void setOnSetCropOverlayReleasedListener(OnSetCropOverlayReleasedListener onSetCropOverlayReleasedListener) {
        this.mOnCropOverlayReleasedListener = onSetCropOverlayReleasedListener;
    }

    public final void setOnSetCropOverlayMovedListener(OnSetCropOverlayMovedListener onSetCropOverlayMovedListener) {
        this.mOnSetCropOverlayMovedListener = onSetCropOverlayMovedListener;
    }

    public final void setOnCropWindowChangedListener(OnSetCropWindowChangeListener onSetCropWindowChangeListener) {
        this.mOnSetCropWindowChangeListener = onSetCropWindowChangeListener;
    }

    public final void setOnSetImageUriCompleteListener(OnSetImageUriCompleteListener onSetImageUriCompleteListener) {
        this.mOnSetImageUriCompleteListener = onSetImageUriCompleteListener;
    }

    public final void setOnCropImageCompleteListener(OnCropImageCompleteListener onCropImageCompleteListener) {
        this.mOnCropImageCompleteListener = onCropImageCompleteListener;
    }

    public final void setImageBitmap(Bitmap bitmap) {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setInitialCropWindowRect(null);
        setBitmap(bitmap, 0, null, 1, 0);
    }

    public final void setImageBitmap(Bitmap bitmap, ExifInterface exifInterface) {
        Bitmap bitmap2;
        int r6;
        if (bitmap == null || exifInterface == null) {
            bitmap2 = bitmap;
            r6 = 0;
        } else {
            BitmapUtils.RotateBitmapResult rotateBitmapByExif = BitmapUtils.INSTANCE.rotateBitmapByExif(bitmap, exifInterface);
            Bitmap bitmap3 = rotateBitmapByExif.getBitmap();
            int degrees = rotateBitmapByExif.getDegrees();
            this.mInitialDegreesRotated = rotateBitmapByExif.getDegrees();
            bitmap2 = bitmap3;
            r6 = degrees;
        }
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        cropOverlayView.setInitialCropWindowRect(null);
        setBitmap(bitmap2, 0, null, 1, r6);
    }

    public final void setImageUriAsync(Uri uri) {
        BitmapLoadingWorkerJob bitmapLoadingWorkerJob;
        if (uri != null) {
            WeakReference<BitmapLoadingWorkerJob> weakReference = this.bitmapLoadingWorkerJob;
            if (weakReference != null) {
                Intrinsics.checkNotNull(weakReference);
                bitmapLoadingWorkerJob = weakReference.get();
            } else {
                bitmapLoadingWorkerJob = null;
            }
            if (bitmapLoadingWorkerJob != null) {
                bitmapLoadingWorkerJob.cancel();
            }
            clearImageInt();
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setInitialCropWindowRect(null);
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            WeakReference<BitmapLoadingWorkerJob> weakReference2 = new WeakReference<>(new BitmapLoadingWorkerJob(context, this, uri));
            this.bitmapLoadingWorkerJob = weakReference2;
            Intrinsics.checkNotNull(weakReference2);
            BitmapLoadingWorkerJob bitmapLoadingWorkerJob2 = weakReference2.get();
            Intrinsics.checkNotNull(bitmapLoadingWorkerJob2);
            bitmapLoadingWorkerJob2.start();
            setProgressBarVisibility();
        }
    }

    public final void clearImage() {
        clearImageInt();
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView == null) {
            return;
        }
        cropOverlayView.setInitialCropWindowRect(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0036, code lost:
        if ((216 <= r1 && r1 <= 304) != false) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void rotateImage(int r17) {
        /*
            Method dump skipped, instructions count: 487
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageView.rotateImage(int):void");
    }

    public final void flipImageHorizontally() {
        this.mFlipHorizontally = !this.mFlipHorizontally;
        applyImageMatrix(getWidth(), getHeight(), true, false);
    }

    public final void flipImageVertically() {
        this.mFlipVertically = !this.mFlipVertically;
        applyImageMatrix(getWidth(), getHeight(), true, false);
    }

    public final void onSetImageUriAsyncComplete(BitmapLoadingWorkerJob.Result result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.bitmapLoadingWorkerJob = null;
        setProgressBarVisibility();
        if (result.getError() == null) {
            this.mInitialDegreesRotated = result.getDegreesRotated();
            setBitmap(result.getBitmap(), 0, result.getUriContent(), result.getLoadSampleSize(), result.getDegreesRotated());
        }
        OnSetImageUriCompleteListener onSetImageUriCompleteListener = this.mOnSetImageUriCompleteListener;
        if (onSetImageUriCompleteListener == null) {
            return;
        }
        onSetImageUriCompleteListener.onSetImageUriComplete(this, result.getUriContent(), result.getError());
    }

    public final void onImageCroppingAsyncComplete(BitmapCroppingWorkerJob.Result result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.bitmapCroppingWorkerJob = null;
        setProgressBarVisibility();
        OnCropImageCompleteListener onCropImageCompleteListener = this.mOnCropImageCompleteListener;
        if (onCropImageCompleteListener != null) {
            onCropImageCompleteListener.onCropImageComplete(this, new CropResult(this.originalBitmap, this.imageUri, result.getBitmap(), result.getUri(), result.getError(), getCropPoints(), getCropRect(), getWholeImageRect(), getRotatedDegrees(), result.getSampleSize()));
        }
    }

    private final void setBitmap(Bitmap bitmap, int r3, Uri uri, int r5, int r6) {
        Bitmap bitmap2 = this.originalBitmap;
        if (bitmap2 == null || !Intrinsics.areEqual(bitmap2, bitmap)) {
            clearImageInt();
            this.originalBitmap = bitmap;
            this.imageView.setImageBitmap(bitmap);
            this.imageUri = uri;
            this.mImageResource = r3;
            this.loadedSampleSize = r5;
            this.mDegreesRotated = r6;
            applyImageMatrix(getWidth(), getHeight(), true, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
                setCropOverlayVisibility();
            }
        }
    }

    private final void clearImageInt() {
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null && (this.mImageResource > 0 || this.imageUri != null)) {
            Intrinsics.checkNotNull(bitmap);
            bitmap.recycle();
        }
        this.originalBitmap = null;
        this.mImageResource = 0;
        this.imageUri = null;
        this.loadedSampleSize = 1;
        this.mDegreesRotated = 0;
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mImageMatrix.reset();
        this.mRestoreCropWindowRect = null;
        this.mRestoreDegreesRotated = 0;
        this.imageView.setImageBitmap(null);
        setCropOverlayVisibility();
    }

    public final void startCropWorkerTask(int r25, int r26, RequestSizeOptions options, Bitmap.CompressFormat saveCompressFormat, int r29, Uri uri) {
        BitmapCroppingWorkerJob bitmapCroppingWorkerJob;
        Pair pair;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(saveCompressFormat, "saveCompressFormat");
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null) {
            WeakReference<BitmapCroppingWorkerJob> weakReference = this.bitmapCroppingWorkerJob;
            if (weakReference != null) {
                Intrinsics.checkNotNull(weakReference);
                bitmapCroppingWorkerJob = weakReference.get();
            } else {
                bitmapCroppingWorkerJob = null;
            }
            if (bitmapCroppingWorkerJob != null) {
                bitmapCroppingWorkerJob.cancel();
            }
            if (this.loadedSampleSize > 1 || options == RequestSizeOptions.SAMPLING) {
                pair = new Pair(Integer.valueOf(bitmap.getWidth() * this.loadedSampleSize), Integer.valueOf(bitmap.getHeight() * this.loadedSampleSize));
            } else {
                pair = new Pair(0, 0);
            }
            Integer orgWidth = (Integer) pair.first;
            Integer orgHeight = (Integer) pair.second;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            WeakReference weakReference2 = new WeakReference(this);
            Uri uri2 = this.imageUri;
            float[] cropPoints = getCropPoints();
            int r9 = this.mDegreesRotated;
            Intrinsics.checkNotNullExpressionValue(orgWidth, "orgWidth");
            int intValue = orgWidth.intValue();
            Intrinsics.checkNotNullExpressionValue(orgHeight, "orgHeight");
            int intValue2 = orgHeight.intValue();
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            WeakReference<BitmapCroppingWorkerJob> weakReference3 = new WeakReference<>(new BitmapCroppingWorkerJob(context, weakReference2, uri2, bitmap, cropPoints, r9, intValue, intValue2, cropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), options != RequestSizeOptions.NONE ? r25 : 0, options != RequestSizeOptions.NONE ? r26 : 0, this.mFlipHorizontally, this.mFlipVertically, options, saveCompressFormat, r29, uri));
            this.bitmapCroppingWorkerJob = weakReference3;
            Intrinsics.checkNotNull(weakReference3);
            BitmapCroppingWorkerJob bitmapCroppingWorkerJob2 = weakReference3.get();
            Intrinsics.checkNotNull(bitmapCroppingWorkerJob2);
            bitmapCroppingWorkerJob2.start();
            setProgressBarVisibility();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Uri uri;
        if (this.imageUri == null && this.originalBitmap == null && this.mImageResource < 1) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        if (this.isSaveBitmapToInstanceState && this.imageUri == null && this.mImageResource < 1) {
            BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            uri = bitmapUtils.writeTempStateStoreBitmap(context, this.originalBitmap, this.customOutputUri);
        } else {
            uri = this.imageUri;
        }
        if (uri != null && this.originalBitmap != null) {
            String str = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(str, "randomUUID().toString()");
            BitmapUtils.INSTANCE.setMStateBitmap(new Pair<>(str, new WeakReference(this.originalBitmap)));
            bundle.putString("LOADED_IMAGE_STATE_BITMAP_KEY", str);
        }
        WeakReference<BitmapLoadingWorkerJob> weakReference = this.bitmapLoadingWorkerJob;
        if (weakReference != null) {
            Intrinsics.checkNotNull(weakReference);
            BitmapLoadingWorkerJob bitmapLoadingWorkerJob = weakReference.get();
            if (bitmapLoadingWorkerJob != null) {
                bundle.putParcelable("LOADING_IMAGE_URI", bitmapLoadingWorkerJob.getUri());
            }
        }
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putParcelable("LOADED_IMAGE_URI", uri);
        bundle.putInt("LOADED_IMAGE_RESOURCE", this.mImageResource);
        bundle.putInt("LOADED_SAMPLE_SIZE", this.loadedSampleSize);
        bundle.putInt("DEGREES_ROTATED", this.mDegreesRotated);
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView);
        bundle.putParcelable("INITIAL_CROP_RECT", cropOverlayView.getInitialCropWindowRect());
        BitmapUtils.INSTANCE.getRECT().set(this.mCropOverlayView.getCropWindowRect());
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapRect(BitmapUtils.INSTANCE.getRECT());
        bundle.putParcelable("CROP_WINDOW_RECT", BitmapUtils.INSTANCE.getRECT());
        CropShape cropShape = this.mCropOverlayView.getCropShape();
        Intrinsics.checkNotNull(cropShape);
        bundle.putString("CROP_SHAPE", cropShape.name());
        bundle.putBoolean("CROP_AUTO_ZOOM_ENABLED", this.mAutoZoomEnabled);
        bundle.putInt("CROP_MAX_ZOOM", this.mMaxZoom);
        bundle.putBoolean("CROP_FLIP_HORIZONTALLY", this.mFlipHorizontally);
        bundle.putBoolean("CROP_FLIP_VERTICALLY", this.mFlipVertically);
        bundle.putBoolean("SHOW_CROP_LABEL", this.mShowCropLabel);
        return bundle;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(state, "state");
        if (state instanceof Bundle) {
            if (this.bitmapLoadingWorkerJob == null && this.imageUri == null && this.originalBitmap == null && this.mImageResource == 0) {
                Bundle bundle = (Bundle) state;
                Parcelable parcelable = bundle.getParcelable("LOADED_IMAGE_URI");
                if (parcelable != null) {
                    String string = bundle.getString("LOADED_IMAGE_STATE_BITMAP_KEY");
                    if (string != null) {
                        Pair<String, WeakReference<Bitmap>> mStateBitmap = BitmapUtils.INSTANCE.getMStateBitmap();
                        if (mStateBitmap == null) {
                            bitmap = null;
                        } else {
                            bitmap = Intrinsics.areEqual(mStateBitmap.first, string) ? (Bitmap) ((WeakReference) mStateBitmap.second).get() : null;
                        }
                        BitmapUtils.INSTANCE.setMStateBitmap(null);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            setBitmap(bitmap, 0, (Uri) parcelable, bundle.getInt("LOADED_SAMPLE_SIZE"), 0);
                        }
                    }
                    if (this.imageUri == null) {
                        setImageUriAsync((Uri) parcelable);
                    }
                } else {
                    int r1 = bundle.getInt("LOADED_IMAGE_RESOURCE");
                    if (r1 > 0) {
                        setImageResource(r1);
                    } else {
                        Uri uri = (Uri) bundle.getParcelable("LOADING_IMAGE_URI");
                        if (uri != null) {
                            setImageUriAsync(uri);
                        }
                    }
                }
                int r12 = bundle.getInt("DEGREES_ROTATED");
                this.mRestoreDegreesRotated = r12;
                this.mDegreesRotated = r12;
                Rect rect = (Rect) bundle.getParcelable("INITIAL_CROP_RECT");
                if (rect != null && (rect.width() > 0 || rect.height() > 0)) {
                    CropOverlayView cropOverlayView = this.mCropOverlayView;
                    Intrinsics.checkNotNull(cropOverlayView);
                    cropOverlayView.setInitialCropWindowRect(rect);
                }
                RectF rectF = (RectF) bundle.getParcelable("CROP_WINDOW_RECT");
                if (rectF != null && (rectF.width() > 0.0f || rectF.height() > 0.0f)) {
                    this.mRestoreCropWindowRect = rectF;
                }
                CropOverlayView cropOverlayView2 = this.mCropOverlayView;
                Intrinsics.checkNotNull(cropOverlayView2);
                String string2 = bundle.getString("CROP_SHAPE");
                Intrinsics.checkNotNull(string2);
                Intrinsics.checkNotNullExpressionValue(string2, "state.getString(\"CROP_SHAPE\")!!");
                cropOverlayView2.setCropShape(CropShape.valueOf(string2));
                this.mAutoZoomEnabled = bundle.getBoolean("CROP_AUTO_ZOOM_ENABLED");
                this.mMaxZoom = bundle.getInt("CROP_MAX_ZOOM");
                this.mFlipHorizontally = bundle.getBoolean("CROP_FLIP_HORIZONTALLY");
                this.mFlipVertically = bundle.getBoolean("CROP_FLIP_VERTICALLY");
                boolean z = bundle.getBoolean("SHOW_CROP_LABEL");
                this.mShowCropLabel = z;
                this.mCropOverlayView.setCropperTextLabelVisibility(z);
            }
            super.onRestoreInstanceState(((Bundle) state).getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int r13, int r14) {
        int width;
        int r2;
        super.onMeasure(r13, r14);
        int mode = View.MeasureSpec.getMode(r13);
        int size = View.MeasureSpec.getSize(r13);
        int mode2 = View.MeasureSpec.getMode(r14);
        int size2 = View.MeasureSpec.getSize(r14);
        Bitmap bitmap = this.originalBitmap;
        if (bitmap != null) {
            if (size2 == 0) {
                size2 = bitmap.getHeight();
            }
            double width2 = size < bitmap.getWidth() ? size / bitmap.getWidth() : Double.POSITIVE_INFINITY;
            double height = size2 < bitmap.getHeight() ? size2 / bitmap.getHeight() : Double.POSITIVE_INFINITY;
            if (width2 == Double.POSITIVE_INFINITY) {
                if (height == Double.POSITIVE_INFINITY) {
                    width = bitmap.getWidth();
                    r2 = bitmap.getHeight();
                    Companion companion = Companion;
                    int onMeasureSpec = companion.getOnMeasureSpec(mode, size, width);
                    int onMeasureSpec2 = companion.getOnMeasureSpec(mode2, size2, r2);
                    this.mLayoutWidth = onMeasureSpec;
                    this.mLayoutHeight = onMeasureSpec2;
                    setMeasuredDimension(onMeasureSpec, onMeasureSpec2);
                    return;
                }
            }
            if (width2 <= height) {
                r2 = (int) (bitmap.getHeight() * width2);
                width = size;
            } else {
                width = (int) (bitmap.getWidth() * height);
                r2 = size2;
            }
            Companion companion2 = Companion;
            int onMeasureSpec3 = companion2.getOnMeasureSpec(mode, size, width);
            int onMeasureSpec22 = companion2.getOnMeasureSpec(mode2, size2, r2);
            this.mLayoutWidth = onMeasureSpec3;
            this.mLayoutHeight = onMeasureSpec22;
            setMeasuredDimension(onMeasureSpec3, onMeasureSpec22);
            return;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r4, int r5, int r6, int r7) {
        super.onLayout(z, r4, r5, r6, r7);
        if (this.mLayoutWidth > 0 && this.mLayoutHeight > 0) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.mLayoutWidth;
            layoutParams.height = this.mLayoutHeight;
            setLayoutParams(layoutParams);
            if (this.originalBitmap != null) {
                float f = r6 - r4;
                float f2 = r7 - r5;
                applyImageMatrix(f, f2, true, false);
                RectF rectF = this.mRestoreCropWindowRect;
                if (rectF != null) {
                    int r72 = this.mRestoreDegreesRotated;
                    if (r72 != this.mInitialDegreesRotated) {
                        this.mDegreesRotated = r72;
                        applyImageMatrix(f, f2, true, false);
                        this.mRestoreDegreesRotated = 0;
                    }
                    this.mImageMatrix.mapRect(this.mRestoreCropWindowRect);
                    CropOverlayView cropOverlayView = this.mCropOverlayView;
                    if (cropOverlayView != null) {
                        cropOverlayView.setCropWindowRect(rectF);
                    }
                    handleCropWindowChanged(false, false);
                    CropOverlayView cropOverlayView2 = this.mCropOverlayView;
                    if (cropOverlayView2 != null) {
                        cropOverlayView2.fixCurrentCropWindowRect();
                    }
                    this.mRestoreCropWindowRect = null;
                    return;
                } else if (this.mSizeChanged) {
                    this.mSizeChanged = false;
                    handleCropWindowChanged(false, false);
                    return;
                } else {
                    return;
                }
            }
            updateImageBounds(true);
            return;
        }
        updateImageBounds(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.mSizeChanged = r3 > 0 && r4 > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void handleCropWindowChanged(boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageView.handleCropWindowChanged(boolean, boolean):void");
    }

    private final void applyImageMatrix(float f, float f2, boolean z, boolean z2) {
        Bitmap bitmap;
        if (this.originalBitmap != null) {
            if (f <= 0.0f || f2 <= 0.0f) {
                return;
            }
            this.mImageMatrix.invert(this.mImageInverseMatrix);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            RectF cropWindowRect = cropOverlayView.getCropWindowRect();
            this.mImageInverseMatrix.mapRect(cropWindowRect);
            this.mImageMatrix.reset();
            float f3 = 2;
            this.mImageMatrix.postTranslate((f - bitmap.getWidth()) / f3, (f2 - bitmap.getHeight()) / f3);
            mapImagePointsByImageMatrix();
            int r0 = this.mDegreesRotated;
            if (r0 > 0) {
                this.mImageMatrix.postRotate(r0, BitmapUtils.INSTANCE.getRectCenterX(this.mImagePoints), BitmapUtils.INSTANCE.getRectCenterY(this.mImagePoints));
                mapImagePointsByImageMatrix();
            }
            float min = Math.min(f / BitmapUtils.INSTANCE.getRectWidth(this.mImagePoints), f2 / BitmapUtils.INSTANCE.getRectHeight(this.mImagePoints));
            if (this.mScaleType == ScaleType.FIT_CENTER || ((this.mScaleType == ScaleType.CENTER_INSIDE && min < 1.0f) || (min > 1.0f && this.mAutoZoomEnabled))) {
                this.mImageMatrix.postScale(min, min, BitmapUtils.INSTANCE.getRectCenterX(this.mImagePoints), BitmapUtils.INSTANCE.getRectCenterY(this.mImagePoints));
                mapImagePointsByImageMatrix();
            } else if (this.mScaleType == ScaleType.CENTER_CROP) {
                this.mZoom = Math.max(getWidth() / BitmapUtils.INSTANCE.getRectWidth(this.mImagePoints), getHeight() / BitmapUtils.INSTANCE.getRectHeight(this.mImagePoints));
            }
            float f4 = this.mFlipHorizontally ? -this.mZoom : this.mZoom;
            float f5 = this.mFlipVertically ? -this.mZoom : this.mZoom;
            this.mImageMatrix.postScale(f4, f5, BitmapUtils.INSTANCE.getRectCenterX(this.mImagePoints), BitmapUtils.INSTANCE.getRectCenterY(this.mImagePoints));
            mapImagePointsByImageMatrix();
            this.mImageMatrix.mapRect(cropWindowRect);
            if (this.mScaleType == ScaleType.CENTER_CROP && z && !z2) {
                this.mZoomOffsetX = 0.0f;
                this.mZoomOffsetY = 0.0f;
            } else if (z) {
                this.mZoomOffsetX = f > BitmapUtils.INSTANCE.getRectWidth(this.mImagePoints) ? 0.0f : Math.max(Math.min((f / f3) - cropWindowRect.centerX(), -BitmapUtils.INSTANCE.getRectLeft(this.mImagePoints)), getWidth() - BitmapUtils.INSTANCE.getRectRight(this.mImagePoints)) / f4;
                this.mZoomOffsetY = f2 <= BitmapUtils.INSTANCE.getRectHeight(this.mImagePoints) ? Math.max(Math.min((f2 / f3) - cropWindowRect.centerY(), -BitmapUtils.INSTANCE.getRectTop(this.mImagePoints)), getHeight() - BitmapUtils.INSTANCE.getRectBottom(this.mImagePoints)) / f5 : 0.0f;
            } else {
                this.mZoomOffsetX = Math.min(Math.max(this.mZoomOffsetX * f4, -cropWindowRect.left), (-cropWindowRect.right) + f) / f4;
                this.mZoomOffsetY = Math.min(Math.max(this.mZoomOffsetY * f5, -cropWindowRect.top), (-cropWindowRect.bottom) + f2) / f5;
            }
            this.mImageMatrix.postTranslate(this.mZoomOffsetX * f4, this.mZoomOffsetY * f5);
            cropWindowRect.offset(this.mZoomOffsetX * f4, this.mZoomOffsetY * f5);
            this.mCropOverlayView.setCropWindowRect(cropWindowRect);
            mapImagePointsByImageMatrix();
            this.mCropOverlayView.invalidate();
            if (z2) {
                CropImageAnimation cropImageAnimation = this.mAnimation;
                Intrinsics.checkNotNull(cropImageAnimation);
                cropImageAnimation.setEndState(this.mImagePoints, this.mImageMatrix);
                this.imageView.startAnimation(this.mAnimation);
            } else {
                this.imageView.setImageMatrix(this.mImageMatrix);
            }
            updateImageBounds(false);
        }
    }

    private final void mapImagePointsByImageMatrix() {
        float[] fArr = this.mImagePoints;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        Bitmap bitmap = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap);
        fArr[2] = bitmap.getWidth();
        float[] fArr2 = this.mImagePoints;
        fArr2[3] = 0.0f;
        Bitmap bitmap2 = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap2);
        fArr2[4] = bitmap2.getWidth();
        float[] fArr3 = this.mImagePoints;
        Bitmap bitmap3 = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap3);
        fArr3[5] = bitmap3.getHeight();
        float[] fArr4 = this.mImagePoints;
        fArr4[6] = 0.0f;
        Bitmap bitmap4 = this.originalBitmap;
        Intrinsics.checkNotNull(bitmap4);
        fArr4[7] = bitmap4.getHeight();
        this.mImageMatrix.mapPoints(this.mImagePoints);
        float[] fArr5 = this.mScaleImagePoints;
        fArr5[0] = 0.0f;
        fArr5[1] = 0.0f;
        fArr5[2] = 100.0f;
        fArr5[3] = 0.0f;
        fArr5[4] = 100.0f;
        fArr5[5] = 100.0f;
        fArr5[6] = 0.0f;
        fArr5[7] = 100.0f;
        this.mImageMatrix.mapPoints(fArr5);
    }

    private final void setCropOverlayVisibility() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setVisibility((!this.mShowCropOverlay || this.originalBitmap == null) ? 4 : 0);
        }
    }

    private final void setProgressBarVisibility() {
        this.mProgressBar.setVisibility(this.mShowProgressBar && ((this.originalBitmap == null && this.bitmapLoadingWorkerJob != null) || this.bitmapCroppingWorkerJob != null) ? 0 : 4);
    }

    private final void updateImageBounds(boolean z) {
        if (this.originalBitmap != null && !z) {
            float rectWidth = (this.loadedSampleSize * 100.0f) / BitmapUtils.INSTANCE.getRectWidth(this.mScaleImagePoints);
            float rectHeight = (this.loadedSampleSize * 100.0f) / BitmapUtils.INSTANCE.getRectHeight(this.mScaleImagePoints);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            Intrinsics.checkNotNull(cropOverlayView);
            cropOverlayView.setCropWindowLimits(getWidth(), getHeight(), rectWidth, rectHeight);
        }
        CropOverlayView cropOverlayView2 = this.mCropOverlayView;
        Intrinsics.checkNotNull(cropOverlayView2);
        cropOverlayView2.setBounds(z ? null : this.mImagePoints, getWidth(), getHeight());
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001Bk\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010'\u001a\u00020(J\u001a\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010'\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020\u001dR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0012\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019¨\u0006,"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$CropResult;", "", "originalBitmap", "Landroid/graphics/Bitmap;", "originalUri", "Landroid/net/Uri;", "bitmap", "uriContent", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cropPoints", "", "cropRect", "Landroid/graphics/Rect;", "wholeImageRect", ViewProps.ROTATION, "", DecodeProducer.SAMPLE_SIZE, "(Landroid/graphics/Bitmap;Landroid/net/Uri;Landroid/graphics/Bitmap;Landroid/net/Uri;Ljava/lang/Exception;[FLandroid/graphics/Rect;Landroid/graphics/Rect;II)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getCropPoints", "()[F", "getCropRect", "()Landroid/graphics/Rect;", "getError", "()Ljava/lang/Exception;", "isSuccessful", "", "()Z", "getOriginalBitmap", "getOriginalUri", "()Landroid/net/Uri;", "getRotation", "()I", "getSampleSize", "getUriContent", "getWholeImageRect", "context", "Landroid/content/Context;", "getUriFilePath", "", "uniqueName", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static class CropResult {
        private final Bitmap bitmap;
        private final float[] cropPoints;
        private final Rect cropRect;
        private final Exception error;
        private final Bitmap originalBitmap;
        private final Uri originalUri;
        private final int rotation;
        private final int sampleSize;
        private final Uri uriContent;
        private final Rect wholeImageRect;

        public CropResult(Bitmap bitmap, Uri uri, Bitmap bitmap2, Uri uri2, Exception exc, float[] cropPoints, Rect rect, Rect rect2, int r10, int r11) {
            Intrinsics.checkNotNullParameter(cropPoints, "cropPoints");
            this.originalBitmap = bitmap;
            this.originalUri = uri;
            this.bitmap = bitmap2;
            this.uriContent = uri2;
            this.error = exc;
            this.cropPoints = cropPoints;
            this.cropRect = rect;
            this.wholeImageRect = rect2;
            this.rotation = r10;
            this.sampleSize = r11;
        }

        public final Bitmap getOriginalBitmap() {
            return this.originalBitmap;
        }

        public final Uri getOriginalUri() {
            return this.originalUri;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Uri getUriContent() {
            return this.uriContent;
        }

        public final Exception getError() {
            return this.error;
        }

        public final float[] getCropPoints() {
            return this.cropPoints;
        }

        public final Rect getCropRect() {
            return this.cropRect;
        }

        public final Rect getWholeImageRect() {
            return this.wholeImageRect;
        }

        public final int getRotation() {
            return this.rotation;
        }

        public final int getSampleSize() {
            return this.sampleSize;
        }

        public final boolean isSuccessful() {
            return this.error == null;
        }

        public final Bitmap getBitmap(Context context) {
            Bitmap bitmap;
            Intrinsics.checkNotNullParameter(context, "context");
            Bitmap bitmap2 = this.bitmap;
            if (bitmap2 == null) {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), this.uriContent);
                } catch (Exception unused) {
                    bitmap = null;
                }
                return bitmap;
            }
            return bitmap2;
        }

        public static /* synthetic */ String getUriFilePath$default(CropResult cropResult, Context context, boolean z, int r3, Object obj) {
            if (obj == null) {
                if ((r3 & 2) != 0) {
                    z = false;
                }
                return cropResult.getUriFilePath(context, z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUriFilePath");
        }

        public final String getUriFilePath(Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Uri uri = this.uriContent;
            if (uri == null) {
                return null;
            }
            return GetFilePathFromUri.getFilePathFromUri(context, uri, z);
        }
    }

    /* compiled from: CropImageView.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, m183d2 = {"Lcom/canhub/cropper/CropImageView$Companion;", "", "()V", "getOnMeasureSpec", "", "measureSpecMode", "measureSpecSize", "desiredSize", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getOnMeasureSpec(int r2, int r3, int r4) {
            if (r2 != Integer.MIN_VALUE) {
                return r2 != 1073741824 ? r4 : r3;
            }
            return Math.min(r4, r3);
        }
    }

    @Override // com.canhub.cropper.CropOverlayView.CropWindowChangeListener
    public void onCropWindowChanged(boolean z) {
        handleCropWindowChanged(z, true);
        OnSetCropOverlayReleasedListener onSetCropOverlayReleasedListener = this.mOnCropOverlayReleasedListener;
        if (onSetCropOverlayReleasedListener != null && !z) {
            onSetCropOverlayReleasedListener.onCropOverlayReleased(getCropRect());
        }
        OnSetCropOverlayMovedListener onSetCropOverlayMovedListener = this.mOnSetCropOverlayMovedListener;
        if (onSetCropOverlayMovedListener == null || !z) {
            return;
        }
        onSetCropOverlayMovedListener.onCropOverlayMoved(getCropRect());
    }
}
