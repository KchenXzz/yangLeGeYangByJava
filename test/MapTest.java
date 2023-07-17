package yangLeGeYang.test;

import yangLeGeYang.util.LayerUtil;
import yangLeGeYang.model.Layer;
import yangLeGeYang.model.Map;

import java.util.List;

/**
 * //TODO
 *
 * @author kcx
 * @version v1.0.0
 * @description //TODO
 * @createTime 2023-7-14 15:56
 */
public class MapTest {
    public static void main(String[] args) {


        Map map = new Map();

        map.setFloorHeight(3);

        List<Layer> list = map.getList();

        Layer layer1= LayerUtil.buildLayer(3,3);
        Layer layer2= LayerUtil.buildLayer(6,6);
        Layer layer3= LayerUtil.buildLayer(9,9);


        list.add(layer1);
        list.add(layer2);
        list.add(layer3);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
            list.get(i).showCells();
        }




    }
}
