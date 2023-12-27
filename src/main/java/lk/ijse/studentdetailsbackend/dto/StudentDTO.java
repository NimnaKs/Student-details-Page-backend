package lk.ijse.studentdetailsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StudentDTO {
     private String studentId;
     private String fName;
     private String lName;
     private String contact;
     private String email;
     private String address;
     private String program;
     private int batchNo;
}
