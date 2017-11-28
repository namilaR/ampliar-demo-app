/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ampliar.webanalytics;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 *
 * @author Kasuni
 */
//Get Active Session 
public class SessionCounterListener implements HttpSessionListener {
    
    private static int totalActiveSessions;

    public static int getTotalActiveSession(){
    return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        totalActiveSessions++;
	System.out.println("sessionCreated - add one session into counter"+totalActiveSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        totalActiveSessions--;
	System.out.println("sessionDestroyed - deduct one session from counter"+totalActiveSessions);
    }
    
}
