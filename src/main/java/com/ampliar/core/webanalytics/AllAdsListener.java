/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ishani
 */
public class AllAdsListener implements ListenerService{

    @Override
    public void premethod(HttpServletRequest request) {
        System.out.println("logBefore() is running!");
    }

    @Override
    public void postmethod(HttpServletRequest request) {
        System.out.println("logAfter() is running!");
    }
    
}
