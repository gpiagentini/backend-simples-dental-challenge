package br.com.gpiagentini.api.infraestructure.persistence.specifications;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification {

    private ContactSpecification() {
    }

    /**
     * Search in every ContactEntity field for the q.
     * 
     * @param q String to search.
     * @return a specification.
     */
    public static <T> Specification<T> searchByText(String q) {
        return (root, query, cb) -> {
            String search = "%" + q.toLowerCase() + "%";
            Predicate predicate = cb.disjunction();
            // Id
            predicate = cb.or(predicate, cb.like(cb.function("TEXT", String.class, root.get("id")), search));

            // Name
            predicate = cb.or(predicate, cb.like(cb.lower(root.get("name")), search));

            // Contact
            predicate = cb.or(predicate, cb.like(cb.lower(root.get("contact")), search));

            // creationDate
            predicate = cb.or(predicate, cb.like(cb.function("TEXT", String.class, root.get("createdDate")), search));

            return predicate;
        };
    }
}
