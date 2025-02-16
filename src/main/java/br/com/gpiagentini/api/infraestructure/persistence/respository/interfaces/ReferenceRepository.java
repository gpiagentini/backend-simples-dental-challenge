package br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces;

public interface ReferenceRepository<T> {
    T getReferenceById(Long id);
}
