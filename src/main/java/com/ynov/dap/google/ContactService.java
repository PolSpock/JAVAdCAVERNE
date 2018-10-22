package com.ynov.dap.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;
import com.ynov.dap.models.ContactModel;

/**
 * SERVICE FOR CONTACT.
 * @author POL
 */
@Service
@PropertySource("classpath:config.properties")
public class ContactService extends GoogleService {

    /** The log. */
    private Logger log = LoggerFactory.getLogger(CalendarService.class);


    /** The env. */
    @Autowired
    private Environment env;


    /**
     * Instantiates a new contact service.
     */
    public ContactService() {
        super();
    }

    /**
     * Gets the nb contacts.
     *
     * @param user the user
     * @return the nb contacts
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws GeneralSecurityException the general security exception
     */
    public ContactModel getNbContacts(final String user) throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        PeopleService service = new PeopleService.Builder(httpTransport, JSON_FACTORY, getCredentials(user))
                .setApplicationName(env.getProperty("application_name"))
                .build();

        ListConnectionsResponse response = service.people().connections()
                .list("people/me")
                .setPersonFields("names,emailAddresses")
                .execute();

        List<Person> connections = response.getConnections();
        if (connections != null && connections.size() > 0) {

            ContactModel contact = new ContactModel(connections.size());

            return contact;
        } else {
            log.info("CONTACT : No contacts found.");
        }
        return null;
    }
}
