package com.jgk.springrecipes.simple.converters;

import java.util.List;
import java.util.Map;

public class CollectionsConverterDemo {
	List<String> stringList;
	List<Integer> numberList;
	Map<String,Integer> nameAgeMap;

	@Override
	public String toString() {
		return "CollectionsConverterDemo [stringList=" + stringList
				+ ", numberList=" + numberList + ", nameAgeMap=" + nameAgeMap
				+ "]";
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
	public Map<String, Integer> getNameAgeMap() {
		return nameAgeMap;
	}
	public void setNameAgeMap(Map<String, Integer> nameAgeMap) {
		this.nameAgeMap = nameAgeMap;
	}
	
}
