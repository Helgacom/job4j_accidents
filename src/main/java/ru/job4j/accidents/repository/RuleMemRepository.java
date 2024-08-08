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

    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>() {
        {
            put(1, new Rule(1, "Статья. 1"));
            put(2, new Rule(2, "Статья. 2"));
            put(3, new Rule(3, "Статья. 3"));
        }
    };

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }

    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }
}
