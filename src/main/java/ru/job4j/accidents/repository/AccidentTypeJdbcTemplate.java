package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate implements TypeRepository {

    private final JdbcTemplate jdbc;

    public AccidentType save(AccidentType type) {
        jdbc.update("INSERT INTO accident_types (name) values (?)", type.getName());
        return type;
    }

    @Override
    public Optional<AccidentType> findById(Long id) {
        var rsl = jdbc.queryForObject("SELECT * FROM accident_types WHERE id = ?", this::accidentTypeRowMapper, id);
        return Optional.ofNullable(rsl);
    }

    @Override
    public List<AccidentType> findAll() {
        return jdbc.query("SELECT * FROM accident_types", this::accidentTypeRowMapper);
    }

    private AccidentType accidentTypeRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new AccidentType(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
}
