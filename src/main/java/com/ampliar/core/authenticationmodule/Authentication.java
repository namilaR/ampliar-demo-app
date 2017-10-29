/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.authenticationmodule;


import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Ishani
 */
public abstract class Authentication {
    
    public abstract String getAuthorizationcode(HttpServletRequest request);
    public abstract String getAccessToken(String output,HttpServletRequest request);
    public abstract void getprofiledetails(HttpServletRequest request,String UserInfoEndpoint,String accessToken);
    public abstract void createuser(HttpServletRequest request,String authenticator);
}
