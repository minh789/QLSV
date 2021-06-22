package content;

import java.util.List;

public interface DAO<T, K> {

	//public List<T> getAll();

	public T get(K k);//key

	public boolean save(T t);

	public boolean update(Student t);

	public void delete(String t);

	List<Student> getAll();
}
