package concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapDemo {
    static ConcurrentMap<Integer,String> mp = new ConcurrentHashMap<>();
    public static void main(String[] args) {


        for (int i = 1; i <= 10 ; i++) {
            int finalI = i;
            new Thread(()  -> {
                System.out.println("Cache operation for thread "+ finalI);
                String val = getCachedValue(finalI);
                System.out.println("Cache mapping for " +finalI +
                "is " + val);
            }).start();
        }
    }

    private static String getCachedValue(int finalI) {

        if(mp.get(finalI) == null) {
            System.out.println("Cache Miss....");
            System.out.println("Computing val...");
            mp.put(finalI, "@" + String.valueOf(finalI));
            return "@" + String.valueOf(finalI);
        }
        else {
            return mp.get(finalI);
        }
    }
}


