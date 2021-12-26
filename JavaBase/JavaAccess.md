# Java访问权限
![Java访问权限](../image/JavaAccess.png)
### 示例
```java
public class Base {
	private void sayHi() {
		System.out.println("Base:Hi!");
	}
	public static void main(String[] args) {
		Base b = new Impl();
		b.sayHi();
	}
}
class Impl extends Base {
	public void sayHi() {
		System.out.println("Impl:Hi!");
	}
}
```
运行结果：`Base:Hi!`

```java
public class Base {
	protected void sayHi() {
		System.out.println("Base:Hi!");
	}
	public static void main(String[] args) {
		Base b = new Impl();
		b.sayHi();
	}
}
class Impl extends Base {
	public void sayHi() {
		System.out.println("Impl:Hi!");
	}
}
```
运行结果：`Impl:Hi!`
### 结果分析

 **结果1**   
- 子类`Impl`与父类`Base`位于同一包下，而`Base`中的`sayHi()`方法的修饰符为`private`，对子类不可见，不满足方法重写的要求，因此调用的仍然是`Base`中的方法，而非子类中的方法。  

 **结果2**   
- 1. Java的多态分为编译时多态和运行时多态  
- 2. 编译时多态主要指方法的重载
- 3. 运行时多态指程序中定义的对象引用所指向的具体类型在运行期间才能确定


