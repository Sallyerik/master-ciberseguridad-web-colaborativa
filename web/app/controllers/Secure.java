package controllers;


import helpers.HashUtils;
import models.User;
import play.i18n.Messages;
import play.mvc.Controller;

public class Secure extends Controller {

    public static void login(){
        render();
    }

    public static void logout(){
        session.remove("password");
        login();
    }

   public static void authenticate(String username, String password){
    	
    	//Validate entry arguments username & password
    	boolean Userempty = StringUtils.isEmpty(username);
    	boolean Passwordempty = StringUtils.isEmpty(password);
    	
    	
    	 if (Userempty != false ){
    		 flash.put("error", Messages.get("Public.login.error.email.field"));
             login();
         }else{	 
        	 if (Passwordempty != false ){
        		 flash.put("error", Messages.get("Public.login.error.password.field"));
                 login();
        	 }else{
		    	System.out.println("username is:" + username);
		    	User u = User.loadUser(username);
		        if (u != null && u.getPassword().equals(HashUtils.getMd5(password))){
		            session.put("username", username);
		            session.put("password", password);
		            Application.index();
		        }else{
		            flash.put("error", Messages.get("Public.login.error.credentials"));
		            login();
		        }
		
		    }
         }
   }
}
