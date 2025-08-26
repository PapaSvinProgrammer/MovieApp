package com.mordva.movieScreen.domain

import com.mordva.movieScreen.domain.model.PersonMovieExtended
import com.mordva.movieScreen.domain.model.UnionParams
import com.mordva.util.UseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class UnionPersonsAndPersonMovie @Inject constructor(
) : UseCase<UnionParams, List<PersonMovieExtended>>(Dispatchers.IO) {
    override suspend fun run(params: UnionParams): List<PersonMovieExtended> {
        val personsMap = params.personsList.associateBy { it.id }

        return params.personsScreenObjectList.mapNotNull { screenObject ->
            personsMap[screenObject.id]?.let { person ->
                PersonMovieExtended(
                    id = person.id,
                    name = person.name,
                    enName = person.enName,
                    photo = person.photo,
                    birthday = person.birthday,
                    death = person.death,
                    age = person.age,
                    description = screenObject.description,
                    enProfession = screenObject.enProfession,
                    profession = screenObject.profession
                )
            }
        }
    }
}
