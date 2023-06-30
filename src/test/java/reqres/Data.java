package reqres;

import com.google.gson.annotations.SerializedName;
import lombok.ToString;

import java.util.ArrayList;
@lombok.Data
public class Data {
    private int id;
    private String email;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String avatar;

}
