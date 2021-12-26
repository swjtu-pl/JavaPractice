//值传递和引用传递
class Book {
	String name;
	double price;
	public Book(String name, double price) {
		this.name = name;
		this.price = price;
	}
}

public class Demo {
	public static void main(String[] args) {
		System.out.println("-----------------");
		Book book1 = new Book("a", 10);
		change(book1, "b", 20);
		System.out.println(book1.name + ", " + book1.price);

	}
	static void change(Book book, String name, double price) {
		book.name = name;
		book.price = price;
	}
}

//-----C++值传递和引用传递-----
//#include <iostream>
//using namespace std;
//
//class Book {
//public:
//	string name;
//	double price;
//	Book() {
//		cout << "-new-\n";
//	}
//	Book(Book&) {
//		cout << "-new(拷贝构造)-\n";
//	}
//	Book(string name, double price) {
//		this->name = name;
//		this->price = price;
//		cout << "-new-\n";
//	}
//	void print() {
//		cout << this->name + ", ";
//		cout << this->price;
//		cout << endl;
//	}
//	~Book() {
//		cout << "-delete-\n";
//	}
//};
//
//void change1(Book book, string name, double price) {
//	book.name = name;
//	book.price = price;
//	cout << "change1函数（值传递，无返回值）：";
//	book.print();
//}
//
//Book change2(Book& book, string name, double price) {
//	book.name = name;
//	book.price = price;
//	cout << "change2函数（引用传递，有返回值）：";
//	book.print();
//	return book;
//}
//
//int main()
//{
//	Book book("a", 10);
//	book.print();
//	cout << endl;
//
//	change1(book, "b", 20);
//	book.print();
//	cout << endl;
//
//	change2(book, "c", 30);
//	book.print();
//	cout << endl;
//}