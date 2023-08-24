package expo.modules.securestore;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.securestore.SecureStoreModule;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class SecureStoreModule extends ExportedModule {
    private static final String ALIAS_PROPERTY = "keychainService";
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String SCHEME_PROPERTY = "scheme";
    private static final String SHARED_PREFERENCES_NAME = "SecureStore";
    static final String TAG = "ExpoSecureStore";
    private AESEncrypter mAESEncrypter;
    private AuthenticationHelper mAuthenticationHelper;
    private HybridAESEncrypter mHybridAESEncrypter;
    private KeyStore mKeyStore;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public interface KeyBasedEncrypter<E extends KeyStore.Entry> {
        void createEncryptedItem(Promise promise, String str, KeyStore keyStore, E e, ReadableArguments readableArguments, AuthenticationCallback authenticationCallback, PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException, JSONException;

        void decryptItem(Promise promise, JSONObject jSONObject, E e, ReadableArguments readableArguments, AuthenticationCallback authenticationCallback) throws GeneralSecurityException, JSONException;

        String getKeyStoreAlias(ReadableArguments readableArguments);

        E initializeKeyStoreEntry(KeyStore keyStore, ReadableArguments readableArguments) throws GeneralSecurityException;
    }

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return TAG;
    }

    public SecureStoreModule(Context context) {
        super(context);
        AESEncrypter aESEncrypter = new AESEncrypter();
        this.mAESEncrypter = aESEncrypter;
        this.mHybridAESEncrypter = new HybridAESEncrypter(context, aESEncrypter);
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        this.mAuthenticationHelper = new AuthenticationHelper(getContext(), moduleRegistry);
    }

    @ExpoMethod
    public void setValueWithKeyAsync(String str, String str2, ReadableArguments readableArguments, Promise promise) {
        try {
            setItemImpl(str2, str, readableArguments, promise);
        } catch (Exception e) {
            Log.e(TAG, "Caught unexpected exception when writing to SecureStore", e);
            promise.reject("E_SECURESTORE_WRITE_ERROR", "An unexpected error occurred when writing to SecureStore", e);
        }
    }

    private void setItemImpl(final String str, String str2, ReadableArguments readableArguments, Promise promise) {
        if (str == null) {
            promise.reject("E_SECURESTORE_NULL_KEY", "SecureStore keys must not be null");
            return;
        }
        final SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        if (str2 == null) {
            if (sharedPreferences.edit().putString(str, null).commit()) {
                promise.resolve(null);
                return;
            } else {
                promise.reject("E_SECURESTORE_WRITE_ERROR", "Could not write a null value to SecureStore");
                return;
            }
        }
        try {
            KeyStore keyStore = getKeyStore();
            if (Build.VERSION.SDK_INT >= 23) {
                this.mAESEncrypter.createEncryptedItem(promise, str2, keyStore, (KeyStore.SecretKeyEntry) getKeyEntry(KeyStore.SecretKeyEntry.class, this.mAESEncrypter, readableArguments), readableArguments, this.mAuthenticationHelper.getDefaultCallback(), new PostEncryptionCallback() { // from class: expo.modules.securestore.SecureStoreModule$$ExternalSyntheticLambda0
                    @Override // expo.modules.securestore.PostEncryptionCallback
                    public final void run(Promise promise2, Object obj) {
                        SecureStoreModule.this.m1694lambda$setItemImpl$0$expomodulessecurestoreSecureStoreModule(sharedPreferences, str, promise2, obj);
                    }
                });
                return;
            }
            this.mHybridAESEncrypter.createEncryptedItem(promise, str2, keyStore, (KeyStore.PrivateKeyEntry) getKeyEntry(KeyStore.PrivateKeyEntry.class, this.mHybridAESEncrypter, readableArguments), readableArguments, this.mAuthenticationHelper.getDefaultCallback(), new PostEncryptionCallback() { // from class: expo.modules.securestore.SecureStoreModule$$ExternalSyntheticLambda1
                @Override // expo.modules.securestore.PostEncryptionCallback
                public final void run(Promise promise2, Object obj) {
                    SecureStoreModule.this.m1695lambda$setItemImpl$1$expomodulessecurestoreSecureStoreModule(sharedPreferences, str, promise2, obj);
                }
            });
        } catch (IOException e) {
            Log.w(TAG, e);
            promise.reject("E_SECURESTORE_IO_ERROR", "There was an I/O error loading the keystore for SecureStore", e);
        } catch (GeneralSecurityException e2) {
            Log.w(TAG, e2);
            promise.reject("E_SECURESTORE_ENCRYPT_ERROR", "Could not encrypt the value for SecureStore", e2);
        } catch (JSONException e3) {
            Log.w(TAG, e3);
            promise.reject("E_SECURESTORE_ENCODE_ERROR", "Could not create an encrypted JSON item for SecureStore", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setItemImpl$0$expo-modules-securestore-SecureStoreModule  reason: not valid java name */
    public /* synthetic */ void m1694lambda$setItemImpl$0$expomodulessecurestoreSecureStoreModule(SharedPreferences sharedPreferences, String str, Promise promise, Object obj) throws JSONException, GeneralSecurityException {
        JSONObject jSONObject = (JSONObject) obj;
        jSONObject.put(SCHEME_PROPERTY, AESEncrypter.NAME);
        saveEncryptedItem(promise, jSONObject, sharedPreferences, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setItemImpl$1$expo-modules-securestore-SecureStoreModule  reason: not valid java name */
    public /* synthetic */ void m1695lambda$setItemImpl$1$expomodulessecurestoreSecureStoreModule(SharedPreferences sharedPreferences, String str, Promise promise, Object obj) throws JSONException, GeneralSecurityException {
        JSONObject jSONObject = (JSONObject) obj;
        jSONObject.put(SCHEME_PROPERTY, HybridAESEncrypter.NAME);
        saveEncryptedItem(promise, jSONObject, sharedPreferences, str);
    }

    private void saveEncryptedItem(Promise promise, JSONObject jSONObject, SharedPreferences sharedPreferences, String str) {
        String jSONObject2 = jSONObject.toString();
        if (jSONObject2 == null) {
            promise.reject("E_SECURESTORE_JSON_ERROR", "Could not JSON-encode the encrypted item for SecureStore");
        } else if (sharedPreferences.edit().putString(str, jSONObject2).commit()) {
            promise.resolve(null);
        } else {
            promise.reject("E_SECURESTORE_WRITE_ERROR", "Could not write encrypted JSON to SecureStore");
        }
    }

    @ExpoMethod
    public void getValueWithKeyAsync(String str, ReadableArguments readableArguments, Promise promise) {
        try {
            getItemImpl(str, readableArguments, promise);
        } catch (Exception e) {
            Log.e(TAG, "Caught unexpected exception when reading from SecureStore", e);
            promise.reject("E_SECURESTORE_READ_ERROR", "An unexpected error occurred when reading from SecureStore", e);
        }
    }

    private void getItemImpl(String str, ReadableArguments readableArguments, Promise promise) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        if (sharedPreferences.contains(str)) {
            readJSONEncodedItem(str, sharedPreferences, readableArguments, promise);
        } else {
            readLegacySDK20Item(str, readableArguments, promise);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
        if (r13 == 1) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
        r12 = java.lang.String.format("The item for key \"%s\" in SecureStore has an unknown encoding scheme (%s)", r12, r4);
        android.util.Log.e(expo.modules.securestore.SecureStoreModule.TAG, r12);
        r15.reject("E_SECURESTORE_DECODE_ERROR", r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0067, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0068, code lost:
        r11.mHybridAESEncrypter.decryptItem2(r15, r6, (java.security.KeyStore.PrivateKeyEntry) getKeyEntry(java.security.KeyStore.PrivateKeyEntry.class, r11.mHybridAESEncrypter, r14), r14, r11.mAuthenticationHelper.getDefaultCallback());
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readJSONEncodedItem(java.lang.String r12, android.content.SharedPreferences r13, expo.modules.core.arguments.ReadableArguments r14, expo.modules.core.Promise r15) {
        /*
            r11 = this;
            java.lang.String r0 = "ExpoSecureStore"
            r1 = 0
            java.lang.String r13 = r13.getString(r12, r1)
            r1 = 2
            r2 = 0
            r3 = 1
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lbc
            r6.<init>(r13)     // Catch: org.json.JSONException -> Lbc
            java.lang.String r4 = "scheme"
            java.lang.String r4 = r6.optString(r4)
            java.lang.String r10 = "E_SECURESTORE_DECODE_ERROR"
            if (r4 != 0) goto L2e
            java.lang.Object[] r14 = new java.lang.Object[r1]
            r14[r2] = r12
            r14[r3] = r13
            java.lang.String r12 = "Stored JSON object is missing a scheme (key = %s, value = %s)"
            java.lang.String r12 = java.lang.String.format(r12, r14)
            android.util.Log.e(r0, r12)
            java.lang.String r12 = "Could not find the encryption scheme used for SecureStore item"
            r15.reject(r10, r12)
            return
        L2e:
            r13 = -1
            int r5 = r4.hashCode()     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r7 = -1202757124(0xffffffffb84f61fc, float:-4.94439E-5)
            if (r5 == r7) goto L48
            r7 = 96463(0x178cf, float:1.35173E-40)
            if (r5 == r7) goto L3e
            goto L51
        L3e:
            java.lang.String r5 = "aes"
            boolean r5 = r4.equals(r5)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            if (r5 == 0) goto L51
            r13 = 0
            goto L51
        L48:
            java.lang.String r5 = "hybrid"
            boolean r5 = r4.equals(r5)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            if (r5 == 0) goto L51
            r13 = 1
        L51:
            if (r13 == 0) goto L81
            if (r13 == r3) goto L68
            java.lang.String r13 = "The item for key \"%s\" in SecureStore has an unknown encoding scheme (%s)"
            java.lang.Object[] r14 = new java.lang.Object[r1]     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r14[r2] = r12     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r14[r3] = r4     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            java.lang.String r12 = java.lang.String.format(r13, r14)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            android.util.Log.e(r0, r12)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r15.reject(r10, r12)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            return
        L68:
            java.lang.Class<java.security.KeyStore$PrivateKeyEntry> r12 = java.security.KeyStore.PrivateKeyEntry.class
            expo.modules.securestore.SecureStoreModule$HybridAESEncrypter r13 = r11.mHybridAESEncrypter     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            java.security.KeyStore$Entry r12 = r11.getKeyEntry(r12, r13, r14)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r7 = r12
            java.security.KeyStore$PrivateKeyEntry r7 = (java.security.KeyStore.PrivateKeyEntry) r7     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            expo.modules.securestore.SecureStoreModule$HybridAESEncrypter r4 = r11.mHybridAESEncrypter     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            expo.modules.securestore.AuthenticationHelper r12 = r11.mAuthenticationHelper     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            expo.modules.securestore.AuthenticationCallback r9 = r12.getDefaultCallback()     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r5 = r15
            r8 = r14
            r4.decryptItem(r5, r6, r7, r8, r9)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            goto L99
        L81:
            java.lang.Class<java.security.KeyStore$SecretKeyEntry> r12 = java.security.KeyStore.SecretKeyEntry.class
            expo.modules.securestore.SecureStoreModule$AESEncrypter r13 = r11.mAESEncrypter     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            java.security.KeyStore$Entry r12 = r11.getKeyEntry(r12, r13, r14)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r7 = r12
            java.security.KeyStore$SecretKeyEntry r7 = (java.security.KeyStore.SecretKeyEntry) r7     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            expo.modules.securestore.SecureStoreModule$AESEncrypter r4 = r11.mAESEncrypter     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            expo.modules.securestore.AuthenticationHelper r12 = r11.mAuthenticationHelper     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            expo.modules.securestore.AuthenticationCallback r9 = r12.getDefaultCallback()     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
            r5 = r15
            r8 = r14
            r4.decryptItem(r5, r6, r7, r8, r9)     // Catch: org.json.JSONException -> L9a java.security.GeneralSecurityException -> La4 java.io.IOException -> Lb0
        L99:
            return
        L9a:
            r12 = move-exception
            android.util.Log.w(r0, r12)
            java.lang.String r13 = "Could not decode the encrypted JSON item in SecureStore"
            r15.reject(r10, r13, r12)
            return
        La4:
            r12 = move-exception
            android.util.Log.w(r0, r12)
            java.lang.String r13 = "E_SECURESTORE_DECRYPT_ERROR"
            java.lang.String r14 = "Could not decrypt the item in SecureStore"
            r15.reject(r13, r14, r12)
            return
        Lb0:
            r12 = move-exception
            android.util.Log.w(r0, r12)
            java.lang.String r13 = "E_SECURESTORE_IO_ERROR"
            java.lang.String r14 = "There was an I/O error loading the keystore for SecureStore"
            r15.reject(r13, r14, r12)
            return
        Lbc:
            r14 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r2] = r12
            r1[r3] = r13
            java.lang.String r12 = "Could not parse stored value as JSON (key = %s, value = %s)"
            java.lang.String r12 = java.lang.String.format(r12, r1)
            android.util.Log.e(r0, r12, r14)
            java.lang.String r12 = "E_SECURESTORE_JSON_ERROR"
            java.lang.String r13 = "Could not parse the encrypted JSON item in SecureStore"
            r15.reject(r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.securestore.SecureStoreModule.readJSONEncodedItem(java.lang.String, android.content.SharedPreferences, expo.modules.core.arguments.ReadableArguments, expo.modules.core.Promise):void");
    }

    private void readLegacySDK20Item(String str, ReadableArguments readableArguments, Promise promise) {
        String string = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(str, null);
        if (TextUtils.isEmpty(string)) {
            promise.resolve(null);
            return;
        }
        LegacySDK20Encrypter legacySDK20Encrypter = new LegacySDK20Encrypter();
        try {
            KeyStore keyStore = getKeyStore();
            String keyStoreAlias = legacySDK20Encrypter.getKeyStoreAlias(readableArguments);
            if (!keyStore.containsAlias(keyStoreAlias)) {
                promise.reject("E_SECURESTORE_DECRYPT_ERROR", "Could not find the keystore entry to decrypt the legacy item in SecureStore");
                return;
            }
            KeyStore.Entry entry = keyStore.getEntry(keyStoreAlias, null);
            if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
                promise.reject("E_SECURESTORE_DECRYPT_ERROR", "The keystore entry for the legacy item is not a private key entry");
            } else {
                promise.resolve(legacySDK20Encrypter.decryptItem(string, (KeyStore.PrivateKeyEntry) entry));
            }
        } catch (IOException e) {
            Log.w(TAG, e);
            promise.reject("E_SECURESTORE_IO_ERROR", "There was an I/O error loading the keystore for SecureStore", e);
        } catch (GeneralSecurityException e2) {
            Log.w(TAG, e2);
            promise.reject("E_SECURESTORE_DECRYPT_ERROR", "Could not decrypt the item in SecureStore", e2);
        }
    }

    @ExpoMethod
    public void deleteValueWithKeyAsync(String str, ReadableArguments readableArguments, Promise promise) {
        try {
            deleteItemImpl(str, promise);
        } catch (Exception e) {
            Log.e(TAG, "Caught unexpected exception when deleting from SecureStore", e);
            promise.reject("E_SECURESTORE_DELETE_ERROR", "An unexpected error occurred when deleting item from SecureStore", e);
        }
    }

    private void deleteItemImpl(String str, Promise promise) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        boolean z = true;
        boolean commit = sharedPreferences.contains(str) ? sharedPreferences.edit().remove(str).commit() : true;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (defaultSharedPreferences.contains(str)) {
            commit = (defaultSharedPreferences.edit().remove(str).commit() && commit) ? false : false;
        }
        if (commit) {
            promise.resolve(null);
        } else {
            promise.reject("E_SECURESTORE_DELETE_ERROR", "Could not delete the item from SecureStore");
        }
    }

    protected SharedPreferences getSharedPreferences() {
        return getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
    }

    private KeyStore getKeyStore() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
        if (this.mKeyStore == null) {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER);
            keyStore.load(null);
            this.mKeyStore = keyStore;
        }
        return this.mKeyStore;
    }

    private <E extends KeyStore.Entry> E getKeyEntry(Class<E> cls, KeyBasedEncrypter<E> keyBasedEncrypter, ReadableArguments readableArguments) throws IOException, GeneralSecurityException {
        KeyStore keyStore = getKeyStore();
        String keyStoreAlias = keyBasedEncrypter.getKeyStoreAlias(readableArguments);
        if (!keyStore.containsAlias(keyStoreAlias)) {
            return keyBasedEncrypter.initializeKeyStoreEntry(keyStore, readableArguments);
        }
        KeyStore.Entry entry = keyStore.getEntry(keyStoreAlias, null);
        if (!cls.isInstance(entry)) {
            throw new KeyStoreException(String.format("The entry for the keystore alias \"%s\" is not a %s", keyStoreAlias, cls.getSimpleName()));
        }
        return cls.cast(entry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class AESEncrypter implements KeyBasedEncrypter<KeyStore.SecretKeyEntry> {
        private static final String AES_CIPHER = "AES/GCM/NoPadding";
        private static final int AES_KEY_SIZE_BITS = 256;
        private static final String CIPHERTEXT_PROPERTY = "ct";
        private static final String DEFAULT_ALIAS = "key_v1";
        private static final String GCM_AUTHENTICATION_TAG_LENGTH_PROPERTY = "tlen";
        private static final String IV_PROPERTY = "iv";
        public static final String NAME = "aes";

        protected AESEncrypter() {
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public String getKeyStoreAlias(ReadableArguments readableArguments) {
            String string = readableArguments.containsKey(SecureStoreModule.ALIAS_PROPERTY) ? readableArguments.getString(SecureStoreModule.ALIAS_PROPERTY) : DEFAULT_ALIAS;
            return "AES/GCM/NoPadding:" + string;
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public KeyStore.SecretKeyEntry initializeKeyStoreEntry(KeyStore keyStore, ReadableArguments readableArguments) throws GeneralSecurityException {
            String keyStoreAlias = getKeyStoreAlias(readableArguments);
            KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(keyStoreAlias, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setUserAuthenticationRequired(readableArguments.getBoolean(AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY, false)).build();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", keyStore.getProvider());
            keyGenerator.init(build);
            keyGenerator.generateKey();
            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(keyStoreAlias, null);
            if (secretKeyEntry != null) {
                return secretKeyEntry;
            }
            throw new UnrecoverableEntryException("Could not retrieve the newly generated secret key entry");
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public void createEncryptedItem(Promise promise, final String str, KeyStore keyStore, KeyStore.SecretKeyEntry secretKeyEntry, ReadableArguments readableArguments, AuthenticationCallback authenticationCallback, PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException {
            SecretKey secretKey = secretKeyEntry.getSecretKey();
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(1, secretKey);
            final GCMParameterSpec gCMParameterSpec = (GCMParameterSpec) cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
            authenticationCallback.checkAuthentication(promise, cipher, gCMParameterSpec, readableArguments, new EncryptionCallback() { // from class: expo.modules.securestore.SecureStoreModule$AESEncrypter$$ExternalSyntheticLambda0
                @Override // expo.modules.securestore.EncryptionCallback
                public final Object run(Promise promise2, Cipher cipher2, GCMParameterSpec gCMParameterSpec2, PostEncryptionCallback postEncryptionCallback2) {
                    return SecureStoreModule.AESEncrypter.this.m206xadbeebb(str, gCMParameterSpec, promise2, cipher2, gCMParameterSpec2, postEncryptionCallback2);
                }
            }, postEncryptionCallback);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$createEncryptedItem$0$expo-modules-securestore-SecureStoreModule$AESEncrypter */
        public /* synthetic */ Object m206xadbeebb(String str, GCMParameterSpec gCMParameterSpec, Promise promise, Cipher cipher, GCMParameterSpec gCMParameterSpec2, PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException, JSONException {
            return createEncryptedItem(promise, str, cipher, gCMParameterSpec, postEncryptionCallback);
        }

        JSONObject createEncryptedItem(Promise promise, String str, Cipher cipher, GCMParameterSpec gCMParameterSpec, PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException, JSONException {
            String encodeToString = Base64.encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)), 2);
            String encodeToString2 = Base64.encodeToString(gCMParameterSpec.getIV(), 2);
            JSONObject put = new JSONObject().put(CIPHERTEXT_PROPERTY, encodeToString).put(IV_PROPERTY, encodeToString2).put(GCM_AUTHENTICATION_TAG_LENGTH_PROPERTY, gCMParameterSpec.getTLen());
            postEncryptionCallback.run(promise, put);
            return put;
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public void decryptItem(Promise promise, JSONObject jSONObject, KeyStore.SecretKeyEntry secretKeyEntry, ReadableArguments readableArguments, AuthenticationCallback authenticationCallback) throws GeneralSecurityException, JSONException {
            String string = jSONObject.getString(CIPHERTEXT_PROPERTY);
            String string2 = jSONObject.getString(IV_PROPERTY);
            int r3 = jSONObject.getInt(GCM_AUTHENTICATION_TAG_LENGTH_PROPERTY);
            final byte[] decode = Base64.decode(string, 0);
            GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(r3, Base64.decode(string2, 0));
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            cipher.init(2, secretKeyEntry.getSecretKey(), gCMParameterSpec);
            authenticationCallback.checkAuthentication(promise, jSONObject.optBoolean(AuthenticationHelper.REQUIRE_AUTHENTICATION_PROPERTY), cipher, gCMParameterSpec, readableArguments, new EncryptionCallback() { // from class: expo.modules.securestore.SecureStoreModule$AESEncrypter$$ExternalSyntheticLambda1
                @Override // expo.modules.securestore.EncryptionCallback
                public final Object run(Promise promise2, Cipher cipher2, GCMParameterSpec gCMParameterSpec2, PostEncryptionCallback postEncryptionCallback) {
                    return SecureStoreModule.AESEncrypter.lambda$decryptItem$1(decode, promise2, cipher2, gCMParameterSpec2, postEncryptionCallback);
                }
            }, null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ Object lambda$decryptItem$1(byte[] bArr, Promise promise, Cipher cipher, GCMParameterSpec gCMParameterSpec, PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException, JSONException {
            String str = new String(cipher.doFinal(bArr), StandardCharsets.UTF_8);
            promise.resolve(str);
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class HybridAESEncrypter implements KeyBasedEncrypter<KeyStore.PrivateKeyEntry> {
        private static final String DEFAULT_ALIAS = "key_v1";
        private static final String ENCRYPTED_SECRET_KEY_PROPERTY = "esk";
        private static final int GCM_AUTHENTICATION_TAG_LENGTH_BITS = 128;
        private static final int GCM_IV_LENGTH_BYTES = 12;
        public static final String NAME = "hybrid";
        private static final String RSA_CIPHER = "RSA/None/PKCS1Padding";
        private static final String RSA_CIPHER_LEGACY_PROVIDER = "AndroidOpenSSL";
        private static final int X509_SERIAL_NUMBER_LENGTH_BITS = 160;
        private AESEncrypter mAESEncrypter;
        protected Context mContext;
        private SecureRandom mSecureRandom = new SecureRandom();

        HybridAESEncrypter(Context context, AESEncrypter aESEncrypter) {
            this.mContext = context;
            this.mAESEncrypter = aESEncrypter;
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public String getKeyStoreAlias(ReadableArguments readableArguments) {
            String string = readableArguments.containsKey(SecureStoreModule.ALIAS_PROPERTY) ? readableArguments.getString(SecureStoreModule.ALIAS_PROPERTY) : DEFAULT_ALIAS;
            return "RSA/None/PKCS1Padding:" + string;
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public KeyStore.PrivateKeyEntry initializeKeyStoreEntry(KeyStore keyStore, ReadableArguments readableArguments) throws GeneralSecurityException {
            String keyStoreAlias = getKeyStoreAlias(readableArguments);
            KeyPairGeneratorSpec.Builder alias = new KeyPairGeneratorSpec.Builder(this.mContext).setAlias(keyStoreAlias);
            KeyPairGeneratorSpec build = alias.setSubject(new X500Principal("CN=" + (Typography.quote + keyStoreAlias.replace("\\", "\\\\").replace("\"", "\\\"") + Typography.quote) + ", OU=SecureStore")).setSerialNumber(new BigInteger((int) X509_SERIAL_NUMBER_LENGTH_BITS, this.mSecureRandom)).setStartDate(new Date(0L)).setEndDate(new Date(Long.MAX_VALUE)).build();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", keyStore.getProvider());
            keyPairGenerator.initialize(build);
            keyPairGenerator.generateKeyPair();
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keyStoreAlias, null);
            if (privateKeyEntry != null) {
                return privateKeyEntry;
            }
            throw new UnrecoverableEntryException("Could not retrieve the newly generated private key entry");
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public void createEncryptedItem(Promise promise, final String str, KeyStore keyStore, final KeyStore.PrivateKeyEntry privateKeyEntry, ReadableArguments readableArguments, AuthenticationCallback authenticationCallback, final PostEncryptionCallback postEncryptionCallback) throws GeneralSecurityException, JSONException {
            GCMParameterSpec gCMParameterSpec;
            final byte[] bArr = new byte[12];
            this.mSecureRandom.nextBytes(bArr);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            final SecretKey generateKey = keyGenerator.generateKey();
            final GCMParameterSpec gCMParameterSpec2 = new GCMParameterSpec(128, bArr);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, generateKey, gCMParameterSpec2);
            try {
                gCMParameterSpec = (GCMParameterSpec) cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
            } catch (InvalidParameterSpecException unused) {
                if (!CodePackage.GCM.equals(cipher.getParameters().getAlgorithm())) {
                    throw new InvalidAlgorithmParameterException("Algorithm chosen by the cipher (" + cipher.getParameters().getAlgorithm() + ") doesn't match requested (GCM).");
                }
                gCMParameterSpec = gCMParameterSpec2;
            }
            authenticationCallback.checkAuthentication(promise, cipher, gCMParameterSpec, readableArguments, new EncryptionCallback() { // from class: expo.modules.securestore.SecureStoreModule.HybridAESEncrypter.1
                @Override // expo.modules.securestore.EncryptionCallback
                public Object run(Promise promise2, Cipher cipher2, GCMParameterSpec gCMParameterSpec3, PostEncryptionCallback postEncryptionCallback2) throws GeneralSecurityException, JSONException {
                    return HybridAESEncrypter.this.mAESEncrypter.createEncryptedItem(promise2, str, cipher2, gCMParameterSpec2, postEncryptionCallback2);
                }
            }, new PostEncryptionCallback() { // from class: expo.modules.securestore.SecureStoreModule.HybridAESEncrypter.2
                @Override // expo.modules.securestore.PostEncryptionCallback
                public void run(Promise promise2, Object obj) throws JSONException, GeneralSecurityException {
                    JSONObject jSONObject = (JSONObject) obj;
                    String string = jSONObject.getString("iv");
                    String encodeToString = Base64.encodeToString(bArr, 2);
                    if (!string.equals(encodeToString)) {
                        Log.e(SecureStoreModule.TAG, String.format("HybridAESEncrypter generated two different IVs: %s and %s", encodeToString, string));
                        throw new IllegalStateException("HybridAESEncrypter must store the same IV as the one used to parameterize the secret key");
                    }
                    byte[] encoded = generateKey.getEncoded();
                    Cipher rSACipher = HybridAESEncrypter.this.getRSACipher();
                    rSACipher.init(1, privateKeyEntry.getCertificate());
                    jSONObject.put(HybridAESEncrypter.ENCRYPTED_SECRET_KEY_PROPERTY, Base64.encodeToString(rSACipher.doFinal(encoded), 2));
                    postEncryptionCallback.run(promise2, jSONObject);
                }
            });
        }

        @Override // expo.modules.securestore.SecureStoreModule.KeyBasedEncrypter
        public void decryptItem(Promise promise, JSONObject jSONObject, KeyStore.PrivateKeyEntry privateKeyEntry, ReadableArguments readableArguments, AuthenticationCallback authenticationCallback) throws GeneralSecurityException, JSONException {
            byte[] decode = Base64.decode(jSONObject.getString(ENCRYPTED_SECRET_KEY_PROPERTY), 0);
            Cipher rSACipher = getRSACipher();
            rSACipher.init(2, privateKeyEntry.getPrivateKey());
            this.mAESEncrypter.decryptItem(promise, jSONObject, new KeyStore.SecretKeyEntry(new SecretKeySpec(rSACipher.doFinal(decode), "AES")), readableArguments, authenticationCallback);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Cipher getRSACipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
            if (Build.VERSION.SDK_INT < 23) {
                return Cipher.getInstance(RSA_CIPHER, RSA_CIPHER_LEGACY_PROVIDER);
            }
            return Cipher.getInstance(RSA_CIPHER);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class LegacySDK20Encrypter {
        private static final String DEFAULT_ALIAS = "MY_APP";
        private static final String RSA_CIPHER = "RSA/ECB/PKCS1Padding";

        private LegacySDK20Encrypter() {
        }

        String getKeyStoreAlias(ReadableArguments readableArguments) {
            return readableArguments.containsKey(SecureStoreModule.ALIAS_PROPERTY) ? readableArguments.getString(SecureStoreModule.ALIAS_PROPERTY) : DEFAULT_ALIAS;
        }

        String decryptItem(String str, KeyStore.PrivateKeyEntry privateKeyEntry) throws GeneralSecurityException {
            byte[] decode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance(RSA_CIPHER);
            cipher.init(2, privateKeyEntry.getPrivateKey());
            return new String(cipher.doFinal(decode), StandardCharsets.UTF_8);
        }
    }
}
