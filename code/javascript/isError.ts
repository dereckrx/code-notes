const eq = require('./eq');

class HttpException extends Error {
  status: number; 

  constructor(status: number, message: string) {
    super(message);
    this.status = status;
  }
};

class User  {
  id: number; 

  constructor(id: number) {
    this.id = id;
  }
};

//!!!!!!!!!!! DO NOT DO THIS!!!!!!
// Just use "if(thing instanceof Error)" instead
// 
// export const isError: <T>(maybeError: any) => asserts maybeError is T = (
//   maybeError
// ) => {
export const isError = (maybeError: any): boolean => {
  if (maybeError instanceof HttpException || maybeError instanceof Error) {
    return true;
  }
  return false;
};

eq(isError(new HttpException(400, 'bla')), true);
eq(isError(new Error('bla')), true);

const foos: Record<string,  {value: string} | Error> = {
  error: new Error('error foo'),
  httpError: new HttpException(400, 'http error foo'),
  value: {value: 'foo'}
};

const getFoo = (kind: string):  {value: string} | Error  => foos[kind];

function returnTypeChecking(foo: Error | {value: string}): string {
  // Doesn't work, TS wont know what type foo is on the next line
  // if(isError(foo)) {
  //   return foo;
  // }

  if(foo instanceof HttpException) {
    return `${foo.status}`;
  } else if(foo instanceof Error) {
    return foo.message;
  } else {
    return foo.value;
  }
}

function getUser(error: boolean): User | HttpException {
  return error ?  new HttpException(500, 'http user error') : new User(1) ;
}

const user = getUser(true);
if(user instanceof Error) {
  console.log('error', user.status )
} else {
  console.log('user', user.id)
}

eq(returnTypeChecking(getFoo('error')), 'error foo');
eq(returnTypeChecking(getFoo('httpError')), '400');
eq(returnTypeChecking(getFoo('value')), 'foo');

