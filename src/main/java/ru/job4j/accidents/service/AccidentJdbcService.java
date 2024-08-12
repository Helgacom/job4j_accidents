package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentJdbcService implements AccidentService {

    private final AccidentJdbcTemplate repository;

    @Override
    public Accident save(Accident accident) {
        return repository.save(accident);
    }

    @Override
    public List<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Accident> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return repository.update(accident);
    }
}
