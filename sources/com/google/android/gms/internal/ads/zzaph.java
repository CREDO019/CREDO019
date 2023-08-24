package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaph {
    private static Cipher zza;
    private static final Object zzb = new Object();
    private static final Object zzc = new Object();

    public zzaph(SecureRandom secureRandom) {
    }

    private static final Cipher zzc() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher;
        synchronized (zzc) {
            if (zza == null) {
                zza = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            cipher = zza;
        }
        return cipher;
    }

    public final String zza(byte[] bArr, byte[] bArr2) throws zzapg {
        byte[] doFinal;
        byte[] bArr3;
        int length = bArr.length;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            synchronized (zzb) {
                zzc().init(1, secretKeySpec, (SecureRandom) null);
                doFinal = zzc().doFinal(bArr2);
                bArr3 = zzc().getIV();
            }
            int length2 = doFinal.length + bArr3.length;
            ByteBuffer allocate = ByteBuffer.allocate(length2);
            allocate.put(bArr3).put(doFinal);
            allocate.flip();
            byte[] bArr4 = new byte[length2];
            allocate.get(bArr4);
            return zzanm.zza(bArr4, false);
        } catch (InvalidKeyException e) {
            throw new zzapg(this, e);
        } catch (NoSuchAlgorithmException e2) {
            throw new zzapg(this, e2);
        } catch (BadPaddingException e3) {
            throw new zzapg(this, e3);
        } catch (IllegalBlockSizeException e4) {
            throw new zzapg(this, e4);
        } catch (NoSuchPaddingException e5) {
            throw new zzapg(this, e5);
        }
    }

    public final byte[] zzb(byte[] bArr, String str) throws zzapg {
        byte[] doFinal;
        int length = bArr.length;
        try {
            byte[] zzb2 = zzanm.zzb(str, false);
            int length2 = zzb2.length;
            if (length2 > 16) {
                ByteBuffer allocate = ByteBuffer.allocate(length2);
                allocate.put(zzb2);
                allocate.flip();
                byte[] bArr2 = new byte[16];
                byte[] bArr3 = new byte[length2 - 16];
                allocate.get(bArr2);
                allocate.get(bArr3);
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                synchronized (zzb) {
                    zzc().init(2, secretKeySpec, new IvParameterSpec(bArr2));
                    doFinal = zzc().doFinal(bArr3);
                }
                return doFinal;
            }
            throw new zzapg(this);
        } catch (IllegalArgumentException e) {
            throw new zzapg(this, e);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new zzapg(this, e2);
        } catch (InvalidKeyException e3) {
            throw new zzapg(this, e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new zzapg(this, e4);
        } catch (BadPaddingException e5) {
            throw new zzapg(this, e5);
        } catch (IllegalBlockSizeException e6) {
            throw new zzapg(this, e6);
        } catch (NoSuchPaddingException e7) {
            throw new zzapg(this, e7);
        }
    }
}
