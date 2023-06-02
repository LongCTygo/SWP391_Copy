package utility;

import java.util.ArrayList;
import java.util.List;

public class BugGeneratorUtil {

    private BugGeneratorUtil(int a, int b){
        this.a = this.a = a;
    }
    private int a,b,c,d;
    public void searchNumber(int x){
        if (x < 0 || x > 255){
            new IllegalArgumentException("invalid");
        }
    }

    public String tostring(){
        return new Integer(a).toString();
    }

    public void infiniteLoopTest(){
        int i = 0;
        while (i >= 0){
            i+= 1;
        }
    }
}
