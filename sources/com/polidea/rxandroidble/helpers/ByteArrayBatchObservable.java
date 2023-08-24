package com.polidea.rxandroidble.helpers;

import java.nio.ByteBuffer;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.functions.Action2;
import p042rx.functions.Func0;
import p042rx.observables.SyncOnSubscribe;

/* loaded from: classes3.dex */
public class ByteArrayBatchObservable extends Observable<byte[]> {
    public ByteArrayBatchObservable(byte[] bArr, int r4) {
        super(createSyncOnSubscribe(copy(bArr), r4));
        if (r4 > 0) {
            return;
        }
        throw new IllegalArgumentException("maxBatchSize must be >0 but found: " + r4);
    }

    private static SyncOnSubscribe<ByteBuffer, byte[]> createSyncOnSubscribe(final byte[] bArr, final int r2) {
        return SyncOnSubscribe.createSingleState(new Func0<ByteBuffer>() { // from class: com.polidea.rxandroidble.helpers.ByteArrayBatchObservable.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public ByteBuffer call() {
                return ByteBuffer.wrap(bArr);
            }
        }, new Action2<ByteBuffer, Observer<? super byte[]>>() { // from class: com.polidea.rxandroidble.helpers.ByteArrayBatchObservable.2
            @Override // p042rx.functions.Action2
            public void call(ByteBuffer byteBuffer, Observer<? super byte[]> observer) {
                int min = Math.min(byteBuffer.remaining(), r2);
                if (min == 0) {
                    observer.onCompleted();
                    return;
                }
                byte[] bArr2 = new byte[min];
                byteBuffer.get(bArr2);
                observer.onNext(bArr2);
            }
        });
    }

    private static byte[] copy(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }
}
