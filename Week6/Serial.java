import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// A 类被序列化
class A implements Serializable
{
    int i;


    public A(int i)
    {
        this.i = i;
    }

}

// B类继承了A类的序列化
class B extends A
{
    int j;


    public B(int i, int j)
    {
        super(i);
        this.j = j;
    }
}


//未被序列化的父类
class C
{
    int i;

    public C(int i)
    {
        this.i = i;
    }

    public C()
    {
        i = 50;
        System.out.println("C's class constructor called");
    }

}

// 子类进行序列化
class D extends C implements Serializable
{
    int j;


    public D(int i,int j)
    {
        super(i);
        this.j = j;
    }
}



public class Serial implements Serializable {
    public static void main(String[] args)
            throws Exception
    {
        B b1 = new B(10,20);

        System.out.println("i = " + b1.i);
        System.out.println("j = " + b1.j);

        //储存子类对象
        FileOutputStream fos = new FileOutputStream("abc.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);


        oos.writeObject(b1);


        oos.close();
        fos.close();

        System.out.println("Object has been serialized");

        //读取序列化对象
        FileInputStream fis = new FileInputStream("abc.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Method for de-serialization of B's class object
        B b2 = (B)ois.readObject();

        // closing streams
        ois.close();
        fis.close();

        System.out.println("Object has been deserialized");

        System.out.println("i = " + b2.i);
        System.out.println("j = " + b2.j);




        D d1 = new D(10, 20);

        System.out.println("i = " + d1.i);
        System.out.println("j = " + d1.j);


        FileOutputStream fos1 = new FileOutputStream("abc1.ser");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);


        oos.writeObject(d1);
        //在这一行会报错
        //也就是说如果父类不是序列化的类 子类序列化了也没有用

        oos1.close();
        fos1.close();

        System.out.println("Object has been serialized");


        FileInputStream fis1 = new FileInputStream("abc1.ser");
        ObjectInputStream ois1 = new ObjectInputStream(fis1);


        // Method for de-serialization of B's class object
        D d2 = (D)ois1.readObject();

        // closing streams
        ois1.close();
        fis1.close();

        System.out.println("Object has been deserialized");

        System.out.println("i = " + d2.i);
        System.out.println("j = " + d2.j);
    }
}