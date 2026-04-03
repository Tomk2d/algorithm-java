package 과제테스트;

/*
*   9. Map 활용 문제 List<User>가 주어질 때,
*   id를 key User를 value 로 하는 Map으로 변환하시오.
*   (Stream 사용)
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class streamAPI2 {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1l, 1000, "a"));
        list.add(new Product(2l, 2000, "b"));
        list.add(new Product(3l, 3000, "c"));
        list.add(new Product(4l, 4000, "d"));
        list.add(new Product(5l, 5000, "e"));
        list.add(new Product(6l, 6000, "f"));

        Map<Long, Product> productMap = productToMap(list);
        for(long key : productMap.keySet()){
            Product product = productMap.get(key);
            System.out.println(key + " : (" + product.getId() + " , " + product.getName() + " , " + product.getPrice() + ")");
        }
    }

    public static Map<Long, Product> productToMap(List<Product> list) {
        return list.stream()
            .collect(Collectors.toMap(
                Product::getId,
                product -> product
            ));
    }

    public static class Product{
        private long id;
        private int price;
        private String name;

        Product(long id, int price, String name){
            this.id = id;
            this.price = price;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public int getPrice(){
            return price;
        }

        public String getName() {
            return name;
        }
    }
}
