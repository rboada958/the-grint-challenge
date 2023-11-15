package com.example.redditapp.domain.usecase

import com.example.redditapp.data.repository.TopRepository
import javax.inject.Inject

class TopUseCase @Inject constructor(private val repository: TopRepository) {

    suspend operator fun invoke(page: String) =
        repository.top(page)
}