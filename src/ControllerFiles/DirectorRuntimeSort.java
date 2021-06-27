package ControllerFiles;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectorRuntimeSort {
    @FXML
    private TextArea textArea;

    public String director = mongo.director;

    public void initialize(){
        Parent tableViewParent = null;
        try {
            textArea.setText(director);
            Logger mongoLogger=Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);
            System.setProperty("jdk.tls.trustNameService","true");
            MongoClientURI mongo = new MongoClientURI("mongodb+srv://khadija:zaidi123@cluster0.6hfbs.mongodb.net/FirstDatabse?retryWrites=true&w=majority&connectTimeoutMS=30000&socketTimeoutMS=30000");
            try( MongoClient mongoClient = new MongoClient(mongo)) {
                MongoDatabase myDB = mongoClient.getDatabase("FirstDatabse");

                BasicDBObject query = new BasicDBObject("directors", director);
                MongoCollection<Document> collection = myDB.getCollection("movies");
                FindIterable<Document> iterDoc = collection.find(query).projection(Projections.fields(Projections.include("title","year","runtime","plot","type","directors","imdb.rating","imdb.votes","countries","genres"))).sort(new BasicDBObject("runtime",-1)).limit(500);
                Iterator it = iterDoc.iterator();
                ArrayList<String> document= new ArrayList<String>();
                while (it.hasNext()) {
                    String result=it.next().toString();
                    result=result+"\n";
                    document.add(result);
                }
                System.out.println(document);
                textArea.setText(String.valueOf(document));
                textArea.setEditable(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Back(ActionEvent event) throws IOException {
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("../FxmlFiles/SearchWindow.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            window.centerOnScreen();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
