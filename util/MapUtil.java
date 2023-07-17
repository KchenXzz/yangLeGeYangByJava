package yangLeGeYang.util;

import yangLeGeYang.model.Card;
import yangLeGeYang.model.Cell;
import yangLeGeYang.model.Layer;
import yangLeGeYang.model.Map;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 16:00
 */
public class MapUtil {
    public static int getAllCard() {
        return allCard;
    }

    //所有图层上的所有卡片  再减去置空的卡片就是游戏界面上的卡片
    private static int allCard = 6 * 6 * 6;

    /**
     * 遍历地图每一层上的每一个卡片
     * 找到一个空卡片就allCard--
     *
     * @param map 图层
     */
    private static void visitGetNullCard(Map map) {

        List<Layer> list = map.getList();

        //狠狠的遍历
        for (Layer layer : list) {

            Cell[][] cells = layer.getCells();

            for (int row1 = 0; row1 < cells.length; row1++) {
                for (int col1 = 0; col1 < cells[row1].length; col1++) {

                    Card card = cells[row1][col1].getCard();
                    if (card.getName().equals("空空")) {
                        allCard--;
                    }

                }
            }
        }

    }

    /**
     * 构建地图 其实只能6层，改变还要改代码
     * @param floorHeight 层数
     * @return 地图
     */
    public static Map build(Integer floorHeight){

        Map map = new Map();

        map.setFloorHeight(floorHeight);

        List<Layer> list = map.getList();

        Random random=new Random();

        Layer layer1 = LayerUtil.buildLayer(6, 6);
        Layer layer2 = LayerUtil.buildLayer(6, 6);
        Layer layer3 = LayerUtil.buildLayer(6, 6);
        Layer layer4 = LayerUtil.buildLayer(6, 6);
        Layer layer5 = LayerUtil.buildLayer(6, 6);
        Layer layer6 = LayerUtil.buildLayer(6, 6);


        //随机偏移量 才能看起来不整齐
        int bound1 = random.nextInt(80) + 50;
        int bound2 = random.nextInt(85) + 50;
        int bound3 = random.nextInt(80) + 50;

        //分别设置每个图层
        layer1.setOffSetX(random.nextInt(bound1) - 10);
        layer1.setOffSetY(random.nextInt(bound1 + 30));

        layer2.setOffSetX(random.nextInt(bound2) - 10);
        layer2.setOffSetY(random.nextInt(bound2));

        layer3.setOffSetX(random.nextInt(bound3 - 10));
        layer3.setOffSetY(random.nextInt(bound3 + 30));

        layer4.setOffSetX(random.nextInt(bound1 - 10));
        layer4.setOffSetY(random.nextInt(bound1));

        layer5.setOffSetX(random.nextInt(bound2 - 5));
        layer5.setOffSetY(random.nextInt(bound2 + 30));

        layer6.setOffSetX(random.nextInt(bound3 - 15));
        layer6.setOffSetY(random.nextInt(bound3));


        //为图层构建链式关系
        layer6.setParent(layer5);
        layer5.setParent(layer4);
        layer4.setParent(layer3);
        layer3.setParent(layer2);
        layer2.setParent(layer1);
        layer1.setParent(null);//说明这层是顶层


        //添加到集合
        list.add(layer1);
        list.add(layer2);
        list.add(layer3);
        list.add(layer4);
        list.add(layer5);
        list.add(layer6);

        //遍历一下，得到所有卡片的总数
        visitGetNullCard(map);
        return map;
    }


    /**
     * 比较卡片又没有被图层遮盖，被遮盖
     * @param card 卡片
     * @param layer 图层
     * @return 被遮盖true 没有false
     */

    public static boolean compare(Card card,Layer layer){

        Cell[][] cells = layer.getCells();
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];

                //如果当前格子有牌，就要判断，
                if (cell.isState()){
                    //这是Java里的比较矩阵有没有交际的方法
                    Rectangle bounds = card.getBounds();
                    Rectangle bounds1 = cell.getCard().getBounds();

                    //ture 说明被遮盖
                    if (bounds1.intersects(bounds)){
                        return true;
                    }
                }//无牌，就不用比较了，直接去看下一个格子
            }
        }

        //如果要是没有父亲，说明是顶层了，就没有遮盖
        if (layer.getParent()==null){
            return false;
        }else {
            //再次递归去调用
            return compare(card,layer.getParent());
        }
    }


}
