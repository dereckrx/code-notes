const eq = (a, b) => ((as, bs, p) => (as === bs ? p('.') : p(`FAIL: ${as} === ${bs}`)))(
  JSON.stringify(a),
  JSON.stringify(b),
  console.log,
);

eq(1, 1);
eq(1, 2); // FAIL: 1 === 2
eq(1, 1.0);
eq(null, null);
eq(undefined, undefined);
eq(null, undefined); // FAIL: null === undefined
eq([null], [undefined]);
eq([1, 2, 3], [1, 2, 3]);
const foo = { a: 1, b: { c: [1, 2, 3] } };
const bar = { a: 1, b: { c: [1, 2, 3] } };
eq(foo, bar);
