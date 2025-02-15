package br.com.gpiagentini.api.infraestructure.persistence.specifications;


import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class ProfessionalSpecifications {

    public static <T> Specification<T> searchByText(String q) {
        return (root, query, cb) -> {
            String search = "%" + q.toLowerCase() + "%";
            Predicate predicate = cb.disjunction();
            // Id
            predicate = cb.or(predicate, cb.like(cb.function("TEXT", String.class, root.get("id")), search));

            // Name
            predicate = cb.or(predicate, cb.like(cb.lower(root.get("name")), search));

            //position
            Join<Object, Object> positionJoin = root.join("position");
            predicate = cb.or(predicate, cb.like(cb.lower(positionJoin.get("name")), search));

            // birthDate
            predicate = cb.or(predicate, cb.like(cb.function("TEXT", String.class, root.get("birthDate")), search));

            // creationDate
            predicate = cb.or(predicate, cb.like(cb.function("TEXT", String.class, root.get("createdDate")), search));

            return predicate;
        };
    }
}
