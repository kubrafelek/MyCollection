package test;

import org.junit.jupiter.api.BeforeEach;

import list.MyLinkedList;

public class TestLinkedList extends TestListBase {

	@BeforeEach
	public void init() {
		listInteger = new MyLinkedList<>();
		listString = new MyLinkedList<>();
	}

}
