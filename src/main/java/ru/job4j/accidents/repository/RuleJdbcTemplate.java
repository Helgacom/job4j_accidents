package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate implements RuleRepository {

    private final JdbcTemplate jdbc;

    public Rule save(Rule rule) {
        jdbc.update("INSERT INTO rules (name) values (?)", rule.getName());
        return rule;
    }

    @Override
    public Optional<Rule> findById(Long id) {
        var rsl = jdbc.queryForObject("SELECT * FROM rules WHERE id = ?", this::ruleRowMapper, id);
        return Optional.ofNullable(rsl);
    }

    @Override
    public List<Rule> findAll() {
        return jdbc.query("SELECT * FROM rules", this::ruleRowMapper);
    }

    private Rule ruleRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Rule(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
}
