package final_test;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.*;

import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  

public class Login extends JFrame{
	public JButton ok = new JButton();
	public Login() {
		JFrame jf = new JFrame("登录");
		Container container = jf.getContentPane();
		JPanel p = new JPanel();//总的Panel布局
		p.setLayout(null);
		
		JLabel account = new JLabel("账户：");
		account.setBounds(20, 40, 50, 50);
		p.add(account);
		
		JTextField account_text = new JTextField();
		account_text.setBounds(80, 55, 220, 30);
		p.add(account_text);
		
		JLabel passwd = new JLabel("密码：");
		passwd.setBounds(20, 80, 50, 50);
		p.add(passwd);	
		
		
		JPasswordField passwd_text = new JPasswordField();
		passwd_text.setBounds(80, 95, 220, 30);
		p.add(passwd_text);

		ImageIcon img = new ImageIcon("./login.png");
		
		ok.setIcon(img);//添加照片到按钮
		ok.setBounds(90, 150, 200, 50);


		p.add(ok);
		
		container.add(p);

	    jf.setSize(400,300);
	    jf.setVisible(true);
	    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		 ok.addMouseListener(new MouseAdapter() {  //添加鼠标监听
	         public void mouseClicked(MouseEvent e) {  
	        	 //JDialog error = new JDialog();
	             if(e.getButton()==e.BUTTON1){  //左键
	             String account_ = account_text.getText();
	        	 String passwd_ = passwd_text.getText();
	            	 if(account_.equals("201701234") && passwd_.equals("201701234")) {
	            		 new final_test.GUI();
	            	 }
	             }     
	             }  
	     }); 
	}	
}



 
