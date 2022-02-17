const express = require('express')
const fetch = require('node-fetch')
const withQuery = require('with-query').default

// Configure the environment variable
require('dotenv').config()

// Set giphy key
const GIPHY_KEY = process.env.GIPHY_KEY

// Create an instance of express
const app = express()

app.get('/api/search-giphy',
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
        //console.info(">>> gifs: ", gifs)
        resp.status(200).type('application/json')
        // return JSON
        resp.json(gifs)
      })
      .catch(err => {
        resp.status(400).type('text/html')
        resp.send('error: ' + JSON.stringify(err))
      })
  }
)

// export Express app to module.exports
module.exports = app
