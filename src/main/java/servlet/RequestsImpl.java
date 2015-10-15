package servlet;


import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by User on 14.10.2015.
 */
public class RequestsImpl   implements Request {

    public DataSource getDs() {
        return ds;
    }

    private DataSource ds;




    ArrayList list = new ArrayList();


    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public List getClients() {
        try {
            Connection connection = ds.getConnection();
            String sq_str = "SELECT * FROM client";
            PreparedStatement ps = connection.prepareStatement(sq_str);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client cl = new Client();

                cl.setClient_id(rs.getInt("idClient"));
                cl.setFirstName(rs.getString("FirstName"));
                cl.setLastName(rs.getString("LastName"));
                cl.setAddres(rs.getString("Adress"));

                list.add(cl);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void insert(Client client) {
        String sql = "Insert into client " + "(idClient, FirstName, LastName,Adress) VALUES (0, ?, ?,?)";
        Connection conn = null;
        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getAddres());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public void delete(String name) {
        Connection con = null;
        try{
            con = ds.getConnection();
            String sq_str="DELETE FROM client WHERE FirstName ='" + name
                    + "'";
            PreparedStatement ps=con.prepareStatement(sq_str);

            ps.executeUpdate();
        }
        catch (SQLException e){
        }
    }

}


