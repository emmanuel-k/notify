package notify.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Note
 * @author Emmanuel KWENE NJUME
 */
public class Note {
    private int id;
    private String text;
    private Timestamp creationTime;
    private int author;
    
    // Constructeur
    //=============
    public Note() {}
    public Note(int id, String text, Timestamp time, int author)
    {
        this.setId(id);
        this.setText(text);
        this.setCreationTime(time);
        this.setAuthor(author);
    }
	// Getters
    //====================================
    public int getId() { return this.id; }
    public String getText() { return this.text; }
    public Timestamp getCreationTime() { return this.creationTime; }
    public int getAuthor() { return this.author; }
    
    // Setters
    //====================================================
    public final void setId(int id) { this.id = id; }
    public final void setText(String text) { this.text = text; }
    public final void setCreationTime() { this.creationTime = new Timestamp(new Date().getTime()); }
    public final void setCreationTime(Timestamp creationTime) { this.creationTime = creationTime; }
    public final void setAuthor(int author) { this.author = author; }
    
    // Miscellanous
    //============================
    public boolean equals(Note n) { return ( (this.id == n.getId()) || (this.text.equals(n.getText())) ); }
}