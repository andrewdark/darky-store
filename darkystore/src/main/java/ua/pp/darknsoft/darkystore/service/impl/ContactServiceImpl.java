package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.darkystore.constants.ApplicationConstants;
import ua.pp.darknsoft.darkystore.dto.ContactRecord;
import ua.pp.darknsoft.darkystore.dto.ContactResponseDto;
import ua.pp.darknsoft.darkystore.exception.ResourceNotFoundException;
import ua.pp.darknsoft.darkystore.model.Contact;
import ua.pp.darknsoft.darkystore.repository.ContactRepository;
import ua.pp.darknsoft.darkystore.service.IContactService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
            contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
            contact = contactRepository.save(contact);
            return contact.toRecord();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ContactResponseDto> getAllOpenMessages() {
        List<Contact> contacts = contactRepository.findByStatus(ApplicationConstants.OPEN_MESSAGE);
        return contacts.stream().map(Contact::toResponseDto).collect(Collectors.toList());
    }
    
    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact", "ContactID", contactId.toString())
        );
        contact.setStatus(status);
        contactRepository.save(contact);
    }
}
