package com.example.sandander_dio.controllers;

import com.example.sandander_dio.models.Funcionario;
import com.example.sandander_dio.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> getAllEmployees() {
        List<Funcionario> list = funcionarioService.findAll();
        return ResponseEntity.ok().body(list);

    }

    // get employee by id rest api
    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> getEmployeeById(@PathVariable Long id) {
        Funcionario emp = funcionarioService.findById(id);
        return ResponseEntity.ok().body(emp);
    }

    // create employee rest api
    @PostMapping("/funcionarios")
    public ResponseEntity<Funcionario> createEmployee(@RequestBody Funcionario funcionario) {
        funcionarioService.create(funcionario);
        return ResponseEntity.ok().build();
    }

    // update employee rest api

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> updateEmployee(@PathVariable Long id,
                                                      @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        funcionarioService.update(funcionario);
        return ResponseEntity.noContent().build();
    }

    // delete employee rest api
    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
