package uz.bprodevelopment.base.constant;

public class BaseApiUrls {

    public static final String BASE_URL = "/api/v1/";

    /*-------------------------------------------------------*/
    /*                  BASE APP URLS                             */
    /*-------------------------------------------------------*/
    public static final String AUTH_URL = BASE_URL + "auth";
    public static final String REGISTER_URL = AUTH_URL + "/register";
    public static final String LOGIN_URL = AUTH_URL + "/login";
    public static final String LOGOUT_URL = AUTH_URL + "/logout";

    public static final String REFRESH_TOKEN_URL = AUTH_URL + "/refresh-token";
    public static final String CHANGE_PASSWORD_URL = AUTH_URL + "/change-password";

    public static final String ACCOUNT_URL = BASE_URL + "account";
    public static final String USER_URL = BASE_URL + "users";
    public static final String ROLE_URL = BASE_URL + "roles";
    public static final String FILES_URL = BASE_URL + "files";


}
