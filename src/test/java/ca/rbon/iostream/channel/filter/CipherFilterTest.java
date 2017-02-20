package ca.rbon.iostream.channel.filter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import ca.rbon.iostream.IoStream;
import ca.rbon.iostream.wrap.BufferedReaderOf;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;
import ca.rbon.iostream.wrap.PrintWriterOf;

public class CipherFilterTest {
    
    static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
    
    final String s = "Using IoStream for encryption";
    
    // SECRETS
    
    final String key = "Bar12345Bar12345";
    final String initVector = "RandomInitVector";
    
    Cipher outCipher;
    Cipher inCipher;
    
    @Before
    public void init() throws Exception {
        // OUTPUT
        IvParameterSpec inIV = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec outSkeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        outCipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
        outCipher.init(Cipher.ENCRYPT_MODE, outSkeySpec, inIV);
        
        // INPUT
        
        IvParameterSpec outIv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec inSkeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        inCipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
        inCipher.init(Cipher.DECRYPT_MODE, inSkeySpec, outIv);
    }
    
    @Test
    public void bytesCipher() throws Exception {
        OutputStreamOf<byte[]> encryptedBytes = IoStream.bytes().cipher(outCipher).outputStream();
        encryptedBytes.write(s.getBytes());
        encryptedBytes.close();
        
        OutputStreamOf<byte[]> bytes = IoStream.bytes().outputStream();
        InputStreamOf<byte[]> in = IoStream.bytes(encryptedBytes.get()).cipher(inCipher).inputStream();
        
        IOUtils.copy(in, bytes);
        Assertions.assertThat(new String(bytes.get())).isEqualTo(s);
    }
    
    @Test
    public void stringCipherBase64() throws Exception {
        PrintWriterOf<byte[]> encryptedBytes = IoStream.bytes().base64().cipher(outCipher).printWriter();
        encryptedBytes.write(s);
        encryptedBytes.close();
        
        BufferedReaderOf<byte[]> in = IoStream.bytes(encryptedBytes.get()).base64().cipher(inCipher).bufferedReader();
        
        Assertions.assertThat(in.readLine()).isEqualTo(s);
    }
    
}
