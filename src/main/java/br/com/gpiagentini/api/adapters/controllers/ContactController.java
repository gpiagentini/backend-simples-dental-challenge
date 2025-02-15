package br.com.gpiagentini.api.adapters.controllers;

import br.com.gpiagentini.api.application.port.in.IContactApplicationService;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contatos")
public class ContactController {

    @Autowired
    private IContactApplicationService contactApplicationService;

    @GetMapping
    public ResponseEntity<List<ContactEntity>> getAllContacts() {
        return ResponseEntity.ok(contactApplicationService.getAllContacts());
    }
}
