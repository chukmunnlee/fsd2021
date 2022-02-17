// Load the required libraries
const express = require('express')
const { engine } =  require('express-handlebars')
const fetch = require('node-fetch')
const withQuery = require('with-query').default

//const cust = require('./customers')
const { processCustomer, checkCustomerValidity  } = require('./customers')

// configure the environment
const PORT = parseInt(process.env.PORT) || 3000
const GIPHY_KEY = process.env.GIPHY_KEY

// Create an instance of the express applicatin
const app = express()

// configure express to use hbs
app.engine('hbs', engine({ defaultLayout: 'default.hbs' }))
app.set('view engine', 'hbs')

// Configure route handlers
app.use(
    (req, resp, next) => {
        console.info(`> method: ${req.method}, resource: ${req.originalUrl}`)
        next()
    }
)

// serve static files
app.use(express.static(__dirname + '/public'))

app.get('/customer/:id', 
    checkCustomerValidity, processCustomer)

app.get('/search', 
    (req, resp) => {
        const q = req.query.q || 'not set'
        resp.status(200).type('text/html')
        resp.send(`<h2>Query string test <code>${q}</code></h2>`)
    }
)

app.get(['/time'],
    (req, resp) => {
        resp.status(200).type('text/html')
        resp.render('time', 
            // Model
            { 
                time: (new Date()).toISOString(),
                message: 'hello'
            }
        )
        //resp.send(`<h1>The current time is ${new Date()}</h1>`)
    }
)

app.get('/search-giphy',
    (req, resp) => {

        // construct the URL
        const url = withQuery('https://api.giphy.com/v1/gifs/search', {
            api_key: GIPHY_KEY,
            q: req.query.q,
            limit: 10
        })
        fetch(url)
            .then(result => result.json())
            .then(result => {
                const gifs = result.data.map(d => {
                    return {
                        title: d.title, 
                        imageUrl: d.images.fixed_height.url
                    }
                })
                console.info(">>> gifs: ", gifs)
                resp.status(200).type('text/html')
                resp.render('giphy', {
                    q: req.query.q,
                    gifs
                })
            })
            .catch(err => {
                resp.status(400).type('text/html')
                resp.send('error: ' + JSON.stringify(err))
            })

    }
)

//app.use(express.urlencoded({ extended: true }))
app.post("/register",
    express.urlencoded({ extended: true }),
    (req, resp) => {
        // process form
        const name = req.body.name
        const email = req.body.email

        // do something with the data

        resp.status(200).type('text/html')
        resp.render('registered', { name, email })
    }
)

// Error
app.use((req, resp) => {
    resp.status(404).type('text/html')
    resp.send('<h2>Not found</h2>')
})

// Start the server
if (!!GIPHY_KEY)
    app.listen(PORT, () => {
        console.info(`Application started on port ${PORT} at ${new Date()}`)
        console.info(`Express is running in __dirname = ${__dirname}`)
    })
else
    console.error('GIPHY_KEY not set')