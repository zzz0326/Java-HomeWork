package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class DynamicProxy implements InvocationHandler{
    private Object proxied;
    public DynamicProxy(Object proxied){
        this.proxied = proxied;
    }

    //必须要进行重写
    @Override
    //proxy 代表代理 method代表调用的方法 args 代表传入的参数
    public Object invoke(Object proxy, Method method,Object[] args)
        throws Throwable{
        //异常处理 try catch也可以

        method.invoke(this.proxied, args);
        //相当于调用 proxied.method(args)
        System.out.println("运行时间: " + System.currentTimeMillis());
        //需要添加的功能
        return null;
        //当函数需要返回对象的时候 可以在这里进行返回
    }
}



public class Dynamic {
    public static void main(String[] args){
        Person person = new Person();
        //动态代理实现动态接口 变为实体类 然后使用
        Speakable speakable = (Speakable) Proxy.newProxyInstance(Speakable.class.getClassLoader(),
                new Class[]{Speakable.class},new DynamicProxy(person));
        //DynamicProxy(person) 把person传入到proxied当中去了 所以可以有效的进行调用
        speakable.speak("today is Sun");
        //这里调用了invoke方法 来实现代理
    }
}
