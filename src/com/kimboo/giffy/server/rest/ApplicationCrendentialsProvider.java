package com.kimboo.giffy.server.rest;


public class ApplicationCrendentialsProvider implements CredentialsProvider {
	private Credentials credentials;
	
	public ApplicationCrendentialsProvider(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public Credentials getCredentials(String realm) {
		return this.credentials;
	}

}
