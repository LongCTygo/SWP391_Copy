package utility;

import java.util.ArrayList;
import java.util.List;

public class BugGeneratorUtil {

    private BugGeneratorUtil(int a, int b){
        this.a = this.a = a;
    }
    private int a,b,c,d;
    public void searchNumber(int x){
        if (x < 0){
            new IllegalArgumentException("x must be non-negative");
        }
    }

    public String tostring(){
        if (a > 0){
            return null;
        }
        return new Integer(a).toString();
    }

    public void infiniteLoopTest(){
        int i = 0;
        while (i >= 0){
            i+= 1;
        }
    }

    public void collectionBugTest(){
        List<Object> list = new ArrayList<>();
        list.add(list);
    }
}
