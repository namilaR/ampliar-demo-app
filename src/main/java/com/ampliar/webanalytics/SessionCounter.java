/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ampliar.webanalytics;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
 
public class SessionCounter implements HttpSessionListener
{
    private List sessions = new ArrayList();
  
    

    public SessionCounter()
    {
        //HttpSession session1 = event.getSession();
        //Date createTime = new Date(session1.getCreationTime());
        //Date lastAccessTime = new Date(session1.getLastAccessedTime());
    }

    public void sessionCreated(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
        sessions.add(session.getId());

        session.setAttribute("counter", this);
    }

    public void sessionDestroyed(HttpSessionEvent event)
    {
        HttpSession session = event.getSession();
sessions.remove(session.getId());

        session.setAttribute("counter", this);
    }
 
    public int getActiveSessionNumber()
    {
        return sessions.size();
    }
}

/**
 *
 * @author Kasuni
 */
