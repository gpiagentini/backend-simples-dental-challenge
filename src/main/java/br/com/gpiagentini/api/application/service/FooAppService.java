package br.com.gpiagentini.api.application.service;

import br.com.gpiagentini.api.application.port.in.IFooAppService;
import br.com.gpiagentini.api.application.port.out.FooServiceRepository;
import br.com.gpiagentini.api.domain.model.Foo;
import br.com.gpiagentini.api.domain.service.IFooService;

import java.util.List;

public class FooAppService implements IFooAppService {

    private final FooServiceRepository fooServiceRepository;
    private final IFooService fooService;

    public FooAppService(FooServiceRepository fooServiceRepository, IFooService fooService) {
        this.fooServiceRepository = fooServiceRepository;
        this.fooService = fooService;
    }

    @Override
    public Foo getFooById(Long id) {
        return fooServiceRepository.getById(id);
    }

    @Override
    public List<Foo> getFooList() {
        return fooServiceRepository.getAll();
    }

    @Override
    public Foo createFoo(String description) {
        var foo = new Foo(description);
        if(fooService.isValidFoo(foo))
            return fooServiceRepository.save(foo);
        return null;
    }
}
