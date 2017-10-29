/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.data;

/**
 *
 * @author Ishani
 */
public class FacebookPojo {
  String id;
  String email;
  String name;
  String picture;
  String gender;
  String link;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getPicture()
  {
    return this.picture;
  }
  public String getGender()
  {
    return this.gender;
  }
  
  public String getLink()
  {
    return this.link;
  }
  @Override
  public String toString()
  {
    return 
    
      "FacebookPojo [id=" + this.id + ", email=" + this.email + ",  name=" + this.name +  "]";
  }
}
