package eliasgirma.spring_security.service;

import eliasgirma.spring_security.model.student;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class studentServices {

  public   List<student> stu= Arrays.asList(
            new student(1,"john"),
            new student(2,"brava"),
            new student(3,"girma")
    );

}
