const eq = (a, b) => ((as, bs, p, f) => (as === bs ? p('.') : p(`FAIL: ${as} === ${bs} - ${f()}`)))(
  JSON.stringify(a),
  JSON.stringify(b),
  console.log,
  () => (new Error()).stack.split('\n')[4].split('/').slice(-1)[0].trim(),
);

module.exports = eq;
