/*
 source: https://www.taniarascia.com/understanding-template-literals/
 */

function assert(a, b) {
  if (a === b) {
    console.log(a);
  } else {
    console.log('FAIL =>', `${a} != ${b}`);
  }
}

assert('a' + 'b' + 'c', 'abc');

const abc2 = 'a\
  b\
  c';
assert(abc2, 'a  b  c');

const abc3 = `a
b
c`;
assert(abc3, 'a\nb\nc');

function bold(strings, ...expressions) {
  console.log('Template Strings: ', strings);
  console.log('Template Expressions: ', expressions);
  let finalString = '';

  // Loop through all expressions
  expressions.forEach((value, i) => {
    finalString += `${strings[i]}<strong>${value}</strong>`;
  });

  // Add the last string literal
  finalString += strings[strings.length - 1];

  return finalString;
}

const string = bold`This is a string with ${true} and ${false} and ${100} interpolated inside.`;
assert(string, 'This is a string with <strong>true</strong> and <strong>false</strong> and <strong>100</strong> interpolated inside.');
