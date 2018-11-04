package com.ynov.dap.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;
import com.ynov.dap.google.GoogleService;
import com.ynov.dap.google.MailService;

// TODO: Auto-generated Javadoc
/**
 * The Class Welcome.
 */
@Controller
public class Welcome {

    /** The mail service. */
    @Autowired
    private MailService mailService;

    /** The google service. */
    @Autowired
    private GoogleService googleService;

    /**
     * Return welcome.
     *
     * @param gUser the g user
     * @param model the model
     * @return the string
     */
    @RequestMapping("/{gUser}")
    public String returnWelcome(@PathVariable final String gUser, final ModelMap model) {
		try {
			model.addAttribute("nbEmails", mailService.getNbUnreadEmails(gUser).getUnRead());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "welcome";
	}

	/**
	 * Return data store.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/data")
	public String returnDataStore(final ModelMap model) {
		try {
			Map<String, Object> dataStore = new HashMap<String, Object>();
			DataStore<StoredCredential> credentials = googleService.getFlow().getCredentialDataStore();

			for (String key : credentials.keySet()) {
				Map<String, Object> userData = new HashMap<String, Object>();
				StoredCredential values = credentials.get(key);
				userData.put("accessToken", values.getAccessToken());
				userData.put("refreshToken", values.getRefreshToken());
				userData.put("expirationTimeMilliseconds", values.getExpirationTimeMilliseconds());

				dataStore.put(key, userData);
			}

			model.addAttribute("dataStore", dataStore);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "data";
	}

}
