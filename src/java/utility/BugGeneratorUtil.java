package utility;

import java.util.ArrayList;
import java.util.List;

public class BugGeneratorUtil {

    private BugGeneratorUtil(int a, int b){
        this.a = a;
    }
    private int a;
    public void searchNumber(int x){
        if (x < 0){
            throw new IllegalArgumentException("x must be non-negative");
        }
    }

    @Override
    public String toString(){
        if (a > 0){
            return "";
        }
        return Integer.toString(a);
    }

    public void infiniteLoopTest(){
        int i = 0;
        while (i >= 0){
            i+= 1;
        }
    }
}
