package br.com.gpiagentini.api.adapters.controllers;

import br.com.gpiagentini.api.application.port.in.IFooAppService;
import br.com.gpiagentini.api.application.dto.CreateFooData;
import br.com.gpiagentini.api.application.dto.FooRequestData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Validated
@RestController("Foo")
@RequestMapping("/api/v1/foo")
@Tag(name = "Foo Controller", description = "Foo related operations.")
public class FooController {

    private final IFooAppService fooAppService;

    public FooController(IFooAppService fooAppService) {
        this.fooAppService = fooAppService;
    }

    @Operation(summary = "Create new Foo", description = "Intended to create a new Foo.")
    @PostMapping
    @Transactional()
    public ResponseEntity<FooRequestData> createFoo(@RequestBody @Valid CreateFooData createFooData, UriComponentsBuilder uriBuilder) {
        var foo = fooAppService.createFoo(createFooData.description());
        var uri = uriBuilder.path("/api/v1/{id}").buildAndExpand(foo.getDescription()).toUri();
        return ResponseEntity.created(uri).body(new FooRequestData(foo));
    }

    @Operation(summary = "Get Foo by id", description = "Intended to retrieve a new Foo, from a given id.")
    @GetMapping("/{id}")
    public ResponseEntity<FooRequestData> getFoo(@Positive(message = "Id must be greater than 0.") @PathVariable Long id) throws InterruptedException {
        Thread.sleep(10 * 1000);
        var foo = fooAppService.getFooById(id);
        var fooRequestData = new FooRequestData(foo);
        return ResponseEntity.ok(fooRequestData);
    }

    @Operation(summary = "Get Foo list", description = "Intended to retrieve a list of available Foo's.")
    @GetMapping
    public ResponseEntity<List<FooRequestData>> getAllFoo() {
        var fooList = fooAppService.getFooList();
        var fooRequestDataList = fooList.stream().map(FooRequestData::new).toList();
        return ResponseEntity.ok(fooRequestDataList);
    }
}
