package org.altbeacon.pojo.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public class LoginResponse {

    /**
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlxdWVfbmFtZSI6ImFrQGcuY29tIiwicm9sZSI6IlVzZXIiLCJ1c2VySWQiOiIyODAxREQ0ODkwNEM0QUVEODdGRTUyQ0Y5OTczNjNENSIsIm9yaWdpbklkIjoiNkVCQUQ1Q0U1NDU1NDZEQjhCNDVFN0YxRUQ1QThGQTciLCJhY2NvdW50SWQiOiJCNTRCMUZDMUMxMEU0MzQxQkQ1RTYwMTEyNkJGQzg2MCIsImFjY291bnRfdHlwZSI6IjAiLCJpc3MiOiJodHRwczovLzAxLWF1dGguZW1iZXJjcm0uY29tIiwiYXVkIjoiNTc3NmQ3NjRkMTMwNDY5N2IwMmY2MzRkZjZiYmIxNzAiLCJleHAiOjE1MTU0ODQzODUsIm5iZiI6MTUxNTQ4MDc4NX0.X98X8a_W6GilR391HG-qNr_KJQTm_In8-WZce6vIxsM
     * token_type : bearer
     * expires_in : 3599
     * refresh_token : 86cef6d619b849a983a1d17130708bb1
     * as:client_id : ios_MyChair_objc.6EBAD5CE545546DB8B45E7F1ED5A8FA7
     * audience : 5776d764d1304697b02f634df6bbb170
     * server_dns : 01-e-na.embercrm.com
     * .issued : Tue, 09 Jan 2018 06:53:05 GMT
     * .expires : Tue, 09 Jan 2018 07:53:05 GMT
     */

    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    @SerializedName("as:client_id")
    private String _$AsClient_id54; // FIXME check this code
    private String audience;
    private String server_dns;
    @SerializedName(".issued")
    private String _$Issued145; // FIXME check this code
    @SerializedName(".expires")
    private String _$Expires309; // FIXME check this code

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String get_$AsClient_id54() {
        return _$AsClient_id54;
    }

    public void set_$AsClient_id54(String _$AsClient_id54) {
        this._$AsClient_id54 = _$AsClient_id54;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getServer_dns() {
        return server_dns;
    }

    public void setServer_dns(String server_dns) {
        this.server_dns = server_dns;
    }

    public String get_$Issued145() {
        return _$Issued145;
    }

    public void set_$Issued145(String _$Issued145) {
        this._$Issued145 = _$Issued145;
    }

    public String get_$Expires309() {
        return _$Expires309;
    }

    public void set_$Expires309(String _$Expires309) {
        this._$Expires309 = _$Expires309;
    }


/*Error Response - 400*/
    /*{
        "error": "invalid_clientId",
            "error_description": "ClientId should be sent."
    }*/
}
