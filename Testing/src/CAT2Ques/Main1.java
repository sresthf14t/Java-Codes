/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAT2Ques;

/**
 *
 * @author Srest
 */

import java.util.*;
import java.io.*;

class MyException extends Exception {
    public MyException(String s)
    {
        super(s);
    }
}
 
class ContactDetails {
    private String mobile;
    private String alternateMobile;
    private String landline;
    private String email;
    private String address;
    
    public void setmobile(String mobile) {
        this.mobile=mobile;
    }
    public void setalternateMobile(String alternateMobile) {
        this.alternateMobile=alternateMobile;
    }
    public void setlandline(String landline) {
        this.landline=landline;
    }
    public void setemail(String email) {
        this.email=email;
    }
    public void setaddress(String address) {
        this.address=address;
    }
    
    public String getmobile() {
        return mobile;
    }
    public String getalternateMobile() {
        return alternateMobile;
    }
    public String getlandline() {
        return landline;
    }
    public String getemail() {
        return email;
    }
    public String getaddress() {
        return address;
    }
    
    ContactDetails(String mobile, String alternateMobile, String landline, String email, String address) {
        this.mobile=mobile;
        this.alternateMobile=alternateMobile;
        this.landline=landline;
        this.email=email;
        this.address=address;
    }
}


class ContactDetailsBO {
    public void Validate(String mobile, String alternateMobile) throws MyException{
        if(mobile.equals(alternateMobile)) {
            throw new MyException("Mobile number and alternate mobile number are same");
        }
    }
}

class Main1{
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in); 
        ContactDetails cd=new ContactDetails(input.next(), input.next(), input.next(), input.next(), input.next());
        ContactDetailsBO cdbo=new ContactDetailsBO();
        try {
            cdbo.Validate(cd.getmobile(), cd.getalternateMobile());
            System.out.println("Correct details");
        }
        catch(MyException ex) {
            System.out.println(ex.getMessage());
        }
    }
}