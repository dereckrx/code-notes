const eq = (a, b) => ((as, bs, p, f) => (as === bs ? p('.') : p(`FAIL: ${as} === ${bs}\t${f()}`)))(
  JSON.stringify(a),
  JSON.stringify(b),
  console.log,
  () => (new Error()).stack.split('\n')[4].match(/\/([^/]+):\d+\)/)[1],
);

module.exports = eq;
