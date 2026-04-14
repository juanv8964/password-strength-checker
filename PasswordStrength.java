import java.util.Scanner;
import java.util.List;
public class PasswordStrength {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String password = enterPassword(input);
        PasswordApiResponse response = PasswordApiSimulator.checkPassword(password);
    
        passwordDisplay(response.result);
        passwordStrength(response.score, response.result, response);
        passwordFeedback(response.feedback);
    }

    public static int Scoring(PasswordResult result) {
        int score = 0;
        if (result.hasUppercase) score++;
        if (result.hasLowercase) score++;
        if (result.hasDigit) score++;
        if (result.hasSpecialChar) score++;
        if (result.hasValidLength) score++;
        if(result.hasNospaces) score++;
        if(!result.isCommonPassword) score++;
        return score;
    }

    public static void passwordDisplay(PasswordResult result) {
        System.out.println("Password Strength:");
        System.out.println("- Uppercase letters: " + (result.hasUppercase ? "Yes" : "No"));
        System.out.println("- Lowercase letters: " + (result.hasLowercase ? "Yes" : "No"));
        System.out.println("- Digits: " + (result.hasDigit ? "Yes" : "No"));
        System.out.println("- Special characters: " + (result.hasSpecialChar ? "Yes" : "No"));
        System.out.println("- Valid length (8+ characters): " + (result.hasValidLength ? "Yes" : "No"));
        System.out.println("- No spaces: " + (result.hasNospaces ? "Yes" : "No"));
        System.out.println("- Not a common password: " + (!result.isCommonPassword ? "Yes" : "No"));
    }

    public static void passwordStrength(int score, PasswordResult result, PasswordApiResponse response) {
        if(result.isCommonPassword) {
            System.out.println("Your password is a common password. Consider using a more unique password for better security.");
            return;
        }   
        else if (score == 7) {
            System.out.println("Your password is strong.");
        } else if (score >= 5) {
            System.out.println("Your password is moderate. Consider adding more character types or increasing the length for better security.");
        } else {
            System.out.println("Your password is weak. Consider adding more character types for better security.");
        }
        System.out.println("Password Score: " + score + "/7");
        System.out.println("Password Strength is: " + response.strength);
    }

    public static String enterPassword(Scanner input) {
        String password;
        System.out.print("Enter a password to check its strength: ");
        password = input.nextLine();
        return password;
    }

    public static void passwordFeedback(List<String> feedback) {
        if(!feedback.isEmpty()) {
            System.out.println("Feedback:");
            for (String msg : feedback) {
                System.out.println("- " + msg );
            }
        }
    }

}