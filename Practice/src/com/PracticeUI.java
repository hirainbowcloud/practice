package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.util.List;

public class PracticeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField milkTea;
	private JTextField blackTea;
	private JTextField coffee;
	private JTextField oolongTea;
	private Order o = new Order();
	private int count = 0;
	private List<Order> orderList = new java.util.ArrayList<>();
	private boolean flag = false;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PracticeUI frame = new PracticeUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PracticeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("訂單明細");
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Step2.\u8A02\u55AE\u660E\u7D30", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(364, 54, 295, 313);
		contentPane.add(panel_2);		

		JLabel lblNewLabel_1 = new JLabel("奶茶");
		lblNewLabel_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 38, 45, 21);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("紅茶");
		lblNewLabel_1_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(27, 73, 45, 21);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("咖啡");
		lblNewLabel_1_1_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(27, 107, 45, 21);
		panel_2.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("烏龍茶");
		lblNewLabel_1_1_2.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(27, 144, 45, 21);
		panel_2.add(lblNewLabel_1_1_2);
		
		JLabel No = new JLabel("");
		No.setForeground(new Color(0, 128, 255));
		No.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 18));
		No.setBounds(198, 245, 77, 58);
		panel_2.add(No);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setToolTipText("訂單輸入");
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Step1.\u8F38\u5165\u8A02\u55AE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(42, 56, 285, 313);
		contentPane.add(panel_1);

		JTextArea getDrink = new JTextArea();
		getDrink.setText("好喝奶茶12杯和很讚紅茶20杯");
		getDrink.setEditable(true);
		getDrink.setLineWrap(true);
		getDrink.setWrapStyleWord(true); 
		getDrink.setBounds(23, 96, 235, 167);
		
		panel_1.add(getDrink);

		JCheckBox member = new JCheckBox("會員");
		member.setSelected(true);
		member.setBounds(25, 273, 94, 22);
		panel_1.add(member);

		JLabel lblNewLabel_2 = new JLabel("請輸入文字點餐，範例如下:");
		lblNewLabel_2.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(23, 30, 235, 23);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("我想要很好喝的紅茶20杯和15杯甜蜜蜜奶茶");
		lblNewLabel.setBounds(23, 63, 253, 23);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 13));
		
		JLabel total = new JLabel("");
		total.setForeground(new Color(0, 128, 255));
		total.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 18));
		total.setBounds(364, 384, 177, 42);
		contentPane.add(total);
		
		JButton sendBtn = new JButton("送出");
		sendBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// 1. 清空上一次的文字欄位
		        milkTea.setText("");
		        blackTea.setText("");
		        coffee.setText("");
		        oolongTea.setText("");
		        total.setText("");
		        orderList.clear();

		        String content = getDrink.getText();		        
		        // 💡 修正後的關鍵 Regex：
		        // 扁平化平行結構 A|B。且中括號排除 \\d（數字）與「和」，避免吞掉下一組點餐
		        Pattern p = Pattern.compile("(\\d+)杯([^\\s,、和\\d]*?(?:奶茶|紅茶|咖啡|烏龍茶))|([^\\s,、和\\d]*?(?:奶茶|紅茶|咖啡|烏龍茶))(\\d+)杯");
		        Matcher m = p.matcher(content);
		        
		        // 新增累加變數，防止多個品項計算時金額被蓋掉，或重複品項被覆寫		      
		        int countMilk = 0;
		        int countBlack = 0;
		        int countCoffee = 0;
		        int countOolong = 0;
		        boolean hasMatch = false;	
		        int quantity = 0;
		        double grandTotal= 0.0;

		        while (m.find()) {
		        	
		            int num = 0;
		            String rawTitle = "";
		            String title = "";		            
		            
		            // 判斷是「數量在前」還是「數量在後」
		            // 修正後：A方案成功時 group(1)有值；B方案成功時 group(4)有值
		            if (m.group(1) != null) { 
		                num = Integer.parseInt(m.group(1));
		                rawTitle = m.group(2);
		            } else if (m.group(4) != null) {
		                num = Integer.parseInt(m.group(4));
		                rawTitle = m.group(3);
		            }

		            if (num <= 0 || rawTitle == null || rawTitle.isEmpty()) {
		                continue;
		            }

		            // 💡 關鍵步驟：利用 contains 找出核心品項，並將數量「累加」
		            
		            if (rawTitle.contains("奶茶")) { title = "奶茶"; countMilk += num; }
		            else if (rawTitle.contains("紅茶")) { title = "紅茶"; countBlack += num; }
		            else if (rawTitle.contains("咖啡")) { title = "咖啡"; countCoffee += num; }
		            else if (rawTitle.contains("烏龍茶")) { title = "烏龍茶"; countOolong += num; }
		            
		            if (!title.isEmpty()) {
		                hasMatch = true;
		            }
		           
		            String orderName = "";
					int orderPrice = 0;					
					
		            switch (title) {
					case "奶茶":
						milkTea.setText(String.valueOf(countMilk));
						orderName = "奶茶";
						orderPrice = 50;
						quantity = countMilk;
						flag = true;
						milkTea.setEditable(flag);
						break;

					case "紅茶":
						blackTea.setText(String.valueOf(countBlack));
						orderName = "紅茶";
						orderPrice = 60;
						quantity = countBlack;
						flag = true;
						blackTea.setEditable(flag);
						break;

					case "咖啡":
						coffee.setText(String.valueOf(countCoffee));
						orderName = "咖啡";
						orderPrice = 70;
						quantity = countCoffee;
						flag = true;
						coffee.setEditable(flag);
						break;

					case "烏龍茶":
						oolongTea.setText(String.valueOf(countOolong));
						orderName = "烏龍茶";
						orderPrice = 55;
						quantity = countOolong;
						flag = true;
						oolongTea.setEditable(flag);
						break;
					default:
						total.setText("品名錯誤");
						break;
					}					
					
					if(!orderName.isEmpty() && quantity > 0)
					{						
						// 💡 每一次迴圈，都 new 一個全新的獨立物件 item
					    Order item = new Order(orderName, orderPrice, quantity, member.isSelected());
					    			 
					    item.total = item.getData(orderName, orderPrice, quantity); 
					    
					    grandTotal += item.getTotal(item.total);	
					    				    					    
					    orderList.add(item);					    				
					}
		        }	
		        
		        if (!orderList.isEmpty()) {
		            o.member = member.isSelected();
		            total.setText("$NT " + grandTotal);
		        }
		    }
		});
		sendBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		sendBtn.setBounds(171, 272, 87, 23);
		panel_1.add(sendBtn);	
		
		JButton printBtn = new JButton("印出單據");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 // 💡 檢查 1：如果清單是空的，提示使用者並關閉
		        if (orderList == null || orderList.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "目前沒有點餐紀錄，無法列印！", "提示", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
				StringBuilder sb = new StringBuilder();
		        
		        sb.append("單號: ").append(Order.count).append("\n");
		        sb.append("============================\n");
		        
		        // 遍歷我們在 sendBtn 存進去的所有飲料物件
		        for (Order order : orderList) {
		            sb.append("品名: ").append(order.name).append("\n")
		              .append("單價: ").append(order.price).append(" 元\n")
		              .append("數量: ").append(order.quantity).append(" 杯\n")
		              .append("小計: ").append(order.total).append(" 元\n")
		              .append("會員折抵: ").append(order.member ? "享 8 折" : "無折扣").append("\n");
		            sb.append("----------------------------\n");
		        }
		        
		        // 加上最下方的總計金額 (直接抓取介面上算好的總額，包含折抵)
		        sb.append("總計金額：").append(total.getText()).append("\n");

		        // 將組合好的完美文字丟給 JTextArea
		        String orderDetail = sb.toString();
		        JTextArea textArea = new JTextArea(orderDetail);
		        textArea.setEditable(false); 
		        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
		        
		        // 彈出對話框顯示訊息
		        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "訂單明細", JOptionPane.INFORMATION_MESSAGE);
		        
		        // 執行列印
		        try {
		            boolean complete = textArea.print(
		                new java.text.MessageFormat("--- 訂單收據 ---"), 
		                new java.text.MessageFormat("頁碼 {0}")
		            );
		            if (complete) {
		                JOptionPane.showMessageDialog(null, "列印完成！", "提示", JOptionPane.INFORMATION_MESSAGE);
		            }
		        } catch (java.awt.print.PrinterException ex) {
		            JOptionPane.showMessageDialog(null, "列印失敗: " + ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
			}
		});
		printBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		printBtn.setBounds(551, 403, 116, 23);
		contentPane.add(printBtn);

		JButton cleanBtn = new JButton("清除");
		cleanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDrink.setText("");
				milkTea.setText("");
				blackTea.setText("");
				coffee.setText("");
				oolongTea.setText("");				
				new Order("", 0, 0, member.isSelected());
				total.setText("");
				milkTea.setEditable(false);
				blackTea.setEditable(false);
				coffee.setEditable(false);
				oolongTea.setEditable(false);
			}
		});
		cleanBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		cleanBtn.setBounds(93, 182, 87, 23);
		panel_2.add(cleanBtn);
		
		JButton getNumBtn = new JButton("取號碼");
		getNumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				No.setText(Order.showOrderNumber());
				milkTea.setEditable(false);
				blackTea.setEditable(false);
				coffee.setEditable(false);
				milkTea.setEditable(false);
				oolongTea.setEditable(false);
			}
		});
		getNumBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		getNumBtn.setBounds(191, 182, 87, 23);
		panel_2.add(getNumBtn);
		
		milkTea = new JTextField();
		milkTea.setBounds(131, 37, 96, 21);
		panel_2.add(milkTea);
		milkTea.setColumns(10);
		milkTea.setEditable(flag);
		
		blackTea = new JTextField();
		blackTea.setColumns(10);
		blackTea.setBounds(132, 73, 96, 21);
		panel_2.add(blackTea);
		blackTea.setEditable(flag);
		
		coffee = new JTextField();
		coffee.setColumns(10);
		coffee.setBounds(132, 106, 96, 21);
		coffee.setEditable(flag);
		panel_2.add(coffee);
		
		oolongTea = new JTextField();
		oolongTea.setColumns(10);
		oolongTea.setBounds(131, 143, 96, 21);
		oolongTea.setEditable(flag);
		panel_2.add(oolongTea);
		
		JButton addBtn_milk = new JButton("+");
		addBtn_milk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
		        int current = 0;
		        String text = milkTea.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current++; 
		        milkTea.setText(String.valueOf(current));  
		     
		        double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText())); 
		        
		        double sum = 0.0;
		        sum += milk;
		        if (blackTea.isEditable() == true) 
		        {
		        		double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        		sum += black;
		        }
		        else if(coffee.isEditable() == true)
		        {
		        		double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText()));
		        		sum += cf;
		        }
		        else if(oolongTea.isEditable() == true)
		        {
		        		double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        		sum += oTea;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		addBtn_milk.setBounds(231, 36, 47, 23);
		panel_2.add(addBtn_milk);

		JButton minusBtn_milk = new JButton("-");
		minusBtn_milk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = milkTea.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current--; 
		        milkTea.setText(String.valueOf(current));                		        
		        double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText())); 
		        double sum = 0.0;
		        
		        sum += milk;
		        if (blackTea.isEditable() == true) 
		        {
		        		double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        		sum += black;
		        }
		        else if(coffee.isEditable() == true)
		        {
		        		double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText()));
		        		sum += cf;
		        }
		        else if(oolongTea.isEditable() == true)
		        {
		        		double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        		sum += oTea;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		
		minusBtn_milk.setBounds(80, 36, 47, 23);
		panel_2.add(minusBtn_milk);

		JButton minusBtn_black = new JButton("-");
		minusBtn_black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = blackTea.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current--; 
		        blackTea.setText(String.valueOf(current));                		        
		        double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        
		        	double sum = 0.0;		        
		        sum += black;
		        if (milkTea.isEditable() == true) 
		        {
		        		double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText()));
		        		sum += milk;
		        }
		        else if(coffee.isEditable() == true)
		        {
		        		double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText()));
		        		sum += cf;
		        }
		        else if(oolongTea.isEditable() == true)
		        {
		        		double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        		sum += oTea;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		minusBtn_black.setBounds(81, 72, 47, 23);
		panel_2.add(minusBtn_black);		

		JButton addBtn_black = new JButton("+");
		addBtn_black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = blackTea.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current++; 
		        blackTea.setText(String.valueOf(current));                		        
		        double black = o.getData("紅茶", 50, Integer.parseInt(blackTea.getText())); 
		     	double sum = 0.0;		        
		        sum += black;
		        if (milkTea.isEditable() == true) 
		        {
		        		double milk = o.getData("奶茶", 60, Integer.parseInt(milkTea.getText()));
		        		sum += milk;
		        }
		        else if(coffee.isEditable() == true)
		        {
		        		double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText()));
		        		sum += cf;
		        }
		        else if(oolongTea.isEditable() == true)
		        {
		        		double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        		sum += oTea;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		addBtn_black.setBounds(232, 72, 47, 23);
		panel_2.add(addBtn_black);			
		
		JButton minusBtn_coffee = new JButton("-");
		minusBtn_coffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = coffee.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current--; 
		        coffee.setText(String.valueOf(current));                		        
		        double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText())); 
		     	double sum = 0.0;		        
		        sum += cf;
		        if (milkTea.isEditable() == true) 
		        {
		        		double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText()));
		        		sum += milk;
		        }
		        else if(blackTea.isEditable() == true)
		        {
		        		double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        		sum += black;
		        }
		        else if(oolongTea.isEditable() == true)
		        {
		        		double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        		sum += oTea;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		minusBtn_coffee.setBounds(81, 105, 47, 23);
		panel_2.add(minusBtn_coffee);


		JButton addBtn_coffee = new JButton("+");
		addBtn_coffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = coffee.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current++; 
		        coffee.setText(String.valueOf(current));                		        
		        double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText())); 
		        double sum = 0.0;		        
		        sum += cf;
		        if (milkTea.isEditable() == true) 
		        {
		        		double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText()));
		        		sum += milk;
		        }
		        else if(blackTea.isEditable() == true)
		        {
		        		double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        		sum += black;
		        }
		        else if(oolongTea.isEditable() == true)
		        {
		        		double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        		sum += oTea;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		addBtn_coffee.setBounds(232, 105, 47, 23);
		panel_2.add(addBtn_coffee);
		
		JButton minusBtn_oolong = new JButton("-");
		minusBtn_oolong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = oolongTea.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current--; 
		        oolongTea.setText(String.valueOf(current));                		        
		        double oTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText())); 
		        
		        double sum = 0.0;		        
		        sum += oTea;
		        if (milkTea.isEditable() == true) 
		        {
		        		double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText()));
		        		sum += milk;
		        }
		        else if(blackTea.isEditable() == true)
		        {
		        		double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        		sum += black;
		        }
		        else if(coffee.isEditable() == true)
		        {
		        		double cf = o.getData("咖啡", 75, Integer.parseInt(coffee.getText()));
		        		sum += cf;
		        }
		        
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		minusBtn_oolong.setBounds(80, 142, 47, 23);
		panel_2.add(minusBtn_oolong);		

		JButton addBtn_oolong = new JButton("+");
		addBtn_oolong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

		        int current = 0;
		        String text = oolongTea.getText();
		        if (text != null && !text.trim().isEmpty()) {
		            try {
		                current = Integer.parseInt(text.trim());
		            } catch (NumberFormatException ex) {
		                current = 0; // 防止非數字
		            }
		        }		        	        
		        current++; 
		        oolongTea.setText(String.valueOf(current));                		        
		        double olTea = o.getData("烏龍茶", 55, Integer.parseInt(oolongTea.getText()));
		        double sum = 0.0;		        
		        sum += olTea;
		        if (milkTea.isEditable() == true) 
		        {
		        		double milk = o.getData("奶茶", 50, Integer.parseInt(milkTea.getText()));
		        		sum += milk;
		        }
		        else if(blackTea.isEditable() == true)
		        {
		        		double black = o.getData("紅茶", 60, Integer.parseInt(blackTea.getText()));
		        		sum += black;
		        }
		        else if(coffee.isEditable() == true)
		        {
		        		double cf = o.getData("咖啡", 70, Integer.parseInt(coffee.getText()));
		        		sum += cf;
		        }
		        total.setText("$NT " + o.getTotal(sum));   				
			}
		});
		addBtn_oolong.setBounds(231, 142, 47, 23);
		panel_2.add(addBtn_oolong);

	}
}