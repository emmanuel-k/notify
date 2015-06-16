package notify.model;

import java.sql.Connection;

/**
 * Manager
 * @author Emmanuel KWENE NJUME
 * @param <K>
 */
public abstract class Manager<K> {
    protected Connection connect = null;
    
    /**
     * Constructeur
     * @param conn 
     */
    public Manager(Connection conn) { this.connect = conn; }
    
    /**
     * Creation d'un nouvel objet
     * @param obj
     * @return boolean
     */
    public abstract boolean create(K obj);
    
    /**
     * Mise à jour d'un objet existant
     * @param obj
     * @return boolean
     */
    public abstract boolean update(K obj);
    
    /**
     * Effacer un objet
     * @param obj
     * @return boolean
     */
    public abstract boolean delete(K obj);
    
    /**
     * Recherche d'un objet spécifique
     * @param id
     * @return 
     */
    public abstract K find( int id);
}