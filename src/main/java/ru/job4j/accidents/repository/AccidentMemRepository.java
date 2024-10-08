package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
@Repository
@AllArgsConstructor
public class AccidentMemRepository implements AccidentRepository {

    private final AtomicLong nextId = new AtomicLong(1);

    private final Map<Long, Accident> accidents = new ConcurrentHashMap<>();

    @Override
    public Accident save(Accident accident) {
        accident.setId(nextId.getAndIncrement());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(Long id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean deleteById(Long id) {
        return accidents.remove(id) != null;
    }

    @Override
    public boolean update(Accident accident) {
        return accidents.computeIfPresent(accident.getId(), (id, oldAccident) ->
                new Accident(oldAccident.getId(), accident.getName(), accident.getText(),
                        accident.getAddress(), accident.getType(), accident.getRules())) != null;
    }
}
