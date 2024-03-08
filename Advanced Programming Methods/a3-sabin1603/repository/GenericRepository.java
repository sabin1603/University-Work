package repository;

import domain.Identifiable;

import java.util.HashMap;
import java.util.Map;

public class GenericRepository<ID,T extends Identifiable<ID>> implements Repository<ID, T> {
    Map<ID, T> repository = new HashMap<ID, T>();

    @Override
    public T add(T item) {

        return repository.put(item.getId(), item);
    }

    @Override
    public T remove(ID id) {
        return repository.remove(id);
    }

    @Override
    public T update(ID id, T entity) {
        if (repository.containsKey(id)) {
            repository.put(id, entity);
        } else {
            throw new RuntimeException("Entity with ID " + id + " not found. Cannot update.");
        }
        return entity;
    }

    @Override
    public T getById(ID id) {

        return repository.get(id);
    }

    @Override
    public Iterable<T> getAll() {

        return repository.values();
    }

    @Override
    public boolean exists(ID id){
        if (repository.containsKey(id))
            return true;
        else return false;
    }

    public Map<ID, T> getRepository() {
        return repository;
    }

}