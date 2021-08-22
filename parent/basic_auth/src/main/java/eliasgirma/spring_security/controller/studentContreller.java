package eliasgirma.spring_security.controller;

import eliasgirma.spring_security.model.student;
import eliasgirma.spring_security.service.studentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class studentContreller {

    @Autowired
    private eliasgirma.spring_security.service.studentServices studentServices;


    @GetMapping("{id}")
    public student getstudentbyid(@PathVariable int id)
    {
        return studentServices.stu.stream().filter(s->s.getId()==id).findFirst().orElseThrow(()->new IllegalStateException("not found"));
    }
}
