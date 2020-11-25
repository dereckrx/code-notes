function mockPromise(success, value, timeout = 0) {
  return new Promise((ok, no) => {
    if (success === null) {
      throw value;
    }
    setTimeout(
      () => {
        success ? ok(value) : no(value);
      },
      timeout,
    );
  });
}

module.exports = mockPromise;
