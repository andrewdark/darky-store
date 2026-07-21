package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.ContactRecord;
import ua.pp.darknsoft.darkystore.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    ContactRecord saveContact(ContactRecord contactRec);

    List<ContactResponseDto> getAllOpenMessages();

    void updateMessageStatus(Long contactId, String status);
}
