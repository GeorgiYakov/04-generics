package ohm.softa.a04;

import javax.swing.text.Element;
import java.util.Comparator;
import java.util.Iterator;

public abstract class CollectionsUtility <T>{

    private CollectionsUtility(){

    }
    public static <T> SimpleList<T> sort(SimpleList<T> list, Comparator<T> cmp){
        SimpleList<T> sorted= createNew(list.getClass());;

        int n = list.size();

        Iterator<T> it = list.iterator();

        T current = null;

        do{
            if(current == null) current = it.hasNext() ? it.next() : null;

            if(it.hasNext()){
                T next = it.next();
                int c = cmp.compare(current,next);
            }

        }while(it.hasNext() || current != null);


        return sorted;
    }

    private static <T> SimpleList<T> createNew(Class<? extends SimpleList> clazz){

        try{
            return clazz.newInstance();
        }
        catch (IllegalAccessException | InstantiationException e){
            return new SimpleListImpl<>();
        }
    }
}
