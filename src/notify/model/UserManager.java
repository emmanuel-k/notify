package notify.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * UserManager
 * @author Emmanuel KWENE NJUME
 */
public class UserManager extends Manager<User> {
    protected static String table = "user";
    
    public UserManager(Connection con) { super(con); }
    
    @Override
    public boolean create(User obj) {
        try {
            this.connect.createStatement().executeUpdate("INSERT INTO "+table+"(name, pass) VALUES('"
                    + obj.getName().replaceAll("'", "''") + "', '"
                    + obj.getPassword() + "')"
            );
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean update(User obj) {
        try {
            this.connect.createStatement().executeUpdate("UPDATE "+table+" SET "
                    +"name = "+obj.getName().replaceAll("'", "''")+", pass = "+obj.getPassword()+" WHERE id = " + obj.getId() );
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean delete(User obj) {
        try {
            this.connect.createStatement().executeUpdate("DELETE FROM "+table+" WHERE id = " + obj.getId() );
            this.connect.createStatement().executeUpdate("DELETE FROM "+NoteManager.getTable()+" WHERE userId = " + obj.getId() );
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public User find(int id) {
        User user = null;
        try {
            ResultSet rs = this.connect.createStatement().executeQuery("SELECT * FROM "+table+" WHERE id = "+id);
            
            while(rs.next()) {
                user = new User( id, rs.getString("name"), rs.getString("pass") );
                
                // Remplissons sa liste de notes
                ResultSet resultSet = this.connect.createStatement().executeQuery("SELECT * FROM " + NoteManager.getTable() + " WHERE userId = " + id+" ORDER BY id");
                NoteManager noteDAO = new NoteManager(this.connect);
                
                while(resultSet.next()) {
                    user.addNote(noteDAO.find(rs.getInt("id")));
                }
                break;
            }
        } catch(SQLException e) {}
        return user;
    }

    /**
     * return a list of all users
     * @return Set of User 
     */
    public Set<User> findAll() {
        Set<User> listUser = new HashSet<>();
        try {
            
            ResultSet rs = this.connect.createStatement().executeQuery("SELECT * FROM "+table+" ORDER BY id DESC");
            
            while(rs.next()) {
                User user = new User( rs.getInt("id"), rs.getString("name"), rs.getString("pass") );
                listUser.add(user);
            }
        } catch(SQLException e) {}
        return listUser;
    }
    
    /**
     * Connect an user, then return him (or null if connection fails)
     * @param name
     * @param password
     * @return User or null
     */
    public User connectUser(String name, String password) {
        User user = null;
        try {
            ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM "+table+" WHERE name = '"+name+"' and pass = '"+password+"'");
            
            while(rs.next()) {
            	connect.createStatement().executeUpdate("UPDATE "+table+" SET active = 1 WHERE id = "+rs.getInt("id"));
                user = new User( rs.getInt("id"), rs.getString("name"), rs.getString("pass") );
                break;
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return user;
    }
    
    /**
     * Returns the current logged user or null
     * @return User
     */
    public User getCurrentUser() {
        User user = null;
        try {
            ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM "+table+" WHERE active = 1");
            
            while(rs.next()) {
                user = new User( rs.getInt("id"), rs.getString("name"), rs.getString("pass") );
                
                // Remplissons sa liste de notes
                ResultSet resultSet = this.connect.createStatement().executeQuery("SELECT * FROM " + NoteManager.getTable() + " WHERE userId = " + rs.getInt("id")+" ORDER BY id DESC");
                NoteManager noteDAO = new NoteManager(this.connect);
                
                while(resultSet.next()) {
                    user.addNote(noteDAO.find(resultSet.getInt("id")));
                }
                break;
            }
            
        } catch(SQLException e) {}
        
        return user;
    }
    
    /**
     * Kill all users' session
     * @return boolean
     */
    public boolean killSession() {
        try {
            connect.createStatement().executeUpdate("UPDATE "+table+" SET active = 0");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean removeAllNotes(User user) {
    	try {
            connect.createStatement().executeUpdate("DELETE FROM "+NoteManager.getTable()+" WHERE userId = " + user.getId());
            user.removeAllNote();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}