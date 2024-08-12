package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeJdbcService implements TypeService {

    private final AccidentTypeJdbcTemplate repository;

    public AccidentType save(AccidentType type) {
        return repository.save(type);
    }

    @Override
    public Optional<AccidentType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<AccidentType> findAll() {
        return repository.findAll();
    }
}
