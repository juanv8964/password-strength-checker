import java.util.ArrayList;
import java.util.List;
public class PasswordApiResponse {
    public PasswordResult result;
    public int score;
    public String strength;
    public List<String> feedback = new ArrayList<>();
}