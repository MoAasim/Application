

import java.awt.*;
import java.awt.event.*;


public class CalPanel extends Panel implements ActionListener
{
 
 Button[] b,b1;
 Button b2;

 Button home;
 
 functionButton[] functionButton;
 Label label;

 // Panel panel;
 //to deter first input being operator.
 boolean setClear = false;

 Double  number;
 char op;
 boolean checkDot = true;
 
 public  CalPanel()
  {
	

	  label = new Label("0",Label.RIGHT);
	  label.setBackground(new Color(190,0,0));
	  label.setForeground(Color.WHITE);
	  label.setBounds(30,40,275,50); 

	  add(label);
	  
	  b1 = new Button[2];
	  b1[0] = new Button("BACKSPACE");
	  b1[1] = new Button("CLEAR");

	 	  
	  b1[0].addActionListener(this);
	 
	  b1[1].addActionListener(this);
	 
	  
	  b1[0].setBounds(30,100,160,50); 
	  b1[1].setBounds(200,100,105,50); 
	  
	  add(b1[0]);
	  add(b1[1]);
 
	  
	  String s1[] = {"7","8","9","4","5","6","1","2","3","0"};	  
	  String functionString[] = {"+","-","*","/","%","1/x","sqrt","="};
	  
	  functionButton = new functionButton[functionString.length];
	  
	  b = new Button[s1.length];

	  b2 = new Button(".");

	  int w = 30,h = 160;  
	  
	   for(int i=0;i<s1.length;++i)
	   {

			  b[i]=new Button(s1[i]);
			  b[i].setBounds(w,h,50,50);
			  b[i].addActionListener(this);
			  add(b[i]);
			  
			if((i+1)%3 == 0)
			{
				w = 30; 
				h += 50+10;
				continue;
			}
			   w += 50+5;
	   }

	   b2.setBounds(w,h,105,50);
	   b2.setFont(new Font("ariel",Font.PLAIN,30));
	   b2.addActionListener(this);
	   add(b2);

	   
	   functionButton = new functionButton[functionString.length];
	   w = 200;	 
	   h = 160;   
	   
	   for(int k=0, i=0; i<functionString.length/2; ++i)
	   {
		   
		   for(int j=0;j<2; ++j)
		   {
			   functionButton[i] = new functionButton(w,h,50,50,functionString[k],this);
		   
		        w += 50+5; 
				k++;
		
		   }
		   w = 200; 
		   h += 50+10;
		   
	   }
	 
  	 
   	  setLayout(null);

  }
  
  
public void actionPerformed(ActionEvent b)  
{  
	
	String string = ((Button)b.getSource()).getLabel();

	// String string = b.getActionCommand();
		
	//To deter multitple dot events occur consecutively.
	if( string == ".") {

		String s2 = label.getText();

		if(this.checkDot){
			if(s2.lastIndexOf(".") > 0){
				if(s2.lastIndexOf("+")>0 || s2.lastIndexOf("-")>0 || s2.lastIndexOf("*")>0 || s2.lastIndexOf("/")>0 || s2.lastIndexOf("%")>0 ) {
					// do nothing
				}
				else {
					return;
				}
			}
			this.checkDot = false;
		} else {
			return;
		}
	}


	this.setClear = true;
	
	if( string == "BACKSPACE" )
	{

		String s1 = label.getText();

		this.checkDot = true;
		this.setClear = false;
		
		if( s1.length() == 1 && s1 != "0" )
		{
			label.setText("0");
		}
		else if( s1 == "0"){
			//nothing.
		}
		else if( s1.equals("Can not divide by zero")){
			label.setText("0");
		}
		else if( s1.equals("infinity")){
			label.setText("0");
		}
		else if( s1.equals("Can not mod with zero")){
			label.setText("0");
		}
		else
		{
			this.setClear = true;
			s1 = s1.substring( 0 , s1.length()-1 );
			label.setText(s1);
		}
		
		return;
	}
	
	if( string == "CLEAR" )
	{
		this.checkDot = true;
		this.setClear = false;
		label.setText("0");
		return;
	}

	
	
     String temp = this.label.getText(); 
	 if(temp.equals("0"))
	 {
	 	 if(string.equals(".")) {
	 	 	// do nothing
	 	 } 
	 	 else {
		 	temp="";
		 }
	 }
	 else if(temp.equals("Can not divide by zero")){
	 	temp = "";
	 }
	 else if(temp.equals("Can not mod with zero")){
	 	temp = "";
	 }
	 else if(temp.equals("infinity")){
	 	temp = "";
	 }
	 
	
	 temp += string;	
	 this.label.setText(temp);
}


 public static String getString(Double temp)
 {
	 String str = ""+temp;
	 
	 if( str.lastIndexOf(".0") > 0 )
		 str = str.substring(0,str.length()-2);
	
	 return str;
 }
  

}



class functionButton extends Button implements ActionListener
{
	CalPanel c;
	
	public functionButton(int x,int y,int w,int h,String str,CalPanel cal)
	{
		super(str);
		setBounds(x,y,w,h);
		cal.add(this);
		this.c = cal;
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent b)  
    {  
	
	String opText=((functionButton)b.getSource()).getLabel();  
	// or it can be written as 
	// opText = b.getActionCommand();


	//To enable dot ofter an operator is pressed.
	c.checkDot = true;

	Double temp = 0.0;
	Double temp2 = 0.0;

	if(!c.setClear) {
		return;
	}
	
	// c.setClear = false;
	String str = c.label.getText();
	
	if(!opText.equals("=")) {
		 if( str.lastIndexOf("+")>0 || str.lastIndexOf("-")>0 || str.lastIndexOf("*")>0 || str.lastIndexOf("/")>0 || str.lastIndexOf("%")>0 ) {
		 	c.setClear = true;
		 	return;
		 }
		 temp=Double.parseDouble(c.label.getText());
		 c.setClear = false;
	}
    else
    {
    	c.setClear = true;
    	if( !( str.lastIndexOf("+")>0 || str.lastIndexOf("-")>0 || str.lastIndexOf("*")>0 || str.lastIndexOf("/")>0 || str.lastIndexOf("%")>0 ) ){
    		return;
    	}

    	if( c.op == '=' ){
    		return;
    	}

       	String tStr = "";
    	String str2 = "";

    	for(int i=(str.length()-1); i>=0; i--){
    		if(str.charAt(i)!=c.op){
    			tStr += str.charAt(i);
    			continue;
    		}

    		// To reverse String.
    		for( i=(tStr.length()-1); i>=0; i--){
    			str2 += tStr.charAt(i);
    		}
    		break;
    	}

    	temp2 = Double.parseDouble(str2);
    }
	
	if( opText == "1/x")
	{
		c.setClear = true;
		try
		{
		temp = 1/(Double)temp;
		}catch(ArithmeticException e)
		{
			c.label.setText("Divide by 0");
		}
		
		c.label.setText(CalPanel.getString(temp));
		return;
	}
	if( opText.equals("sqrt"))
	{	
		 c.setClear = true;
		 temp = Math.sqrt(temp);
		 String s = ""+temp;
		 if(s.lastIndexOf(".0")>0)
		 {
			s= s.substring(0,s.length()-2);
		 }
			
		c.label.setText(s);	
		return;

	}
	
	if(!opText.equals("="))
	{
		c.number = temp;
		c.label.setText(c.label.getText()+opText);
		c.op = opText.charAt(0);
		return;
	}
	

	switch(c.op)
	{
		case '+':
			temp2=c.number+temp2;
			break;
			
		case '-':
			temp2=c.number-temp2;
			break;
			
		case '*':
			temp2=c.number*temp2;
			break;
			
		case '/':

			if( temp2 == 0)
			{
				c.label.setText("Can not divide by zero");
				return;
			}

			temp2=c.number/temp2;

			break;
			
		case '%':
			
			if( temp2 == 0 ){
				c.label.setText("Can not mod with zero");
				return;
			}
			
			temp2 = c.number%temp2;
			
			break;
	}

	if (opText.equals("=")){
		c.op = opText.charAt(0);
	}
	
	c.label.setText(CalPanel.getString(temp2));
	
   }  
}


