package DoodleJump;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class StartFrame extends JFrame implements Runnable,ActionListener{//开始界面
    //属性
    int MODE,TIME=8;//模式,修改数值变小可以减少卡顿(StartFrame,MainFrame)

    //控件
    JButton Start,Scores,Next;//声明按钮
    JLabel NextLabel,UfoLabel,nextScoreCrown1,nextScoreCrown2,nextScoreCrown3;//二级界面框、UFO动画、模式界面、皇冠图
    JTextArea nextScoreRecord=new JTextArea();//排行榜
    SmallBoy Stan;//Stan动画
    //参数
    int t;//动画参数
    boolean isStart=false,isScores=false,isNext=false;
    //路径
    String languagepath;
    JMenuItem focusmeJMenuItem = new JMenuItem("添加微信");

    public void initialJMenuBar() {

        JMenuBar jMenuBar = new JMenuBar();
        JMenu aboutusJMenu = new JMenu("关于作者");
        aboutusJMenu.add(focusmeJMenuItem);
        focusmeJMenuItem.addActionListener(this);
        jMenuBar.add(aboutusJMenu);
        this.setJMenuBar(jMenuBar);
    }

    @SuppressWarnings("deprecation")
    public StartFrame(int mode){//初始化开始界面
        //界面属性设置

        initialJMenuBar();
        MODE=mode;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(450,720);
        languagepath="image/English/";
        this.setTitle("DoodleJump");
        //背景，模式背景
        JLabel bgLabel=new JLabel(new ImageIcon("image/System/menu11.jpg"));

        bgLabel.setBounds(-5,-50,450,730);

        this.getLayeredPane().add(bgLabel, Integer.valueOf(Integer.MIN_VALUE+1));//将 bgLabel 添加到容器的层级面板中，并设置为最底层。
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp=(JPanel)this.getContentPane();
        jp.setOpaque(false);

        //开始按钮
        Start=new JButton(new ImageIcon(languagepath+"Button/start.png"));
        Start.setContentAreaFilled(false);
        Start.setBorder(null);
        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isStart=true;

            }});
        Start.setBounds(90,250,105,40);
        add(Start);

        //排行榜按钮
        Scores=new JButton(new ImageIcon(languagepath+"Button/scores.png"));
        Scores.setContentAreaFilled(false);
        Scores.setBorder(null);
        Scores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isScores=true;

            }});
        Scores.setBounds(220,320,105,40);
        add(Scores);
        //小人动画
        Board myboard=new Board(1,1);
        myboard.setBounds(40,527,63,15);
        add(myboard);
        Stan=new SmallBoy(1);
        Stan.setBounds(35,280,62,47);
        add(Stan);
        //UFO动画
        UfoLabel=new JLabel(new ImageIcon("System/UFO1.png"));
        UfoLabel.setBounds(291, 29, 101, 146);
        add(UfoLabel,-1);

        //二级界面框
        NextLabel=new JLabel();
        NextLabel.setOpaque(true);
        NextLabel.setVisible(false);
        Next=new JButton(new ImageIcon(languagepath+"Button/next.png"));
        Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isNext=true;
            }});
        NextLabel.setIcon(new ImageIcon(languagepath+"System/rule1.png"));
        NextLabel.add(Next);
        add(NextLabel);
        //排行榜界面
        nextScoreRecord =new JTextArea();
        nextScoreCrown1 =new JLabel();
        nextScoreCrown2=new JLabel();
        nextScoreCrown3 =new JLabel();
        Scores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NextLabel.setIcon(new ImageIcon(languagepath+"System/rank.png"));
                NextLabel.setBounds(13,240,411,368);
                nextScoreRecord.setBounds(160,90,200,200);
                nextScoreRecord.setFont(new java.awt.Font("Dialog",1,25));
                nextScoreRecord.setOpaque(false);
                nextScoreRecord.setVisible(false);
                NextLabel.add(nextScoreRecord);
                nextScoreCrown1.setIcon(new ImageIcon("image/System/crown1.png"));
                nextScoreCrown1.setBounds(100,130,30,30);
                nextScoreCrown1.setVisible(false);
                NextLabel.add(nextScoreCrown1);
                nextScoreCrown2.setIcon(new ImageIcon("image/System/crown2.png"));
                nextScoreCrown2.setBounds(100,165,30,30);
                nextScoreCrown2.setVisible(false);
                NextLabel.add(nextScoreCrown2);
                nextScoreCrown3.setIcon(new ImageIcon("image/System/crown3.png"));
                nextScoreCrown3.setBounds(100,195,30,30);
                nextScoreCrown3.setVisible(false);
                NextLabel.add(nextScoreCrown3);
                Next.setIcon(new ImageIcon(languagepath+"Button/back.png"));
                Next.setBounds(180,290,83,40);
                FileReadWrite a=new FileReadWrite();
                a.FileRW();
                nextScoreRecord.setText("ID          SCORE");
                a.FileRead(nextScoreRecord);
                nextScoreRecord.setEditable(false);
                isScores=true;

            }});

    }

    public static void main(String[] args) {
        StartFrame start=new StartFrame(1);
        Thread main=new Thread(start);
        main.start();
    }

    public void run(){
        int Step=1;
        int x=300,y=25;
        while(true) {
            //动画
            Stan.setLocation(35,480-4*t+t*t/50);//基于t的值实现Stan的垂直移动
            t++;
            UfoLabel.setIcon(new ImageIcon("image/System/UFO3.png"));
            UfoLabel.setLocation(x+(int)(25*Math.sin(6.28*t/200-3.14)),y+(int)(25*Math.cos(6.28*t/200-3.14)));//这行代码使用了三角函数（正弦和余弦）来实现UFO的曲线移动效果
            if(t==200){
                t=0;//让动画能够循环播放
            }
            //开始游戏
            if(isStart==true){
                MainFrame game=new MainFrame(MODE);
                game.setLocation(this.getLocation());
                Thread Game=new Thread(game);
                Game.start();
                this.setVisible(false);
                Thread.interrupted();
                break;
            }

            //排行榜二级界面
            if(isScores==true){
                nextlabelVisible();
                nextScoreRecord.setVisible(true);
                nextScoreCrown1.setVisible(true);
                nextScoreCrown2.setVisible(true);
                nextScoreCrown3.setVisible(true);
                if(isNext==true){
                    nextlabelHidden();
                    isScores=false;
                }
                isNext=false;
            }

            //保证线程常驻
            try {
                Thread.sleep(TIME);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    //主页面二级提示框显示与隐藏
    public void nextlabelHidden(){
        NextLabel.setVisible(false);
        Start.setVisible(true);
        Scores.setVisible(true);

        if(isScores==true){
            nextScoreRecord.setVisible(true);
            nextScoreCrown1.setVisible(true);
            nextScoreCrown2.setVisible(true);
            nextScoreCrown3.setVisible(true);
        }
    }
    public void nextlabelVisible(){
        NextLabel.setVisible(true);
        Start.setVisible(false);
        Scores.setVisible(false);
        if(isScores==false){
            nextScoreRecord.setVisible(false);
            nextScoreCrown1.setVisible(false);
            nextScoreCrown2.setVisible(false);
            nextScoreCrown3.setVisible(false);
        }
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == focusmeJMenuItem) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/System/wechat.png"));
            jLabel.setBounds(0, 0, 500, 682);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(500, 682);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);

        }
    }
}