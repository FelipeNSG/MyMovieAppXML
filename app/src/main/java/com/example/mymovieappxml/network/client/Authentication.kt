package com.example.mymovieappxml.network.client

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class Authentication : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {
        // request
        val token = ""

        return response.request.newBuilder()
            .header("Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMWNlYjVmN2IzNjJkMTNmM2E2MDZiM2YyNDg4MTYxNCIsInN1YiI6IjY1YWFlMTIzMzU3YzAwMDBjYmQ2ZGE5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FgwahiZJIqrtSuCeFmWnKs49eVpZzRFNAfhoStjjb40")
            .build()
    }
}