package javamongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guexa
 */

public class Connection {
    DB Database;
    DBCollection colection;
    BasicDBObject document = new BasicDBObject();
    
    public Connection(){
        try {
            Mongo mongo = new Mongo("localhost", 27017);
            Database = mongo.getDB("Restaurant");
            colection = Database.getCollection("Plates");
            
            System.out.println("Established connection");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Create the CRUD
    public boolean Insert(String action){
        document.put("action", action);
        colection.insert(document);
        return true;
    }
    
    public void Show(){
        DBCursor cursor = colection.find();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    
    public boolean Update(String beforeAction, String newAction){
        document.put("action", beforeAction);
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("action", newAction);
        colection.findAndModify(document, newDocument);
        
        return true;
    }
    
    public boolean Delete(String action){
        document.put("action", action);
        colection.remove(document);
        
        return true;
    }
}
