package draw.entity;

import java.awt.*;

//在一个package内 不需要import
public class Square extends Shape{
    private int Width;
    public Square(int OrgX, int OrgY, Color BorderColor, int BorderType, int BorderWidth, Color FillColor, int FillType, int width){
        super(OrgX,OrgY,BorderColor,BorderType,BorderWidth,FillColor,FillType);
        //调用父类中的构造函数
        Width = width;
        System.out.println(getOrgX()+Width);//可以直接使用基类中的函数
    }
    public int getWidth(){
        return  Width;
    }
}
