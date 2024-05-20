package com.example.fatpetserver.breed.controller

import com.example.fatpetserver.breed.dto.CreateBreedCommand
import com.example.fatpetserver.breed.dto.UpdateBreedCommand
import com.example.fatpetserver.breed.entity.Breed
import com.example.fatpetserver.breed.service.BreedCommandService
import com.example.fatpetserver.breed.service.BreedQueryService
import com.example.fatpetserver.common.dto.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/breeds")
class BreedController(
    private val breedCommandService: BreedCommandService,
    private val breedQueryService: BreedQueryService,
) : BreedApi {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    override fun getAll(): ApiResponse<List<Breed>> {
        return ApiResponse.success(breedQueryService.getAll())
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    override fun create(@Valid @RequestBody command: CreateBreedCommand) {
        breedCommandService.create(command)
    }

    @PutMapping("/{breedId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun update(
        @PathVariable breedId: Long,
        @Valid @RequestBody command: UpdateBreedCommand,
    ) {
        breedCommandService.update(breedId, command)
    }

    @DeleteMapping("/{breedId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable breedId: Long) {
        breedCommandService.delete(breedId)
    }
}
