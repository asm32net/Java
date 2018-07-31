class Person{
	String name;
	int age;
	char sex;
	public void eat(){
		System.out.println("³Ô");
	}

	public Person(String name, int age, char sex){
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
}

public class classDemo01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person("Ò¹»ª", 20, 'ÄÐ');
		p.eat();
		System.out.println(p);
	}

}
