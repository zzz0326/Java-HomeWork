package draw.entity;

import java.awt.*;

public class Mouse {
    //获取鼠标位置
    PointerInfo pointerInfo = MouseInfo.getPointerInfo();
    Point point = pointerInfo.getLocation();
    double orgX = point.getX();
    double orgY = point.getY();

}
