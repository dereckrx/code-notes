const eq = require('./eq');
const mockPromise = require('./mockPromise');

// Handling Errors
// https://medium.com/@JonasJancarik/handling-those-unhandled-promise-rejections-when-using-javascript-async-await-and-ifee-5bac52a0b29f

(async () => {
  // Success
  let result = await mockPromise(true, 'ok')
    .catch((e) => { eq(e.message, undefined); });
  eq(result, 'ok');

  // Can catch rejections
  result = await mockPromise(false, 'rejected')
    .catch((e) => { eq(e, 'rejected'); return `caught-${e}`; });
  eq(result, 'caught-rejected');

  // Can catch thrown errors
  result = await mockPromise(null, new Error('bang!'))
    .catch((e) => { eq(e.message, 'bang!'); return new Error('bang! bang!'); });
  eq(result.message, 'bang! bang!');

  // Catch rejections
  try {
    result = await mockPromise(false, 'rejected');
  } catch (error) {
    eq(error, 'rejected');
  }

  // Catch thrown errors
  try {
    result = await mockPromise(null, new Error('nice try'));
  } catch (error) {
    eq(error.message, 'nice try');
  }
})();

// If you rethrow, you must catch somewhere else or get a warning
(async () => {
  await mockPromise(null, new Error('throw me!'))
    .catch((e) => { throw e; });
})().catch(e => eq(e.message, 'throw me!'));
