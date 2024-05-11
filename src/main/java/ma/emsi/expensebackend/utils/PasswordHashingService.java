package ma.emsi.expensebackend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class PasswordHashingService {
	 public String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(password.getBytes());
	        return Base64.getEncoder().encodeToString(hash);
	    }

	    // Méthode pour vérifier le mot de passe
	    public boolean verifyPassword(String rawPassword, String hashedPassword) throws NoSuchAlgorithmException {
	        return hashPassword(rawPassword).equals(hashedPassword);
	    }
}
