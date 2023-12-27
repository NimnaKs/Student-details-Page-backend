package lk.ijse.studentdetailsbackend.api;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(
        name = "student",
        urlPatterns = "/student",
        initParams = {
            @WebInitParam( name = "db-user" , value = "root"),
            @WebInitParam( name = "db-pw" , value = "ijse@200108"),
            @WebInitParam( name = "db-url" , value = "jdbc:mysql://localhost:3306/Ijse?createDatabaseIfNotExist=true"),
            @WebInitParam( name = "db-class" , value = "com.mysql.cj.jdbc.Driver")
        }
)

public class Student extends HttpServlet {

    Connection connection;

    @Override
    public void init() throws ServletException {

        var user = getServletConfig().getInitParameter("db-user");
        var password = getServletConfig().getInitParameter("db-pw");
        var url = getServletConfig().getInitParameter("db-url");
        try {
            Class.forName(getServletConfig().getInitParameter("db-class"));
            this.connection = DriverManager.getConnection(user,password,url);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
