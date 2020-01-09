import com.tensquare.encrypt.rsa.RsaKeys;
import com.tensquare.encrypt.service.RsaService;
import com.tensquare.encrypt.service.RsaServiceImpl;
import org.junit.Test;

/**
 * @author JinLu
 * @date 2020/1/9 14:55
 */
public class Test2 {

    private RsaService rsaService = new RsaServiceImpl();

    @Test
    public void test1() throws Exception {
        String str = "{\n" +
                "\t\"title\":\"java\"\n" +
                "}";
        String encode = rsaService.RSAEncryptDataPEM(str, RsaKeys.getServerPubKey());

        System.out.println(encode);
        String decode = rsaService.RSADecryptDataPEM(encode, RsaKeys.getServerPrvKeyPkcs8());
        System.out.println(decode);
    }
}
