package db2.onlineshop.web.filter;

import javax.servlet.http.Cookie;

public class UserToken {
    // service
    public static String getToken(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
