/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kasuni
 */
public interface ListenerService {
    
   
    public void premethod(HttpServletRequest request);
    public void postmethod (HttpServletRequest request);
    
}
