package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface TypeService {

    Optional<AccidentType> findById(Long id);

    Collection<AccidentType> findAll();
}
