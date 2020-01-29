package test;

import org.junit.jupiter.api.BeforeEach;

import list.MyDoubleLinkedList;

public class TestDoubleLinkedList extends TestListBase {
	
	@BeforeEach
	public void init() {
		listInteger = new MyDoubleLinkedList<>();
		listString = new MyDoubleLinkedList<>();
	}

}
