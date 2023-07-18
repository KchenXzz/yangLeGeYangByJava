package yangLeGeYang;

import yangLeGeYang.model.*;
import yangLeGeYang.util.MapUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * //TODO
 * 游戏主界面
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 10:04
 */
public class YangLeGeYang extends JFrame {


    public static Map map; //几层都可以

    private  int floor;

    public YangLeGeYang(int floorHeight) {

        floor=floorHeight;

        map = MapUtil.build(floorHeight);

        //初始化面板
        initFrame(floorHeight);

        //渲染图层
        render();

        //判断所有卡片是否置灰
        map.compareAll();

        //初始化背景
        initBackground();

        //背景音乐
        new Music().music();

    }


    /**
     * 判断是否赢了
     */
    public boolean isWin() {

        YangLeGeYang start = this;
        int cardSum = MapUtil.getAllCard();
        if (Card.getCount() == cardSum) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //给弹框设置大小
            jDialog.setSize(237, 222);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            jDialog.setModal(true);

            //设置不可变大小
            jDialog.setResizable(false);

            jDialog.setTitle("win！");

            jDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // 在窗口关闭时执行自定义操作
                    //System.exit(0);

                    jDialog.dispose();
                    start.dispose();
                    floor++;
                    new YangLeGeYang(floor);
                }
            });

            //创建Jlabel对象管理文字并添加到弹框当中
            JLabel warning = new JLabel(new ImageIcon("res\\赢.了.png"));

            warning.setSize(237, 185);

            jDialog.getContentPane().add(warning);
            //让弹框展示出来
            jDialog.setVisible(true);

            jDialog.getContentPane().add(warning);

            //让弹框展示出来
            jDialog.setVisible(true);
            //退出程序
            //System.exit(0);



            return true;


        } else return false;
    }

    /**
     * 渲染界面
     */
    private void render() {
        List<Layer> list = map.getList();
        for (Layer layer : list) {//先加入的在上面
            //把图层上的一个个单元格添加到界面
            //先获取单元格数组
            Cell[][] cells = layer.getCells();

            layer.showCells();

            //循环遍历这个二维数组添加
//        // 要修改card类的坐标
            for (int row = 0; row < cells.length; row++) {
                for (int col = 0; col < cells[row].length; col++) {
                    int x = col * 59 + layer.getOffSetX();
                    int y = row * 66 + layer.getOffSetY() + 40;
                    Card card1 = cells[row][col].getCard();
                    //只有不是被置空才能添加到界面
                    if (!card1.getName().equals("空空")) {
                        card1.setBounds(x, y, 59, 66);
                        this.getContentPane().add(card1);
                    }

                }
            }
        }
    }

    /**
     * 添加背景图片
     */
    private void initBackground() {
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("res\\背景.jpg"));
        background.setSize(480, 800);

        //把背景图片添加到界面当中
        this.getContentPane().add(background);
    }



    /**
     * 窗口自动刷新线程
     */
    private void autoFlushed() {

        YangLeGeYang start = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //自动刷新界面
                    start.repaint();
                    //随时判断是否胜利
                   start.isWin();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


    }


    /**
     * 界面初始
     */
    private void initFrame(int floorHeight) {

        //设置界面的标题
        this.setTitle("羊了个羊！第" + (floorHeight - 1) + "关");
        //大小
        this.setSize(492, 822);
        //设置不可变大小
        this.setResizable(false);
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setDefaultCloseOperation(JFrame.);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);


        //显示出来
        setVisible(true);

        //启动自动刷新
        autoFlushed();

    }

}
