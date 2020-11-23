## Overview

### Domain errors vs recoverable errors vs unexpected/exceptional errors

Does it mean there's a bug somewhere?
Should developers be notified when it happens?
Should the user be notified? Can the user retry with different inputs?

Do we expect it, is it a common error in our domain? Can you give it a name?

The case of company_id from session and front end passing program_id
if company_id is invalid, it means theres a bug in the session code (or user was deleted, super edge case
if program_id is invalid, it means the theres a bug in the link generation code
Under our definition, this is a panic situation because 
- it was caused by a bug and 
- there's nothing the user can do about it
However, a 404 with company/program not found is much more helpful
- to the developers, not the user who still can't do anything about it
- so maybe still treating it as 500 and logging the details means you can be notified in production

### Is there anything a 'user' can do about this?
Can they retry with different data? 
Is there different input that would allow the code path to succeed?

Looking up a user_id in a API request findUser(user_id) => 404 error
- It seems the user passed in an invalid id

Looking up a user_id based off another entity findUser(company.admin_user_id)
- we've discovered a bad state. `company.admin_user_id` is invalid
- there is no way for this code path to succeed no matter what the user inputs
- you could log or fire some event to investigate the invalid id,
    - but you still have to panic for now
    - there is a bug somewhere that created some bad state

Same example for user_id passed into API via param vs session
- In the param case, the bug is on the font end, it sent the wrong value
- In the session case, the bug is on the backend, it created an bad session

Reading a file
Reading a file by passing a hardcoded filename
The user can't do anything about this, the bad state is hardcoded in the code
vs 
Reading a file by passing in a filename
The user might be able to try a different filename

## panics will normally happen on their own.
Never write panics/throws and let them happen on their own
- But that is hard to debug since a bad state can continue for a while before exploding
 
If you let a bad state continue you're bound to panic/throw later on.
- If you know you are going to fail eventually, might as well fail now and provide a clear reason

## Debug Support: So why write the panic'ing/throw code at all?
- One benefit is extra information. You know where and can explain why it panic'ed.
- Type safe code sometimes requires explicit checks and handling of nulls, etc.
    - If this value should be not be null, panic'ing will help you debug this bad state
    - and there's no point in returning an error because downstream code depends on this value
    - You reduce error code that offers no value, panic'ing is the best you can do

## Boundary: So why write the panic'ing/throw code at all?
Catch an exception at the boundary (ie: API response, library interface)
And prevent it from crashing the whole server/UI.

## Strategies for dealing with recoverable errors

return null // Non type-safe. Caller is unaware that it might be null.
return T | null // type-safe so caller still forced to deal with it
return Option<T>
return T | Error
return Result<T, Err>
throw Error
Try{}: Success<T>, Failure<Throwable>



-----------------------------------------------------------
## Go style

There are no exceptions, except panics

- Always return errors
- Always check for errors
- Append contextual message to error as it travels up the stack
    - Optionally Append stack trace (is not included by default)

```golang
val, err := createUser(user)
if err != nil {
    return fmt.Errorf("could not create user: %w", err)
} 
...
// Add more details
if err := db.Connected(), err != nil {
    return fmt.Errorf("could not connect to database", err)
} 
```

## Rust
Recoverable and unrecoverable
Result <T,E> vs panic! Macro

Panic only if: (there is a bug somewhere that a programmer should fix.)
- your code has a bug (ended up in a bad state)
- caller's code has a bug (ie: violated the contract)

Panic when you are in a "bad state" 
bad state is when any of these are passed to your code: 
- invalid, missing values (as apposed to user input that could be validated)
- The bad state is not something that’s expected to happen occasionally.
    - it's not expected, It's exceptional
- Your code after this point needs to rely on not being in this bad state.
    - no point in going any further
    - if you propagate the error, are you just going to have to panic anyway?
- There’s not a good way to encode this information in the types you use.

Examples of expected errors: 
- (caller must decide how to deal with it)
- parsing dynamic input (from users or callers)
- API rate limiting
- like an index being out of bounds for an array.

Examples expected problem:
- like a file not being found.
- Due to an unexpected problem, 

```rust
let f = File::open("hello.txt");

let f = match f {
    Ok(file) => file,
    Err(error) => panic!("Problem opening the file: {:?}", error),
};
```

Auto error propagation with ? operator
```
fn read_username_from_file() -> Result<String, io::Error> {
    let mut f = File::open("hello.txt")?;
    ...
}

// Is equivilent to
fn read_username_from_file() -> Result<String, io::Error> {
    let f = File::open("hello.txt");

    let mut f = match f {
        Ok(file) => file,
        Err(e) => return Err(e),
    };
    ...
}

```
## Macro 

```
const user <<= createUser(userProps)
const user = try!(createUser(userProps))
const user = createUser(userProps)?
```

expands to: 
// Stack and function name comes from original error, no need to append
```
const user = createUser(userProps);
if (user instanceOf Error) {
    return user;
}
```
