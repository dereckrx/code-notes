// source: https://medium.com/@AkyunaAkish/quick-examples-of-working-with-promises-javascript-d4a0ead4a892
const eq = require('./eq');
const mockPromise = require('./mockPromise');

// New promise
let p = new Promise((resolve, reject) => resolve('ok'));
p.then(result => eq(result, 'ok'));

// Instantly resolve
p = Promise.resolve('done');
p.then(result => eq(result, 'done'));

// Instantly reject
p = Promise.reject('fail');
p.catch(error => eq(error, 'fail'));

// handles resolves
mockPromise(true, 'ok').then((result) => {
  eq(result, 'ok');
});

// handles rejections
mockPromise(false, new Error('not good')).catch((error) => {
  eq(error.message, 'not good');
});

// handles throws
mockPromise(null, new Error('bang!')).catch((error) => {
  eq(error.message, 'bang!');
});

// Fire many and wait for all
Promise.all([
  mockPromise(true, 'ok'),
  mockPromise(true, 'ok: 500', 500),
]).then(result => eq(result, ['ok', 'ok: 500']))
  .catch(error => eq(error.message, undefined));

// Any failures will be caught
Promise.all([
  mockPromise(true, 'ok'),
  mockPromise(false, new Error('not good: 500'), 500),
]).then(result => eq(result, undefined))
  .catch(error => eq(error.message, 'not good: 500'));

// Do not fail all if one fails
const promises = [
  mockPromise(true, 'ok'),
  mockPromise(false, new Error('not good')),
  mockPromise(true, 'ok'),
];
Promise.all(
  promises.map(p => p.catch(e => e)),
).then((results) => {
  eq(results[0], 'ok');
  eq(results[1].message, 'not good');
  eq(results[2], 'ok');
}).catch(error => eq(error.message, undefined));

// Promise chaining
mockPromise(true, 1).then(result => mockPromise(true, result + 1))
  .then(secondResult => mockPromise(true, secondResult + 1))
  .then(thirdResult => mockPromise(true, thirdResult + 1))
  .then(fourthResult => eq(fourthResult, 4))
  .catch(error => eq(error.message, undefined));

// Railway oriented programing
// Any errors will short circuit
mockPromise(true, 1).then(result => mockPromise(true, result + 1))
  .then(secondResult => mockPromise(secondResult + 1))
  .then(_ => mockPromise(false, new Error('fail')))
  .then(fourthResult => eq(fourthResult, 4))
  .catch(error => eq(error.message, 'fail'));
