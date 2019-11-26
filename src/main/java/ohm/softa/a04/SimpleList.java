package ohm.softa.a04;

import java.util.function.Function;

public interface SimpleList<T> extends Iterable <T> {
	/**
	 * Add a given object to the back of the list.
	 */
	void add(T o);

	/**
	 * @return current size of the list
	 */
	int size();

	default void addDefault(Class<T> element){
		try
		{
			this.add(element.newInstance());
		}
		catch(InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}

	}
	;

	/**
	 * Generate a new list using the given filter instance.
	 * @return a new, filtered list
	 */
	default SimpleList<T> filter(SimpleFilter<T> filter){
		SimpleList<T> filtered;

		try{
			filtered = (SimpleList<T>) getClass().newInstance();
		}
		catch (InstantiationException | IllegalAccessException e){
			filtered = new SimpleListImpl<>();
		}

		for(T o : this){
			if(filter.include(o))
				filtered.add(o);
		}

		return filtered;
	};

	default <R> SimpleList<R> map(Function<T,R> transform){
		SimpleList<R> transformed;

		try{
			transformed = (SimpleList<R>) getClass().newInstance();
		}
		catch (InstantiationException | IllegalAccessException e){
			transformed = new SimpleListImpl<>();
		}

		for(T o: this){
			transformed.add(transform.apply(o));
		}

		return transformed;
	}
}
