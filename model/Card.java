package yangLeGeYang.model;

import yangLeGeYang.YangLeGeYang;
import yangLeGeYang.util.MapUtil;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * //卡片类
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 10:11
 */
public class Card extends Component{

    //卡片名字
    private String name;

    //卡片的图片对象
    private Image image;

    //卡片的灰色图片对象
    private Image grayImage;

    //判断当前卡片是否为灰色
    private boolean isGray;

    //卡片在窗口的X坐标
    private Integer x;
    //卡片在窗口的Y坐标
    private Integer y;

    //当前卡片对应的单元格
    private Cell cell;
    //所有卡片的移动次数
    private static int count=0;

    public static int getCount() {
        return count;
    }

    //用来调用
    // cardBox.addPool(card);
    CardBox cardBox=new CardBox();

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    int moveCount;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    //卡片的高度和宽度
    private Integer width;
    private Integer height;


    /**
     * 有参的构造，接受卡片名字，然后初始化，name,image,grayImage,isGray,x,y,width,height
     * @param name 卡片名字
     */
    public Card(String name) {
        this.name = name;
        this.image=Toolkit.getDefaultToolkit().getImage("res\\"+this.name+".png");
        this.grayImage=Toolkit.getDefaultToolkit().getImage("res\\"+this.name+"2.png");

        this.isGray=false;

        this.x=0;
        this.y=0;

        this.width=59;
        this.height=66;

        this.moveCount=0;
        //测试代码，添加鼠标监听，
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Card card = (Card)e.getSource();//得到被点击自身组件对象

                if (card.isGray()||card.getMoveCount()>=1){
                    //灰色不能点击
                    System.out.println("不能点击");
                    return;
                }else {
                    System.out.println("能点击,步数："+count+"  总数步数（卡片数）："+ MapUtil.getAllCard());
                    //移动到消除框
                    cardBox.addPool(card);
                    card.moveCount++;

                    count++;
//                   card.getParent().remove(card);//删除组件
                    //只是在UI上删除了card对象，但是数据模型中，cell中对应的card还在，对应的那个格子还是有card对象
                    //要把cell中的对象删除
                    cell.setState(false);
                    cell.setCard(null);

                    //不太好的定义方式，但是从高card去找它的map太复杂
                    YangLeGeYang.map.compareAll();
                }


            }
        });


    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getGrayImage() {
        return grayImage;
    }

    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }

    public boolean isGray() {
        return isGray;
    }

    /**
    true 灰色
     */
    public void setGray(boolean gray) {
        isGray = gray;
    }



    public void setX(Integer x) {
        this.x = x;
    }



    public void setY(Integer y) {
        this.y = y;
    }



    public void setWidth(Integer width) {
        this.width = width;
    }



    public void setHeight(Integer height) {
        this.height = height;
    }


    @Override
    public void paint(Graphics g) {

        if (isGray){
            //绘制灰色
            g.drawImage(this.grayImage,this.x,this.y,null);

        }else {
            //绘制正常图片
            g.drawImage(this.image,this.x,this.y,null);

        }
    }
}
