package br.com.gpiagentini.api.adapters.controllers;

import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.application.dto.ValidationErrorData;
import br.com.gpiagentini.api.application.port.in.IProfessionalApplicationService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/profissionais")
@Tag(name = "Profissionais Controller", description = "Operaçõs relacionadas ao gerenciamento de Profissionais.")
public class ProfessionalController {

    @Autowired
    private IProfessionalApplicationService professionalApplicationService;

    @Operation(summary = "Buscar profissional por id")
    @GetMapping("/{id}")
    public ResponseEntity<MappingJacksonValue> getProfessionalById(@PathVariable Long id) {
        var professional = professionalApplicationService.getProfessionalById(id);
        MappingJacksonValue mapping = new MappingJacksonValue(professional);
        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.serializeAll());
        mapping.setFilters(filters);
        return ResponseEntity.ok(mapping);
    }

    @Operation(summary = "Buscar todos os profissionais")
    @GetMapping()
    public ResponseEntity<MappingJacksonValue> getAllProfessionals(@RequestParam(required = false, defaultValue = "") String q,
                                                                   @RequestParam(required = false) List<String> fields) {
        var professionalList = professionalApplicationService.getAllProfessionals(q);
        MappingJacksonValue mapping = new MappingJacksonValue(professionalList);
        if (fields == null || fields.isEmpty()) {
            FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.serializeAll());
            mapping.setFilters(filters);
        } else {
            var fieldSet = new HashSet<>(fields);
            FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fieldSet));
            mapping.setFilters(filters);
        }
        return ResponseEntity.ok(mapping);
    }

    @Operation(summary = "Criação de novos Profissionais")
    @PostMapping
    public ResponseEntity<String> createProfessional(@Valid @RequestBody NewProfessionalData body) {
        var id = professionalApplicationService.createNewProfessional(body);
        return ResponseEntity.ok("Sucesso, profissional com id " + id + " cadastrado.");
    }

    @Operation(summary = "Desabilitar um Profissional")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessional(@PathVariable Long id) {
        professionalApplicationService.deleteProfessionalById(id);
        return ResponseEntity.ok("Sucesso, profissional excluído");
    }

    @Operation(summary = "Update professional informations")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfessionalInfo(@PathVariable Long id, @Valid @RequestBody UpdateProfessionalData updateProfessionalData) {
        professionalApplicationService.updateProfessionalData(id, updateProfessionalData);
        return ResponseEntity.ok("Cadastro alterado.");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoSuchElementException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorData> handleInvalidMethodArgument(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream().map(fieldError -> new ValidationErrorData(fieldError.getField(), fieldError.getDefaultMessage())).toList();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorData> handleConstraintViolation(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(constraintViolation -> new ValidationErrorData(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage())).toList();
    }
}
