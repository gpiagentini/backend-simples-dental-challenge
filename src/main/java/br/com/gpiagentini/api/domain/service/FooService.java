package br.com.gpiagentini.api.domain.service;

import br.com.gpiagentini.api.domain.model.Foo;

public class FooService implements IFooService{

    @Override
    public boolean isValidFoo(Foo foo){
        return foo.hasDescription();
    }
}
