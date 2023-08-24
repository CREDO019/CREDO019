package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzglm {
    private final ECPublicKey zza;

    public zzglm(ECPublicKey eCPublicKey) {
        this.zza = eCPublicKey;
    }

    public final zzgll zza(String str, byte[] bArr, byte[] bArr2, int r20, int r21) throws GeneralSecurityException {
        BigInteger bigInteger;
        char c;
        int r8;
        byte[] bArr3;
        int r4;
        byte[] bArr4;
        KeyPair zzb = zzgln.zzb(this.zza.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) zzb.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) zzb.getPrivate();
        ECPublicKey eCPublicKey2 = this.zza;
        try {
            ECParameterSpec params = eCPublicKey2.getParams();
            ECParameterSpec params2 = eCPrivateKey.getParams();
            if (!params.getCurve().equals(params2.getCurve()) || !params.getGenerator().equals(params2.getGenerator()) || !params.getOrder().equals(params2.getOrder()) || params.getCofactor() != params2.getCofactor()) {
                throw new GeneralSecurityException("invalid public key spec");
            }
            ECPoint w = eCPublicKey2.getW();
            zzgln.zzc(w, eCPrivateKey.getParams().getCurve());
            PublicKey generatePublic = ((KeyFactory) zzglp.zzg.zza("EC")).generatePublic(new ECPublicKeySpec(w, eCPrivateKey.getParams()));
            KeyAgreement keyAgreement = (KeyAgreement) zzglp.zze.zza("ECDH");
            keyAgreement.init(eCPrivateKey);
            int r82 = 1;
            try {
                keyAgreement.doPhase(generatePublic, true);
                byte[] generateSecret = keyAgreement.generateSecret();
                EllipticCurve curve = eCPrivateKey.getParams().getCurve();
                BigInteger bigInteger2 = new BigInteger(1, generateSecret);
                if (bigInteger2.signum() == -1 || bigInteger2.compareTo(zzgln.zza(curve)) >= 0) {
                    throw new GeneralSecurityException("shared secret is out of range");
                }
                BigInteger zza = zzgln.zza(curve);
                BigInteger mod = bigInteger2.multiply(bigInteger2).add(curve.getA()).multiply(bigInteger2).add(curve.getB()).mod(zza);
                if (zza.signum() != 1) {
                    throw new InvalidAlgorithmParameterException("p must be positive");
                }
                BigInteger mod2 = mod.mod(zza);
                if (mod2.equals(BigInteger.ZERO)) {
                    bigInteger = BigInteger.ZERO;
                } else {
                    BigInteger bigInteger3 = null;
                    if (!zza.testBit(0) || !zza.testBit(1)) {
                        if (zza.testBit(0) && !zza.testBit(1)) {
                            BigInteger bigInteger4 = BigInteger.ONE;
                            BigInteger shiftRight = zza.subtract(BigInteger.ONE).shiftRight(1);
                            int r14 = 0;
                            while (true) {
                                BigInteger mod3 = bigInteger4.multiply(bigInteger4).subtract(mod2).mod(zza);
                                if (mod3.equals(BigInteger.ZERO)) {
                                    bigInteger = bigInteger4;
                                    break;
                                }
                                BigInteger modPow = mod3.modPow(shiftRight, zza);
                                if (!modPow.add(BigInteger.ONE).equals(zza)) {
                                    if (modPow.equals(BigInteger.ONE)) {
                                        bigInteger4 = bigInteger4.add(BigInteger.ONE);
                                        r14++;
                                        if (r14 == 128 && !zza.isProbablePrime(80)) {
                                            throw new InvalidAlgorithmParameterException("p is not prime");
                                        }
                                        r82 = 1;
                                    } else {
                                        throw new InvalidAlgorithmParameterException("p is not prime");
                                    }
                                } else {
                                    BigInteger shiftRight2 = zza.add(BigInteger.ONE).shiftRight(r82);
                                    BigInteger bigInteger5 = BigInteger.ONE;
                                    BigInteger bigInteger6 = bigInteger4;
                                    for (int bitLength = shiftRight2.bitLength() - 2; bitLength >= 0; bitLength--) {
                                        BigInteger multiply = bigInteger6.multiply(bigInteger5);
                                        BigInteger mod4 = bigInteger6.multiply(bigInteger6).add(bigInteger5.multiply(bigInteger5).mod(zza).multiply(mod3)).mod(zza);
                                        BigInteger mod5 = multiply.add(multiply).mod(zza);
                                        if (shiftRight2.testBit(bitLength)) {
                                            BigInteger mod6 = mod4.multiply(bigInteger4).add(mod5.multiply(mod3)).mod(zza);
                                            bigInteger5 = bigInteger4.multiply(mod5).add(mod4).mod(zza);
                                            bigInteger6 = mod6;
                                        } else {
                                            bigInteger6 = mod4;
                                            bigInteger5 = mod5;
                                        }
                                    }
                                    bigInteger3 = bigInteger6;
                                }
                            }
                        }
                    } else {
                        bigInteger3 = mod2.modPow(zza.add(BigInteger.ONE).shiftRight(2), zza);
                    }
                    if (bigInteger3 != null && bigInteger3.multiply(bigInteger3).mod(zza).compareTo(mod2) != 0) {
                        throw new GeneralSecurityException("Could not find a modular square root");
                    }
                    bigInteger = bigInteger3;
                }
                if (!bigInteger.testBit(0)) {
                    zza.subtract(bigInteger).mod(zza);
                }
                EllipticCurve curve2 = eCPublicKey.getParams().getCurve();
                ECPoint w2 = eCPublicKey.getW();
                zzgln.zzc(w2, curve2);
                int bitLength2 = (zzgln.zza(curve2).subtract(BigInteger.ONE).bitLength() + 7) / 8;
                int r7 = r21 - 1;
                if (r7 != 0) {
                    if (r7 != 2) {
                        int r42 = bitLength2 + 1;
                        bArr4 = new byte[r42];
                        byte[] byteArray = w2.getAffineX().toByteArray();
                        int length = byteArray.length;
                        c = 0;
                        System.arraycopy(byteArray, 0, bArr4, r42 - length, length);
                        bArr4[0] = true != w2.getAffineY().testBit(0) ? (byte) 2 : (byte) 3;
                    } else {
                        int r72 = bitLength2 + bitLength2;
                        bArr4 = new byte[r72];
                        byte[] byteArray2 = w2.getAffineX().toByteArray();
                        int length2 = byteArray2.length;
                        if (length2 > bitLength2) {
                            byteArray2 = Arrays.copyOfRange(byteArray2, length2 - bitLength2, length2);
                        }
                        byte[] byteArray3 = w2.getAffineY().toByteArray();
                        int length3 = byteArray3.length;
                        if (length3 > bitLength2) {
                            byteArray3 = Arrays.copyOfRange(byteArray3, length3 - bitLength2, length3);
                        }
                        int length4 = byteArray3.length;
                        c = 0;
                        System.arraycopy(byteArray3, 0, bArr4, r72 - length4, length4);
                        int length5 = byteArray2.length;
                        System.arraycopy(byteArray2, 0, bArr4, bitLength2 - length5, length5);
                    }
                    bArr3 = bArr4;
                    r4 = 2;
                    r8 = 1;
                } else {
                    c = 0;
                    r8 = 1;
                    int r73 = bitLength2 + bitLength2 + 1;
                    bArr3 = new byte[r73];
                    byte[] byteArray4 = w2.getAffineX().toByteArray();
                    byte[] byteArray5 = w2.getAffineY().toByteArray();
                    int length6 = byteArray5.length;
                    System.arraycopy(byteArray5, 0, bArr3, r73 - length6, length6);
                    int length7 = byteArray4.length;
                    System.arraycopy(byteArray4, 0, bArr3, (bitLength2 + 1) - length7, length7);
                    bArr3[0] = 4;
                    r4 = 2;
                }
                byte[][] bArr5 = new byte[r4];
                bArr5[c] = bArr3;
                bArr5[r8] = generateSecret;
                byte[] zzc = zzgle.zzc(bArr5);
                Mac mac = (Mac) zzglp.zzb.zza(str);
                if (r20 <= mac.getMacLength() * 255) {
                    if (bArr == null || bArr.length == 0) {
                        mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
                    } else {
                        mac.init(new SecretKeySpec(bArr, str));
                    }
                    byte[] doFinal = mac.doFinal(zzc);
                    byte[] bArr6 = new byte[r20];
                    mac.init(new SecretKeySpec(doFinal, str));
                    byte[] bArr7 = new byte[0];
                    int r2 = 0;
                    while (true) {
                        mac.update(bArr7);
                        mac.update(bArr2);
                        mac.update((byte) r8);
                        byte[] doFinal2 = mac.doFinal();
                        int length8 = doFinal2.length;
                        int r11 = r2 + length8;
                        if (r11 < r20) {
                            System.arraycopy(doFinal2, 0, bArr6, r2, length8);
                            r8++;
                            bArr7 = doFinal2;
                            r2 = r11;
                        } else {
                            System.arraycopy(doFinal2, 0, bArr6, r2, r20 - r2);
                            return new zzgll(bArr3, bArr6);
                        }
                    }
                } else {
                    throw new GeneralSecurityException("size too large");
                }
            } catch (IllegalStateException e) {
                throw new GeneralSecurityException(e);
            }
        } catch (IllegalArgumentException | NullPointerException e2) {
            throw new GeneralSecurityException(e2);
        }
    }
}
