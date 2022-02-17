
const checkCustomerValidity = (req, resp, next) => {
    const id = parseInt(req.params.id) || 0
    if (id >= 100) 
        return next()

    resp.status(400).type('text/html')
    resp.send(`<h2>Customer not found: <code>${id}</code></h2>`)
}
const processCustomer = (req, resp) => {
    const id = req.params.id
    resp.status(200).type('text/html')
    resp.send(`<h2>Customer id <code>${id}</code></h2>`)
}

module.exports = { 
    checkCustomerValidity, 
    processCustomer 
}