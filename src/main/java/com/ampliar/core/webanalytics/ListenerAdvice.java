/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;

import com.amplier.demo.controller.RestController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;


/**
 *
 * @author Kasuni
 */
@Aspect
public class ListenerAdvice {
    static Logger logger=Logger.getLogger(RestController.class.getName());
    
    
    @Before("execution(* com.amplier.demo.controller.RestController.getAddById(..)) && @annotation(listenerAnnotation)")
	public void logBeforeGetItem(Listener listenerAnnotation) {
                ListenerService listenerService=new GetItemListener();
                listenerService.premethod(null);
		
	}
        
    @AfterReturning(pointcut = "execution(* com.amplier.demo.controller.RestController.getAddById(..))&& args(request,..) && @annotation(listenerAnnotation)",returning="returnString")
	public void logAfterGetItem(Listener listenerAnnotation,String returnString,HttpServletRequest request) {
                ListenerService listenerService=new GetItemListener();
                request.getSession().setAttribute("returnString", returnString);
                listenerService.postmethod(request);
		
		
	}
        
    @Before("execution(* com.amplier.demo.controller.RestController.apiInsertMobile(..)) && args(request,..)&& @annotation(listenerAnnotation)")
	public void logBeforePostItem(Listener listenerAnnotation,HttpServletRequest request) {
                ListenerService listenerService=new PostItemListener();
                listenerService.premethod(request);
		
	}
        
    @AfterReturning(pointcut="execution(* com.amplier.demo.controller.RestController.apiInsertMobile(..)) && args(request,..)&& @annotation(listenerAnnotation)",returning="returnString")
	public void logAfterPostItem(Listener listenerAnnotation,String returnString,HttpServletRequest request) {
                ListenerService listenerService=new PostItemListener();
                listenerService.postmethod(request);
		
		
	}
        
    @Before("execution(* com.amplier.demo.controller.RestController.getAllCarsApi(..)) && @annotation(listenerAnnotation)")
	public void logBeforeAllAds(Listener listenerAnnotation) {
                ListenerService listenerService=new AllAdsListener();
                listenerService.premethod(null);
		
	}
        
    @AfterReturning(pointcut="execution(* com.amplier.demo.controller.RestController.getAllCarsApi(..)) && args(request,..) && @annotation(listenerAnnotation)",returning="returnString")
	public void logAfterAllAds(Listener listenerAnnotation,String returnString,HttpServletRequest request) {
                ListenerService listenerService=new AllAdsListener();
                listenerService.postmethod(null);
		
		
	}
}
