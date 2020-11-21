const eq = (a, b) => ((as, bs, p, f) => (as === bs ? p('.') : p(`FAIL: ${as} === ${bs} - ${f()}`)))(
  JSON.stringify(a),
  JSON.stringify(b),
  console.log,
  () => (new Error()).stack.split('\n')[4].split('/').slice(-1)[0].trim(),
);

function isError(obj) {
  return Object.prototype.toString.call(obj) === '[object Error]';
}

class AppError extends Error {}

const myError = new Error('foo');
const appError = new AppError();
const myString = 'Whatever';
const myObj = {};
const myArray = [];

eq(myError instanceof Error, true);
eq(appError instanceof Error, true);
eq(myError instanceof Object, true); // WAT?
eq(myString instanceof Error, false);
eq(myObj instanceof Error, false);
eq(myArray instanceof Error, false);

eq(isError(myError), true);
eq(isError(appError), true);
eq(isError(myString), false);
eq(isError(myObj), false);
eq(isError(myArray), false);


// Sync error handling

const world = () => new Error('bad sync');

const foo = world();
if (isError(foo)) {
  eq(foo.stack.split('\n')[0], 'Error: bad sync');
}

// Doing it async

(async function () {
  const hello = () => new Promise(() => { throw new Error('bad async'); });

  const res = await hello().catch(err => err);
  if (isError(res)) {
    eq(res.stack.split('\n')[0], 'Error: bad async');
  }
}());
