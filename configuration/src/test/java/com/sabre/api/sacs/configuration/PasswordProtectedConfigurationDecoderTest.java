package com.sabre.api.sacs.configuration;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.junit.Assert;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.sabre.api.sacs.configuration.PasswordProtectedConfigurationDecoder;

public class PasswordProtectedConfigurationDecoderTest {

	private static final Logger LOG = LogManager.getLogger(PasswordProtectedConfigurationDecoderTest.class.getName());
    private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
    private static final byte[] SALT = {
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    @Test
    public void testDecryptString() throws UnsupportedEncodingException, GeneralSecurityException {
    	String toDecrypt = "OdkodujTo!";
    	String encrypted = this.encrypt(toDecrypt);
    	LOG.info(encrypted);
    	PasswordProtectedConfigurationDecoder decoder = new PasswordProtectedConfigurationDecoder();
    	Assert.assertEquals(toDecrypt, decoder.decode(encrypted));
    }
    
    private String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }

    private String base64Encode(byte[] bytes) {

        return Base64.encodeBase64String(bytes);
    }

}
