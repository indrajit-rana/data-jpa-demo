package com.example.datajpademo.business;

public class SampleBusiness {

	public int doAddition(int[] numbers) {
		int addition = 0;
		for (int number : numbers) {
			addition += number;
		}
		return addition;
	}
}
