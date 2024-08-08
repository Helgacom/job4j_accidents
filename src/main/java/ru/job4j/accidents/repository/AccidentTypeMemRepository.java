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

    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>() {
        {
            put(1, new AccidentType(1, "Две машины"));
            put(2, new AccidentType(2, "Машина и человек"));
            put(3, new AccidentType(3, "Машина и велосипед"));
        }
    };

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }

    @Override
    public Collection<AccidentType> findAll() {
        return types.values();
    }
}
