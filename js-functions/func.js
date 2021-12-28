const hello = function(name) {
    console.info(`hello ${name}`)
}
const fred = 'fred'

console.info('>>>> hello = ', hello)
console.info('>>>> name = ', fred)

hello(fred)

const print3 = (text) => {
    console.info(text)
    console.info(text)
    console.info(text)
}

print3(fred)

const apply = (f, v) => {
    f(v)
}

apply(hello, fred)
apply(console.info, 'this is my string')