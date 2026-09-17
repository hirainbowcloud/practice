package com;

public class Test {
	 public static void main(String[] args) {
	 byte b = 10;
	 int x = 3;
	 b += x;
	 x = b++ + ++b;
	 System.out.println(b + ":" + x);
	 }
	}