package draw.entity;

import jdk.nashorn.internal.ir.Block;

import java.awt.*;

//申明抽象类(虚基类)
public abstract class Shape {
    private int OrgX;       //初始坐标x
    private int OrgY;       //初始坐标y
    private Color BorderColor;      //边界颜色
    private int BorderType;         //边界类型
    private int BorderWidth;        //边界宽度
    private Color FillColor;        //填充颜色
    private int FillType;       //填充类型
    Shape(int orgX,int orgY,Color borderColor,int borderType,int borderWidth,Color fillColor,int fillType)
    {
        OrgX = orgX;
        OrgY = orgY;
        BorderColor = borderColor;
        BorderType = borderType;
        BorderWidth = borderWidth;
        FillColor = fillColor;
        FillType = fillType;
    }
    Shape()
    {
        OrgX = 0;
        OrgY = 0;
        BorderColor = Color.BLACK;
        BorderType = 0;
        BorderWidth = 0;
        FillColor = Color.WHITE;
        FillType = 0;
    }
    public int getOrgX(){
        return OrgX;
    }
    public int getOrgY(){
        return OrgY;
    }
    public Color getBorderColor(){
        return BorderColor;
    }
    public int getBorderType(){
        return BorderType;
    }
    public int getBorderWidth(){
        return BorderWidth;
    }
    public Color getFillColor() {
        return FillColor;
    }
    public int getFillType(){
        return FillType;
    }
}

class Rectangle extends Shape{
    private int Width;
    private int Height;
    Rectangle(int OrgX,int OrgY,Color BorderColor,int BorderType,int BorderWidth,Color FillColor,int FillType,int width,int height){
        super(OrgX,OrgY,BorderColor,BorderType,BorderWidth,FillColor,FillType);
        //调用父类的构造器
        Width = width;
        Height = height;
    }
    Rectangle(){

        Width = 50;
        Height = 20;
    }
    public int getWidth(){
        return  Width;
    }
    public int getHeight(){
        return Height;
    }
}
class Main {
    public static void main(String []args){
        Rectangle rectangle = new Rectangle(20,33,Color.GRAY,3,44,Color.cyan,2,22,99);
        System.out.println(rectangle.getOrgX());
        //父类中的属性
        System.out.println(rectangle.getWidth());
        //自己本身的属性
    }

}