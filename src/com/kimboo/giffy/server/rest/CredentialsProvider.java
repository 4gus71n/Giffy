package com.kimboo.giffy.server.rest;


public interface CredentialsProvider {
	Credentials getCredentials(String realm);
}
