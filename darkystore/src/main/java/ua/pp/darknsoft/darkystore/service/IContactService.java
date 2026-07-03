package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.ContactRecord;

public interface IContactService {
    ContactRecord saveContact(ContactRecord contactRec);
}
