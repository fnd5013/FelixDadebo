/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "IntroJavaFXPU"     
        manager = (EntityManager) Persistence.createEntityManagerFactory("FelixDadeboPU").createEntityManager();
         //database reference: "IntroJavaFXPU"

        
        StudentName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        StudentFollowers.setCellValueFactory(new PropertyValueFactory<>("Followers"));

        StudentActivity.setCellValueFactory(new PropertyValueFactory<>("Activity"));

        //enable row selection
        //(SelectionMode.MULTIPLE);
        UserTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
   
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
        List<Usermodel> users = query.getResultList();

        for (Usermodel s : users) {
            System.out.println(s.getFollowers() + " " + s.getName() + " " + s.getActivity());
        }
        
        return users ;
    } 
      public Usermodel readByFollowers(int followers){
        Query query = manager.createNamedQuery("Usermodel.findByFollowers");
        
        // setting query parameter
        query.setParameter("Followers", followers);
        
        // execute query
        Usermodel u = (Usermodel) query.getSingleResult();
        if (u != null) {
            System.out.println(u.getFollowers() + " " + u.getName() + " " + u.getActivity());
        }
        
        return u;
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
      public List<Usermodel> readByAndActivity(boolean activity){
        Query query = manager.createNamedQuery("Usermodel.findByActivity");
        
        // setting query parameter
        query.setParameter("activity", activity);
        
        
        // execute query
        List<Usermodel> user =  query.getResultList();
        for (Usermodel u: user) {
            System.out.println(u.getFollowers() + " " + u.getName() + " " + u.getActivity());
        }
        
        return user;
    }      
       
      
      public List<Usermodel> readByNameAdvanced(String name) {
        Query query = manager.createNamedQuery("Usermodel.findByNameAdvanced");

        // setting query parameter
        query.setParameter("name", name);

        // execute query
        List<Usermodel> user = query.getResultList();
        for (Usermodel u : user) {
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
    private TableView<Usermodel> UserTable;

    @FXML
    private TableColumn<Usermodel,Integer> StudentFollowers;

    @FXML
    private TableColumn<Usermodel, String> StudentName;

    @FXML
    private TableColumn<Usermodel, Boolean> StudentActivity;

    @FXML
    private Button searchButton;

    @FXML
    private TextField SearchField;
    
    private ObservableList<Usermodel> UserData;
    public void setTableData(List<Usermodel> UserList) {

        // initialize the studentData variable
        UserData = FXCollections.observableArrayList();

        // add the student objects to an observable list object for use with the GUI table
        UserList.forEach(s -> {
            UserData.add(s);
        });

        // set the the table items to the data in studentData; refresh the table
        UserTable.setItems(UserData);
        UserTable.refresh();
    }
    
      @FXML
      void searchByNameAction(ActionEvent event) {
        System.out.println("clicked");

        // getting the name from input box        
        String name = SearchField.getText();

        // calling a db read operaiton, readByName
        List<Usermodel> user = readByName(name);

        if (user == null || user.isEmpty()) {

            // show an alert to inform user 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("This is header section to write heading");// line 3
            alert.setContentText("No student");// line 4
            alert.showAndWait(); // line 5
        } else {

            // setting table data
            setTableData(user);
        }
    }
       @FXML
    void searchByNameAdvancedAction(ActionEvent event) {
        System.out.println("clicked");

        // getting the name from input box        
        String name = SearchField.getText();

        // calling a db read operaiton, readByName
        List<Usermodel> user = readByNameAdvanced(name);

        // setting table data
        //setTableData(students);
        // add an alert
        if (user == null || user.isEmpty()) {

            // show an alert to inform user 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");// line 2
            alert.setHeaderText("This is header section to write heading");// line 3
            alert.setContentText("No student");// line 4
            alert.showAndWait(); // line 5
        } else {
            // setting table data
            setTableData(user);
        }

    }
    
    @FXML
    void actionShowDetails(ActionEvent event) throws IOException {
        System.out.println("clicked");

        
        // pass currently selected model
        Usermodel selectedUser = UserTable.getSelectionModel().getSelectedItem();
        
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailModelView.fxml"));

        // load the ui elements
        Parent detailedModelView = loader.load();

        // load the scene
        Scene tableViewScene = new Scene(detailedModelView);

        //access the detailedControlled and call a method
        DetailModelController detailedControlled = loader.getController();


        detailedControlled.initData(selectedUser);

        // create a new state
        Stage stage = new Stage();
        stage.setScene(tableViewScene);
        stage.show();

    }
    
     @FXML
    void actionShowDetailsInPlace(ActionEvent event) throws IOException {
        System.out.println("clicked");

       
        Usermodel selectedUser = UserTable.getSelectionModel().getSelectedItem();

        
        // fxml loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DetailModelView.fxml"));

        // load the ui elements
        Parent detailedModelView= loader.load();

        // load the scene
        Scene tableViewScene = new Scene(detailedModelView);

        //access the detailedControlled and call a method
         DetailModelController detailedControlled = loader.getController();


        detailedControlled.initData(selectedUser);

        // pass current scene to return
        Scene currentScene = ((Node) event.getSource()).getScene();
        detailedControlled.setPreviousScene(currentScene);

        //This line gets the Stage information
        Stage stage = (Stage) currentScene.getWindow();

        stage.setScene(tableViewScene);
        stage.show();
    }
    
        

    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   
        
    

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
        System.out.println("Enter Number of Followers:");
        int followers = input.nextInt();
        
       
        
        Usermodel u = readByFollowers(followers);
        System.out.println("we are deleting this student with this number of followers: "+ u.toString());
        delete(u);

    }



    

    @FXML
    void readByFollowers(ActionEvent event) {
       Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter Number of followers:");
        int Followers = input.nextInt();
        
        Usermodel u = readByFollowers(Followers);
        System.out.println(u.toString());

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
