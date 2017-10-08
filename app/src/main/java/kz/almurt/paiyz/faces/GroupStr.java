package kz.almurt.paiyz.faces;

/**
 * Created by Mukhtar on 20/08/2017.
 */
import java.util.ArrayList;
import java.util.List;

public class GroupStr {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public GroupStr(String string){
        this.string = string;
    }
}