class Student {
	String name;
	int Num;
	public Student(String name, int Num) {
		this.name = name;
		this.Num = Num;
	}
	public Student() {
		this.name = "彭磊";
		this.Num = 2017;
	}
}

/* Object.equals和==区别
    比较运算符“==” 判断的是这两个对象是否同一（即对象地址是否相同：是不是同一个对象）
    Object中equals函数的函数体:
        public boolean equals(Object obj) {
            return (this == obj);
        }
    String类中已经重写了Object类的equals方法，可以判别两个不同字符串对象内容是否相同
    故，非字符串对象：Object.equals等价于==
*/

public class EqualsTest {
	public static void main(String[] args) {
		String s1, s2, s3 = "abc", s4 = "abc";
		s1 = new String("abc");
		s2 = new String("abc");
		System.out.println(s3 == s4);
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));

		System.out.println("--------------");

		Student Stu1 = new Student();
		Student Stu2 = Stu1;
		Student Stu3 = new Student("张三", 2018);
		Student Stu4 = new Student("张三", 2018);
		System.out.println(Stu1 == Stu2);
		System.out.println(Stu1.equals(Stu2));
		System.out.println(Stu3 == Stu4);
		System.out.println(Stu3.equals(Stu4));
        
		System.out.println("--------------");

		int n = 10;
		System.out.println(n == 10);
		// System.out.println(n.equals(10)); // Error: the primitive type
	}
}
// 运行结果：
// true
// false
// true
//--------------
// true
// true
// false
// false
//--------------
// true
