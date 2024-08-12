package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface TypeRepository {

    Optional<AccidentType> findById(Long id);

    Collection<AccidentType> findAll();
}
