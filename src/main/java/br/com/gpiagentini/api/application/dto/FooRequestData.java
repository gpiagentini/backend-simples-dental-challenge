package br.com.gpiagentini.api.application.dto;

import br.com.gpiagentini.api.domain.model.Foo;

public record FooRequestData(String description){
    public FooRequestData(Foo foo){
        this(foo.getDescription());
    }
}
