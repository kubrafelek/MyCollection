package reflection;

import java.lang.reflect.Method;

import list.MyArrayList;
import list.MyLinkedList;

public class ReflectionDeneme {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Method[] methods = MyArrayList.class.getMethods();

		for(Method method : methods){
		    System.out.println("method = " + method.getName() );
		}
		
		@SuppressWarnings({ "rawtypes", "deprecation" })
		MyLinkedList instance = (MyLinkedList)Class.forName("list.MyLinkedList").newInstance();
		instance.addLast("merhaba");
		instance.addLast(3);
		
		System.out.println(instance);

	}

}
