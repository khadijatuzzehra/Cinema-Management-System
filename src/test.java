import com.mongodb.*;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class test extends Application {
    @FXML
    private TextArea textArea;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("titleSearch.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Faculty Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void initialize(){
        try
        {
            //2) we are using localhost; by default password is 27017//
            MongoClient mongoClient=new MongoClient (new MongoClientURI("mongodb://localhost:27017"));

            //3) similar to "use test" DB in shell//
            DB myDB= mongoClient.getDB("test");

            //4) Using mytest_collection//

            DBCollection dbCollection=  myDB.getCollection("mytest_collection");

            //5)define list of hobbies for document//

            //7) Here age is an object//
            BasicDBObject query=new BasicDBObject("last_name","Anderson");
            DBCursor cursor=dbCollection.find();
            //dbCollection.aggregate();
            ArrayList<String> document=new ArrayList<String>();

            while (cursor.hasNext()){
                String result=cursor.next().toString();
                result=result+"\n";
                document.add(result);
                document.add(result);document.add(result);
                document.add(result);document.add(result);document.add(result);document.add(result);document.add(result);document.add(result);
            }
            textArea.setText(String.valueOf(document));
            textArea.setEditable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args)  {
        launch(args);
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Connecting to the database
        MongoDatabase database = mongo.getDatabase("test");
        MongoCollection<Document>collection = database.getCollection("mytest_collection");
        FindIterable<Document> iterDoc =
                collection.find().projection(Projections.fields(Projections.include("first_name"),Projections.excludeId()));
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        FindIterable<Document> iterDoc2 =
                collection.find().projection(Projections.fields(Projections.include("Education"),Projections.excludeId()));
        Iterator it2 = iterDoc2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }*/
}

