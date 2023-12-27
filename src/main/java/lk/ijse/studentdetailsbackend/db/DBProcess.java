package lk.ijse.studentdetailsbackend.db;

import lk.ijse.studentdetailsbackend.dto.StudentDTO;

import java.sql.Connection;
import java.sql.SQLException;

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
}
