package notify.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emmanuel KWENE NJUME
 */
public class DB {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/notify";
    private static final String DB_USER = "Ken";
    private static final String DB_PASS = "p@ss";
    private static Connection connect;
    private final static int MySQL = 0;
    private final static int SQLite = 1;
    
    private DB() {}
    
    public static Connection getInstance() {
        return getConnection(SQLite);
    }
    
    private static Connection getConnection(int sgbd) {
    	
    	if( sgbd == MySQL )
    	{
    		if(connect == null) {
                try
                {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                } 
                catch (SQLException e) {}
            }
    	}
    	else if( sgbd == SQLite )
    	{
    		if(connect == null) {
                try
                {
                    try {
                        Class.forName("org.sqlite.JDBC");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    connect = DriverManager.getConnection("jdbc:sqlite:notify.db");
                } 
                catch (SQLException e) {}
                
                try {
                	connect.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS note (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, text TEXT DEFAULT NULL, creationTime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, userId INTEGER NOT NULL)");
                	connect.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS user ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL UNIQUE, pass TEXT NOT NULL, active INTEGER DEFAULT '0' )");
    			} catch (SQLException e) {
					e.printStackTrace();
				}
            }
    	}

        return connect;
    }
}
