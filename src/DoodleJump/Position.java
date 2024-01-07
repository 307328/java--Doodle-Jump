package DoodleJump;

import java.util.Random;

public class Position{
    int x,y;
    public Position(int y){
        Random Ran=new Random();
        x=Ran.nextInt(387);
        this.y=y;
    }

}