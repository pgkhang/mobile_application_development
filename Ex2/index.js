const express = require('express')
const app = express()
port = process.env.PORT || 3000

app.get("/api/getdistance", function (req, res) {
    // Retrieve and store the two coordinates with latitude and longtitude converted to radian
    const lat1 = req.query.lat1 * Math.PI / 180
    const long1 = req.query.long1 * Math.PI / 180
    const lat2 = req.query.lat2 * Math.PI / 180
    const long2 = req.query.long2 * Math.PI / 180
    
    // Radius of Earth in kilometer
    const R = 6373

    // Use Haversine Formula to calculate the distance between two points on the Earth
    const a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((long2 - long1) / 2), 2)
    const distance = R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

    // Response with a JSON contains the distance and the unit
    res.json({
        distance: distance,
        unit: "kilometer"
    })
})

app.listen(port, function () {
    console.log("App started on port " + port)
})