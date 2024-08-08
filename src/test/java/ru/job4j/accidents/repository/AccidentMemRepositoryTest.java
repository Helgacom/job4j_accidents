package ru.job4j.accidents.repository;

import org.junit.jupiter.api.Test;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentMemService;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class AccidentMemRepositoryTest {

    private static AccidentMemRepository repository = new AccidentMemRepository();
    private static AccidentMemService service = new AccidentMemService(repository);

    @Test
    void whenSaveUser() {
        var accident = new Accident();
        accident.setId(0);
        accident.setName("test");
        accident.setText("test");
        accident.setAddress("test");
        var type = new AccidentType(1, "test");
        accident.setType(type);
        var rules = new HashSet<Rule>();
        accident.setRules(rules);
        assertThat(repository.save(accident)).isEqualTo(accident);
        assertThat(repository.findById(accident.getId()).get()).isEqualTo(accident);
        assertThat(repository.findAll()).contains(accident);
    }

    @Test
    void whenCreateUser() {
        var accident = new Accident();
        accident.setId(1);
        accident.setName("test");
        accident.setText("test");
        accident.setAddress("test");
        var type = new AccidentType(1, "test");
        accident.setType(type);
        assertThat(service.create(accident).get()).isEqualTo(accident);
        assertThat(service.findById(accident.getId()).get()).isEqualTo(accident);
        assertThat(service.findAll()).contains(accident);
    }
}