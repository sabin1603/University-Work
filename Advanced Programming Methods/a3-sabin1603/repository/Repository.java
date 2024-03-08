package repository;

import domain.Identifiable;
import java.util.Collection;

public interface Repository<ID, T extends Identifiable<ID>> {
    T add(T item);
    T getById(ID id);
    T update(ID id, T newElement) throws RuntimeException;
    T remove(ID id);
    boolean exists(ID id);

    Iterable<T> getAll();
}
