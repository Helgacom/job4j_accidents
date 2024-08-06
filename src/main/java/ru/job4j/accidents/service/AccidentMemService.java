package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMemRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentMemService implements AccidentService {

    private final AccidentMemRepository repository;

    @Override
    public Accident save(Accident accident) {
        return repository.save(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return repository.update(accident);
    }
}
