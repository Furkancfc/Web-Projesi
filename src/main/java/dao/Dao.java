package dao;

import java.util.List;

public interface Dao<T> {
	public List<T> display();

	public T display(T t);

	public T display(String id);

	public T delete(T t);

	public T create(T t);

	public T update(T t);
}