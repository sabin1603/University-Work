package repository;

import domain.Identifiable;

import java.sql.SQLException;

public interface Repository<ID, T extends Identifiable<ID>> {
    T add(T item) throws SQLException;
    T getById(ID id) throws SQLException;
    T update(ID id, T newElement) throws RuntimeException, SQLException;
    T delete(ID id);
    boolean exists(ID id);

    Iterable<T> getAll();
}
