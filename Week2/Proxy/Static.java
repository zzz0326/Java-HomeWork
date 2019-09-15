package Proxy;

//建立接口
interface Speakable{
    //抽象方法
    public void speak(String message);
}

class Person implements Speakable{
    //接口中的每一个方法都需要被重写
    @Override
    public void speak(String message){
        System.out.println("person speak: "+message);
    }
}

class PersonProxy implements Speakable{

    private Person person;

    public PersonProxy(Person person){
        this.person = person;
    }

    //实现接口
    @Override
    public void speak(String message){
        this.person.speak(message);
        System.out.println("运行时间: "+System.currentTimeMillis());
    }
}

public class Static {
    public static void main(String [] args){
        Person person = new Person();
        PersonProxy personProxy = new PersonProxy(person);
        personProxy.speak("today is Sun");
    }
}
