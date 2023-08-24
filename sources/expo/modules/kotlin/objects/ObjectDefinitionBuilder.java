package expo.modules.kotlin.objects;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.EventsDefinition;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: ObjectDefinitionBuilder.kt */
@Metadata(m184d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0005J,\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052\u000e\b\u0004\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0\rH\u0086\bø\u0001\u0000J+\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00052\u0010\b\u0004\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH\u0087\bø\u0001\u0000¢\u0006\u0002\b!JI\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052#\b\u0004\u0010 \u001a\u001d\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002H\u001f0#H\u0086\bø\u0001\u0000Jf\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u000528\b\u0004\u0010 \u001a2\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H\u001f0'H\u0086\bø\u0001\u0000J\u0083\u0001\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052M\b\u0004\u0010 \u001aG\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u001f0*H\u0086\bø\u0001\u0000J \u0001\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052b\b\u0004\u0010 \u001a\\\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0004\u0012\u0002H\u001f0-H\u0086\bø\u0001\u0000J½\u0001\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052w\b\u0004\u0010 \u001aq\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0004\u0012\u0002H\u001f00H\u0086\bø\u0001\u0000JÜ\u0001\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u0001\"\u0006\b\u0006\u00102\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052\u008d\u0001\b\u0004\u0010 \u001a\u0086\u0001\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H2¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0004\u0012\u0002H\u001f03H\u0086\bø\u0001\u0000Jù\u0001\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u0001\"\u0006\b\u0006\u00102\u0018\u0001\"\u0006\b\u0007\u00105\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052¢\u0001\b\u0004\u0010 \u001a\u009b\u0001\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H2¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H5¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002H\u001f06H\u0086\bø\u0001\u0000J\u0096\u0002\u0010\u001d\u001a\u00020\u0006\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u0001\"\u0006\b\u0006\u00102\u0018\u0001\"\u0006\b\u0007\u00105\u0018\u0001\"\u0006\b\b\u00108\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052·\u0001\b\u0004\u0010 \u001a°\u0001\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H2¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H5¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(7\u0012\u0013\u0012\u0011H8¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(:\u0012\u0004\u0012\u0002H\u001f09H\u0086\bø\u0001\u0000J\"\u0010;\u001a\u00020<2\u001a\u0010\f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e0\rJ;\u0010;\u001a\u00020<2.\u0010=\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010?0>\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010?¢\u0006\u0002\u0010@J\u001d\u0010A\u001a\u00020<2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00050>H\u0007¢\u0006\u0004\bC\u0010DJ\u001f\u0010A\u001a\u00020<2\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050>\"\u00020\u0005¢\u0006\u0002\u0010DJ,\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052\u000e\b\u0004\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0\rH\u0086\bø\u0001\u0000J+\u0010E\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00052\u0010\b\u0004\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH\u0087\bø\u0001\u0000¢\u0006\u0002\bFJI\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052#\b\u0004\u0010 \u001a\u001d\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0004\u0012\u0002H\u001f0#H\u0086\bø\u0001\u0000Jf\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u000528\b\u0004\u0010 \u001a2\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0004\u0012\u0002H\u001f0'H\u0086\bø\u0001\u0000J\u0083\u0001\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052M\b\u0004\u0010 \u001aG\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0004\u0012\u0002H\u001f0*H\u0086\bø\u0001\u0000J \u0001\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052b\b\u0004\u0010 \u001a\\\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0004\u0012\u0002H\u001f0-H\u0086\bø\u0001\u0000J½\u0001\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052w\b\u0004\u0010 \u001aq\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0004\u0012\u0002H\u001f00H\u0086\bø\u0001\u0000JÜ\u0001\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u0001\"\u0006\b\u0006\u00102\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052\u008d\u0001\b\u0004\u0010 \u001a\u0086\u0001\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H2¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0004\u0012\u0002H\u001f03H\u0086\bø\u0001\u0000Jù\u0001\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u0001\"\u0006\b\u0006\u00102\u0018\u0001\"\u0006\b\u0007\u00105\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052¢\u0001\b\u0004\u0010 \u001a\u009b\u0001\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H2¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H5¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(7\u0012\u0004\u0012\u0002H\u001f06H\u0086\bø\u0001\u0000J\u0096\u0002\u0010E\u001a\u00020\u0019\"\u0006\b\u0000\u0010\u001f\u0018\u0001\"\u0006\b\u0001\u0010\"\u0018\u0001\"\u0006\b\u0002\u0010&\u0018\u0001\"\u0006\b\u0003\u0010)\u0018\u0001\"\u0006\b\u0004\u0010,\u0018\u0001\"\u0006\b\u0005\u0010/\u0018\u0001\"\u0006\b\u0006\u00102\u0018\u0001\"\u0006\b\u0007\u00105\u0018\u0001\"\u0006\b\b\u00108\u0018\u00012\u0006\u0010\u001e\u001a\u00020\u00052·\u0001\b\u0004\u0010 \u001a°\u0001\u0012\u0013\u0012\u0011H\"¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(%\u0012\u0013\u0012\u0011H&¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b((\u0012\u0013\u0012\u0011H)¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(+\u0012\u0013\u0012\u0011H,¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(.\u0012\u0013\u0012\u0011H/¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(1\u0012\u0013\u0012\u0011H2¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(4\u0012\u0013\u0012\u0011H5¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(7\u0012\u0013\u0012\u0011H8¢\u0006\f\b$\u0012\b\b\u001e\u0012\u0004\b\b(:\u0012\u0004\u0012\u0002H\u001f09H\u0086\bø\u0001\u0000J\u001c\u0010G\u001a\u00020<2\u000e\b\u0004\u0010 \u001a\b\u0012\u0004\u0012\u00020<0\rH\u0086\bø\u0001\u0000J\u001c\u0010H\u001a\u00020<2\u000e\b\u0004\u0010 \u001a\b\u0012\u0004\u0012\u00020<0\rH\u0086\bø\u0001\u0000J\u000e\u0010I\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0005J*\u0010I\u001a\u00020\u0014\"\u0004\b\u0000\u0010J2\u0006\u0010\u001e\u001a\u00020\u00052\u000e\b\u0004\u0010 \u001a\b\u0012\u0004\u0012\u0002HJ0\rH\u0086\bø\u0001\u0000J\u0006\u0010K\u001a\u00020LJ\u0010\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0005H\u0002R0\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00120\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0002\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000bR0\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00190\u00048\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u0002\u001a\u0004\b\u001b\u0010\t\"\u0004\b\u001c\u0010\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006P"}, m183d2 = {"Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "", "()V", "asyncFunctions", "", "", "Lexpo/modules/kotlin/functions/AsyncFunction;", "getAsyncFunctions$annotations", "getAsyncFunctions", "()Ljava/util/Map;", "setAsyncFunctions", "(Ljava/util/Map;)V", "constantsProvider", "Lkotlin/Function0;", "", "eventsDefinition", "Lexpo/modules/kotlin/events/EventsDefinition;", "functionBuilders", "Lexpo/modules/kotlin/functions/AsyncFunctionBuilder;", "properties", "Lexpo/modules/kotlin/objects/PropertyComponentBuilder;", "getProperties$annotations", "getProperties", "setProperties", "syncFunctions", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getSyncFunctions$annotations", "getSyncFunctions", "setSyncFunctions", "AsyncFunction", "name", "R", TtmlNode.TAG_BODY, "AsyncFunctionWithoutArgs", "P0", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "p0", "P1", "Lkotlin/Function2;", "p1", "P2", "Lkotlin/Function3;", "p2", "P3", "Lkotlin/Function4;", "p3", "P4", "Lkotlin/Function5;", "p4", "P5", "Lkotlin/Function6;", "p5", "P6", "Lkotlin/Function7;", "p6", "P7", "Lkotlin/Function8;", "p7", "Constants", "", "constants", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "Events", "events", "EventsWithArray", "([Ljava/lang/String;)V", "Function", "FunctionWithoutArgs", "OnStartObserving", "OnStopObserving", "Property", "T", "buildObject", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "containsFunction", "", "functionName", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class ObjectDefinitionBuilder {
    private EventsDefinition eventsDefinition;
    private Functions<? extends Map<String, ? extends Object>> constantsProvider = new Functions<Map<String, ? extends Object>>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$constantsProvider$1
        @Override // kotlin.jvm.functions.Functions
        public final Map<String, ? extends Object> invoke() {
            return MapsKt.emptyMap();
        }
    };
    private Map<String, SyncFunctionComponent> syncFunctions = new LinkedHashMap();
    private Map<String, AsyncFunction> asyncFunctions = new LinkedHashMap();
    private Map<String, AsyncFunctionBuilder> functionBuilders = new LinkedHashMap();
    private Map<String, PropertyComponentBuilder> properties = new LinkedHashMap();

    public static /* synthetic */ void getAsyncFunctions$annotations() {
    }

    public static /* synthetic */ void getProperties$annotations() {
    }

    public static /* synthetic */ void getSyncFunctions$annotations() {
    }

    public final Map<String, SyncFunctionComponent> getSyncFunctions() {
        return this.syncFunctions;
    }

    public final void setSyncFunctions(Map<String, SyncFunctionComponent> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncFunctions = map;
    }

    public final Map<String, AsyncFunction> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final void setAsyncFunctions(Map<String, AsyncFunction> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.asyncFunctions = map;
    }

    public final Map<String, PropertyComponentBuilder> getProperties() {
        return this.properties;
    }

    public final void setProperties(Map<String, PropertyComponentBuilder> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.properties = map;
    }

    public final ObjectDefinitionData buildObject() {
        if (this.eventsDefinition != null) {
            if (!containsFunction("addListener")) {
                getSyncFunctions().put("addListener", new SyncFunctionComponent("addListener", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(String.class))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$buildObject$lambda-2$$inlined$Function$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object[] it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        Object obj = it[0];
                        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                        String str = (String) obj;
                        return new Functions<Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$buildObject$1$1$1
                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                            }

                            @Override // kotlin.jvm.functions.Functions
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        };
                    }
                }));
            }
            if (!containsFunction("removeListeners")) {
                getSyncFunctions().put("removeListeners", new SyncFunctionComponent("removeListeners", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(Integer.TYPE))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$buildObject$lambda-2$$inlined$Function$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object[] it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        Object obj = it[0];
                        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
                        ((Integer) obj).intValue();
                        return new Functions<Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$buildObject$1$2$1
                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                            }

                            @Override // kotlin.jvm.functions.Functions
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        };
                    }
                }));
            }
        }
        Functions<? extends Map<String, ? extends Object>> functions = this.constantsProvider;
        Map<String, SyncFunctionComponent> map = this.syncFunctions;
        Map<String, AsyncFunction> map2 = this.asyncFunctions;
        Map<String, AsyncFunctionBuilder> map3 = this.functionBuilders;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map3.size()));
        Iterator<T> it = map3.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), ((AsyncFunctionBuilder) entry.getValue()).build$expo_modules_core_release());
        }
        Map plus = MapsKt.plus(map2, linkedHashMap);
        EventsDefinition eventsDefinition = this.eventsDefinition;
        Map<String, PropertyComponentBuilder> map4 = this.properties;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map4.size()));
        Iterator<T> it2 = map4.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            linkedHashMap2.put(entry2.getKey(), ((PropertyComponentBuilder) entry2.getValue()).build());
        }
        return new ObjectDefinitionData(functions, map, plus, eventsDefinition, linkedHashMap2);
    }

    private final boolean containsFunction(String str) {
        return this.syncFunctions.containsKey(str) || this.asyncFunctions.containsKey(str) || this.functionBuilders.containsKey(str);
    }

    public final void Constants(Functions<? extends Map<String, ? extends Object>> constantsProvider) {
        Intrinsics.checkNotNullParameter(constantsProvider, "constantsProvider");
        this.constantsProvider = constantsProvider;
    }

    public final void Constants(final Tuples<String, ? extends Object>... constants) {
        Intrinsics.checkNotNullParameter(constants, "constants");
        this.constantsProvider = new Functions<Map<String, ? extends Object>>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Constants$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final Map<String, ? extends Object> invoke() {
                return MapsKt.toMap(constants);
            }
        };
    }

    public final SyncFunctionComponent FunctionWithoutArgs(String name, final Functions<? extends Object> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R> SyncFunctionComponent Function(String name, final Functions<? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> SyncFunctionComponent Function(String name, final Function1<? super P0, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function1<P0, R> function1 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                return function1.invoke(obj);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> SyncFunctionComponent Function(String name, final Function2<? super P0, ? super P1, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function2<P0, P1, R> function2 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                return function2.invoke(obj, obj2);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> SyncFunctionComponent Function(String name, final Function3<? super P0, ? super P1, ? super P2, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        Intrinsics.reifiedOperationMarker(6, "P2");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function3<P0, P1, P2, R> function3 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                Object obj3 = it[2];
                Intrinsics.reifiedOperationMarker(1, "P2");
                return function3.invoke(obj, obj2, obj3);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> SyncFunctionComponent Function(String name, final Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        Intrinsics.reifiedOperationMarker(6, "P2");
        Intrinsics.reifiedOperationMarker(6, "P3");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function4<P0, P1, P2, P3, R> function4 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                Object obj3 = it[2];
                Intrinsics.reifiedOperationMarker(1, "P2");
                Object obj4 = it[3];
                Intrinsics.reifiedOperationMarker(1, "P3");
                return function4.invoke(obj, obj2, obj3, obj4);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> SyncFunctionComponent Function(String name, final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        Intrinsics.reifiedOperationMarker(6, "P2");
        Intrinsics.reifiedOperationMarker(6, "P3");
        Intrinsics.reifiedOperationMarker(6, "P4");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function5<P0, P1, P2, P3, P4, R> function5 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                Object obj3 = it[2];
                Intrinsics.reifiedOperationMarker(1, "P2");
                Object obj4 = it[3];
                Intrinsics.reifiedOperationMarker(1, "P3");
                Object obj5 = it[4];
                Intrinsics.reifiedOperationMarker(1, "P4");
                return function5.invoke(obj, obj2, obj3, obj4, obj5);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> SyncFunctionComponent Function(String name, final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        Intrinsics.reifiedOperationMarker(6, "P2");
        Intrinsics.reifiedOperationMarker(6, "P3");
        Intrinsics.reifiedOperationMarker(6, "P4");
        Intrinsics.reifiedOperationMarker(6, "P5");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function6<P0, P1, P2, P3, P4, P5, R> function6 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                Object obj3 = it[2];
                Intrinsics.reifiedOperationMarker(1, "P2");
                Object obj4 = it[3];
                Intrinsics.reifiedOperationMarker(1, "P3");
                Object obj5 = it[4];
                Intrinsics.reifiedOperationMarker(1, "P4");
                Object obj6 = it[5];
                Intrinsics.reifiedOperationMarker(1, "P5");
                return function6.invoke(obj, obj2, obj3, obj4, obj5, obj6);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> SyncFunctionComponent Function(String name, final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        Intrinsics.reifiedOperationMarker(6, "P2");
        Intrinsics.reifiedOperationMarker(6, "P3");
        Intrinsics.reifiedOperationMarker(6, "P4");
        Intrinsics.reifiedOperationMarker(6, "P5");
        Intrinsics.reifiedOperationMarker(6, "P6");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function7<P0, P1, P2, P3, P4, P5, P6, R> function7 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                Object obj3 = it[2];
                Intrinsics.reifiedOperationMarker(1, "P2");
                Object obj4 = it[3];
                Intrinsics.reifiedOperationMarker(1, "P3");
                Object obj5 = it[4];
                Intrinsics.reifiedOperationMarker(1, "P4");
                Object obj6 = it[5];
                Intrinsics.reifiedOperationMarker(1, "P5");
                Object obj7 = it[6];
                Intrinsics.reifiedOperationMarker(1, "P6");
                return function7.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> SyncFunctionComponent Function(String name, final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(6, "P0");
        Intrinsics.reifiedOperationMarker(6, "P1");
        Intrinsics.reifiedOperationMarker(6, "P2");
        Intrinsics.reifiedOperationMarker(6, "P3");
        Intrinsics.reifiedOperationMarker(6, "P4");
        Intrinsics.reifiedOperationMarker(6, "P5");
        Intrinsics.reifiedOperationMarker(6, "P6");
        Intrinsics.reifiedOperationMarker(6, "P7");
        AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$Function$19
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Function8<P0, P1, P2, P3, P4, P5, P6, P7, R> function8 = body;
                Object obj = it[0];
                Intrinsics.reifiedOperationMarker(1, "P0");
                Object obj2 = it[1];
                Intrinsics.reifiedOperationMarker(1, "P1");
                Object obj3 = it[2];
                Intrinsics.reifiedOperationMarker(1, "P2");
                Object obj4 = it[3];
                Intrinsics.reifiedOperationMarker(1, "P3");
                Object obj5 = it[4];
                Intrinsics.reifiedOperationMarker(1, "P4");
                Object obj6 = it[5];
                Intrinsics.reifiedOperationMarker(1, "P5");
                Object obj7 = it[6];
                Intrinsics.reifiedOperationMarker(1, "P6");
                Object obj8 = it[7];
                Intrinsics.reifiedOperationMarker(1, "P7");
                return function8.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
            }
        });
        getSyncFunctions().put(name, syncFunctionComponent);
        return syncFunctionComponent;
    }

    public final AsyncFunction AsyncFunctionWithoutArgs(String name, Functions<? extends Object> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(name, new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$1(body));
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R> AsyncFunction AsyncFunction(String name, final Functions<? extends R> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent(name, new AnyType[0], new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object[] it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return body.invoke();
            }
        });
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0> AsyncFunction AsyncFunction(String name, final Function1<? super P0, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P0");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, new AnyType[0], new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] noName_0, Promise promise) {
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function1<P0, R> function1 = body;
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    function1.invoke(promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function1<P0, R> function1 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    return function1.invoke(obj);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1> AsyncFunction AsyncFunction(String name, final Function2<? super P0, ? super P1, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P1");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function2<P0, P1, R> function2 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    function2.invoke(obj, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$9
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function2<P0, P1, R> function2 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    return function2.invoke(obj, obj2);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2> AsyncFunction AsyncFunction(String name, final Function3<? super P0, ? super P1, ? super P2, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P2");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$11
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function3<P0, P1, P2, R> function3 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = args[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    function3.invoke(obj, obj2, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$12
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function3<P0, P1, P2, R> function3 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = it[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    return function3.invoke(obj, obj2, obj3);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3> AsyncFunction AsyncFunction(String name, final Function4<? super P0, ? super P1, ? super P2, ? super P3, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P3");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$14
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function4<P0, P1, P2, P3, R> function4 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = args[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = args[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    function4.invoke(obj, obj2, obj3, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$15
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function4<P0, P1, P2, P3, R> function4 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = it[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = it[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    return function4.invoke(obj, obj2, obj3, obj4);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4> AsyncFunction AsyncFunction(String name, final Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P4");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$17
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function5<P0, P1, P2, P3, P4, R> function5 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = args[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = args[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = args[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    function5.invoke(obj, obj2, obj3, obj4, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$18
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function5<P0, P1, P2, P3, P4, R> function5 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = it[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = it[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = it[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    return function5.invoke(obj, obj2, obj3, obj4, obj5);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5> AsyncFunction AsyncFunction(String name, final Function6<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P5");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$20
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function6<P0, P1, P2, P3, P4, P5, R> function6 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = args[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = args[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = args[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = args[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    Intrinsics.reifiedOperationMarker(1, "P5");
                    function6.invoke(obj, obj2, obj3, obj4, obj5, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            Intrinsics.reifiedOperationMarker(6, "P5");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$21
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function6<P0, P1, P2, P3, P4, P5, R> function6 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = it[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = it[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = it[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    Object obj6 = it[5];
                    Intrinsics.reifiedOperationMarker(1, "P5");
                    return function6.invoke(obj, obj2, obj3, obj4, obj5, obj6);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6> AsyncFunction AsyncFunction(String name, final Function7<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P6");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            Intrinsics.reifiedOperationMarker(6, "P5");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$23
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function7<P0, P1, P2, P3, P4, P5, P6, R> function7 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = args[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = args[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = args[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = args[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    Object obj6 = args[5];
                    Intrinsics.reifiedOperationMarker(1, "P5");
                    Intrinsics.reifiedOperationMarker(1, "P6");
                    function7.invoke(obj, obj2, obj3, obj4, obj5, obj6, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            Intrinsics.reifiedOperationMarker(6, "P5");
            Intrinsics.reifiedOperationMarker(6, "P6");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$24
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function7<P0, P1, P2, P3, P4, P5, P6, R> function7 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = it[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = it[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = it[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    Object obj6 = it[5];
                    Intrinsics.reifiedOperationMarker(1, "P5");
                    Object obj7 = it[6];
                    Intrinsics.reifiedOperationMarker(1, "P6");
                    return function7.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final /* synthetic */ <R, P0, P1, P2, P3, P4, P5, P6, P7> AsyncFunction AsyncFunction(String name, final Function8<? super P0, ? super P1, ? super P2, ? super P3, ? super P4, ? super P5, ? super P6, ? super P7, ? extends R> body) {
        AsyncFunctionComponent asyncFunctionComponent;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.reifiedOperationMarker(4, "P7");
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Object.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            Intrinsics.reifiedOperationMarker(6, "P5");
            Intrinsics.reifiedOperationMarker(6, "P6");
            AnyType[] anyTypeArr = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent(name, anyTypeArr, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$26
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Function8<P0, P1, P2, P3, P4, P5, P6, P7, R> function8 = body;
                    Object obj = args[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = args[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = args[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = args[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = args[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    Object obj6 = args[5];
                    Intrinsics.reifiedOperationMarker(1, "P5");
                    Object obj7 = args[6];
                    Intrinsics.reifiedOperationMarker(1, "P6");
                    Intrinsics.reifiedOperationMarker(1, "P7");
                    function8.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, promise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            Intrinsics.reifiedOperationMarker(6, "P0");
            Intrinsics.reifiedOperationMarker(6, "P1");
            Intrinsics.reifiedOperationMarker(6, "P2");
            Intrinsics.reifiedOperationMarker(6, "P3");
            Intrinsics.reifiedOperationMarker(6, "P4");
            Intrinsics.reifiedOperationMarker(6, "P5");
            Intrinsics.reifiedOperationMarker(6, "P6");
            Intrinsics.reifiedOperationMarker(6, "P7");
            AnyType[] anyTypeArr2 = {AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null), AnyTypeKt.toAnyType(null)};
            Intrinsics.needClassReification();
            asyncFunctionComponent = new AsyncFunctionComponent(name, anyTypeArr2, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.objects.ObjectDefinitionBuilder$AsyncFunction$27
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Function8<P0, P1, P2, P3, P4, P5, P6, P7, R> function8 = body;
                    Object obj = it[0];
                    Intrinsics.reifiedOperationMarker(1, "P0");
                    Object obj2 = it[1];
                    Intrinsics.reifiedOperationMarker(1, "P1");
                    Object obj3 = it[2];
                    Intrinsics.reifiedOperationMarker(1, "P2");
                    Object obj4 = it[3];
                    Intrinsics.reifiedOperationMarker(1, "P3");
                    Object obj5 = it[4];
                    Intrinsics.reifiedOperationMarker(1, "P4");
                    Object obj6 = it[5];
                    Intrinsics.reifiedOperationMarker(1, "P5");
                    Object obj7 = it[6];
                    Intrinsics.reifiedOperationMarker(1, "P6");
                    Object obj8 = it[7];
                    Intrinsics.reifiedOperationMarker(1, "P7");
                    return function8.invoke(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
                }
            });
        }
        getAsyncFunctions().put(name, asyncFunctionComponent);
        return asyncFunctionComponent;
    }

    public final AsyncFunctionBuilder AsyncFunction(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        AsyncFunctionBuilder asyncFunctionBuilder = new AsyncFunctionBuilder(name);
        this.functionBuilders.put(name, asyncFunctionBuilder);
        return asyncFunctionBuilder;
    }

    public final void Events(String... events) {
        Intrinsics.checkNotNullParameter(events, "events");
        this.eventsDefinition = new EventsDefinition(events);
    }

    public final void EventsWithArray(String[] events) {
        Intrinsics.checkNotNullParameter(events, "events");
        this.eventsDefinition = new EventsDefinition(events);
    }

    public final PropertyComponentBuilder Property(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder(name);
        getProperties().put(name, propertyComponentBuilder);
        return propertyComponentBuilder;
    }

    public final <T> PropertyComponentBuilder Property(String name, Functions<? extends T> body) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(body, "body");
        PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder(name);
        propertyComponentBuilder.setGetter(new SyncFunctionComponent("get", new AnyType[0], new PropertyComponentBuilder$get$1$1(body)));
        getProperties().put(name, propertyComponentBuilder);
        return propertyComponentBuilder;
    }

    public final void OnStartObserving(Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getAsyncFunctions().put("startObserving", new AsyncFunctionComponent("startObserving", new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$1(body)));
    }

    public final void OnStopObserving(Functions<Unit> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        getAsyncFunctions().put("stopObserving", new AsyncFunctionComponent("stopObserving", new AnyType[0], new ObjectDefinitionBuilder$AsyncFunction$1(body)));
    }
}
