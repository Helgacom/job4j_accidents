package ru.job4j.accidents.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class AccidentMemRepository implements AccidentRepository {

    private final AtomicInteger nextId = new AtomicInteger(1);

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private AccidentMemRepository() {
        create(new Accident(0, "Иванов И.И.", "превышение скорости", "Москва, ул.Вернадского, 8"));
        create(new Accident(0, "Кочанов С.В.", "пересечение сплошной линии", "Москва, ул. Гоголя, 34"));
        create(new Accident(0, "Лаврова Е.И.", "остановка в неположенном месте", "Москва, ул.Пионеров, 75"));
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(nextId.getAndIncrement());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public boolean deleteById(int id) {
        return accidents.remove(id) != null;
    }
}
