package share;

import com.demo.model.Clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: nhsoft.ll
 * @Description:
 * @Date:Create：2020-08-25 20:55
 * @Modified By：
 */
public class Demo {

    public static int size = 10000000;

    public static void main(String[] args) {
        createList();
        createListBySize();
    }

    public static void createList() {
        List<Clazz> clazzes = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            clazzes.add(new Clazz(i));
        }
        System.out.println(clazzes.size());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void createListBySize() {
        List<Clazz> clazzes = new ArrayList<>(size);
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            clazzes.add(new Clazz(i));
        }
        System.out.println(clazzes.size());
        System.out.println(System.currentTimeMillis() - start);
    }
}
