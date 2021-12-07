package com.sample.controller;


import com.sample.TaskerRepository;
import com.sample.model.Tasker;
import net.bytebuddy.asm.Advice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tasker")
public class TaskerCrudController {

    private final TaskerRepository taskerRepository;

    public TaskerCrudController(TaskerRepository taskerRepository) {
        this.taskerRepository = taskerRepository;
    }

    @GetMapping
    public List<Tasker> getAllTaskerComponents() {
        return taskerRepository.findAll();
    }

    @GetMapping("/{name}")
    public Tasker getTaskerComponent(@PathVariable String name) {
        return taskerRepository.findById(name).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createTaskerComponent(@RequestBody Tasker tasker) throws URISyntaxException {
        Tasker taskerComponent = taskerRepository.save(tasker);
        return ResponseEntity.created(new URI("/tasker/" + taskerComponent.getName())).body(taskerComponent);
    }

    @PutMapping("/{name}")
    public ResponseEntity updateTaskerComponent(@PathVariable String name, @RequestBody Tasker tasker) {
        Tasker currentTaskerComponent = taskerRepository.findById(name).orElseThrow(RuntimeException::new);
        currentTaskerComponent.setDate(tasker.getDate());
        currentTaskerComponent = taskerRepository.save(tasker);

        return ResponseEntity.ok(currentTaskerComponent);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity deleteTaskerComponent(@PathVariable String name) {
        taskerRepository.deleteById(name);
        return ResponseEntity.ok().build();
    }
}
