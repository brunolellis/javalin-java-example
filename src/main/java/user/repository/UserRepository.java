package user.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import user.User;

public class UserRepository {

    private static final Map<Integer, User> users = new ConcurrentHashMap<>();

    private AtomicInteger lastId;

    static {
        users.put(1, new User(1, "Bruno", "bruno@bruno.com"));
        users.put(2, new User(2, "John", "john@doe.com"));
        users.put(3, new User(3, "Steve", "steve@jobs.com"));
        users.put(4, new User(4, "Bill", "bill@gates.com"));
    }

    public UserRepository() {
        lastId = new AtomicInteger(users.size());
    }

    public User save(User user) {
        var id = lastId.incrementAndGet();
        users.put(id, new User(id, user.getName(), user.getEmail()));
        return users.get(id);
    }

    public Collection<User> findAll() {
        return users.values();
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }

    public void update(Integer id, User user) {
        users.put(id, new User(id, user.getName(), user.getEmail()));
    }

    public void delete(Integer id) {
        users.remove(id);
    }

}