package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMemRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleMemService implements RuleService {

    private final RuleMemRepository repository;

    @Override
    public Optional<Rule> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Rule> findAll() {
        return repository.findAll();
    }
}
