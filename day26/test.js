
// Service provider 
let p = new Promise(
	// Your work
	(resolve, reject) => {
		let sum = 0
		console.info('starting work')
		for (let i = 0; i < 1000; i++) {
			//console.info(`i = ${i}`)
			sum += i
		}
		console.info('consumer done')
		//reject('error ')
		resolve(sum)
	}
)
console.info('created promise')

// consumer, wait for the result
p
	.then(result => {
		console.info(`1st then: consumer: ${result}`);
		throw new Error('error in result: ' + result)
		//return result * 2
	})
	.then(result => {
		console.info(`2nd then: result ${result}`)
	})

	.catch(error => {
		console.error(`1st catch: error message: ${error}`); 
		return ('hello world')
	})
	.then(result => {
		console.info(`3nd then: result ${result}`)
	})
	.catch(error => {
		console.error(`2nd catch error message: ${error}`);
	})

console.info('completed')
