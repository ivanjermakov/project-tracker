package com.gmail.ivanjermakov1.projecttracker.core.security;

import java.util.UUID;

public class TokenGenerator {
	
	public static String generate() {
		return UUID.randomUUID().toString();
	}
	
}
