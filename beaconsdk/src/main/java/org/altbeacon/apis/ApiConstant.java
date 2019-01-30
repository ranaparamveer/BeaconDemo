package org.altbeacon.apis;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public class ApiConstant {

    /*
    * Api Header's
    * */
    public static final String ACCEPT = "Accept";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String USER_AGENT = "User-Agent";
    public static final String AUTHORIZATION = "Authorization";

    public static final boolean QA_MODE_ENABLE = true;

    //Production server base url
    public static final String PROD_BASE_URL = "https://01-auth.embercrm.com";

    // QA mode base url
    public static final String QA_BASE_URL = "https://01-auth.embercrm.com";

    public static final String SYNC_URl = "https://01-e-na.embercrm.com/api";
    //    Base url
    public static final String BASE_URL = QA_MODE_ENABLE ? QA_BASE_URL : PROD_BASE_URL;

    public static final int STATUS_200 = 200;

    /*Api Error code*/
    public static final int ERROR_400 = 400;
    public static final int ERROR_401 = 401;
}
