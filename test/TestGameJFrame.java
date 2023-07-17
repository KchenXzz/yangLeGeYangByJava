package yangLeGeYang.test;

import yangLeGeYang.model.Card;

import javax.swing.*;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 10:04
 */
public class TestGameJFrame extends JFrame {



    public TestGameJFrame() {

        //初始化面板
        initFrame();

        Card card = new Card("帽子");

        card.setBounds(300,23,59,66);
        this.getContentPane().add(card);


    }

    private void autoFlushed() {

        TestGameJFrame start = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    start.repaint();
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


    }




    private void initFrame() {

        //设置界面的标题
        this.setTitle("羊了个喜羊羊！");

        this.setSize(492, 822);
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);


        //显示出来
        setVisible(true);

        //启动自动刷新
        autoFlushed();


    }


}
