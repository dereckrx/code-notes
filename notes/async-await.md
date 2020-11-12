(async function () {
var result = await failingPromise().catch((e) => { console.error(e.message) })
console.log( result ? 'This was a success! ' + result : 'This was a failure.' )
})()

## Handling Errors
https://medium.com/@JonasJancarik/handling-those-unhandled-promise-rejections-when-using-javascript-async-await-and-ifee-5bac52a0b29f

(async function () {
   try {
       await returnsPromise()
   } catch (error) {
       console.error(error)
       process.exit(1)
   }
   console.log('This will not be printed.');
})()

var result = await returnsPromise()
.catch((e) => { console.error(e.message) })