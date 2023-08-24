package org.bouncycastle.pqc.jcajce.provider.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

/* loaded from: classes4.dex */
public abstract class CipherSpiExt extends CipherSpi {
    public static final int DECRYPT_MODE = 2;
    public static final int ENCRYPT_MODE = 1;
    protected int opMode;

    public abstract int doFinal(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException;

    public final byte[] doFinal() throws IllegalBlockSizeException, BadPaddingException {
        return doFinal(null, 0, 0);
    }

    public final byte[] doFinal(byte[] bArr) throws IllegalBlockSizeException, BadPaddingException {
        return doFinal(bArr, 0, bArr.length);
    }

    public abstract byte[] doFinal(byte[] bArr, int r2, int r3) throws IllegalBlockSizeException, BadPaddingException;

    @Override // javax.crypto.CipherSpi
    protected final int engineDoFinal(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        return doFinal(bArr, r2, r3, bArr2, r5);
    }

    @Override // javax.crypto.CipherSpi
    protected final byte[] engineDoFinal(byte[] bArr, int r2, int r3) throws IllegalBlockSizeException, BadPaddingException {
        return doFinal(bArr, r2, r3);
    }

    @Override // javax.crypto.CipherSpi
    protected final int engineGetBlockSize() {
        return getBlockSize();
    }

    @Override // javax.crypto.CipherSpi
    protected final byte[] engineGetIV() {
        return getIV();
    }

    @Override // javax.crypto.CipherSpi
    protected final int engineGetKeySize(Key key) throws InvalidKeyException {
        if (key instanceof Key) {
            return getKeySize(key);
        }
        throw new InvalidKeyException("Unsupported key.");
    }

    @Override // javax.crypto.CipherSpi
    protected final int engineGetOutputSize(int r1) {
        return getOutputSize(r1);
    }

    @Override // javax.crypto.CipherSpi
    protected final AlgorithmParameters engineGetParameters() {
        return null;
    }

    @Override // javax.crypto.CipherSpi
    protected final void engineInit(int r1, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameters == null) {
            engineInit(r1, key, secureRandom);
        } else {
            engineInit(r1, key, (AlgorithmParameterSpec) null, secureRandom);
        }
    }

    @Override // javax.crypto.CipherSpi
    protected final void engineInit(int r3, Key key, SecureRandom secureRandom) throws InvalidKeyException {
        try {
            AlgorithmParameterSpec algorithmParameterSpec = null;
            engineInit(r3, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    @Override // javax.crypto.CipherSpi
    protected void engineInit(int r2, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof AlgorithmParameterSpec)) {
            throw new InvalidAlgorithmParameterException();
        }
        if (key == null || !(key instanceof Key)) {
            throw new InvalidKeyException();
        }
        this.opMode = r2;
        if (r2 == 1) {
            initEncrypt(key, algorithmParameterSpec, secureRandom);
        } else if (r2 == 2) {
            initDecrypt(key, algorithmParameterSpec);
        }
    }

    @Override // javax.crypto.CipherSpi
    protected final void engineSetMode(String str) throws NoSuchAlgorithmException {
        setMode(str);
    }

    @Override // javax.crypto.CipherSpi
    protected final void engineSetPadding(String str) throws NoSuchPaddingException {
        setPadding(str);
    }

    @Override // javax.crypto.CipherSpi
    protected final int engineUpdate(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws ShortBufferException {
        return update(bArr, r2, r3, bArr2, r5);
    }

    @Override // javax.crypto.CipherSpi
    protected final byte[] engineUpdate(byte[] bArr, int r2, int r3) {
        return update(bArr, r2, r3);
    }

    public abstract int getBlockSize();

    public abstract byte[] getIV();

    public abstract int getKeySize(Key key) throws InvalidKeyException;

    public abstract String getName();

    public abstract int getOutputSize(int r1);

    public abstract AlgorithmParameterSpec getParameters();

    public abstract void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) throws InvalidKeyException, InvalidAlgorithmParameterException;

    public abstract void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException;

    protected abstract void setMode(String str) throws NoSuchAlgorithmException;

    protected abstract void setPadding(String str) throws NoSuchPaddingException;

    public abstract int update(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws ShortBufferException;

    public final byte[] update(byte[] bArr) {
        return update(bArr, 0, bArr.length);
    }

    public abstract byte[] update(byte[] bArr, int r2, int r3);
}
