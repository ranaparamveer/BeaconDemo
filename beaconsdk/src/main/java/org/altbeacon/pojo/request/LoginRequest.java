package org.altbeacon.pojo.request;

/**
 * Created by KaurSarabjot on 1/25/2019.
 */

public class LoginRequest {

    /**
     * username : garora@seasiainfotech.com
     * password : Mind@123
     * grant_type : password
     */

    private String username;
    private String password;
    private String grant_type;
    private String Email;
    private String scope;
    private String refresh_token;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
