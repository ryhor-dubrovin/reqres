package reqres;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class ListUniversal {
    private int page;
    @SerializedName("per_page")
    private int perPage;
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    private ArrayList<reqres.Data> data;
    private Support support;
}
