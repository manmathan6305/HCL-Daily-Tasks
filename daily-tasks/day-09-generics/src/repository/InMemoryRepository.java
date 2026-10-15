package repository;

import java.util.*;
import java.util.function.Function;

/**
 * Generic In-Memory implementation of CrudRepository<T, ID>.
 * Reusable for ANY domain entity by passing an ID extraction function.
 *
 * @param <T>  The domain entity type
 * @param <ID> The primary key type
 */
public class InMemoryRepository<T, ID> implements CrudRepository<T, ID> {
    private final Map<ID, T> storage = new LinkedHashMap<>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = Objects.requireNonNull(idExtractor, "idExtractor cannot be null");
    }

    @Override
    public T save(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        ID id = idExtractor.apply(entity);
        Objects.requireNonNull(id, "Extracted entity ID cannot be null");
        storage.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean existsById(ID id) {
        return id != null && storage.containsKey(id);
    }

    @Override
    public boolean deleteById(ID id) {
        if (id == null) return false;
        return storage.remove(id) != null;
    }

    @Override
    public long count() {
        return storage.size();
    }
}
