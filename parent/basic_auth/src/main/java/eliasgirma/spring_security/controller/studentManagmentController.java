package eliasgirma.spring_security.controller;

import eliasgirma.spring_security.model.student;
import eliasgirma.spring_security.service.studentManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("managment/api/v1/students")
public class studentManagmentController
{

    @Autowired
   private studentManagment studentManagment;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<student> getAll()
    {
        return studentManagment.stu;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deletStudnet(@PathVariable Integer id)
    {
        System.out.println("deleted "+ id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerStudent(@RequestBody  student student)
    {
        System.out.println("register student"+ student);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer id, @RequestBody student student)
    {
        System.out.println("update student "+ student);
    }
}
