package br.com.gpiagentini.api.infraestructure.persistence.mapper;

import br.com.gpiagentini.api.domain.model.Foo;
import br.com.gpiagentini.api.infraestructure.persistence.entity.FooDataMapper;

public class FooMapper {

    // Converts JPA entity to domain entity
    public static Foo toDomain(FooDataMapper fooDataMapper) {
        return new Foo(fooDataMapper.getDescription());
    }

    // Converts domain entity to JPA entity
    public static FooDataMapper toJpa(Foo foo) {
        FooDataMapper fooDataMapper = new FooDataMapper();
        fooDataMapper.setDescription(foo.getDescription());
        return fooDataMapper;
    }

}
