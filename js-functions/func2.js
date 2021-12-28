
const mkHello = function(n) {
    return function() {
        console.info(`Hello ${n}`)
    }
}

const fred = mkHello('Fred')
const barney = mkHello('Barney')

console.info('>> fred = ', fred)
console.info('>> barney = ', barney)

fred()
barney()
