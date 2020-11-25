// https://medium.com/nmc-techblog/functional-error-handling-in-js-8b7f7e4fa092
// https://fsharpforfunandprofit.com/posts/against-railway-oriented-programming/

const eq = require('./eq');

function validatePrices(items) {
  return new Promise((ok, no) => {
    const invalidItems = items.filter(item => !item.price);
    invalidItems.length > 0
      ? no({ invalidItems, error: 'invalid price' })
      : ok(items);
  });
}

function validateNames(items) {
  return new Promise((ok, no) => {
    const invalidItems = items.filter(item => !item.name);
    invalidItems.length > 0
      ? no({ invalidItems, error: 'invalid name' })
      : ok(items);
  });
}

// Validation chain will fail if any fail
const validateItems = items => validatePrices(items).then(validateNames);

// All valid
validateItems([
  { name: 'foo', price: 3 },
  { name: 'bar', price: 4 },
])
  .then((result) => {
    eq(result.length, 2);
  });

// One invalid
validateItems([
  { name: 'foo', price: 3 },
  { name: 'bar' },
  { name: 'baz', price: 4 },
])
  .catch(({ error, invalidItems }) => {
    eq(error, 'invalid price');
    eq(invalidItems, [{ name: 'bar' }]);
  });

// Testing is easy
(async () => {
  const result = await validatePrices([{ id: 1, price: 42 }, { id: 2 }]).catch(e => e);
  eq(result.invalidItems[0], { id: 2 });
  eq(result.error, 'invalid price');
})();

