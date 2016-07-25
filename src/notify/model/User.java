package notify.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Objet représentant un utilisateur
 * @author Emmanuel KWENE NJUME
 */
public class User {
    private int id;
    private String name;
    private String password;
    private Set<Note> listeNote = new HashSet<>();
    
    // Constructeurs
    //==============
    public User() {}
    public User(int id, String name, String password) 
    { 
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
    }
    
    // Getters
    //===================================
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getPassword() { return this.password; }
    public Set<Note> getListeNote() { return this.listeNote; }
    
    // Setters
    //===================================================
    public final void setId(int id) { this.id = id; }
    public final void setName(String name) { this.name = name; }
    public final void setPassword(String pass) { this.password = pass; }
    public void setListeNote(Set<Note> otherList) { this.listeNote = otherList; }
    
    // Miscellanous
    public void addNote(Note note) { this.listeNote.add(note); }
    public void removeNote(Note note) { this.listeNote.remove(note); }
    public boolean equals(User u) { return ( (this.getId() == u.getId()) || (this.getName().equals(u.getName())) ); }
    
    public boolean hasNote(Note note) {
    	for(Note usernote: this.getListeNote()) {
    		if(usernote != null) {
    			if(usernote.equals(note)) return true;
    		}
    	}
    	
    	return false; 
    }
    
    public void removeAllNote() {
    	this.listeNote.clear();
    }
    
    @Override
    public String toString() { return this.getName(); }
}