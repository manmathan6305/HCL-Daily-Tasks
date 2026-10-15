package repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic CRUD Repository interface modeling standard entity persistence operations.
 * Demonstrates multi-parameter generic interfaces <T, ID>.
 *
 * @param <T>  The domain entity type
 * @param <ID> The entity primary key type
 */
public interface CrudRepository<T, ID> {

    /**
     * Saves a given entity.
     */
    T save(T entity);

    /**
     * Retrieves an entity by its primary key ID.
     */
    Optional<T> findById(ID id);

    /**
     * Returns all instances of the entity.
     */
    List<T> findAll();

    /**
     * Returns whether an entity with the given id exists.
     */
    boolean existsById(ID id);

    /**
     * Deletes the entity with the given id.
     */
    boolean deleteById(ID id);

    /**
     * Returns the total count of entities.
     */
    long count();
}
