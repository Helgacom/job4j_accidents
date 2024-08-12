package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class AccidentTypeMemRepository implements TypeRepository {

    private final Map<Long, AccidentType> types = new ConcurrentHashMap<>() {
        {
            put(1L, new AccidentType(1L, "Две машины"));
            put(2L, new AccidentType(2L, "Машина и человек"));
            put(3L, new AccidentType(3L, "Машина и велосипед"));
        }
    };

    @Override
    public Optional<AccidentType> findById(Long id) {
        return Optional.ofNullable(types.get(id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return types.values();
    }
}
