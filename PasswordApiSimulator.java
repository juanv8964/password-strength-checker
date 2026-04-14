
public class PasswordApiSimulator {
    public static PasswordApiResponse checkPassword(String password) {
    PasswordResult result = new PasswordResult();
    result.hasValidLength = password.length() >= 8;
    result.hasNospaces = !password.contains(" ");
    String[] commonPasswords = {"password", "123456", "123456789", "qwerty", "abc123", "111111"};
    for (String common : commonPasswords) {
        if (password.equalsIgnoreCase(common)) {
            result.isCommonPassword = true;
            break;
        }
    }
    for(char c : password.toCharArray()) {
        if (Character.isUpperCase(c)) {
            result.hasUppercase = true;
        } else if (Character.isLowerCase(c)) {
            result.hasLowercase = true;
        } else if (Character.isDigit(c)) {
            result.hasDigit = true;
        } else if (!Character.isLetterOrDigit(c)) {
            result.hasSpecialChar = true;
        }
    }
    int score = 0;
        if (result.hasUppercase) score++;
        if (result.hasLowercase) score++;
        if (result.hasDigit) score++;
        if (result.hasSpecialChar) score++;
        if (result.hasValidLength) score++;
        if(result.hasNospaces) score++;
        if(!result.isCommonPassword) score++;
        PasswordApiResponse response = new PasswordApiResponse();
        response.result = result;
        response.score = score;

        if(result.isCommonPassword) {
            response.strength = "Weak";
        }   
        else if (score == 7) {
            response.strength = "Strong";
        } else if (score >= 5) {
            response.strength = "Moderate";
        } else {
            response.strength = "Weak";
        }
        if (!result.hasUppercase) {
            response.feedback.add("Consider adding uppercase letters to strengthen your password.");
        }
        if (!result.hasLowercase) {
            response.feedback.add("Consider adding lowercase letters to strengthen your password.");
        }
        if (!result.hasDigit) {
            response.feedback.add("Consider adding digits to strengthen your password.");
        }
        if (!result.hasSpecialChar) {
            response.feedback.add("Consider adding special characters to strengthen your password.");
        }
        if (!result.hasValidLength) {
            response.feedback.add("Consider increasing the length of your password to at least 8 characters for better security.");
        }
        if (!result.hasNospaces) {
            response.feedback.add("Consider removing spaces from your password for better security.");
        }
    return response;

}

}
