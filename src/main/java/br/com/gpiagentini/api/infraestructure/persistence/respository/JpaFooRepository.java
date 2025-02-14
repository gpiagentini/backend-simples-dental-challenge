package br.com.gpiagentini.api.infraestructure.persistence.respository;

import br.com.gpiagentini.api.application.port.out.FooServiceRepository;
import br.com.gpiagentini.api.domain.model.Foo;
import br.com.gpiagentini.api.infraestructure.persistence.entity.FooDataMapper;
import br.com.gpiagentini.api.infraestructure.persistence.mapper.FooMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class JpaFooRepository implements FooServiceRepository {

    @Autowired
    private FooRepository fooRepository;


    @Override
    public Foo save(Foo foo) {
        var fooDataMapper = FooMapper.toJpa(foo);
        fooDataMapper = fooRepository.save(fooDataMapper);
        return FooMapper.toDomain(fooDataMapper);
    }

    @Override
    public Foo getById(Long id) {
        var fooDataMapper = fooRepository.getReferenceById(id);
        return FooMapper.toDomain(fooDataMapper);
    }

    @Override
    public List<Foo> getAll() {
        var fooDataMapper = fooRepository.findAll();
        return fooDataMapper.stream().map(FooMapper::toDomain).toList();
    }
}

interface FooRepository extends JpaRepository<FooDataMapper, Long> {
}
