package org.bouncycastle.jcajce.provider.asymmetric.dsa;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.DSAParametersGenerator;
import org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import org.bouncycastle.crypto.params.DSAParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAlgorithmParameterGeneratorSpi;
import org.bouncycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

/* loaded from: classes5.dex */
public class AlgorithmParameterGeneratorSpi extends BaseAlgorithmParameterGeneratorSpi {
    protected DSAParameterGenerationParameters params;
    protected SecureRandom random;
    protected int strength = 2048;

    @Override // java.security.AlgorithmParameterGeneratorSpi
    protected AlgorithmParameters engineGenerateParameters() {
        DSAParametersGenerator dSAParametersGenerator = this.strength <= 1024 ? new DSAParametersGenerator() : new DSAParametersGenerator(new SHA256Digest());
        if (this.random == null) {
            this.random = CryptoServicesRegistrar.getSecureRandom();
        }
        int defaultCertainty = PrimeCertaintyCalculator.getDefaultCertainty(this.strength);
        int r3 = this.strength;
        if (r3 == 1024) {
            DSAParameterGenerationParameters dSAParameterGenerationParameters = new DSAParameterGenerationParameters(1024, 160, defaultCertainty, this.random);
            this.params = dSAParameterGenerationParameters;
            dSAParametersGenerator.init(dSAParameterGenerationParameters);
        } else if (r3 > 1024) {
            DSAParameterGenerationParameters dSAParameterGenerationParameters2 = new DSAParameterGenerationParameters(r3, 256, defaultCertainty, this.random);
            this.params = dSAParameterGenerationParameters2;
            dSAParametersGenerator.init(dSAParameterGenerationParameters2);
        } else {
            dSAParametersGenerator.init(r3, defaultCertainty, this.random);
        }
        DSAParameters generateParameters = dSAParametersGenerator.generateParameters();
        try {
            AlgorithmParameters createParametersInstance = createParametersInstance("DSA");
            createParametersInstance.init(new DSAParameterSpec(generateParameters.getP(), generateParameters.getQ(), generateParameters.getG()));
            return createParametersInstance;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    protected void engineInit(int r3, SecureRandom secureRandom) {
        if (r3 < 512 || r3 > 3072) {
            throw new InvalidParameterException("strength must be from 512 - 3072");
        }
        if (r3 <= 1024 && r3 % 64 != 0) {
            throw new InvalidParameterException("strength must be a multiple of 64 below 1024 bits.");
        }
        if (r3 > 1024 && r3 % 1024 != 0) {
            throw new InvalidParameterException("strength must be a multiple of 1024 above 1024 bits.");
        }
        this.strength = r3;
        this.random = secureRandom;
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DSA parameter generation.");
    }
}
