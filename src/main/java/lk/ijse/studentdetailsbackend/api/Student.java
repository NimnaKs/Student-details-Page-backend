package lk.ijse.studentdetailsbackend.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.studentdetailsbackend.db.DBProcess;
import lk.ijse.studentdetailsbackend.dto.StudentDTO;

import java.io.IOException;
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
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO studentDTO = jsonb.fromJson(req.getReader(), StudentDTO.class);
            var dbProcess = new DBProcess();
            boolean result = dbProcess.saveStudent(studentDTO, connection);
            if (result) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Student information saved successfully.");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save student information.");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO studentDTO = jsonb.fromJson(req.getReader(), StudentDTO.class);
            var dbProcess = new DBProcess();
            boolean result = dbProcess.updateStudent(studentDTO, connection);
            if (result) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Student information update successfully.");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update student information.");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getContentType() != null && req.getContentType().toLowerCase().startsWith("application/json")){
            Jsonb jsonb = JsonbBuilder.create();
            StudentDTO studentDTO = jsonb.fromJson(req.getReader(), StudentDTO.class);
            var dbProcess = new DBProcess();
            boolean result = dbProcess.deleteStudent(studentDTO, connection);
            if (result) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Student information delete successfully.");
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete student information.");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
