package com.dame.uasz.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Action {

    private String type;
    private String userEmail;
    private Date dateCreation;

    /*public Action(){}
    public Action(String type, String userEmail, Date dateCreation){
        this.type = type;
        this.userEmail = userEmail;
        this.dateCreation = dateCreation;
    }

    public String getType(){return this.type;}
    public String getUserEmail(){return this.userEmail;}
    public Date getDateCreation(){return this.dateCreation;}

    public void setType(String type){this.type = type;}
    public void setUserEmail(String userEmail){this.userEmail = userEmail;}
    public void setDateCreation(Date dateCreation){this.dateCreation = dateCreation;}*/
}
