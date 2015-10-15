package servlet;

import com.sun.corba.se.impl.corba.RequestImpl;
import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.10.2015.
 */
@WebServlet(name = "NewServlet")
public class NewServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        RequestsImpl r =(RequestsImpl)context.getBean("RequestsImpl");
        Flyway fl = new Flyway();

        fl.setDataSource(r.getDs());
        fl.migrate();
        List<Client> ClientList = new ArrayList<Client>();

        if(path.contains("/servlets")){
             ClientList = r.getClients();

            request.setAttribute("list", ClientList);
            getServletContext().getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);

        }
        else if(path.contains("/edit")){

            String name=request.getParameter("fisrtname");
            if(name!=null){
                String lastname=request.getParameter("lastname");
                String adress=request.getParameter("adress");


                try {
                    Client cl = new Client(0,name,lastname,adress);
                     r.insert(cl);


                    ClientList = r.getClients();
                    request.setAttribute("list",ClientList);
                    getServletContext().getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);
                    return;

                }
                catch (NumberFormatException e) {
                }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/insert.jsp").forward(request, response);
        }
        else if(path.contains("/del")){
            String name = request.getParameter("name");
            if( name != null){
                try {



                        r.delete(name);
                        ClientList = r.getClients();
                        request.setAttribute("list",ClientList);
                        getServletContext().getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);
                        return;

                }catch (NumberFormatException e) {
                    System.err.println("Неверный формат строки!");
                }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/del.jsp").forward(request, response);

        }
    }
}
