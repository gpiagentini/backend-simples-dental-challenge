package br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces;

public interface ReferenceRepository<T> {
    /**
     * Get a reference of <T> by its id.
     * 
     * @param id
     * @return the reference with the given id.
     * @throws IllegalArgumentException if the reference does not exist.
     */
    T getReferenceById(Long id) throws IllegalArgumentException;
}
