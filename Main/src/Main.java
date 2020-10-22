
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.
import java.awt.event.ActionListener;


class Windows extends Frame
{
	
		Label lb1,lb2,lb3; 
		//задаем параметры окна 
		public Windows() 
		{	
			setSize(200,200);
			setTitle("Calendar");
			setLayout(new FlowLayout());
			lb1 = new Label("Label1");
			lb2 = new Label("Label2");
			lb3 = new Label("Label3");
			bt1 = new Button();
			bt2 = new Button();
			
				add(lb1);
				add(lb2);
				add(lb3);
				add(bt1);
	}
		
}

class main{
	public static void main (String[] args) {
		Windows wind = new Windows ();
		wind.setVisible(true);
		
		wind.setLocation(300, 300);
	}
}