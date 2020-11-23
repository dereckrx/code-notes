// source: https://medium.com/@AkyunaAkish/quick-examples-of-working-with-promises-javascript-d4a0ead4a892

const eq = (a, b) => ((as, bs, p, f) => (as === bs ? p('.') : p(`FAIL: ${as} === ${bs} - ${f()}`)))(
  JSON.stringify(a),
  JSON.stringify(b),
  console.log,
  () => (new Error()).stack.split('\n')[4].split('/').slice(-1)[0].trim(),
);

function asyncFun(success, value, timeout = 0) {
  return new Promise((ok, no) => {
    if (value instanceof Error) {
      throw value;
    }
    setTimeout(
      () => {
        success ? ok(value) : no(new Error(value));
      },
      timeout,
    );
  });
}

// handles resolves
asyncFun(true, 'ok').then((result) => {
  eq(result, 'ok');
});

// handles rejections
asyncFun(false, 'not good').catch((error) => {
  eq(error.message, 'not good');
});

// handles throws
asyncFun(false, new Error('bang!')).catch((error) => {
  eq(error.message, 'bang!');
});

// Fire many and wait for all
Promise.all([
  asyncFun(true, 'ok'),
  asyncFun(true, 'ok: 500', 500),
]).then(result => eq(result, ['ok', 'ok: 500']))
  .catch(error => eq(error.message, undefined));

// Any failures will be caught
Promise.all([
  asyncFun(true, 'ok'),
  asyncFun(false, 'not good: 500', 500),
]).then(result => eq(result, undefined))
  .catch(error => eq(error.message, 'not good: 500'));

// Do not fail all if one fails
const promises = [
  asyncFun(true, 'ok'),
  asyncFun(false, 'not good'),
  asyncFun(true, 'ok'),
];
Promise.all(
  promises.map(p => p.catch(e => e)),
).then((results) => {
  eq(results[0], 'ok');
  eq(results[1].message, 'not good');
  eq(results[2], 'ok');
}).catch(error => eq(error.message, undefined));

// Promise chaining
asyncFun(true, 1).then(result => asyncFun(true, result + 1))
  .then(secondResult => asyncFun(true, secondResult + 1))
  .then(thirdResult => asyncFun(true, thirdResult + 1))
  .then(fourthResult => eq(fourthResult, 4))
  .catch(error => eq(error.message, undefined));

// Railway oriented programing
// Any errors will short circuit
asyncFun(true, 1).then(result => asyncFun(true, result + 1))
  .then(secondResult => asyncFun(secondResult + 1))
  .then(_ => asyncFun(false, 'fail'))
  .then(fourthResult => eq(fourthResult, 4))
  .catch(error => eq(error.message, 'fail'));
