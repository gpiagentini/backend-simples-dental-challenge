package br.com.gpiagentini.api.adapters.controllers;

import br.com.gpiagentini.api.application.dto.*;
import br.com.gpiagentini.api.application.port.in.IContactApplicationService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contatos")
@Tag(name = "Contatos Controller", description = "Operaçõs relacionadas ao gerenciamento de Contatos.")
public class ContactController {

    @Autowired
    private IContactApplicationService contactApplicationService;

    @GetMapping
    @Operation(summary = "Buscar todos os contatos.")
    public ResponseEntity<MappingJacksonValue> getAllContacts(@RequestParam(required = false, defaultValue = "") String q,
                                                              @RequestParam(required = false) List<String> fields) {
        var contactDataList = contactApplicationService.getAllContacts(q);
        MappingJacksonValue mapping = new MappingJacksonValue(contactDataList);
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

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contato pelo id.")
    public ResponseEntity<MappingJacksonValue> getContactById(@PathVariable Long id) {
        var contactData = contactApplicationService.getContactById(id);
        MappingJacksonValue mapping = new MappingJacksonValue(contactData);
        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", SimpleBeanPropertyFilter.serializeAll());
        mapping.setFilters(filters);
        return ResponseEntity.ok(mapping);
    }

    @PostMapping
    @Operation(summary = "Criação de novos contatos.")
    public ResponseEntity<String> createNewContact(@Valid @RequestBody NewContactData newContactData) {
        var id = contactApplicationService.saveNewContact(newContactData);
        return ResponseEntity.ok("Contato com id " + id + " cadastrado.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir contato do profissional.")
    public ResponseEntity<String> deleteContactById(@PathVariable Long id) {
        contactApplicationService.deleteContactById(id);
        return ResponseEntity.ok("Sucesso, profissional excluído");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar dados de contato.")
    public ResponseEntity<String> updateContactById(@PathVariable Long id, @RequestBody UpdateContactData updateContactData) {
        contactApplicationService.updateContactInformation(id, updateContactData);
        return ResponseEntity.ok("Contato alterado");
    }
}
