package DoodleJump;

import java.util.Random;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class Layer extends JLabel{
    int x,y,NUM,MODE;
    int[] TYPE;
    int PROP;  //一个数字对应一个道具或者怪物
    Board[] myBoard;//板
    Board prop;//支柱
    Position[] myPosition;//位置
    Layer(int y,int mode,int score){
        NewNUM(score);//根据当前分数获取NUM的值
        this.y=y;
        NewPosition(NUM);
        NewTYPE(NUM,score);
        MODE=mode;
        NewBoard(NUM,TYPE);
        NewPROP(score);
    }
    void NewNUM(int score){
        if(score<500) {
            long time = System.currentTimeMillis();//获取当前的总毫秒数
            Random Ran=new Random(time);
            int ran=Ran.nextInt(100);
            if(ran<80) NUM=1;
            else NUM=2;
        }
        if(score>=500&&score<20000) {
            long time = System.currentTimeMillis();
            Random Ran=new Random(time);
            int ran=Ran.nextInt(100);
            if(ran<80) NUM=1;
            else if(ran<=95) NUM=2;
            else NUM=3;
        }
        else NUM=1;
    }
    void NewPROP(int score){//生成新道具
        long time = System.currentTimeMillis();
        Random Ran=new Random(time);
        int ran=Ran.nextInt(100);
        if(score<500)
            PROP=0;//普通
        else {
            if(TYPE[0]==1) {//只在普通木板生成
                if(ran==3||ran==6||ran==9||ran==13||ran==16||ran==19)
                    PROP=1;//弹簧
                else if(ran==1||ran==11)
                    PROP=2;//飞帽
                else if(ran==2||ran==12)
                    PROP=3;//火箭
                else if(ran==4)
                    PROP=4;//小怪
                else if(ran==5)
                    PROP=5;//移动怪
                else if(ran==7)
                    PROP=6;//大怪
                else if(ran==8)
                    PROP=7;//黑洞
            }
            prop=new Board(MODE,PROP,0);//在木板上生成道具
        }
    }
    void NewPosition(int NUM){
        myPosition=new Position[NUM];
        for(int i=0;i<NUM;i++){
            boolean flag=true;//控制do while循环，循环会一直执行直到 flag 变为 true
            do{
                flag=true;
                myPosition[i]=new Position(y);
                if(i>0){//从第二个位置开始检查
                    for(int j=i-1;j>=0;j--){
                        if(Math.abs(myPosition[i].x-myPosition[j].x)<63)//检查新生成的位置与先前位置的水平距离是否小于 63 像素。
                            flag=false;//如果新生成的位置与之前已经存在的位置水平距离小于 63 像素，将 flag 设为 false，表示该位置不合适，需要重新生成。
                    }
                }
            }while(flag==false);
        }
    }
    void NewTYPE(int NUM,int score){
        TYPE=new int[NUM];
        if(score<500) {
            for(int i=NUM-1;i>=0;i--) TYPE[i]=1;
        }
        else {
            Random Ran=new Random();
            for(int i=NUM-1;i>=0;i--) {
                int ran=Ran.nextInt(100);
                if(ran<70) TYPE[i]=1;
                else if(ran<85) TYPE[i]=2;
                else if(ran<90) TYPE[i]=3;
                else if(ran<96) TYPE[i]=4;
                else  {TYPE[i]=5; x=myPosition[i].x;}
            }
        }
    }
    void NewBoard(int NUM,int[] TYPE){
        myBoard=new Board[NUM];
        for(int i=0;i<NUM;i++) {
            myBoard[i]=new Board(TYPE[i],MODE);
        }
    }
    void Change() {
        switch(PROP){
            case 1:
                prop.Change();
                PROP=8;
                break;
            case 2:
                PROP=0;
                prop.setVisible(false);//踩一次就消失的木板
                break;
            case 3:
                PROP=0;
                prop.setVisible(false);//碎木板
                break;
        }
    }
}