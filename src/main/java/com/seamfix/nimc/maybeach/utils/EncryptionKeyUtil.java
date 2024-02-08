package com.seamfix.nimc.maybeach.utils;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.seamfix.nimc.maybeach.exceptions.CbsIntegrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class EncryptionKeyUtil {

    private static final int BITS_PER_BYTE = 8;
    private static final int KEY_LENGTH = 128;
    private static final int DEFAULT_IV_LENGTH = 16;

    private EncryptionKeyUtil(){

    }

    public static String decrypt(String cbsApiKey, String encryptedData, String algorithm){
        try {
            byte[] data = Base64.getDecoder().decode(encryptedData.getBytes());
            SecretKey secretKey = toSecretKey(cbsApiKey, KEY_LENGTH);
            return decrypt(data, secretKey, algorithm);
        }catch (GeneralSecurityException | UnsupportedEncodingException e){
            log.error("Error decrypting string",e);
        }
        return null;
    }

    private static String decrypt(byte[] data, SecretKey secretKey, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(algorithm);
        byte[] ivArray = Arrays.copyOfRange(data, 0, DEFAULT_IV_LENGTH);
        IvParameterSpec ivSpec = new IvParameterSpec(ivArray);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        return new String(cipher.doFinal(Arrays.copyOfRange(data, DEFAULT_IV_LENGTH, data.length)));
    }

    private static SecretKey toSecretKey(String key, int keySize) throws UnsupportedEncodingException {
        if(keySize != 128 && keySize != 192 && keySize != 256){
            throw new CbsIntegrationException("Unsupported key size");
        }
        byte[] keySrc = key.getBytes(StandardCharsets.UTF_8.name());
        if(keySrc.length * BITS_PER_BYTE < keySize){
            throw new CbsIntegrationException("Key too short");
        }
        byte[] keyDest = new byte[keySize / BITS_PER_BYTE];
        System.arraycopy(keySrc, 0, keyDest, 0, keyDest.length);

        return new SecretKeySpec(keyDest,"AES");
    }
}