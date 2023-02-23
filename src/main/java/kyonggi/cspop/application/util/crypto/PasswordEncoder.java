package kyonggi.cspop.application.util.crypto;

public interface PasswordEncoder {
    String encryptPassword(String rawPassword);
    boolean isMatch(String rawPassword, String hashedPassword);
}
