package ua.pp.darknsoft.darkystore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.darknsoft.darkystore.dto.ContactRecord;
import ua.pp.darknsoft.darkystore.service.IContactService;

@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping
    public ResponseEntity<ContactRecord> createContact(@Valid @RequestBody ContactRecord contactRec) {
        contactRec = contactService.saveContact(contactRec);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactRec);
    }
}
