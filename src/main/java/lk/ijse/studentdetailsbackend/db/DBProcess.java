package lk.ijse.studentdetailsbackend.db;

import lk.ijse.studentdetailsbackend.dto.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBProcess {
    public boolean saveStudent(StudentDTO studentDTO, Connection connection) {

        try {
            String save_student = "INSERT INTO student(student_id, first_name, last_name, address, batch_no, contact_number,email, program) VALUES (?,?,?,?,?,?,?,?);";
            var preparedStatement = connection.prepareStatement(save_student);
            preparedStatement.setString(1,studentDTO.getStudentId());
            preparedStatement.setString(2,studentDTO.getFirstName());
            preparedStatement.setString(3,studentDTO.getLastName());
            preparedStatement.setString(4,studentDTO.getAddress());
            preparedStatement.setInt(5,studentDTO.getBatchNo());
            preparedStatement.setString(6,studentDTO.getContact());
            preparedStatement.setString(7,studentDTO.getEmail());
            preparedStatement.setString(8,studentDTO.getProgram());

            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean updateStudent(StudentDTO studentDTO, Connection connection) {
        try {
            String update_student = "UPDATE Student SET first_name = ? ,last_name = ? , address = ? , batch_no = ? , contact_number = ? , email = ? , program = ?  WHERE student_id = ?;";
            var preparedStatement = connection.prepareStatement(update_student);
            preparedStatement.setString(1,studentDTO.getFirstName());
            preparedStatement.setString(2,studentDTO.getLastName());
            preparedStatement.setString(3,studentDTO.getAddress());
            preparedStatement.setInt(4,studentDTO.getBatchNo());
            preparedStatement.setString(5,studentDTO.getContact());
            preparedStatement.setString(6,studentDTO.getEmail());
            preparedStatement.setString(7,studentDTO.getProgram());
            preparedStatement.setString(8,studentDTO.getStudentId());

            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteStudent(StudentDTO studentDTO, Connection connection) {
        try {

            String delete_student = "DELETE FROM Student WHERE student_id = ?;";
            var preparedStatement = connection.prepareStatement(delete_student);
            preparedStatement.setString(1,studentDTO.getStudentId());

            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<StudentDTO> getAllStudent(Connection connection) {

        try {
            String get_all = "SELECT student_id, first_name, last_name,contact_number,email, address, program, batch_no FROM student;";
            var preparedStatement = connection.prepareStatement(get_all);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<StudentDTO> studentDTOS = new ArrayList<>();

            while(resultSet.next()){

                StudentDTO studentDTO = new StudentDTO(
                        resultSet.getString("student_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("program"),
                        resultSet.getInt("batch_no")
                );
                studentDTOS.add(studentDTO);

            }

            return studentDTOS;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getLastStudentId(Connection connection) {

        String get_last_stud_id = "SELECT MAX(student_id) as last_student_id FROM student;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(get_last_stud_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return "stu-"+String.format("%03d",Integer.parseInt(resultSet.getString("last_student_id").substring(4))+1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
