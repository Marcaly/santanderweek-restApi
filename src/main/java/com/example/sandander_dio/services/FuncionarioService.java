package com.example.sandander_dio.services;

import com.example.sandander_dio.exceptions.FuncionarioDuplicadoException;
import com.example.sandander_dio.exceptions.NaoEncontradoException;
import com.example.sandander_dio.models.Funcionario;
import com.example.sandander_dio.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Id não encontrado"));
    }

    public Funcionario create(Funcionario funcionario) {
        if (funcionarioRepository.existsByEmail(funcionario.getEmail())) {
            throw new FuncionarioDuplicadoException("Um funcionário com o mesmo email já está registrado");
        }
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario update(Funcionario funcionario) {
        Funcionario novoFunc = findById(funcionario.getId());
        updateData(novoFunc, funcionario);
        if (funcionarioRepository.existsByEmail(funcionario.getEmail())) {
            throw new FuncionarioDuplicadoException("Um funcionário com o mesmo email já está registrado");
        }
        return funcionarioRepository.save(novoFunc);
    }

    private void updateData(Funcionario novoFunc, Funcionario funcionario) {
        novoFunc.setFirstName(funcionario.getFirstName());
        novoFunc.setLastName(funcionario.getLastName());
        novoFunc.setEmail(funcionario.getEmail());
    }
}

