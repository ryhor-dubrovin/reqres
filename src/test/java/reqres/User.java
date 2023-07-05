package reqres;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private String job;
    private String email;
    private String password;


}
