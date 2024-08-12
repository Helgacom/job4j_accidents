package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class RuleMemRepository implements RuleRepository {

    private final Map<Long, Rule> rules = new ConcurrentHashMap<>() {
        {
            put(1L, new Rule(1L, "Статья. 1"));
            put(2L, new Rule(2L, "Статья. 2"));
            put(3L, new Rule(3L, "Статья. 3"));
        }
    };

    @Override
    public Optional<Rule> findById(Long id) {
        return Optional.ofNullable(rules.get(id));
    }

    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }
}
