package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleJdbcService implements RuleService {

    private final RuleJdbcTemplate repository;

    public Rule save(Rule rule) {
        return repository.save(rule);
    }

    @Override
    public Optional<Rule> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Rule> findAll() {
        return repository.findAll();
    }
}
