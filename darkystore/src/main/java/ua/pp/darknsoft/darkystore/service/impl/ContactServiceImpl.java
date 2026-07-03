package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.darkystore.dto.ContactRecord;
import ua.pp.darknsoft.darkystore.model.Contact;
import ua.pp.darknsoft.darkystore.repository.ContactRepository;
import ua.pp.darknsoft.darkystore.service.IContactService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public ContactRecord saveContact(ContactRecord contactRec) {
        try {
            Contact contact = contactRec.toContact();
            contact.setCreatedAt(Instant.now());
            contact.setCreatedBy(contactRec.name());
            contact = contactRepository.save(contact);
            return contact.toRecord();
        } catch (Exception e) {
            return null;
        }
    }
}
