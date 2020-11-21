## Go style

- Always return errors
- Always check for errors
- Append new info to error as it travels up the stack
    - Optionally Append stack trace 

```golang
val, err := createUser(user)
if err != nil {
    return fmt.Errorf("could not create user: %w", err)
} 
...
if err := db.Connected(), err != nil {
    return fmt.Errorf("could not connect to database", err)
} 
```

## Macro 

```
const user <<= createUser(userProps)
```

expands to: 
// Stack and function name comes from original error, no need to append
```
const createUserResult = createUser(userProps);
if (createUserResult instanceOf Error) {
    return createUserResult;
}
```
