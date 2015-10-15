package servlet;

import java.util.List;

/**
 * Created by User on 14.10.2015.
 */
public interface Request {
    public List getClients();
    public void insert(Client client);
    public void delete(String name);

}
