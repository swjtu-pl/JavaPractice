# 类(class)的面向对象特性
 **封装、继承、多态** 
## 类的继承规则(extends)  
- 1. Java不支持多继承，支持多重继承  
- 2. Java提供接口  
### 子类继承的成员变量

**1. 子类继承超类的成员变量**  
> `public`、`protected` 成员变量  
> 同一个包中超类的默认成员变量 

**2. 子类隐藏超类成员变量**  
> 如果子类成员变量名与超类相同，则称子类覆盖超类成员，隐藏了超类的成员  
> 使用`super.成员名`引用超类中被隐藏的成员变量

**3. 子类不能继承超类的成员变量**  
> `private`成员变量  
> 如果希望超类的成员变量不被子类访问，就声明为私有的，充分体现了面向对象编程的封装原则

### 子类继承的成员方法

- 1. 子类可继承超类的所有`public`、`protected`成员方法；
- 2. 子类可继承同一个包中超类的默认成员方法；
- 3. 如果子类成员方法与超类相同，称子类覆盖超类成员方法，即子类重新定义超类的同名方法；
- 4. 子类不能继承超类的`private`成员方法，不能继承超类的构造方法，但可以调用超类的构造方法，即`super(参数列表);`。

## 多态

### 重载和覆盖
| 途径 | 位置 | 方式 | 要求 |
| :---------: | :---------: | :--------: | :--------: |
| 方法重载 | 一个类的内部 | 同名方法共存 | 同名，参数(类型、顺序、个数)不同 |
| 方法覆盖 | 子类与超类  | 同名方法共存 | 同名、参数(类型、顺序、个数)相同、返回值相同 |

### 子类重写超类的方法(覆盖)
- 1. 子类不能覆盖超类中的`final`或`static`方法  
- 2. 超类的`abstract`方法在子类中必须覆盖  
- 3. 子类实现覆盖时：由`super`调用超类的同名方法，即`super.方法名`  
- 4. 子类覆盖超类的成员方法，必须满足同名、返回值相同、参数相同。如果子类的一个成员方法只是与超类的成员方法同名，而不是完全相同的首部，则称为重载（子类有两个同名方法）。  
- 5. 子类重写超类的方法的访问修饰符，不能低于超类的方法访问权限。例如：如果超类的一个方法被声明为`public`，那么在子类中重写该方法就不能声明为`protected`。  
```java
class C {
	public void test1(String str) {
		System.out.println("父类方法test1");
	}
	public void test2(String str) {
		System.out.println("父类方法test2");
	}
}
class D extends C {
	public void test1(String str) { // test1为覆盖
		System.out.println("子类方法test1");
	}
	public boolean test2(String str) { // test2报错，相当于类D有两个test2，并且函数名和参数均相同，无法区分。
		System.out.println("子类方法test2");
		return true;
	}
}
```

```java
class A {
	int x = 1;// 被隐藏
	void print() {// 被覆盖
		System.out.println("这里是父类方法，x=" + x);// 父类A的方法中访问的变量必然是A类或A的父类的，不可能访问B类的。
		m();// 父类A的方法中调用的实例方法m()是子类B的，由于发生了覆盖
	}
	void m() {// 被覆盖
		System.out.println("这里是父类的实例方法m()");
	}
	static void m2() {// 被隐藏
		System.out.println("这里是父类的静态方法m2()");
	}
}
class B extends A {
	int x = 2;
	void print() {
		System.out.println("这里是子类方法，x=" + x);// 子类方法访问的变量是子类对象的（当然条件是子类中声明了这个变量）
		System.out.println("这里是子类方法，super.x=" + super.x);// super.x是父类对象的
		super.print();// 调用父类的print()方法
		m();// 调用本对象的m()方法
	}
	void m() {
		System.out.println("这里是子类的实例方法m()");
	}
	static void m2() {
		System.out.println("这里是子类的静态方法m2()");
	}
}
public class SuperSonTest {
	public static void main(String[] s) {
		A p1 = new B();
		System.out.println(p1.x);// 通过引用变量p来访问变量或静态方法，要看p的声明类型。所以x是A类的。
		p1.m2();// 同上。静态方法m2()是A类的。
		p1.print();// 通过引用变量p来访问实例方法，要看p指向的对象的实际类型。由于覆盖，调用的print()方法是子类的。
	}
}
```
运行结果：（`super.print()`调用父类的`print()`方法时，父类A调用的方法`m()`是子类B的，因为子类的`m()`方法覆盖了父类的`m()`方法）
```
1
这里是父类的静态方法m2()
这里是子类方法，x=2
这里是子类方法，super.x=1
这里是父类方法，x=1
这里是子类的实例方法m()
这里是子类的实例方法m()
```

### 子类构造方法

- 1. 子类的对象也是超类的对象，反之不成立  
- 2. 如果定义类的时候不显式声明一个构造方法，Java会自动创建一个不带参数的默认构造方法；只要在类中定义了构造方法，无论带参数与否，Java运行时系统不会再自动生成默认构造方法  
- 3. 子类构造方法在对新添加的成员变量执行初始化之前，都将显式地（通过`super`）或隐式地（调用超类的默认构造方法或无参构造方法）调用其直接超类的构造方法  
- 4. 使用`super(参数列表);`显式调用超类的构造方法时，`super(参数列表);`必须位于子类构造方法的首行  

```java
class FatherClass {
	String name = "FATHER";
	FatherClass() {
		whoAmI();
		tellName(name);
	}
	void whoAmI() {
		System.out.println("Father says, I am " + name);
	}
	void tellName(String name) {
		System.out.println("Father's name is " + name);
	}
}
class SonClass extends FatherClass {
	String name = "SON";
	SonClass() { // 调用FatherClass()父类无参构造方法时，可不写super();
		whoAmI();
		tellName(name);
	}
	void whoAmI() {
		System.out.println("Son says, I am " + this.name);
		// 执行父类构造方法时，this.name(Son的变量)还没有初始化
	}
	void tellName(String name) {
		System.out.println("Son's name is " + name);
	}
}
public class Demo{
	public static void main(String[] args) {
		FatherClass f = new FatherClass();
		SonClass s = new SonClass();
	}
}
```
运行结果：（执行父类构造方法时，`this.name`还没有初始化，等于`null`）
```
Father says, I am FATHER
Father's name is FATHER
Son says, I am null
Son's name is FATHER
Son says, I am SON
Son's name is SON
```

### 向上转型 向下转型

| 对象的类型转换 | 引用类型 | 转换方式 | 举例 |
|---------|--------------|---------|---------|
|  向上转型  | 超类对象指向子类对象实例 | 自动转型   | Person p=new Student(); |
|  向下转型  | 子类对象指向超类对象实例 | 强制类型转换 | Person p=new Person(); Student s=(Student)p; |

- 1. 只有有继承关系的对象才能进行向上向下转型  
- 2. 类型转换只是对象的引用，对象本身并没有转型或转换  
- 3. 超类对象名指向子类对象实例，即向上转型的子类对象，只能调用与超类同名的方法(多态)，不能调用子类新增加的成员  

### 静态代码块、非静态代码块

- 1. 静态代码块格式`static{ ...... }`  
- 2. 非静态代码块格式`{ ...... }`  
- 3. 静态代码块执行优先级高于非静态的初始化块，在类初始化的时候执行一次，执行完成便销毁，可用于初始化类变量，即static修饰的数据成员。  
- 4. 每个对象生成时都会被执行一次，可用于初始化类的实例变量；非静态初始化块在构造方法执行时，在构造方法代码执行前运行  
- 5. `静态代码块(仅执行一次)--->非静态代码块--->构造方法`  

```java
class SuperClass { // 超类
	static int count; // 静态成员变量
	static { // 静态代码块
		System.out.println("超类中静态代码块:" + count);
	}
	{ // 非静态代码块
		System.out.println("超类中非静态代码块:" + count);
	}
	public SuperClass() { // 构造方法
		count++;
		System.out.println("超类类中构造方法:" + count);
	}
	public static void test() { // 静态成员方法
		System.out.println("超类中静态方法:" + count);
	}
}
class SonClass extends SuperClass { // 子类
	static int count; // 静态成员变量
	static { // 静态代码块
		System.out.println("子类中静态代码块:" + count);
	}
	{ // 非静态代码块
		System.out.println("子类中非静态代码块:" + count);
	}
	public SonClass() { // 构造方法
		count++;
		System.out.println("子类类中构造方法:" + count);
	}
	public static void test() { // 静态成员方法
		System.out.println("子类中静态方法:" + count);
	}
}
public class Demo{
	public static void main(String args[]) {
		System.out.println("main方法运行中...");
		new SuperClass().test();
		new SuperClass().test();

		new SonClass().test();
		new SonClass().test();
	}
}
```
运行结果：  

```
main方法运行中...
超类中静态代码块:0
超类中非静态代码块:0
超类类中构造方法:1
超类中静态方法:1
超类中非静态代码块:1
超类类中构造方法:2
超类中静态方法:2
子类中静态代码块:0
超类中非静态代码块:2
超类类中构造方法:3
子类中非静态代码块:0
子类类中构造方法:1
子类中静态方法:1
超类中非静态代码块:3
超类类中构造方法:4
子类中非静态代码块:1
子类类中构造方法:2
子类中静态方法:2
```
### 抽象类和最终类
- 1. 用`final`修饰的类是最终类，用`final`修饰的方法是最终方法  
- 2. 用`abtract`修饰的类是抽象类，用`abtract`修饰的方法是抽象方法  
- 3. 最终类不能派生子类，不能作为超类被继承；如果希望类不被继承就把它声明为final类  
- 4. 如果需要类中的方法不被子类修改（覆盖），在类中，把方法定义为最终方法  
#### final总结
> final类不能被继承  
> final方法不能被覆盖，但可以被继承（使用）  
> final变量不能被修改（常量）  
#### 抽象方法
- 1. 抽象方法：仅有方法头无方法体，如`abstract void f();`  
- 2. 抽象方法为该类的子类定义一个方法的接口标准，子类必须根据需要重新定义它  
- 3. 构造方法不能为抽象的，抽象`abtract`与最终`final`不能同时存在  
#### 抽象类
- 1. 任何含有抽象方法的类为抽象类  
- 2. 抽象类是不能实例化的类，即不能直接用`new`操作符生成实例，抽象类只能被子类继承  
- 3. 一个抽象类的子类如果不是抽象类，则它必须为父类中的所有抽象方法书写方法体，即重写父类中的所有抽象方法  
- 4. 只有抽象类才能具有抽象方法，即如果一个类中含有抽象方法，则必须将这个类声明为抽象类  
- 5. 除了抽象方法，抽象类中还可以包括非抽象方法  
