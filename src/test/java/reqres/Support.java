package reqres;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Support {
    public static final String TEXT_MESSAGE = "To keep ReqRes free, contributions towards server costs are appreciated!";
    private String url;
    private String text;


}
