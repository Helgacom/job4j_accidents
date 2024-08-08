package ru.job4j.accidents.repository;

import org.junit.jupiter.api.Test;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class AccidentMemRepositoryTest {

    private static AccidentMemRepository repository = new AccidentMemRepository();

    @Test
    void whenCreateUser() {
        var accident = new Accident();
        accident.setId(0);
        accident.setName("test");
        accident.setText("test");
        accident.setAddress("test");
        var type = new AccidentType(1, "test");
        accident.setType(type);
        assertThat(repository.save(accident)).isEqualTo(accident);
        assertThat(repository.findById(accident.getId()).get()).isEqualTo(accident);
        assertThat(repository.findAll()).contains(accident);
    }
}