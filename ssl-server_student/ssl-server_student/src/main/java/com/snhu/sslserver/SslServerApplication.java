package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class HashController {

	@GetMapping("/hash")
	public String getHash() throws NoSuchAlgorithmException {
		// Static data to be hashed - includes student identifier
		String data = "Hello World Check Sum! - Joseph Opheim";
		String name = "Joseph Opheim";

		// Create SHA-256 hash
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

		// Convert byte array to hexadecimal string
		StringBuilder hexString = new StringBuilder();
		for (byte b : hashBytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		// Return formatted response
		return "<html><body>" +
			   "<h1>Checksum Verification</h1>" +
			   "<p><strong>Student Name:</strong> " + name + "</p>" +
			   "<p><strong>Data String:</strong> " + data + "</p>" +
			   "<p><strong>SHA-256 Checksum:</strong> " + hexString.toString() + "</p>" +
			   "</body></html>";
	}
}