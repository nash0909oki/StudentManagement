package reisetech.student.management.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentCourses {

    public String id;
    public String studentId;
    public String courseName;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
}
