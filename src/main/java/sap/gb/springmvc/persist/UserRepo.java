package sap.gb.springmvc.persist;

import sap.gb.springmvc.model.User;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UserRepo {
    private Long genId = 0l;
    private ConcurrentHashMap<Long, User> dataBase;

    public UserRepo(ConcurrentHashMap<Long, User> dataBase) {
        this.dataBase = dataBase;
    }

    public void saveUser(User user) {
        user.setId(generateId());
        dataBase.put(user.getId(), user);
    }

    public User getUserById(Long id) {
        return dataBase.get(id);
    }


    public List<User> getAllUsers() {
        return dataBase
                .entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    private Long generateId() {
        return ++genId;
    }
}
