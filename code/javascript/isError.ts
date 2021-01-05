const eq = require('./eq');

class HttpException extends Error {
  status: number; 

  constructor(status: number, message: string) {
    super(message);
    this.status = status;
  }
};

export const isError: <T>(maybeError: any) => asserts maybeError is T = (
  maybeError
) => {
  if (maybeError instanceof HttpException || maybeError instanceof Error) {
    return true;
  }
  return false;
};

eq(isError(new HttpException(400, 'bla')), true);
eq(isError(new Error('bla')), true);


