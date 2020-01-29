package test;

import org.junit.jupiter.api.BeforeEach;

import list.MyArrayList;

public class TestArrayList extends TestListBase {

	@BeforeEach
	public void init() {
		listInteger = new MyArrayList<>();
		listString = new MyArrayList<>();
	}

}
