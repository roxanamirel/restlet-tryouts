package org.inria.restlet.mta.database.api;

import java.util.Collection;
import java.util.List;

import org.inria.restlet.mta.internals.User;

/**
 *
 * Interface to the database. 
 *
 * @author msimonin
 *
 */
public interface Database
{

    /**
     *
     * Create a new user in the database. 
     *
     * @param name      Le nom de l'utilisateur
     * @param age       L'âge de l'utilisateur
     * @return  Le nouvel utilisateur.
     */
    User createUser(String name, int age);


    /**
     *
     * Returns the list of users.
     *
     * @return l'ensemble des utilisateurs
     */
    Collection<User> getUsers();


    /**
     *  Returns the user with a given id.
     *
     *  @return the user
     */
    User getUser(int id);
    
    User deleteUser(int userId);

}
