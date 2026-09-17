package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class quoteUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtProjectNo;
	private JTextField txtBudget;
	private JTextField txtHourlyCost;
	private JTextField txtDiscount;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable tableProject;
	private DefaultTableModel tableModel; //必用

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quoteUI frame = new quoteUI();
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
	public quoteUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setToolTipText("專案基本資料");
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5C08\u6848\u57FA\u672C\u8CC7\u6599", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(22, 34, 447, 194);
		contentPane.add(panel_1);
		
		JCheckBox chkUrgent = new JCheckBox("急件");
		chkUrgent.setSelected(true);
		chkUrgent.setBounds(327, 128, 94, 22);
		panel_1.add(chkUrgent);
		
		JLabel lblNewLabel_2 = new JLabel("專案編號");
		lblNewLabel_2.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(23, 30, 66, 23);
		panel_1.add(lblNewLabel_2);
		
		txtProjectNo = new JTextField();
		txtProjectNo.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		txtProjectNo.setBounds(113, 30, 109, 21);
		panel_1.add(txtProjectNo);
		txtProjectNo.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("預算上限");
		lblNewLabel_2_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(23, 63, 66, 23);
		panel_1.add(lblNewLabel_2_1);
		
		txtBudget = new JTextField();
		txtBudget.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		txtBudget.setColumns(10);
		txtBudget.setBounds(113, 67, 109, 21);
		panel_1.add(txtBudget);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("每小時成本");
		lblNewLabel_2_1_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(24, 102, 79, 23);
		panel_1.add(lblNewLabel_2_1_1);
		
		txtHourlyCost = new JTextField();
		txtHourlyCost.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		txtHourlyCost.setColumns(10);
		txtHourlyCost.setBounds(114, 106, 109, 21);
		panel_1.add(txtHourlyCost);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("折扣率(%)");
		lblNewLabel_2_1_1_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBounds(23, 137, 79, 23);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		txtDiscount = new JTextField();
		txtDiscount.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(113, 141, 109, 21);
		panel_1.add(txtDiscount);
		
		JCheckBox chkMaintenance = new JCheckBox("維護費用");
		chkMaintenance.setSelected(true);
		chkMaintenance.setBounds(327, 156, 94, 22);
		panel_1.add(chkMaintenance);
		
		String[] types = {"Web", "AI", "Data"};
		JComboBox cboProjectType = new JComboBox(types);//在建構時直接傳入陣列
		int typeCode = cboProjectType.getSelectedIndex() + 1;//預設是0，所以加1
		quote quote = new quote(typeCode);//new建構子
		
		cboProjectType.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		cboProjectType.setBounds(329, 33, 90, 23);
		panel_1.add(cboProjectType);
		
		JLabel lblNewLabel_2_3 = new JLabel("專案類型");
		lblNewLabel_2_3.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(253, 30, 66, 23);
		panel_1.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("客戶等級");
		lblNewLabel_2_3_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_3_1.setBounds(253, 70, 66, 23);
		panel_1.add(lblNewLabel_2_3_1);
		
		JRadioButton rdoNormal = new JRadioButton("一般");		
		rdoNormal.setBounds(325, 69, 82, 23);
		panel_1.add(rdoNormal);
		
		JRadioButton rdoVip = new JRadioButton("VIP");		
		rdoVip.setBounds(325, 94, 82, 23);
		panel_1.add(rdoVip);
		
		ButtonGroup rdoLevelGroup = new ButtonGroup();
		rdoLevelGroup.add(rdoNormal);
		rdoLevelGroup.add(rdoVip);
		rdoVip.setSelected(true);
		
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("加值項目");
		lblNewLabel_2_3_1_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_3_1_1.setBounds(253, 125, 66, 23);
		panel_1.add(lblNewLabel_2_3_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setToolTipText("工作階段工時");
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5DE5\u4F5C\u968E\u6BB5\u5DE5\u6642", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(501, 34, 363, 245);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("不等長二維陣列");
		lblNewLabel_2_2.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(23, 30, 111, 23);
		panel_1_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("每一列代表一個工作群組，每列以逗號分隔階段工時");
		lblNewLabel_2_2_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		lblNewLabel_2_2_1.setBounds(23, 184, 332, 23);
		panel_1_1.add(lblNewLabel_2_2_1);
		
		JTextArea txtWorkHours = new JTextArea();
		txtWorkHours.setBounds(23, 51, 314, 124);
		panel_1_1.add(txtWorkHours);
		
		JPanel panelReport1 = new JPanel();
		panelReport1.setLayout(null);
		panelReport1.setToolTipText("計算報價結果");
		panelReport1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u8A08\u7B97\u5831\u50F9\u7D50\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelReport1.setBounds(501, 309, 363, 138);
		contentPane.add(panelReport1);		
		
		JTextArea textCalculateResult = new JTextArea();
		textCalculateResult.setEditable(false); // 設為唯讀
		textCalculateResult.setBackground(SystemColor.activeCaption); // 保持背景色
		JScrollPane scrollPane1 = new JScrollPane(textCalculateResult);
		scrollPane1.setBounds(23, 30, 317, 90);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		textCalculateResult.setBounds(50, 70, 450, 200);
		panelReport1.add(scrollPane1);
		
		/*===============================================
		 * EVENT
		 * ==============================================
		 */
		JButton btnCalculCost = new JButton("計算報價");
		btnCalculCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtWorkHours.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(quoteUI.this,"請輸入工時。");
				}	
				
				int projectType = cboProjectType.getSelectedIndex() + 1;
				
				// 2. 文字輸入框轉換為數值
	            String projectNo = txtProjectNo.getText().trim();
	            int hourlyCost = Integer.parseInt(txtHourlyCost.getText().trim());
	            double discountRate = Double.parseDouble(txtDiscount.getText().trim());					 
				
	            // 3. 單選與核取方塊狀態
	            int clientLevel = rdoNormal.isSelected() ? 1 : 2;
	            boolean maintenance = chkMaintenance.isSelected();
	            boolean urgent = chkUrgent.isSelected();
	            
	            // 4. 解析工時
	            quote currentQuote = new quote(projectType);
	            int[][] workHours = currentQuote.parseWorkHours(txtWorkHours.getText());
	            
	            double finalPrice = currentQuote.calculCost(
	                    projectNo,
	                    hourlyCost, 
	                    discountRate,
	                    projectType, 
	                    clientLevel, 
	                    maintenance, 
	                    urgent, 
	                    workHours);
					
	            int budgetLimit =Integer.parseInt(txtBudget.getText().trim());
				textCalculateResult.setText(currentQuote.CalculateResult(finalPrice,budgetLimit));
			}
			
		});
		btnCalculCost.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		btnCalculCost.setBounds(27, 258, 102, 23);
		contentPane.add(btnCalculCost);
		
		JButton btnAddRowToDataTable = new JButton("加入資料表");
		btnAddRowToDataTable.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		btnAddRowToDataTable.setBounds(139, 256, 110, 23);
		contentPane.add(btnAddRowToDataTable);		
		// 為「加入資料表」按鈕新增點擊事件
		btnAddRowToDataTable.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // 1. 抓取文字輸入欄位值
		            String projectNo = txtProjectNo.getText().trim();
		            int budgetLimit = Integer.parseInt(txtBudget.getText().trim());
		            int hourlyCost = Integer.parseInt(txtHourlyCost.getText().trim());
		            double discountRate = Double.parseDouble(txtDiscount.getText().trim());

		            // 2. 抓取選單與選項狀態
		            int projectType = cboProjectType.getSelectedIndex() + 1; // 1=Web, 2=AI, 3=Data
		            int clientLevel = rdoNormal.isSelected() ? 1 : 2;        // 1=一般, 2=VIP
		            boolean maintenance = chkMaintenance.isSelected();
		            boolean urgent = chkUrgent.isSelected();

		            // 3. 建立 quote 物件並解析工時二維陣列
		            quote newQuote = new quote(projectType);
		            int[][] workHours = newQuote.parseWorkHours(txtWorkHours.getText());

		            // 4. 計算費用取得最終報價
		            double finalPrice = newQuote.calculCost(
		                    projectNo,
		                    hourlyCost,
		                    discountRate,
		                    projectType,
		                    clientLevel,
		                    maintenance,
		                    urgent,
		                    workHours);

		            // 5. 組合各欄位字串（順序對應先前設定的 12 個欄位標題）
		            String[] rowData = new String[] {
		                projectNo,
		                String.valueOf(budgetLimit),
		                String.valueOf(hourlyCost),
		                String.valueOf(discountRate),
		                newQuote.getProjectTypeName(),
		                (clientLevel == 2 ? "VIP" : "一般"),
		                urgent ? "是" : "否",
		                maintenance ? "是" : "否",
		                String.valueOf(newQuote.getTotalHours()),
		                String.valueOf((int) newQuote.getLaborCost()),
		                String.valueOf((int) finalPrice),
		                newQuote.getStatus(finalPrice, budgetLimit)
		            };

		            // 6. 將單列資料新增到介面的 JTable 模型中
		            tableModel.addRow(rowData);

		            // 7. 同步記錄到 quote 後端靜態陣列
		            newQuote.addRowToDataTable();

		            JOptionPane.showMessageDialog(quoteUI.this, "資料已成功加入表格！");

		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(quoteUI.this, 
		                "請確認各項數值（預算、成本、折扣率、工時）格式正確且不可為空！", 
		                "格式錯誤", 
		                JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		
		JButton btnMinusRowToDataTable = new JButton("刪除列");
		btnMinusRowToDataTable.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		btnMinusRowToDataTable.setBounds(22, 592, 102, 23);
		contentPane.add(btnMinusRowToDataTable);
		btnMinusRowToDataTable.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // 1. 取得使用者目前選取的列索引（若未選取會回傳 -1）
		        int selectedRow = tableProject.getSelectedRow();

		        // 2. 檢查是否有選取資料列
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, 
		                "請先點選表格中要刪除的資料列！", 
		                "提示", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // 3. 跳出確認視窗（防止誤刪）
		        int confirm = JOptionPane.showConfirmDialog(
		            null, 
		            "確定要刪除第 " + (selectedRow + 1) + " 筆資料嗎？", 
		            "確認刪除", 
		            JOptionPane.YES_NO_OPTION
		        );

		        if (confirm == JOptionPane.YES_OPTION) {
		            // 4. 後端靜態		            
		        	quote.minusRowToDataTable(selectedRow);

		            // 5. 從畫面表格模型中移除該列
		            tableModel.removeRow(selectedRow);

		            JOptionPane.showMessageDialog(null, "資料列已成功刪除！");
		        }
		    }
		});
		
		/*
		 * DefaultTableModel：Swing 提供的預設表格資料模型類別，負責管理表格的欄位名稱、資料列、儲存格數值變更與更新事件。
		 *columnNames：表格的欄位標題（通常為 Object[] 或 String[]，例如 String[] columnNames = {"學號", "姓名", "成績"};）。
		 *0：初始化時表格的起始資料列數（row count）。設為 0 表示建立一個「有欄位標題、但目前沒有任何資料」的空表格。
		 */
		
		//table
		String[] columnNames = {
			    "專案編號", "預算上限", "每小時成本", "折扣率(%)", 
			    "專案類型", "客戶等級", "急件", "維護費用", 
			    "總工時", "勞動成本", "最終報價", "狀態"
			};

			tableModel = new DefaultTableModel(columnNames, 0);
			tableProject = new JTable(tableModel);

			// 關閉欄位壓縮
			tableProject.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			// 設定欄位預設寬度
			for (int i = 0; i < tableProject.getColumnCount(); i++) {
			    tableProject.getColumnModel().getColumn(i).setPreferredWidth(85);
			}

			JScrollPane scrollPaneTable = new JScrollPane(tableProject);
			scrollPaneTable.setBounds(22, 309, 445, 273); // 可適度將寬度稍微拉大一點
			scrollPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

			contentPane.add(scrollPaneTable);		
		
		JPanel panelReport2 = new JPanel();
		panelReport2.setLayout(null);
		panelReport2.setToolTipText("報價結果");
		panelReport2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u7D71\u8A08\u7D50\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelReport2.setBounds(501, 477, 363, 138);
		contentPane.add(panelReport2);
		
		/*
		JTextArea txtgetSummaryResult = new JTextArea();
		txtgetSummaryResult.setEditable(false); // 設為唯讀
		txtgetSummaryResult.setBackground(SystemColor.activeCaption); // 保持背景色
		JScrollPane scrollPane = new JScrollPane(txtgetSummaryResult);
		scrollPane.setBounds(23, 30, 317, 90);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		txtgetSummaryResult.setBounds(25, 20, 313, 100);
		panelReport2.add(scrollPane);
		*/
		JTextArea txtgetSummaryResult = new JTextArea();
		txtgetSummaryResult.setEditable(false);
		txtgetSummaryResult.setBackground(SystemColor.activeCaption);

		JScrollPane scrollPane = new JScrollPane(txtgetSummaryResult);
		scrollPane.setBounds(23, 30, 317, 90);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// 注意：不要對內部的 txtgetSummaryResult 呼叫 setBounds，尺寸全由外層 scrollPane 控制
		panelReport2.add(scrollPane);
		
		JButton btnSummaryResult = new JButton("統計");
		btnSummaryResult.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		btnSummaryResult.setBounds(253, 256, 102, 23);
		contentPane.add(btnSummaryResult);
		btnSummaryResult.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // 呼叫 quote 類別的靜態方法取得統計結果並顯示
		        String summary = quote.getSummaryResult();
		        txtgetSummaryResult.setText(summary);
		    }
		});	
		

		JButton btnClean = new JButton("清除");
		btnClean.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 14));
		btnClean.setBounds(365, 256, 102, 23);
		contentPane.add(btnClean);
		btnClean.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // 1. 清空文字輸入框
		        txtProjectNo.setText("");
		        txtBudget.setText("");
		        txtHourlyCost.setText("");
		        txtDiscount.setText("");
		        txtWorkHours.setText("");

		        // 2. 下拉選單恢復到第一項 (Web)
		        cboProjectType.setSelectedIndex(0);

		        // 3. 單選按鈕恢復預設 (一般)
		        rdoNormal.setSelected(true);

		        // 4. 核取方塊取消勾選 (或依你的需求保留預設勾選)
		        chkUrgent.setSelected(false);
		        chkMaintenance.setSelected(false);

		        // 5. 清空右側計算與統計顯示區域
		        textCalculateResult.setText("");
		        txtgetSummaryResult.setText("");

		        // 6. 將焦點重新放回第一個輸入框，方便使用者重新打字
		        txtProjectNo.requestFocus();
		        
		        tableModel.setRowCount(0); // 清空整張 JTable 資料
		    }
		});

	}
}
