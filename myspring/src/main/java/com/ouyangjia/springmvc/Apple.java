package com.ouyangjia.springmvc;

import java.util.HashMap;
import java.util.Map;

public class Apple {
    private String color;

    public Apple(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(!(obj instanceof Apple)){
            return false;
        }
        if(this==obj){
            return true;
        }
        return ((Apple) obj).color.equals(this.color);
    }

    //HashCode的存在主要是为了查找的快捷性
   /* @Override
    public int hashCode(){
        return color.hashCode();
    }*/

    public static void main(String[] args) {
        Apple a1=new Apple("green");
        Apple a2=new Apple("red");
        Map<Apple,Integer> map=new HashMap<Apple, Integer>();
        map.put(a1,10);
        map.put(a2,20);

        /**
         * 以Java.lang.Object来理解， JVM每次new一个Object， 都会将Object丢到一个哈希表中去，下次做Object的比较或者取这个对象的时候，
         * 它会根据对象的hashcode再从Hash表中取这个对象。这样做的目的是提高取对象的效率。
         *
         * 由于Apple没有重写hashCode方法，还是采用Object的hashCode方法，新创建的对象a3的hashCode和a1的hashCode值不一样，所以取不到数据
         * 重写了hashCode方法后，就能取到数据了
         */
        Apple a3=new Apple("green");
        System.out.println(map.get(a3));
    }
}
