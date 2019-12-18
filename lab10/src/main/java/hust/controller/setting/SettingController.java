package main.java.hust.controller.setting;

import javax.servlet.http.HttpServletRequest;

public class SettingController {
    private static SettingController instance;

    private SettingController() {
    }

    public static SettingController getInstance() {
        if (instance == null) {
            instance = new SettingController();
        }
        return instance;
    }

    public SettingController configureRole(HttpServletRequest req) {
        String adminRole = (String) req.getSession().getAttribute("role");
        String role = "admin".equals(adminRole) ? "admin" : "customer";
        String switchRole = req.getParameter("role");
        if (switchRole != null && !switchRole.isEmpty()) {
            role = "customer".equals(switchRole) ? "customer" : "admin";
        }
        req.getSession().setAttribute("role", role);
        return this;
    }

    public SettingController configureLanguage(HttpServletRequest req) {
        String language = (String) req.getSession().getAttribute("language");
        String lang = "eng";
        if(language != null){
            switch (language) {
                case "vie":
                    lang = "vie";
                    break;
                case "jap":
                    lang = "jap";
                    break;
            }
        }

        String switchLang = req.getParameter("lang");
        if (switchLang != null && !switchLang.isEmpty()) {
            lang = "eng";
            switch (switchLang) {
                case "vie":
                    lang = "vie";
                    break;
                case "jap":
                    lang = "jap";
                    break;
            }
        }
        req.getSession().setAttribute("language", lang);
        return this;
    }
}
