package org.mnode.ousia;

import java.awt.Component;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.auth.LoginService;

public class DialogAuthenticator extends Authenticator {

	private final Component parent;
	
	private final JXLoginPane loginPane;
	
	public DialogAuthenticator(Component parent) {
		this.parent = parent;
		loginPane = new JXLoginPane(new LoginService() {
			@Override
			public boolean authenticate(String arg0, char[] arg1, String arg2)
					throws Exception {
				return true;
			}
		});
	}

	public JXLoginPane getLoginPane() {
		return loginPane;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		loginPane.setMessage(getRequestingPrompt());
		loginPane.setBanner(null);
		loginPane.setBannerText(null);
		if (JXLoginPane.Status.CANCELLED != JXLoginPane.showLoginDialog(parent, loginPane)) {
			return new PasswordAuthentication(loginPane.getUserName(), loginPane.getPassword());
		}
		else {
			return super.getPasswordAuthentication();
		}
	}
}
