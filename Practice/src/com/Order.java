package com;

import java.util.List;

public class Order {
	public String name;
	public int price;
	public int quantity;
	public boolean member;
	public double total;	
	public List<Order> orderList = new java.util.ArrayList<>();
	
	public static int count = 0;
	
	public Order()
	{
		
	}
	
	public Order(String name, int price, int quantity, boolean member) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.member = member;
		
	}
	
	public double getData(String name,  int price, int quantity)
	{			
		total = price * quantity;
		return total;
	}
	
	public double getTotal(double total)
	{	
		if(member)
		{
			total *= 0.8;
		}

		return  total;
	}
	
	public static String showOrderNumber() 
	{
		count++;
        return 
                "No." + count;              
    }	
	
	/*
	String showOrderDetail()
	{
				
		return  "No." + count+"\n"+
				"品名:"+ this.name+"\n"+
				"單價:"+ this.price+"\n"+
				"數量:"+ this.quantity+"\n"+
				"金額:"+ this.total+"\n";
	}
	*/

}