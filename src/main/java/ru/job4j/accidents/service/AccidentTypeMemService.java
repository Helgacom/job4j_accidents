package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeMemRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeMemService implements TypeService {

    private final AccidentTypeMemRepository repository;

    @Override
    public Optional<AccidentType> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return repository.findAll();
    }
}
