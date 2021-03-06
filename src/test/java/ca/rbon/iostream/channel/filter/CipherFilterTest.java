package ca.rbon.iostream.channel.filter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ca.rbon.iostream.utils.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import ca.rbon.iostream.IO;
import ca.rbon.iostream.wrap.BufferedReaderOf;
import ca.rbon.iostream.wrap.InputStreamOf;
import ca.rbon.iostream.wrap.OutputStreamOf;
import ca.rbon.iostream.wrap.PrintWriterOf;

import static java.nio.charset.StandardCharsets.UTF_8;

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
        IvParameterSpec inIV = new IvParameterSpec(initVector.getBytes(UTF_8));
        SecretKeySpec outSkeySpec = new SecretKeySpec(key.getBytes(UTF_8), "AES");
        outCipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
        outCipher.init(Cipher.ENCRYPT_MODE, outSkeySpec, inIV);

        // INPUT

        IvParameterSpec outIv = new IvParameterSpec(initVector.getBytes(UTF_8));
        SecretKeySpec inSkeySpec = new SecretKeySpec(key.getBytes(UTF_8), "AES");
        inCipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
        inCipher.init(Cipher.DECRYPT_MODE, inSkeySpec, outIv);
    }

    @Test
    public void bytesCipher() throws Exception {
        OutputStreamOf<byte[]> encryptedBytes = IO.bytes().cipher(outCipher).outputStream();
        encryptedBytes.write(s.getBytes());
        encryptedBytes.close();

        OutputStreamOf<byte[]> bytes = IO.bytes().outputStream();
        InputStreamOf<byte[]> in = IO.bytes(encryptedBytes.getInner()).cipher(inCipher).inputStream();

        IOUtils.copy(in, bytes);
        Assertions.assertThat(new String(bytes.getInner())).isEqualTo(s);
    }

    @Test
    public void stringCipherBase64() throws Exception {
        PrintWriterOf<byte[]> encryptedBytes = IO.bytes().base64().cipher(outCipher).printWriter();
        encryptedBytes.write(s);
        encryptedBytes.close();

        BufferedReaderOf<byte[]> in = IO.bytes(encryptedBytes.getInner()).base64().cipher(inCipher)
                .bufferedReader();

        Assertions.assertThat(in.readLine()).isEqualTo(s);
    }

}
