package db2.onlineshop.web.controller;

import db2.onlineshop.entity.User;
import db2.onlineshop.security.SecurityService;
import db2.onlineshop.security.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        log.info("login:start");
        model.addAttribute("user", new User());

        return "login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@ModelAttribute User user, HttpServletResponse response) throws IOException {
        log.info("login:user={}", user);
        Session session = securityService.login(user.getLogin(), user.getPassword());
        if (session != null) {
            Cookie cookie = new Cookie("user-token", session.getToken());
            cookie.setMaxAge(60 * 60 * 5);
            response.addCookie(cookie);
            response.sendRedirect("products");
        } else {
            response.sendRedirect("/login");
        }
    }
}
