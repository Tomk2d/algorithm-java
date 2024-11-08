package 손코딩_대비;

public class Gugudan {
    public void makeGugudan(){
        for(int i = 1; i <= 9; i++){
            for(int j = 2; j <= 9; j++){
                System.out.print(j + " * " + i + " = " + String.format("%2d",i*j) + "   ");
            }
            System.out.println();
        }
    }
}
