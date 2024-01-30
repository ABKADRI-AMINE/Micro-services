// Specify the package for the client code
package com.alibou.school.client;

// Import necessary classes
import com.alibou.school.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Declare a FeignClient with the name "student-service" and provide the base URL using a placeholder
@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {

    // Define a method to fetch all students for a given school ID using HTTP GET
    @GetMapping("/school/{school-id}")
    List<Student> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);
}
