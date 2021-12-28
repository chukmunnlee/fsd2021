const apply = function(f, v) {
	return f(v)
}

const mkPower = function(y) {
	return function(x) {
		return (x ** y);
	}
}

const square = mkPower(2)
const cube = mkPower(3)

console.info(`3^2 = `, square(3))
console.info(`3^3 = `, cube(3))

const quad = apply(mkPower, 4)
let ans = quad(4)
console.info(`3^4 = `, ans)

ans = apply(apply(mkPower, 5), 5)
console.info(`5^5 = `, ans)


