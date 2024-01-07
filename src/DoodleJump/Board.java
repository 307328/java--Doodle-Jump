package DoodleJump;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Board extends JLabel{
    int TYPE;//木板种类
    int MODE;
    int PROP;//道具种类和怪物种类
    String filepath=null;
    Board(int type,int mode){//初始化木板
        TYPE=type;
        MODE=mode;
        filepath="image/Classic Mode/";
        BoardLoading(TYPE);
    }
    Board(int mode,int prop,int zz){//初始化道具
        MODE=mode;
        PROP=prop;
        filepath="image/Classic Mode/";
        PropLoading(PROP);
    }
    void BoardLoading(int type){//加载图片
        switch(type){//木板选择
            case 1://普通木板
                this.setIcon(new ImageIcon(filepath+"Plattform Green.png"));
                break;
            case 2://只能踩一次的木板
                this.setIcon(new ImageIcon(filepath+"Plattform White.png"));
                break;
            case 3://碎木板(陷阱木板)
                this.setIcon(new ImageIcon(filepath+"Plattform Brown.png"));
                break;
            case 4://左右移动木板
                this.setIcon(new ImageIcon(filepath+"Plattform Blue.png"));
                break;
            case 5://上下移动木板
                this.setIcon(new ImageIcon(filepath+"Plattform Blue.png"));
                break;
        }
    }
    void PropLoading(int prop){//加载图片
        switch(prop){//木板选择
            case 1://弹簧
                this.setIcon(new ImageIcon(filepath+"Spring.png"));
                this.setSize(17,9);
                break;
            case 2://飞帽
                this.setIcon(new ImageIcon(filepath+"Fly.png"));
                this.setSize(28,23);
                break;
            case 3://火箭
                this.setIcon(new ImageIcon(filepath+"Rocket.png"));
                this.setSize(26,37);
                break;
            case 4://小怪
                this.setIcon(new ImageIcon(filepath+"fly1.png"));
                this.setSize(79,45);
                break;
            case 5://移动怪
                this.setIcon(new ImageIcon(filepath+"Move2.png"));
                this.setSize(39,50);
                break;
            case 6://大怪
                Random Ran=new Random();
                int ran=Ran.nextInt(2)+1;
                System.out.println(ran);
                this.setIcon(new ImageIcon(filepath+"Monster"+ran+".png"));
                this.setSize(83,53);
                break;
            case 7://黑洞
                this.setIcon(new ImageIcon(filepath+"BlackHole.png"));
                this.setSize(64,62);
                break;
        }
    }
    void Change(){//弹簧碰撞更改状态
        this.setIcon(new ImageIcon(filepath+"Spring Up.png"));//更新弹簧状态
        this.setSize(19,29);
    }
}