import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
public class mongo extends Application {
   public static String title="";
    @FXML
    private TextField movietxt,actortxt,genretxt,directortxt;
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
    /*public static void main(String[] args) {
        Logger mongoLogger=Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        System.setProperty("jdk.tls.trustNameService","true");
        MongoClientURI mongo = new MongoClientURI("mongodb+srv://khadija:zaidi123@cluster0.6hfbs.mongodb.net/FirstDatabse?retryWrites=true&w=majority&connectTimeoutMS=30000&socketTimeoutMS=30000");
        try( MongoClient mongoClient = new MongoClient(mongo)) {
            MongoDatabase myDB = mongoClient.getDatabase("FirstDatabse");
            BasicDBObject query = new BasicDBObject("cast", "Charles Kayser");
            MongoCollection<Document> collection = myDB.getCollection("movies");
            FindIterable<Document> iterDoc =
                    collection.find().projection(Projections.fields(Projections.include("imdb.rating"))).sort(new BasicDBObject("imdb.rating",1)).limit(200);
            Iterator it = iterDoc.iterator();
            while (it.hasNext()) {
                System.out.println(it.next()+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public void searchByTitle(ActionEvent event){
        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("titleSearch.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
            Logger mongoLogger=Logger.getLogger("org.mongodb.driver");
            mongoLogger.setLevel(Level.SEVERE);
            System.setProperty("jdk.tls.trustNameService","true");
            MongoClientURI mongo = new MongoClientURI("mongodb+srv://khadija:zaidi123@cluster0.6hfbs.mongodb.net/FirstDatabse?retryWrites=true&w=majority&connectTimeoutMS=30000&socketTimeoutMS=30000");
            try( MongoClient mongoClient = new MongoClient(mongo)) {
                MongoDatabase myDB = mongoClient.getDatabase("FirstDatabse");
                title=movietxt.getText();
                BasicDBObject query = new BasicDBObject("title", title);
                MongoCollection<Document> collection = myDB.getCollection("movies");
                FindIterable<Document> iterDoc = collection.find(query).projection(Projections.fields(Projections.include("title","year","runtime","plot","type","directors","imdb.rating","imdb.votes","countries","genres")));
                Iterator it = iterDoc.iterator();
                ArrayList<String> document= new ArrayList<String>();
                while (it.hasNext()) {
                    String result=it.next().toString();
                    result=result+"\n";
                    document.add(result);
                }
                System.out.println(document);
                //textArea.setText(String.valueOf(document));
                textArea.setText("ABC");
                textArea.setEditable(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchByActor(ActionEvent event){
    }
    public void searchByDirector(ActionEvent event){
    }
    public void searchByGenre(ActionEvent event){
    }

}
