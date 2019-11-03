package final_test;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class GUI extends JFrame{
	public GUI() {
	JFrame jf = new JFrame("个人简历");
	Container container = jf.getContentPane();
												
	//字体
	Font font_menu = new Font("Diglog",1,18);
	Font font_text = new Font("Diglog",1,20);
	
	
	//菜单栏
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("文件");
	menuBar.add(fileMenu);
	jf.setJMenuBar(menuBar);//往框架中添加菜单栏
	
	
	//总面板                              可以滚动，包含一个JScrollPane,JScrollPane中有一个JPane
	JPanel p = new JPanel();//总的Panel布局
	p.setAutoscrolls(isPreferredSizeSet());
    JScrollPane sp = new JScrollPane(p);//初始化面板，用于之后添加简历信息，面板中包含一个panel，panel中包含多个组件。
    p.setLayout(null);
    sp.setBounds(10, 10, 175, 70);
	//上部
	ImageIcon img = new ImageIcon("D:\\java\\resume\\timg.jpg");
	JButton photo = new JButton();
	photo.setIcon(img);//添加照片到按钮
	photo.setBounds(250, 10,150, 205);
	
	//个人信息
	JLabel name = new JLabel("姓名 ：       邹子壮");
	JLabel gender = new JLabel("性别 ：       男");
	JLabel birthday = new JLabel("出生年月 ：       1999.03");
	//JLabel address = new JLabel("籍贯 ：       湖北武汉");
	JLabel phone = new JLabel("电话 ：      xxxx");
	JLabel email = new JLabel("邮箱 ：       xxxxx@163.com");
	JLabel from_school = new JLabel("毕业院校 ：       长江大学");
	//个人信息位置设置
	int x ,y , width , height;
	x=600 ; y=10 ; width = 400 ; height = 40 ;
	name.setBounds(x,y, width, height);
	gender.setBounds(x,y+height, width, height);
	birthday.setBounds(x,y+2*height, width, height);
	phone.setBounds(x,y+3*height, width, height);
	email.setBounds(x,y+4*height, width, height);
	from_school.setBounds(x,y+5*height, width, height);
	//
	ImageIcon icon_name = new ImageIcon("./name_.png");
	JLabel name_=new JLabel("",icon_name,SwingConstants.CENTER);
	name_.setBounds(x-40,y+5, 30, 30);
	p.add(name_);
	
	ImageIcon icon_gender = new ImageIcon("./gender.png");
	JLabel gender_=new JLabel("",icon_gender,SwingConstants.CENTER);
	gender_.setBounds(x-40,y+5+height, 30, 30);
	p.add(gender_);
	
	ImageIcon icon_birthday = new ImageIcon("./calender.png");
	JLabel birthday_=new JLabel("",icon_birthday,SwingConstants.CENTER);
	birthday_.setBounds(x-40,y+5+2*height, 30, 30);
	p.add(birthday_);
	
	ImageIcon icon_phone = new ImageIcon("./phone.png");
	JLabel phone_=new JLabel("",icon_phone,SwingConstants.CENTER);
	phone_.setBounds(x-40,y+5+3*height, 30, 30);
	p.add(phone_);
	
	ImageIcon icon_email = new ImageIcon("./email.png");
	JLabel email_=new JLabel("",icon_email,SwingConstants.CENTER);
	email_.setBounds(x-40,y+5+4*height, 30, 30);
	p.add(email_);
	
	ImageIcon icon_from_school = new ImageIcon("./school.png");
	JLabel 	from_school_=new JLabel("",icon_from_school,SwingConstants.CENTER);
	from_school_.setBounds(x-40,y+5+5*height, 30, 30);
	p.add(	from_school_);
	
	
    name.setFont(font_text);
    gender.setFont(font_text);
    birthday.setFont(font_text);
    //address.setFont(font_text);
    from_school.setFont(font_text);
    phone.setFont(font_text);
    email.setFont(font_text);
    
	p.add(photo);
	p.add(name);
	p.add(gender);
	p.add(birthday);
	p.add(phone);
	p.add(email);
	p.add(from_school);
	
	
	//技能证书
	JLabel ability = new JLabel("技能证书");
	ability.setOpaque(true);//设置为不透明,否则颜色无法显示
	ability.setBackground(Color.gray);
	JLabel language = new JLabel("语言技能：       CET6");
	JLabel profession = new JLabel("专业技能： "    );
	JLabel office = new JLabel("办公技能：    "  );
	//职业证书位置设置
	int x2,y2,width2,height2;
	x2=250 ; y2=280 ; width2 = 700 ; height2 = 35;
	ability.setBounds(x2, y2, width2, height2);
	language.setBounds(x2, y2+height2, width2, height2);
	profession.setBounds(x2, y2+2*height2, width2, height2);
	office.setBounds(x2, y2+3*height2, width2, height2);
	p.add(ability);
	p.add(language);
	p.add(profession);
	p.add(office);
	language.setFont(font_text);
	profession.setFont(font_text);
	office.setFont(font_text);
	ability.setFont(font_text);
	
    
    
    

	//教育背景
	JTextArea major = new JTextArea(7,15);
	JLabel major_title = new JLabel("主修课程 : ");
	major_title.setOpaque(true);//设置为不透明,否则颜色无法显示
	major_title.setBackground(Color.gray);
	String major_text = "C语言 python java ssm";
	major.setLineWrap(true);    //激活自动换行功能  
	major.setEditable(false);    //设置不可编辑
	major.setText(major_text);
	//教育背景位置设置
	int x3,y3,width3,height3;
	x3=250 ; y3=490 ; width3 = 700 ; height3 = 100;
	major_title.setBounds(x3, y3-40, width3, 35);
	major.setBounds(x3, y3, width3, height3);
	p.add(major_title);
	p.add(major);
	major.setFont(font_text);
	major_title.setFont(font_text);

	

    container.add(sp);

    
    jf.setSize(1200,800);
    jf.setVisible(true);
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
	}
	
}
   





