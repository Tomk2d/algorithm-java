package 과제테스트.상속;

/*
*
    2. 메서드 오버라이딩 문제
    Shape 클래스의
    double area()
    메서드를
    Rectangle 클래스에서 오버라이딩하여
    사각형 넓이를 계산하도록 구현하시오.
* */
public class 상속2 {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 4);
        System.out.println(rectangle.area());
    }

    public static class Shape{
        private int row;
        private int col;

        public Shape(int row, int col){
            this.row = row;
            this.col = col;
        }

        public double area(){
            return (double) 100;
        }

        public int getRow(){
            return row;
        }

        public int getCol(){
            return col;
        }
    }

    public static class Rectangle extends Shape{
        public Rectangle(int row, int col){
            super(row, col);
        }

        @Override
        public double area() {
            return (double) super.getRow() * (double) super.getCol();
        }
    }
}
