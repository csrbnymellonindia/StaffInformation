package com.codeusingjava.controller;

import com.codeusingjava.model.ClassModel;
import com.codeusingjava.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping("/addClass")
    public ResponseEntity<String> addClass(@RequestBody ClassModel classModel) {
        return classService.addClass(classModel);
    }

    @PutMapping("/updateClass/{classIdentifier}")
    public ResponseEntity<String> updateClass(@PathVariable int classIdentifier, @RequestBody ClassModel classModel) {
        return classService.updateClass(classIdentifier, classModel);
    }

    @DeleteMapping("/deleteClass/{classIdentifier}")
    public ResponseEntity<String> deleteClass(@PathVariable int classIdentifier) {
        return classService.deleteClass(classIdentifier);
    }

    @GetMapping("/getAll")
    public List<ClassModel> getAllClasses() {
        return classService.findAllClasses();
    }

    @GetMapping("/getClass/{classIdentifier}")
    public ResponseEntity<ClassModel> getClassById(@PathVariable int classIdentifier) {
        Optional<ClassModel> classModel = classService.findClassById(classIdentifier);
        return classModel.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
