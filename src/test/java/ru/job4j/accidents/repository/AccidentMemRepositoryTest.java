package ru.job4j.accidents.repository;

import org.junit.jupiter.api.Test;
import ru.job4j.accidents.model.Accident;

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
        assertThat(repository.save(accident)).isEqualTo(accident);
    }
}