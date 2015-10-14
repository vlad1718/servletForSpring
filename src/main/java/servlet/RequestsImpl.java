package servlet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by User on 14.10.2015.
 */
public class RequestsImpl implements Request {

    private DataSource ds;


    ArrayList list = new ArrayList();



    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public List getClients() {
        try{
            Connection connection =ds.getConnection();
            String  sq_str = "SELECT * FROM client";
            PreparedStatement ps=connection.prepareStatement(sq_str);
            ResultSet rs=ps.executeQuery();

            while (rs.next()) {
                Client cl = new Client();

                cl.setClient_id(rs.getInt("idClient"));
                cl.setFirstName(rs.getString("FirstName"));
                cl.setLastName(rs.getString("LastName"));
                cl.setAddres(rs.getString("Adress"));

                list.add(cl);

            }
        }

        catch (SQLException e){
        }
        return list;
    }

}
