package ru.job4j.accidents.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String text;
    private String address;

    private AccidentType type;

    private Set<Rule> rules;
}
