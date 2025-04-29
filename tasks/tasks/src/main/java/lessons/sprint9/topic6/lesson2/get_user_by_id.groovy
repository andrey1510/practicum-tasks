Contract.make {
    request {
        method 'GET'
        url '/users/42'
    }
    response {
        status 200
        body([
                id: 42,
                username: 'sherlock_holmes',
                email: 'sherlock@bakerstreet.uk'
        ])
        headers {
            contentType(applicationJson())
        }
    }

}