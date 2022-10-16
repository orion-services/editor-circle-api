package circle.util;

import java.security.SecureRandom;
import java.util.Base64;

public class BuildRandomHashcode {

    public String generateHash() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[6];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String hash = encoder.encodeToString(bytes);
        return hash;
    }


}
