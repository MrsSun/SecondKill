package secondkill.util;

import java.util.UUID;

/**
 * @author chaoge
 * @date 2017/6/9
 */
public class UuidUtils {

    public static String generateUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
