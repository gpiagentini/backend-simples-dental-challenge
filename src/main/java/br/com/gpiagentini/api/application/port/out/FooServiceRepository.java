package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.domain.model.Foo;

import java.util.List;

public interface FooServiceRepository {
    Foo save(Foo foo);
    Foo getById(Long id);
    List<Foo> getAll();
}
