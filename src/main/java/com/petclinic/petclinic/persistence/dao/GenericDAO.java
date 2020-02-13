package com.petclinic.petclinic.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    T create(T t);

    Optional<T> retrieve(Serializable serializable);

    void delete(T t);

    T update(Serializable serializable, T t);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);
}
