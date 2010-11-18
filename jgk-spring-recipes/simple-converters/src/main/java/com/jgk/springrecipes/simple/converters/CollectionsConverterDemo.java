package com.jgk.springrecipes.simple.converters;

import java.util.List;

public class CollectionsConverterDemo {
	List<String> stringList;
	List<Integer> numberList;
	@Override
	public String toString() {
		return "CollectionsConverterDemo [stringList=" + stringList
				+ ", numberList=" + numberList + "]";
	}
	public List<String> getStringList() {
		return stringList;
	}
	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}
	public List<Integer> getNumberList() {
		return numberList;
	}
	public void setNumberList(List<Integer> numberList) {
		this.numberList = numberList;
	}
}
