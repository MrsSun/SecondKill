package secondkill.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public class UuidUtilsTest {

    @Test
    public void testGenerateUuid() throws Exception {
        String uuid = UuidUtils.generateUuid();
        System.out.println(uuid.length());
        System.out.println(uuid);
    }
}