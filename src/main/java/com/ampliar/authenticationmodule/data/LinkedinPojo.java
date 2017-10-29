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
public class LinkedinPojo {
    private String pictureUrl;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String publicProfileUrl;
    private String summary;
    private String industry;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getEmail() {
        return emailAddress;
    }

    public void setEmail(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPublicProfileUrl() {
        return publicProfileUrl;
    }

    public void setPublicProfileUrl(String publicProfileUrl) {
        this.publicProfileUrl = publicProfileUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    @Override
  public String toString()
  {
    return 
    
      "FacebookPojo [email=" + this.emailAddress + ",  first name=" + this.firstName +  ", last name="+this.lastName+", industry="+this.industry+", picture URL="+this.pictureUrl+", public profile URL="+this.publicProfileUrl+", summary="+this.summary+"]";
  }
}
