package notify.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Emmanuel KWENE NJUME
 */
public class NoteManager extends Manager<Note> {
    protected static String table = "note";
    protected final static long OLDNESS_INTERVAL = 7 * 24 * 3600 * 1000; // 7 jours

    public NoteManager(Connection con) { 
    	super(con); 
    	
    	// Removing old message
    	this.removeOldNote(OLDNESS_INTERVAL);
    }
    
    public static String getTable() { return table; }
    
    @Override
    public boolean create(Note obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO "+table+"(text, creationTime, userId) VALUES('"
                    + obj.getText().replaceAll("'", "''") + "', '"
                    + obj.getCreationTime() + "', '"
                    + obj.getAuthor() + "')"
            );
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean update(Note obj) {
        try {
            this.connect.createStatement().executeUpdate("UPDATE "+table+" SET "
                    +"text = '"+obj.getText().replaceAll("'", "''")+"', creationTime = '"+obj.getCreationTime()+"', userId = '"+obj.getAuthor()+"' WHERE id = " + obj.getId() );
            return true;
        } catch (SQLException ex) { return false; }
    }

    @Override
    public boolean delete(Note obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM "+table+" WHERE id = " + obj.getId() );
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public Note find(int id) {
        Note note = null;
        try {
            
            ResultSet rs = this.connect.createStatement().executeQuery("SELECT * FROM "+table+" WHERE id = "+id);
            
            while(rs.next()) {
                note = new Note( id, rs.getString("text"), rs.getTimestamp("creationTime"), rs.getInt("userId") );
                break;
            }
        } catch(SQLException e) {}
        return note;
    }
    
    public ArrayList<Note> findAll() {
    	ArrayList<Note> listNote = new ArrayList<>();
        try {
            
            ResultSet rs = this.connect.createStatement().executeQuery("SELECT * FROM "+table+" ORDER BY id DESC");
            
            while(rs.next()) {
                Note note = new Note( rs.getInt("id"), rs.getString("text"), rs.getTimestamp("creationTime"), rs.getInt("userId") );
                listNote.add(note);
            }
        } catch(SQLException e) {}
        return listNote;
    }
    
    public void removeOldNote(long intval) {
    	ArrayList<Note> listNote = this.findAll();
    	
    	for(Note note: listNote) {
    		if(note != null) {
    			if( (new Date().getTime() - note.getCreationTime().getTime()) > intval ) {
    				this.delete(note);
    			}
    		}
    	}
    }
}
