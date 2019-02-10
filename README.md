# users-api

Small project using javalin microframework

### httpie commands

Commands using [httpie](https://www.cheatography.com/clucinvt/cheat-sheets/httpie/) to test the REST api:

- return all users: `http localhost:7000/users`
- return specific user: `http localhost:7000/users/1`
- return specific user by its email: `http localhost:7000/users/email/bruno@bruno.com`
- create user: `http POST localhost:7000/users "name=Ayrton Senna" email=ayrton@senna.com`
- update user: `http PATCH localhost:7000/users/3 "name=Steve Jobs"`
- delete user: `http DELETE localhost:7000/users/5`