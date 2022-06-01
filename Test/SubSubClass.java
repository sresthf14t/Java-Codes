class SuperClass {
    public void show() {
        System.out.println("Inside SuperClass");
    }
}
class SubClass extends SuperClass{
    public void show() {
        System.out.println("Inside SubClass");
    }
}
class SubSubClass extends SubClass{
    public void show() {
        System.out.println("Inside SubSubClass");
        super.show();
        super.super.show();
    }
    public static void main(String args[]) {
        SubSubClass obj=new SubSubClass();
        obj.show();
    }
}