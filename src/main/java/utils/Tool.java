package utils;

import javax.servlet.http.HttpServletRequest;

public class Tool {
    public static boolean checkReferer(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        referer = referer == null ? request.getHeader("Referer") : referer;
        if (referer != null) {
            boolean flag = false;
            for (String item : AppConfig.REFERRER_WHITELIST) {
                if (referer.contains(item)) {
                    flag = true;
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }
}
