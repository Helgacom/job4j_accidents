package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Accident save(Accident accident) {
        String sql = "INSERT INTO accidents (name, text, address, type_id) VALUES (?, ?, ?, ?) RETURNING id";
        Long accidentId = jdbc.queryForObject(sql, Long.class,
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId());
        if (accidentId != null) {
            for (Rule rule : accident.getRules()) {
                jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)", accidentId, rule.getId());
            }
        }
        return accident;
    }

    @Override
    public List<Accident> findAll() {
        String sql = "SELECT a.id, a.name, a.text, a.address, a.type_id, t.name as type_name "
                + "FROM accidents a JOIN accident_types t ON a.type_id = t.id";
        List<Accident> accidents = jdbc.query(sql, this::accidentRowMapper);
        for (Accident accident : accidents) {
            accident.setRules(getRulesForAccident(accident));
        }
        return accidents;
    }

    @Override
    public Optional<Accident> findById(Long id) {
        String sql = "SELECT a.id, a.name, a.text, a.address, a.type_id, t.name as type_name "
                + "FROM accidents a JOIN accident_types t ON a.type_id = t.id "
                + "WHERE a.id = ?";
        Accident accident = jdbc.queryForObject(
                sql, this::accidentRowMapper, id);
        if (accident == null) {
            return Optional.empty();
        }
        accident.setRules(getRulesForAccident(accident));
        return Optional.of(accident);
    }

    @Override
    public boolean deleteById(Long id) {
        int rows = jdbc.update("DELETE FROM accidents WHERE id = ?", id);
        return rows > 0;
    }

    @Override
    public boolean update(Accident accident) {
        boolean rsl = false;
        String sql = "UPDATE accidents SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?";
        if (jdbc.update(sql, accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId()) > 0) {
            jdbc.update("DELETE FROM accidents_rules WHERE accident_id = ?", accident.getId());
            for (Rule rule : accident.getRules()) {
                jdbc.update("INSERT INTO accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                        accident.getId(), rule.getId());
            }
            rsl = true;
        }
        return rsl;
    }

    private Set<Rule> getRulesForAccident(Accident accident) {
        String sql = "SELECT r.id, r.name FROM rules r JOIN accidents_rules ar ON r.id = ar.rule_id WHERE ar.accident_id = ?";
        return new HashSet<>(jdbc.query(sql, this::mapRowToRule, accident.getId()));
    }

    private Accident accidentRowMapper(ResultSet resultSet, int rowNum) throws SQLException {
        var accident = new Accident();
        accident.setId(resultSet.getLong("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        var type = new AccidentType();
        type.setId(resultSet.getLong("type_id"));
        type.setName(resultSet.getString("type_name"));
        accident.setType(type);
        return accident;
    }

    private Rule mapRowToRule(ResultSet rs, int rowNum) throws SQLException {
        return new Rule(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
}
