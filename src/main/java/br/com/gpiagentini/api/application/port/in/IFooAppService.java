package br.com.gpiagentini.api.application.port.in;

import br.com.gpiagentini.api.domain.model.Foo;
import java.util.List;

public interface IFooAppService {
    Foo getFooById(Long id);
    List<Foo> getFooList();
    Foo createFoo(String description);
}
