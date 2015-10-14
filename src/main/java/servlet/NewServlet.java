package servlet;

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
import java.util.List;

/**
 * Created by User on 14.10.2015.
 */
@WebServlet(name = "NewServlet")
public class NewServlet extends HttpServlet {


    public NewServlet(){

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(path.equals("/servlets")){
            ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            Request r =(Request)context.getBean("RequestsImpl");


            List<Client> ClientList = r.getClients();
            request.setAttribute("list", ClientList);
            getServletContext().getRequestDispatcher("/WEB-INF/new.jsp").forward(request, response);

        }

    }
}
