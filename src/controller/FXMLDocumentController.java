/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.Boolean;
import model.Usermodel;
/**
 *
 * @author felixdadebo
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    
    @FXML
    private Label label;
    
     @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
         Query query = manager.createNamedQuery("Usermodel.findAll");
        List<Usermodel> data = query.getResultList();
        
        for (Usermodel s : data) {            
            System.out.println(s.getFollowers() + " " + s.getName()+ " " + s.getActivity());         
        }           

        
    }
    
    EntityManager manager;
   
  
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "IntroJavaFXPU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("FelixDadeboPU").createEntityManager();
    }    
    // Create operation
    public void create(Usermodel user) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (user.getFollowers() != null) {
                
                // create student
                manager.persist(user);
                
                // end transaction
                manager.getTransaction().commit();
                
                System.out.println(user.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public List<Usermodel> readAll(){
        Query query = manager.createNamedQuery("Usermodel.findAll");
        List<Usermodel> students = query.getResultList();

        for (Usermodel s : students) {
            System.out.println(s.getFollowers() + " " + s.getName() + " " + s.getActivity());
        }
        
        return students;
    }
      public Usermodel readByFollowers(int Followers){
        Query query = manager.createNamedQuery("Usermodel.findByFollowers");
        
        // setting query parameter
        query.setParameter("Followers", Followers);
        
        // execute query
        Usermodel user = (Usermodel) query.getSingleResult();
        if (user != null) {
            System.out.println(user.getFollowers() + " " + user.getName() + " " + user.getActivity());
        }
        
        return user;
    }  
      public List<Usermodel> readByName(String name){
        Query query = manager.createNamedQuery("Usermodel.findByName");
        
        // setting query parameter
        query.setParameter("name", name);
        
        // execute query
        List<Usermodel> user =  query.getResultList();
        for (Usermodel u: user) {
            System.out.println(u.getFollowers() + " " + u.getName() + " " + u.getActivity());
        }
        
        return user;
    }    
      
      public List<Usermodel> readByNameAndActivity(String name, boolean activity){
        Query query = manager.createNamedQuery("Usermodel.findByNameAndActivity");
        
        // setting query parameter
        query.setParameter("activity", activity);
        query.setParameter("name", name);
        
        
        // execute query
        List<Usermodel> user =  query.getResultList();
        for (Usermodel u: user) {
            System.out.println(u.getFollowers() + " " + u.getName() + " " + u.getActivity());
        }
        
        return user;
    }        
      
      // Update operation
    public void update(Usermodel model) {
        try {

            Usermodel existingUser = manager.find(Usermodel.class, model.getFollowers());

            if (existingUser != null) {
                // begin transaction
                manager.getTransaction().begin();
                
                // update all atttributes
                existingUser.setName(model.getName());
                existingUser.setActivity(model.getActivity());
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(Usermodel model) {
        try {
             Usermodel existingUser = manager.find(Usermodel.class, model.getFollowers());

            // sanity check
            if (existingUser != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingUser);
                
                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    


    


    
      

    
    

    
    
    
    
    @FXML
    private Button buttonCreateUser;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonReadAllUsers;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonReadByName;

    @FXML
    private Button buttonReadByFollowers;

    @FXML
    private Button buttonReadByNameactivity;

    @FXML
    void createUser(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("How many followers do you have:");
        int Followers = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Are you Active: TRUE or FALSE");
        Boolean active =  input.nextBoolean();
        
       
        
        
        
        // create a student instance
        Usermodel user = new Usermodel();
        
        // set properties
        user.setFollowers(Followers);
        user.setName(name);
        user.setActivity(active);
        
        // save this student to database by calling Create operation        
        create(user);
    }
     
       


    

    @FXML
    void deleteUser(ActionEvent event) {
      Scanner input = new Scanner(System.in);
        
         // read input from command line
        System.out.println("Enter Followers:");
        int Followers = input.nextInt();
        
       
        
        Usermodel s = readByFollowers(Followers);
        System.out.println("we are deleting this student with this number of followers: "+ s.toString());
        delete(s);

    }



    

    @FXML
    void readByFollowers(ActionEvent event) {
       Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter Number of followers:");
        int Followers = input.nextInt();
        
        Usermodel s = readByFollowers(Followers);
        System.out.println(s.toString());

    }



    

    @FXML
    void readByName(ActionEvent event) {
          Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter Name:");
        String name = input.next();
        
        List<Usermodel> s = readByName(name);
        System.out.println(s.toString());


        
    }


    

    @FXML
    void readByNameactivity(ActionEvent event) {
         Scanner input = new Scanner(System.in);
        
        // read input from command line
        
        System.out.println("Enter Name:");
        String name = input.next();
         
      
        System.out.println("Are you Active: TRUE or FALSE");
        Boolean active =  input.nextBoolean();
        
        
        // create a student instance      
        List<Usermodel> user =  readByNameAndActivity(name, active);

    


   }

    @FXML
    void readUser(ActionEvent event) {
        

    }

    @FXML
    void updateUser(ActionEvent event) {
         Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter Followers:");
        int Followers = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
     
        System.out.println("Are you Active: TRUE or FALSE");
        Boolean active =  input.nextBoolean();
        
        // create a student instance
        Usermodel user = new Usermodel();
        
        // set properties
        user.setFollowers(Followers);
        user.setName(name);
        user.setActivity(active);
        
        // save this student to database by calling Create operation        
        update(user);

    }
    /*
    Boolean returnBoolean(String string){
        Boolean bool = null;
        if (string == "yes"|| string == "Yes"){
           bool = true; 
        }
        if (string == "no"|| string == "No"){
            bool = false;
        }
        return bool;
        
    };
    
    
    
    */
    
   
    

   
    
}
