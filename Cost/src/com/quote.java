package com;
import java.util.Arrays;

public class quote {
	private String projectNo; //專案編號
	private int budgetLimit; //客戶預算上限
	private int hourlyCost; //公司每小時成本
	private double discountRate; //折扣率百分比
	private int projectType; //1.web 2.Ai 3.data //前端
	private int clientLevel; //1.一般 2.Vip
	private boolean urgent; //是否急件
	private boolean maintenance; //是否含服務費
	private int[][]  workHours; //個工作群組的階段工時
	private int totalHours; //專案總工時
	private double laborCost; //勞動成本
	private double quotePrice; //折扣前報價
	private double finalPrice; //最終報價
	
	private static int count = 0;
	private static double totalAllFinalPrice = 0;
	private static int availableProjects = 0;
	private static int requiresPrjoects = 0;
	private static int overBudgetProject = 0;
	private static int urgentCount = 0;
	private static double[] priceArray = new double[] {};
	private static String[][] dataTable = new String[0][];
	
	
// ==================================================
// constructor
// ==================================================
	
public quote(int projectType)//專案類型
{
	setProjectType(projectType);
}

public quote(String projectNo, 
		int budgetLimit, 
		int hourlyCost, 
		double discountRate,
		int projcetType,
		int clientLevel,
		boolean urgent,
		boolean maintenance,
		int[][] workHours)
{
	setProjectNo(projectNo);
	setBudgetLimit(budgetLimit);
	setHourlyCost(hourlyCost);
	setDiscountRate(discountRate);
	setProjectType(projcetType);
	setClientLevel(clientLevel);
	setUrgent(urgent);
	segMaintenance(maintenance);
	setWorkHours(workHours);
	
	count++;
}

//===================================================
//method
//===================================================

public String getProjectTypeName() //專案類型
{
	switch(this.projectType)
	{
	case 1:
		return "Web";
	case 2:
		return "AI";
	case 3:
		return "Data";
	default:
		return "位指定";		
	}
}

public int[][] parseWorkHours(String text)
{
	if(text == null || text.trim().isEmpty())
	{
		return new int[0][0];
	}
	
	String[] lines = text.trim().split("\\r?\\n");
	int[][] workHours = new int[lines.length][];
	
	for(int i=0; i<lines.length; i++)
	{
		String line = lines[i].trim();
		if(line.isEmpty())
		{
			workHours[i] = new int[0];
			continue;
		}
		
		String[] tokens = line.split(",");
        workHours[i] = new int[tokens.length];

        for (int j = 0; j < tokens.length; j++) 
        {
            workHours[i][j] = Integer.parseInt(tokens[j].trim());
        }
	}
	return workHours;
}

public double giveProjectType(int projectType)//計算專案加成
{	
	this.projectType = projectType;
	if(projectType == 1)
	{
		this.quotePrice *= 0.15;
	}
	else if(projectType == 2)
	{
		this.quotePrice *= 0.25;
	}
	else if(projectType == 3)
	{
		this.quotePrice *= 0.20;
	}
	return this.quotePrice;
}

public double giveUrgent(boolean urgent)//計算急件處理
{
	if(urgent)
	{
		urgentCount ++;
		this.quotePrice *= 0.12;
	}
	return quotePrice;
}

public double giveMaintenance(boolean maintenance)//計算維護服務
{
	if(maintenance)
	{
		this.maintenance = maintenance;
		this.quotePrice += 18000;		
	}
	return quotePrice;
}

public double calculCost(
			String projectNo,
			int hourlyCost, 
			double discountRate,
			int projectType, 
			int clientLevel, 
			boolean maintenance, 
			boolean urgent , 
			int[][] workHours)//計算finalPrice
{	
	count++;
	
	for(int i=0;i<workHours.length;i++)
	{
		this.workHours = new int [workHours.length][];
		
		for(int j=0;j<workHours[i].length;j++)
		{
			totalHours += workHours[i][j];
			laborCost += workHours[i][j] * hourlyCost;
		}
	}
		
	this.quotePrice = laborCost + giveProjectType(projectType) 
	+ giveMaintenance(maintenance) 
	+ giveUrgent(urgent);
	
	this.finalPrice = this.quotePrice * ((100-discountRate)/100);
	if(clientLevel == 2)
	{
		this.finalPrice *= 0.97; 
	}
	totalAllFinalPrice += finalPrice;
	//priceArray[count] = finalPrice; //error
	// 使用 Arrays.copyOf 讓陣列長度每次自動 +1
	priceArray = Arrays.copyOf(priceArray, priceArray.length + 1);
	priceArray[priceArray.length - 1] = finalPrice; //實際索引值
	return finalPrice;
}
/*
 * 超預算判斷式修正：原邏輯中的 finalPrice < budgetLimit * 0.10 
 * 是筆誤，0.10 代表 10%（預算的十分之一），
 * 應修正為 budgetLimit * 1.10（超出預算 10% 以內才算「需協商」）。
 */

public String getStatus(double finalPrice, int budgetLimit)//狀態
{
	if(finalPrice <= budgetLimit)
	{
		availableProjects ++;
		return "可承接";
	}
	else if(finalPrice > budgetLimit && finalPrice < budgetLimit*1.10)
	{
		requiresPrjoects ++;
		return "需協商";
	}
	else
	{
		overBudgetProject ++;
		return "超出預算";
	}
}

public void addRowToDataTable()//加入資料表
{
	String[] rowData = new String[]
	{
		String.valueOf(this.projectNo),
		String.valueOf(this.budgetLimit),
		String.valueOf(this.hourlyCost),
		String.valueOf(this.discountRate),
		(this.projectType == 1 ? "Web":(this.projectType == 2 ? "AI" : "Data")),
		(this.clientLevel == 2 ? "VIP" : "一般"),
		this.urgent ? "是" : "否",
		this.maintenance ? "是" : "否",
		String.valueOf(this.totalHours),
		String.valueOf((int)this.laborCost),
        String.valueOf((int)this.finalPrice),
        (this.finalPrice <= this.budgetLimit ? "可承接" : ((finalPrice > budgetLimit && finalPrice < budgetLimit*1.10) ? "需協商" : "超出預算"))
	};
	
	/*
	 * 舊 dataTable (長度 2):
	 *	[0] -> 專案 1
	 *	[1] -> 專案 2
		
	 *	執行 Arrays.copyOf(dataTable, 2 + 1) 後：
	 *	新 dataTable (長度 3):
	 *	[0] -> 專案 1
	 *	[1] -> 專案 2
	 *	[2] -> null   <-- 這是剛多生出來的空位！
	 */
	
	// 擴增二維陣列的列數（加 1 列）,Arrays.copyOf(原陣列, 新長度)
    dataTable = Arrays.copyOf(dataTable, dataTable.length + 1);
    // 將新的一筆資料放入最後一列,[dataTable.length - 1]指的是索引位子
    dataTable[dataTable.length - 1] = rowData;
}

public static void minusRowToDataTable(int rowIndex)//刪除選取列
{
	if(dataTable == null || rowIndex < 0 || rowIndex >= dataTable.length)
	{
		return;
	}
	
	String[][] newTable = new String[dataTable.length - 1][];
	System.arraycopy(dataTable, 0, newTable, 0, rowIndex);
	System.arraycopy(dataTable, rowIndex+1, newTable, rowIndex, dataTable.length - rowIndex -1);
	dataTable = newTable;
}
/*
 * 舊陣列 dataTable (長度 4)：
 * 索引 0: [專案 A]  <-- 要保留
 * 索引 1: [專案 B]  <-- 【要刪除】
 * 索引 2: [專案 C]  <-- 要保留
 * 索引 3: [專案 D]  <-- 要保留
 * System.arraycopy(dataTable, 0, newTable, 0, rowIndex);
 * 目的：把位於被刪除列 rowIndex 前面的資料原封不動搬過去。
 * 參數解析（以刪除 rowIndex = 1 為例）：
 * 1. dataTable：從舊陣列拿。
 * 2. 0：從舊陣列索引 0 開始搬。
 * 3. newTable：搬到新陣列。
 * 4. 0：從新陣列索引 0 開始放。
 * 5. rowIndex（值為 1）：總共搬 1 筆（即索引 0 的 [專案 A]）。
 * 此時，新陣列進度
 * newTable:
 * [0] -> [專案 A]
 * [1] -> null
 * [2] -> null
 * System.arraycopy(dataTable, rowIndex+1, newTable, rowIndex, dataTable.length - rowIndex -1);
 * 目的：跳過要被刪除的那一列，把後面的剩餘資料往前遞補。
 * 參數解析（以 rowIndex = 1，總長度 4 為例）：
 * dataTable：從舊陣列拿。
 * rowIndex + 1（1 + 1 = 2）：故意跳過索引 1，直接從舊陣列索引 2（[專案 C]）開始搬。
 * newTable：搬到新陣列。
 * rowIndex（1）：放入新陣列的索引 1（剛好填補前一步驟留下的空位）。
 * dataTable.length - rowIndex - 1（4 - 1 - 1 = 2 筆）：計算後面還剩幾筆要搬（剩下 [專案 C] 與 [專案 D] 共 2 筆）。
 * 搬完後的結果：
 * newTable:
 * [0] -> [專案 A]
 * [1] -> [專案 C]  (往前遞補進來了)
 * [2] -> [專案 D]
 * 舊的 4 筆長度陣列因為不再被使用，Java 的記憶體垃圾回收機制（Garbage Collection）會自動將其銷毀釋放記憶體
 */


public String CalculateResult(double finalPrice, int budgetLimit)//計算報價
{
	String status = getStatus(finalPrice,budgetLimit);
	return  "總工時: "+totalHours+
			"\n人工成本: "+laborCost+
			"\n實際報價: "+finalPrice+
			"\n狀態: "+status;
}

public static double GetmaxPrice(double[] priceArray)//最高報價
{
	double max = 0;
	for(int i=0;i<priceArray.length;i++)
	{
		if(max < priceArray[i])
		{
			max = priceArray[i];
		}
	}

	return max;
}

public static String getSummaryResult()//統計
{
	// 防呆：若目前尚未有任何專案計算，直接返回提示，避免除以 0 出現 Infinity
    if (count == 0) {
        return "尚無任何專案統計資料！";
    }
    
    double avgTotalAllFinalPrice = totalAllFinalPrice / count;
    double max = GetmaxPrice(priceArray);
	
	return  "專案筆數: "+count+"\n"+
			"報價總數: "+totalAllFinalPrice+"\n"+
			"平均報價: "+avgTotalAllFinalPrice+"\n"+
			"可承接專案: "+availableProjects+"\n"+
			"需協商專案"+requiresPrjoects+"\n"+
			"超出預算專案"+overBudgetProject+"\n"+
			"急件專案: "+urgentCount+"\n"+
			"最高報價: "+max;
}

// ==================================================
// setter / getter
// ==================================================

public int getTotalHours() 
{ 
	return totalHours; 
}

public double getLaborCost() 
{ 
	return laborCost; 
}

public double getFinalPrice() 
{ 
	return finalPrice; 
}

public String getProjectNo()
{
	return projectNo;
}

public void setProjectNo(String projectNo)
{
	if(projectNo != " ")
	{
		this.projectNo = projectNo;
	}
}

public int getBudgetLimit()
{
	return budgetLimit;
}

public void setBudgetLimit(int budgetLimit)
{
	if(budgetLimit > 0)
	{
		this.budgetLimit = budgetLimit;
	}
}

public int getHourlyCost()
{
	return hourlyCost;
}

public void setHourlyCost(int hourlyCost)
{
	if(hourlyCost > 0)
	{
		this.hourlyCost = hourlyCost;
	}
}

public double getDiscountRate()
{
	return discountRate;
}

public void setDiscountRate(double discountRate)
{
	if(discountRate > 0)
	{
		this.discountRate = discountRate;
	}
}

public int getProjcetType()
{
	return projectType;
}

//Setter：設定專案類型 (限定只能輸入 1, 2, 3)
public void setProjectType(int projcetType)
{
	if(projectType >= 1 && projectType <= 3)
	{
		this.projectType = projectType;		
	}
	else
	{
		this.projectType = 0;
	}
}

public int getClientLevel()
{
	return clientLevel;
}

public void setClientLevel(int clientLevel)
{
	if(clientLevel > 0)
	{
		this.clientLevel = clientLevel;
	}
}

public boolean getUrgent()
{
	return urgent;
}

public void setUrgent(boolean urgent)
{
	if(urgent)
	{
		this.urgent = urgent;
	}
}

public boolean getMaintenance()
{
	return maintenance;
}

public void segMaintenance(boolean maintenance)
{
	if(maintenance)
	{
		this.maintenance = maintenance;
	}
}

public int[][] getWorkHours(int[][]workHours)
{
	return this.workHours;
}

public void setWorkHours(int[][] workHours)
{ 
	this.workHours = new int[workHours.length][];
	for(int i=0;i>workHours.length;i++)
	{
		this.workHours[i] = new int[workHours[i].length];
		for(int j=0;j>workHours[j].length;j++)
		{
			if(workHours[i][j] > 0)
			{
				this.workHours[i][j] = workHours[i][j];
			}
			else
			{
				this.workHours[i][j] = 0;
			}			
		}
	}
}

}

