package user.api;

import java.util.Optional;

import user.User;
import user.repository.UserRepository;

import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;

public class UserController implements CrudHandler {

    private UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @Override
    public void create(Context ctx) {
        var user = ctx.bodyAsClass(User.class);
        var newUser = users.save(user);
        ctx.json(newUser);
        ctx.status(201);
    }

    @Override
    public void delete(Context ctx, String id) {
        users.delete(Integer.valueOf(id));
        ctx.status(204);
    }

    @Override
    public void getAll(Context ctx) {
        ctx.json(users.findAll());
    }

    @Override
    public void getOne(Context ctx, String id) {
        Optional<User> user = users.findById(Integer.valueOf(id));
        handleOptionalResponse(ctx, user);
    }

    public void findByEmail(Context ctx, String email) {
        Optional<User> user = users.findByEmail(email);
        handleOptionalResponse(ctx, user);
    }

    private void handleOptionalResponse(Context ctx, Optional<User> user) {
        user.map(ctx::json)
                .orElse(ctx.status(404));
    }

    @Override
    public void update(Context ctx, String id) {
        var user = ctx.bodyAsClass(User.class);
        users.update(Integer.valueOf(id), user);
        ctx.status(204);
    }

}